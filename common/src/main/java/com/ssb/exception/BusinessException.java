package com.ssb.exception;

import com.ssb.entity.enums.ResponseEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 用于处理业务异常
 */
@Getter
public class BusinessException extends RuntimeException {

    private ResponseEnum responseEnum;

    private Integer code;

    private String msg;

    public BusinessException(String message) {
        super(message);
        this.msg = message;
    }

    public BusinessException(String message, Throwable e) {
        super(message, e);
        this.msg = message;
    }

    public BusinessException(Throwable e) {
        super(e);
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }

    public BusinessException(ResponseEnum codeEnum) {
        super(codeEnum.getText());
        this.responseEnum = codeEnum;
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getText();
    }

    @Override
    public String getMessage(){
        return msg;
    }

    /**
     * 重写fillInStackTrace 业务异常不需要堆栈信息，提高效率.
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
