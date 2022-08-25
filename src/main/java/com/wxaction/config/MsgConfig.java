package com.wxaction.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Program: wx-action
 * @Author: ytq
 * @Date: 2022/08/25 13:55:36
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "msg")
public class MsgConfig {
    private List<String> scheduleSendOpenId;
    private String suggest;

}
