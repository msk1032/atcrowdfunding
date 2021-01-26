package com.studyhub.crowd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author haoren
 * @create 2021-01-17 17:08
 */

@Configuration
public class CrowdMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/auth/member/to/reg/page.html").setViewName("member-reg");

        registry.addViewController("/auth/member/to/login/page").setViewName("member-login");

        registry.addViewController("/auth/member/to/center/page").setViewName("member-center");

        registry.addViewController("/member/my/crowd").setViewName("member-crowd");
    }
}
