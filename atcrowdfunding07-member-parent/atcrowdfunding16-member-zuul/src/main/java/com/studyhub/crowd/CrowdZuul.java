package com.studyhub.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author haoren
 * @create 2020-12-18 23:19
 */

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class CrowdZuul {
    public static void main(String[] args) {

        SpringApplication.run(CrowdZuul.class, args);

    }
}
