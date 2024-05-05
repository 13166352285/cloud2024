package com.atguigu.cloud;

import java.time.ZonedDateTime;

/**
 * @Description
 * @Author chentian
 * @Date 2024/4/25
 */
public class Main {
    public static void main(String[] args) {
        ZonedDateTime zbj = ZonedDateTime.now(); // 默认时区
        System.out.println(zbj);
    }
}