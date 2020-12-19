package com.studyhub.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author haoren
 * @create 2020-12-18 20:51
 */

@EnableEurekaClient
@SpringBootApplication
public class CrowdRedisProvider {

    public static void main(String[] args) {
        SpringApplication.run(CrowdRedisProvider.class, args);
    }
}
