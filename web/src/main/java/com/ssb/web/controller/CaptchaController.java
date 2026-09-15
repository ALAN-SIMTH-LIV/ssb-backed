package com.ssb.web.controller;

import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.ssb.component.EmailComponent;
import com.ssb.entity.dto.CaptchaDTO;
import com.ssb.entity.vo.ResponseVO;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 验证码操作处理
 */
@RestController
@RequestMapping(value = "/api/captcha")
public class CaptchaController extends ABaseController{

    @Resource
    private CaptchaService captchaService;

    @Autowired
    private EmailComponent emailComponent;

    /**
     * 获取验证码接口
     *
     * @param captchaDTO 验证码参数
     *                  "captchaType": "blockPuzzle",
     *                  "clientUid": "唯一标识"
     */
    @PostMapping("/get")
    public ResponseVO<ResponseModel> get(@RequestBody CaptchaDTO captchaDTO) {
        ResponseModel responseModel = captchaService.get(captchaDTO);
        return ResponseSuccess(responseModel);
    }

    /**
     * 校验滑动验证
     *
     * @param captchaDTO 验证码参数
     *                  "captchaType": "blockPuzzle",
     *                  "pointJson": "QxIVdlJoWUi04iM+65hTow==",  //aes加密坐标信息
     *                  "token": "71dd26999e314f9abb0c635336976635"  //get请求返回的token
     */
    @PostMapping("/check")
    public ResponseVO<ResponseModel> check(@RequestBody CaptchaDTO captchaDTO) {
        ResponseModel responseModel = captchaService.check(captchaDTO);
        return  ResponseSuccess(responseModel);
    }

    /**
     * 发送验证码接口，需要二次验证
     *
     * @param captchaDto
     * @return
     */
    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    public ResponseVO<ResponseModel> sendRegisterCode(
            @RequestBody CaptchaDTO captchaDto
    ) {
        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCaptchaVerification(captchaDto.getCaptchaVerification());
        // 判断是否已经通过滑块验证
        ResponseModel response = captchaService.verification(captchaVO);
        System.out.println(response.isSuccess() ? "二次验证通过" : "二次验证未通过");
        emailComponent.sendEmail(captchaDto.getEmail());
        return ResponseSuccess(response);
    }

}