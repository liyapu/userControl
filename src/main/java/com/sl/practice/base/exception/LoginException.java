package com.sl.practice.base.exception;


import com.sl.practice.base.enums.ResultStatusEnum;

/***
 *  登录相关异常处理
 */
public class LoginException extends BaseException {

    private static final long serialVersionUID = 8560592818351895348L;

    public LoginException(String message){
        this(ResultStatusEnum.REQUEST_PARAM_ERR.getCode(),message);
    }

    public LoginException(int code,String message){
        super(code,message);
    }
}
