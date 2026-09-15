package com.ssb.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


/**
 * 响应代码枚举 <br/>
 * code : 响应代码 <br/>
 * msg : 响应描述 <br/>
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum {
    CODE_200(200,"请求成功"),
    CODE_404(404,"资源不存在"),
    CODE_500(500,"服务器错误，请联系管理员"),
    CODE_600(600,"请求参数错误"),
    CODE_601(601,"信息已存在");

    private final Integer code; // 代码
    private final String msg; // 解释


}
