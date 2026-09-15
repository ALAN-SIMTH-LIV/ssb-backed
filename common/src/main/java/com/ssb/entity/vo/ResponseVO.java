package com.ssb.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 响应类VO <br/>
 * @param <T> JAVA类
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVO<T> {
    private Integer code;
    private String msg;
    private T data;
    private String status;
}
