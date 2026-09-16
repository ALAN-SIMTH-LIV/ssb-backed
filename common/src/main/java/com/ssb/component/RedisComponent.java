package com.ssb.component;

import com.ssb.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Redis
 */
@Component
public class RedisComponent {

    @Autowired
    private RedisUtil<Object> redisUtil;


    /**
     * 保存用户登录时的验证码信息
     * @param key 路径
     * @param value 验证码
     * @param unit 过期时间
     */
    public void saveEmailCode(String key, String value,long time, TimeUnit unit){
        redisUtil.set(key,value,time,unit);
    }

    /**
     * 检查邮箱验证码是否存在
     * @param key 目标键
     * @return 存在 true 不存在 false
     */
    public boolean checkEmailCode(String key){
        return redisUtil.hasKay(key);
    }

    /**
     * 获取邮箱验证码
     * @param key 邮箱
     * @return 邮箱验证码
     */
    public String getEmailCode(String key){
        return redisUtil.getString(key);
    }

    /**
     * 删除存储的验证码
     * @param key 邮箱
     * @return 成功 true 失败 false
     */
    public boolean cleanEmailCode(String key){
        return redisUtil.delete(key);
    }
}
