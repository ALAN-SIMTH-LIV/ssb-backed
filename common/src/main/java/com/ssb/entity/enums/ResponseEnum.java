package com.ssb.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


/**
 * 响应代码枚举 <br/>
 * code : 响应代码 <br/>
 * text : 响应描述 <br/>
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum {
    CODE_200(200,"请求成功"),
    CODE_404(404,"资源不存在");

    private final Integer code; // 代码
    private final String text; // 解释


}
