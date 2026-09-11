package com.ssb.entity.po;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户表
 */
@Getter
@Setter
@Builder
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer id; // 用户ID
    private String userName; // 账号
    private String password; // 密码
}
