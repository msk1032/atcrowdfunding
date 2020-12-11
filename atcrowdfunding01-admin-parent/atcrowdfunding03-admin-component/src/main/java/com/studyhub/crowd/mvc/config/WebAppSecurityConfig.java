package com.studyhub.crowd.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author haoren
 * @create 2020-12-11 17:00
 * WebSecurityConfigurerAdapter
 */

@Configuration
//表明当前类是一个配置类
@EnableWebSecurity
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

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
        builder.inMemoryAuthentication().withUser("tom").password("123456").roles("ADMIN");
    }
}
