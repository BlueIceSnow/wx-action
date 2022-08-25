package com.wxaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Program: wx-action
 * @Author: ytq
 * @Date: 2022/08/23 15:12:36
 */
@SpringBootApplication
@EnableScheduling
public class WxActionApplication {
    public static void main(String[] args) {
        SpringApplication.run(WxActionApplication.class);
    }
}
