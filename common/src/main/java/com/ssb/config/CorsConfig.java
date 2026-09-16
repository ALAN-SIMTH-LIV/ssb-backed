package com.ssb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 匹配所有接口
                .allowedOriginPatterns("*") // 允许所有来源（Spring Boot 2.4+ 推荐用 allowedOriginPatterns）
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true) // 允许携带 Cookie
                .maxAge(3600); // 预检请求缓存时间，单位秒
    }
}