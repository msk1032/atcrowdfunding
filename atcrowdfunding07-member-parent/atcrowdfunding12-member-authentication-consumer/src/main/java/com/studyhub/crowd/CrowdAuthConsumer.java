package com.studyhub.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author haoren
 * @create 2020-12-18 22:49
 */

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class  CrowdAuthConsumer {
    public static void main(String[] args) {
        SpringApplication.run(CrowdAuthConsumer.class, args);
    }
}
