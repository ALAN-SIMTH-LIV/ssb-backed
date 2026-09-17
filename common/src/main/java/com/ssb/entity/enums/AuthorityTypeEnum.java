package com.ssb.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 权限类型枚举 0: 菜单 1: 接口
 */
@Getter
@AllArgsConstructor
public enum AuthorityTypeEnum {
    MENU(0, "菜单"),
    API(1, "接口");

    private final Integer code; // 值
    private final String type; // 描述
}
