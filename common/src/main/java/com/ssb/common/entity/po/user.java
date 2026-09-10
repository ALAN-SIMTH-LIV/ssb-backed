package com.ssb.common.entity.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class user {
    private Integer Id; // 用户ID
    private String userName; // 账号
    private String password; // 密码
}
