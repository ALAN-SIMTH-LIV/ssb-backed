package com.ssb.web.controller;

import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.ssb.component.EmailComponent;
import com.ssb.component.RedisComponent;
import com.ssb.constants.Constants;
import com.ssb.entity.vo.SlideCaptchaVO;
import com.ssb.entity.vo.ResponseVO;
import com.ssb.exception.BusinessException;
import com.ssb.utils.RandomUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;


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

    @Autowired
    private RedisComponent redisComponent;

    /**
     * 获取验证码接口
     *
     * @param slideCaptchaVO 验证码参数
     *                  "captchaType": "blockPuzzle",
     *                  "clientUid": "唯一标识"
     */
    @PostMapping("/get")
    public ResponseVO<ResponseModel> get(@RequestBody SlideCaptchaVO slideCaptchaVO) {
        ResponseModel responseModel = captchaService.get(slideCaptchaVO);
        return ResponseSuccess(responseModel);
    }

    /**
     * 校验滑动验证
     *
     * @param slideCaptchaVO 验证码参数
     *                  "captchaType": "blockPuzzle",
     *                  "pointJson": "QxIVdlJoWUi04iM+65hTow==",  //aes加密坐标信息
     *                  "token": "71dd26999e314f9abb0c635336976635"  //get请求返回的token
     */
    @PostMapping("/check")
    public ResponseVO<ResponseModel> check(@RequestBody SlideCaptchaVO slideCaptchaVO) {
        ResponseModel responseModel = captchaService.check(slideCaptchaVO);
        return  ResponseSuccess(responseModel);
    }

    /**
     * 发送验证码接口，需要二次验证
     */
    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    public ResponseVO<Void> sendRegisterCode(
            @RequestBody SlideCaptchaVO slideCaptchaVO
    ) {
        // 新建CaptchaVO对象
        CaptchaVO captchaVO = new CaptchaVO();
        // 获取验证码Token
        captchaVO.setCaptchaVerification(slideCaptchaVO.getCaptchaVerification());

        // 判断是否已经通过滑块验证
        ResponseModel response = captchaService.verification(captchaVO);
//        System.out.println(response.isSuccess() ? "二次验证通过" : "二次验证未通过");
        if (!response.isSuccess()){
            throw new BusinessException("滑块验证码未通过");
        }

        // 4位验证码
        String code = RandomUtil.generateCode(4);

        // 查看Email是否存在Redis
        String key = Constants.REDIS_LOGIN + slideCaptchaVO.getEmail();
       if (redisComponent.checkEmailCode(key)){
           throw new BusinessException("请求过于频繁，请1分钟后再试");
       }

       // 发送邮件 异步
        emailComponent.sendEmail(slideCaptchaVO.getEmail(),code);

       // 保存Redis
        redisComponent.saveEmailCode(key ,code, 2, TimeUnit.MINUTES);

        return ResponseSuccess();
    }

}