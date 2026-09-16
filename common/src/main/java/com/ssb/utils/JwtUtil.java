package com.ssb.utils;

import com.ssb.constants.Constants;
import com.ssb.entity.vo.UserVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;

public class JwtUtil {
    /**
     * 生成密钥
     * @return {@code String}密钥
     */
    public static String generateJwtKey(){
        SecretKey key = Jwts.SIG.HS256.key().build();
        return Encoders.BASE64.encode(key.getEncoded());
    }

    /**
     * 密钥还原Key对象
     * @param strBase64Key 字符串密钥
     * @return {@code Key} 密钥
     */
    public static Key Base64StringToJwtKey(String strBase64Key){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(strBase64Key));
    }

    /**
     * 生成JWT令牌
     * @param subject 主题
     * @return JWT令牌
     */
    public static String generateJWT(String subject){
        Key key = Base64StringToJwtKey(Constants.BASE64_STRING_KEY_JWT);
        Date issuedDate = new Date(System.currentTimeMillis());
        Date ExpirationDate = new Date(System.currentTimeMillis() + Constants.JWT_EXPIRATION_TIME);
        String id = UUID.randomUUID().toString().replace("-","");
        return Jwts.builder()
                .signWith(key)
                .subject(subject)
                .issuedAt(issuedDate)
                .expiration(ExpirationDate)
                .issuer(Constants.JWT_ISSUER)
                .id(id)
                .compact();
    }

    /**
     * 生成JWT令牌
     * @param claims 自定义载荷数据（key为声明名称，value为对应的值）
     * @return JWT令牌
     */
    public static <E> String generateJWT(Map<String, E> claims){
        Key key = Base64StringToJwtKey(Constants.BASE64_STRING_KEY_JWT);
        Date issuedDate = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(System.currentTimeMillis() + Constants.JWT_EXPIRATION_TIME);
        String id = UUID.randomUUID().toString().replace("-","");
        return Jwts.builder()
                .signWith(key)
                .issuedAt(issuedDate)
                .claims(claims)
                .expiration(expirationDate)
                .issuer(Constants.JWT_ISSUER)
                .id(id)
                .compact();
    }

    /**
     * 获取所有载荷信息
     * @param token JWT令牌
     * @return 载荷信息
     */
    public static Claims getDecodedPayload(String token){
        Key key = Base64StringToJwtKey(Constants.BASE64_STRING_KEY_JWT);
        return Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * 获取载荷中的某个信息
     * @param token JWT令牌
     * @param claims 载荷
     * @return 看谁用,就取那个值
     * @param <T> 谁的
     */
    public static <T> T getPayloadInfo(String token,Function<Claims, T> claims){
        Claims payload = getDecodedPayload(token);
        return claims.apply(payload);
    }

    /**
     * 获取载荷中的主题
     * @param token JWT令牌
     * @return 主题信息
     */
    public static String getSubject(String token){
        return getPayloadInfo(token,Claims::getSubject);
    }

    /**
     * 获取载荷中的过期时间
     * @param token JWT令牌
     * @return 过期时间
     */
    public static Date getExpiration(String token){
        return getPayloadInfo(token,Claims::getExpiration);
    }

    /**
     * 检查JWT令牌是否过期
     * @param token JWT令牌
     * @return {@code true} 表示已过期，{@code false} 表示未过期
     */
    public static boolean isExpired(String token){
        return getExpiration(token).before(new Date());
    }

    public static void main(String[] args) {
        // 密钥生成
//        System.out.println(generateJwtKey());
//        System.out.println(Base64StringToJwtKey(Constants.BASE64_STRING_KEY_JWT));

//        String subject = "111";
//        String token = generateJWT(subject);
//        UserVO user = UserVO.builder()
//                .id(1)
//                .userName("小三")
//                .build();
//        String role = "admin";
//        Map<String,Object> claims = new HashMap<>();
//        claims.put("info",user);
//        claims.put("role",role);
//
//        String token = generateJWT(claims);
//
//        System.out.println(token);
//
//        System.out.println(getDecodedPayload(token));
//
//        System.out.println(getSubject(token));
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(sdf.format(getExpiration(token)));
//
//        System.out.println(isExpired(token));
    }
}
