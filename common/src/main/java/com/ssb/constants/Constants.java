package com.ssb.constants;

public class Constants {
    //JWT密钥
    public static final String BASE64_STRING_KEY_JWT = "6s+e9Z0XoVK/kPCGQKEn/3M1IrhlZfNS5BBxWdNQVcc=";
    //JWT过期时间
    public static final long JWT_EXPIRATION_TIME = 3600000; // 1小时
    //JWT颁发者
    public static final String JWT_ISSUER = "ssb";
    //响应状态成功
    public static final String RESPONSE_SUCCESS = "success";
    //响应状态失败
    public static final String RESPONSE_ERROR = "error";
    // Redis用
    // 项目名称
    public static final String REDIS_SSB = "ssb:";
    // 登录
    public static final String REDIS_LOGIN = REDIS_SSB + "login:";
}
