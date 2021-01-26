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

        // view-controller 是在 project-consumer 内部定义的，所以这里是一个不经过 Zuul
        // 访问的地址，所以这个路径前面不加路由规则中定义的前缀：“/project”
        registry.addViewController("/agree/protocol/page").setViewName("project-agree");
        registry.addViewController("/launch/project/page").setViewName("project-launch");


    }
}
