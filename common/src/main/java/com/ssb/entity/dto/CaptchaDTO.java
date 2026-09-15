package com.ssb.entity.dto;


import com.anji.captcha.model.vo.CaptchaVO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 继承CaptchaVO,添加项目需要字段
 */
@Getter
@Setter
public class CaptchaDTO extends CaptchaVO implements Serializable {
    // 邮箱
    private String email;
}
