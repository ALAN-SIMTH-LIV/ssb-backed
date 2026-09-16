package com.ssb.entity.vo;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
public class UserVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer id; // 用户ID
    private String email; // 邮箱
    private String nickName; // 昵称
    private String code; // 用于一些验证码，不涉及数据库
}
