package com.wxaction.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Program: wx-action
 * @Author: ytq
 * @Date: 2022/08/23 19:09:17
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "weather")
public class WeatherConfig {
    private String appid;
    private String secret;
    private String city;
}
