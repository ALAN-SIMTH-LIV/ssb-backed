package com.ssb.config;


import com.ssb.filter.JWTVerifyFilter;
import com.ssb.service.UserService;
import com.ssb.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * security配置类
 */
@Configuration
@EnableWebSecurity
@Slf4j
@EnableMethodSecurity
public class SecurityConfig {

    // 过滤链
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) //跨域
                .formLogin(AbstractHttpConfigurer::disable) // 禁用表单
                .httpBasic(AbstractHttpConfigurer::disable) // 禁用Basic认证
                .csrf(AbstractHttpConfigurer::disable) // 禁用CSRF
                .authorizeHttpRequests(
                        req -> req
                                .requestMatchers(
                                        "/api/captcha/**",
                                        "/api/auth/**"
                                )
                                .permitAll()
                                .requestMatchers(HttpMethod.OPTIONS,"/**")
                                .permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(new JWTVerifyFilter(),  UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    // 配置跨域
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        // CORS 配置对象
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 设置允许的源
        corsConfiguration.setAllowedOrigins(
                List.of(
                        "http://localhost:5173",
                        "http://localhost:5174"
                )
        );
        // 允许的方法
        corsConfiguration.setAllowedMethods(
                List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "PATCH",
                        "OPTIONS"

                )
        );
        // 允许的请求头
        corsConfiguration.setAllowedHeaders(List.of("*"));
        // 允许携带凭证
        corsConfiguration.setAllowCredentials(true);
        // 预检请求的缓存时间(秒)
        corsConfiguration.setMaxAge(3600L);
        // 创建基于 URL 的配置源，并将配置注册到所有路径("/**")
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);

        return source;
    }

    // 密码加密用的
    @Bean
    public static PasswordEncoder passwordEncoder(){
        // 用 bcrypt 加密
        String idForEncode = "bcrypt";
        Map<String,PasswordEncoder> encoders = new HashMap<>();
        // 注册 bcrypt 编码器
        encoders.put("bcrypt",new BCryptPasswordEncoder(12));
        return new DelegatingPasswordEncoder(idForEncode,encoders);
    }
}
