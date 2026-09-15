package com.ssb.web.controller;

import com.ssb.constants.Constants;
import com.ssb.entity.enums.ResponseEnum;
import com.ssb.entity.vo.ResponseVO;

/**
 * 控制器父类
 */
public class ABaseController {

    public <T> ResponseVO <T> ResponseSuccess(T data){
       ResponseVO<T> responseVO = new ResponseVO<>();
       responseVO.setCode(ResponseEnum.CODE_200.getCode());
       responseVO.setData(data);
       responseVO.setMsg(ResponseEnum.CODE_200.getMsg());
       responseVO.setStatus(Constants.RESPONSE_SUCCESS);
       return responseVO;
    }

    public <T> ResponseVO <T> ResponseSuccess(){
        ResponseVO<T> responseVO = new ResponseVO<>();
        responseVO.setCode(ResponseEnum.CODE_200.getCode());
        responseVO.setData(null);
        responseVO.setMsg(ResponseEnum.CODE_200.getMsg());
        responseVO.setStatus(Constants.RESPONSE_SUCCESS);
        return responseVO;
    }

    public <T> ResponseVO  <T> ResponseError(Integer code,String message,T data){
        ResponseVO<T> responseVO = new ResponseVO<>();
        responseVO.setCode(code);
        responseVO.setData(data);
        responseVO.setMsg(message);
        responseVO.setStatus(Constants.RESPONSE_ERROR);
        return responseVO;
    }

    public <T> ResponseVO  <T> ResponseError(Integer code,String message){
        ResponseVO<T> responseVO = new ResponseVO<>();
        responseVO.setCode(code);
        responseVO.setData(null);
        responseVO.setMsg(message);
        responseVO.setStatus(Constants.RESPONSE_ERROR);
        return responseVO;
    }


 }
