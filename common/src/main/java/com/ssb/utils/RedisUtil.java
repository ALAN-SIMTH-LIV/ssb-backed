package com.ssb.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 * @param <V> 值的类型
 */
@Component("redisUtils")
public class RedisUtil<V> {

    @Autowired
    private RedisTemplate<String,V> redisTemplate;

    /**
     * 设置键值对
     * @param key 键
     * @param value 值
     */
    public void set(String key,V value){
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置键值对并指定过期时间
     * @param key 键
     * @param value 值
     * @param time 时间
     * @param unit 时间类型(秒、分、......),TimeUnit
     */
    public void set(String key, V value, long time, TimeUnit unit){
        redisTemplate.opsForValue().set(key, value,time,unit);
    }

    /**
     * 获取值
     * @param key 键
     * @return Object
     */
    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取值，字符串
     * @param key 键
     * @return String
     */
    public String getString(String key){
        Object value = redisTemplate.opsForValue().get(key);
        return null == value ? null : value.toString();
    }

    /**
     * 删除信息
     * @param key 键
     * @return boolean
     */
    public Boolean delete(String key){
        return redisTemplate.delete(key);
    }

    /**
     * 判断键是否存在
     * @param key 键
     * @return boolean
     */
    public Boolean hasKay(String key){
        return redisTemplate.hasKey(key);
    }
}
