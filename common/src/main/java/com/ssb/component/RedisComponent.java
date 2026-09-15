package com.ssb.component;

import com.ssb.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedisComponent {

    @Autowired
    private RedisUtil<Object> redisUtil;


}
