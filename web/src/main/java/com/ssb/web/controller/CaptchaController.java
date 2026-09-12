package com.ssb.web.controller;

import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


/**
 * 验证码操作处理
 */
@RestController
public class CaptchaController {

    @Resource
    private CaptchaService captchaService;

    /**
     * 获取验证码接口
     *
     * @param captchaVO 验证码参数
     *                  "captchaType": "blockPuzzle",
     *                  "clientUid": "唯一标识"
     */
    @PostMapping("/captcha/get")
    public ResponseModel get(@RequestBody CaptchaVO captchaVO) {
        return captchaService.get(captchaVO);
    }

    /**
     * 校验滑动验证
     *
     * @param captchaVO 验证码参数
     *                  "captchaType": "blockPuzzle",
     *                  "pointJson": "QxIVdlJoWUi04iM+65hTow==",  //aes加密坐标信息
     *                  "token": "71dd26999e314f9abb0c635336976635"  //get请求返回的token
     */
    @PostMapping("/captcha/check")
    public ResponseModel check(@RequestBody CaptchaVO captchaVO) {
        return captchaService.check(captchaVO);
    }

    /**
     * 发送验证码接口，需要二次验证
     *
     * @param captchaVO
     * @return
     */
    @RequestMapping(value = "/sendLoginCode", method = RequestMethod.POST)
    public ResponseModel sendRegisterCode(@RequestBody CaptchaVO captchaVO) {
        // 判断是否已经通过滑块验证
        ResponseModel response = captchaService.verification(captchaVO);
        System.out.println(response.isSuccess() ? "二次验证通过" : "二次验证未通过");

        // todo 发送验证码

        return response;
    }

}