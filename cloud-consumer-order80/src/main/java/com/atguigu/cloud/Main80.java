package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author chentian
 * @Date 2024/4/25
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Main80 {

    public static void main(String[] args) {

        SpringApplication.run(Main80.class, args);
    }
}