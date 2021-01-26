package com.studyhub.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author haoren
 * @create 2021-01-19 12:04
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class CrowdProject {
    public static void main(String[] args) {
        SpringApplication.run(CrowdProject.class, args);
    }
}
