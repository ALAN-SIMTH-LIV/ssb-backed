package com.ssb.web.controller;

import com.ssb.component.RedisComponent;
import com.ssb.constants.Constants;
import com.ssb.entity.po.User;
import com.ssb.entity.vo.ResponseVO;
import com.ssb.entity.vo.UserVO;
import com.ssb.exception.BusinessException;
import com.ssb.service.UserService;
import com.ssb.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController extends ABaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private RedisComponent redisComponent;

    @PostMapping("/login")
    public ResponseVO<String> login(@RequestBody UserVO userVO){
        // 用户注册
        User result = userService.loginOrRegister(
                User.builder()
                .email(userVO.getEmail())
                .build()
        );

        // 获取Redis中存储的验证码
        String redisEmailCode = redisComponent.getEmailCode(Constants.REDIS_LOGIN+userVO.getEmail());

        // 防止空指针
        if (null == redisEmailCode){
            throw new BusinessException("验证码已过期，请重新获取");
        }

        // 判断是否验证码正确
        if (!userVO.getCode().equals(redisEmailCode)) {
            throw new BusinessException("验证码错误");
        }

        // 令牌需要的一些信息
        Map<String,Object> userInfoMap = new HashMap<>();
        userInfoMap.put("authorities","111");
        // 创建令牌
        String token = JwtUtil.generateJWT(result.getId().toString(),userInfoMap);
        // 清楚Redis中存储的邮箱验证码
        redisComponent.cleanEmailCode(Constants.REDIS_LOGIN+userVO.getEmail());

        return ResponseSuccess(token);
    }
}
