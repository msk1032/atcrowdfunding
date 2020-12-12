package com.studyhub.crowd.mvc.config;

import com.studyhub.crowd.constant.CrowdConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author haoren
 * @create 2020-12-11 17:00
 * WebSecurityConfigurerAdapter
 */

@Configuration
//表明当前类是一个配置类
@EnableWebSecurity
// 启用全局方法权限控制功能，并且设置 prePostEnabled = true。保证@PreAuthority、@PostAuthority、@PreFilter、@PostFilter 生效
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity security) throws Exception {

        //与 SpringSecurity 环境下请求授权相关
        security
                .authorizeRequests()
                .antMatchers("/admin/to/login/page.html")
                .permitAll()    //无条件放行登陆页面
                .antMatchers("/static/**")
                .permitAll()//无条件放行静态资源
                .anyRequest()//其他请求
                .authenticated()//认证后访问
                .and()
                .exceptionHandling()
                .accessDeniedHandler(
                        new AccessDeniedHandler() {
                            @Override
                            public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
                                httpServletRequest.setAttribute("exception", new Exception(CrowdConstant.MESSAGE_ACCESS_DENIED));

                                httpServletRequest.getRequestDispatcher("/WEB-INF/system-error.jsp").forward(httpServletRequest, httpServletResponse);
                            }
                        }
                )

                .and()
                .csrf()
                .disable()//禁用csrf请求验证
                .formLogin()
                .loginPage("/admin/to/login/page.html")//默认登录页面
                .loginProcessingUrl("/security/do/login.html")//登录处理请求地址
                .defaultSuccessUrl("/admin/to/main/page.html")//设置登录成功页面
                .usernameParameter("loginAcct")
                .passwordParameter("userPwsd")
                .and()
                .logout()//开启退出功能
                .logoutUrl("/security/do/logout.html")
                .logoutSuccessUrl("/admin/to/login/page.html")


        ;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        // 与 SpringSecurity 环境下用户登录相关

        // 临时使用内存版登录的模式测试代码
        //builder.inMemoryAuthentication().withUser("tom").password("123456").roles("ADMIN");

        builder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}
