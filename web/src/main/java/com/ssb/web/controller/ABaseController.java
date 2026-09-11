package com.ssb.web.controller;

import com.ssb.entity.enums.ResponseEnum;
import com.ssb.entity.vo.ResponseVO;

public class ABaseController {
    public <T> ResponseVO <T> ResponseSuccess(T data){
       ResponseVO<T> responseVO = new ResponseVO<>();
       responseVO.setCode(ResponseEnum.CODE_200.getCode());
       responseVO.setData(data);
       responseVO.setMsg(ResponseEnum.CODE_200.getText());
       return responseVO;
    }

    public <T> ResponseVO <T> ResponseSuccess(){
        ResponseVO<T> responseVO = new ResponseVO<>();
        responseVO.setCode(ResponseEnum.CODE_200.getCode());
        responseVO.setMsg(ResponseEnum.CODE_200.getText());
        return responseVO;
    }
 }
