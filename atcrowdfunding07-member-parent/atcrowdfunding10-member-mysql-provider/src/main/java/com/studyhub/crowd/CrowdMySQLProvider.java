package com.studyhub.crowd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author haoren
 * @create 2020-12-17 22:54
 */

@EnableEurekaClient
@MapperScan("com.studyhub.crowd.mapper")
@SpringBootApplication
public class CrowdMySQLProvider {
    public static void main(String[] args) {
        SpringApplication.run(CrowdMySQLProvider.class, args);
    }
}
