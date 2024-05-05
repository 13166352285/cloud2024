package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author chentian
 * @Date 2024/4/26
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class MainFeignOrder80 {
    public static void main(String[] args) {
        SpringApplication.run(MainFeignOrder80.class,args);
    }
}