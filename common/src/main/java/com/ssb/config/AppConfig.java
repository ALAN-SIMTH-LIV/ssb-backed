package com.ssb.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 获取配置
 */
@Configuration
@Getter
public class AppConfig {
    @Value("${spring.mail.username}")
    private String email; // 发送邮箱
}
