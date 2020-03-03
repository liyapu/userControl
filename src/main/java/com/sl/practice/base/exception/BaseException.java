package com.sl.practice.base.exception;

/**
 * 基础异常
 *
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 833762720580575740L;

    private Integer code;
    private String message;

    public BaseException(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
