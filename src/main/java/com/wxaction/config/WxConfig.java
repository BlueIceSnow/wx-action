package com.wxaction.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Program: wx-action
 * @Author: ytq
 * @Date: 2022/08/23 16:30:56
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "wx")
public class WxConfig {
    private String appid;
    private String secret;
}
