package com.sl.practice.base.exception;


import com.sl.practice.base.enums.ResultStatusEnum;

/**
 * Created by wxcsdb88 on 2017/5/23 23:58.
 *
 *  验证相关异常处理
 */
public class AuthorizationException extends BaseException{

    public AuthorizationException(String message){
        this(ResultStatusEnum.REQUEST_PARAM_ERR.getCode(),message);
    }

    public AuthorizationException(int code,String message){
        super(code,message);
    }
}
