package com.sl.practice.base.exception;

import com.sl.practice.base.enums.ResultStatusEnum;

/**
 * 业务异常
 * Service 层异常
 *
 */
public class BusinessException extends BaseException{

    public BusinessException(int code,String message){
        super(code,message);
    }

    public static BusinessException build(int code, String message) {
        return new BusinessException(code, message);
    }

    public static BusinessException build(String message) {
        return new BusinessException(420, message);
    }

    public static BusinessException build(ResultStatusEnum resultStatusEnum) {
        return new BusinessException(resultStatusEnum.getCode(), resultStatusEnum.getMsg());
    }



    //============= 常用异常定义====================
    public static final BusinessException PARAM_ERR = build(ResultStatusEnum.REQUEST_PARAM_ERR);
    public static final BusinessException SERVER_ERR = build(ResultStatusEnum.SYSTEM_ERR);

    public static final BusinessException TOKEN_EMPTY_ERR = build(420,"请求token为空!");

    public static final BusinessException TOKEN__ERR = build(420,"请求token无效!");

    public static final BusinessException TOKEN_EXPIRE_ERR = build(420,"token失效，请重新登录！");

    public static final BusinessException TOKEN_NO_LOGIN_ERR = build(420,"用户token未登录！");


    public static BusinessException tokenCreateErr(Long userId) {
        return build(420,"token生成失败");
    }

    public static BusinessException addErr(){
        return build(530,"添加失败");
    }

    public static BusinessException editErr(){
        return build(531,"编辑失败");
    }
    //======================== 用户异常信息 2开头 ==========================

    public static BusinessException userPassErr() {
        return build(420,"账号或密码错误" );
    }

    public static BusinessException userNotExistErr() {
        return build(420,"账户名不存在，请重新输入!");
    }

    public static BusinessException usernameExistErr() {
        return build(420,"用户名已经存在，请修改并重新输入!");
    }

    public static BusinessException imgEmptyErr() {
        return build(420,"上传图片为空");
    }

    public static BusinessException imgTypeErr() {
        return build(420,"上传图片格式不支持");
    }

    public static BusinessException uploadErr() {
        return build(530,"上传失败");
    }
}
