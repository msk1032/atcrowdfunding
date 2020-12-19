package com.studyhub.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author haoren
 * @create 2020-12-16 11:14
 */
@EnableEurekaServer
@SpringBootApplication
public class CrowdEureka {
    public static void main(String[] args) {
        SpringApplication.run(CrowdEureka.class, args);
    }
}
