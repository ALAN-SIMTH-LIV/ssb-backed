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
    private String userName; // 账号
}
