package com.ssb.controller;

import com.ssb.constants.Constants;
import com.ssb.entity.enums.ResponseEnum;
import com.ssb.entity.vo.ResponseVO;
import com.ssb.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;


/**
 * 全局异常捕获
 */
@RestControllerAdvice
public class AGlobalExceptionHandlerController {
    private static final Logger logger = LoggerFactory.getLogger(AGlobalExceptionHandlerController.class);

    // 处理所有异常
    @ExceptionHandler(value = Exception.class)
    Object handleException(Exception e, HttpServletRequest request){
        logger.error("请求错误，请求地址{},错误信息:", request.getRequestURL(), e);
        ResponseVO<Object> ajaxResponse = new ResponseVO<>();
        // 404
        if (e instanceof NoHandlerFoundException){
            ajaxResponse.setCode(ResponseEnum.CODE_404.getCode());
            ajaxResponse.setMsg(ResponseEnum.CODE_404.getMsg());
            ajaxResponse.setStatus(Constants.RESPONSE_ERROR);
        } else if (e instanceof BusinessException) {
            // 业务错误
            BusinessException be = (BusinessException) e;
            ajaxResponse.setCode(be.getCode() == null ? ResponseEnum.CODE_600.getCode() : be.getCode());
            ajaxResponse.setMsg(be.getMsg());
            ajaxResponse.setStatus(Constants.RESPONSE_ERROR);
        }else if (e instanceof BindException || e instanceof MethodArgumentTypeMismatchException) {
            //参数类型错误
            ajaxResponse.setCode(ResponseEnum.CODE_600.getCode());
            ajaxResponse.setMsg(ResponseEnum.CODE_600.getMsg());
            ajaxResponse.setStatus(Constants.RESPONSE_ERROR);
        } else if (e instanceof DuplicateKeyException) {
            //主键冲突
            ajaxResponse.setCode(ResponseEnum.CODE_601.getCode());
            ajaxResponse.setMsg(ResponseEnum.CODE_601.getMsg());
            ajaxResponse.setStatus(Constants.RESPONSE_ERROR);
        } else if (e instanceof ConstraintViolationException) {
            //请求参数错误
            ajaxResponse.setCode(ResponseEnum.CODE_600.getCode());
            ajaxResponse.setMsg(ResponseEnum.CODE_600.getMsg());
            ajaxResponse.setStatus(Constants.RESPONSE_ERROR);
        } else {
            ajaxResponse.setCode(ResponseEnum.CODE_500.getCode());
            ajaxResponse.setMsg(ResponseEnum.CODE_500.getMsg());
            ajaxResponse.setStatus(Constants.RESPONSE_ERROR);
        }
        return ajaxResponse;
    }
}
