package com.ssb.entity.vo;


import com.anji.captcha.model.vo.CaptchaVO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 继承CaptchaVO,调用第三方组件
 */
@Getter
@Setter
public class SlideCaptchaVO extends CaptchaVO implements Serializable {
    // 邮箱
    private String email;
}
