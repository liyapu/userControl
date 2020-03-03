package com.sl.practice.base.enums;

/**
 * 类描述: 主要时用于返回错误码和错误信息
 * @author licanfeng
 * @description 返回码和msg
 */
public enum ResultStatusEnum {
    SUCCESS(200, "成功"),
    SUCCESS_QUERY(200, "查询成功"),
    SUCCESS_SAVE(200, "保存成功"),
    SUCCESS_UPDATE(200, "更新成功"),
    SUCCESS_DELETE(200, "删除成功"),


    HTTP_ERROR_100(100, "1XX错误"),
    SIGN_ERROR(120, "签名错误"),
    TIME_OUT(130, "访问超时"),


    HTTP_ERROR_300(300, "3XX错误"),
    KICK_OUT(300, "您已经在其他地方登录，请重新登录！"),


    HTTP_ERROR_400(400, "4XX错误"),
    BAD_REQUEST(400,"错误的请求"),
    INVALID_TOKEN(401, "无效的授权码"),
    INVALID_CLIENT_ID(402, "无效的密钥"),
    REQUEST_NOT_FOUND(404, "访问地址不存在！"),
    METHOD_NOT_ALLOWED(405, "不支持当前请求方法"),
    REPEAT_REQUEST_NOT_ALLOWED(406, "请求重复提交"),
    REQUEST_TIMEOUT(408, "请求超时"),
    REQUEST_PARAM_ERR(412, "请求参数错误!"),
    REQUEST_MEDIA_TYPE_ERR(415, "媒体类型不匹配!"),
    REQUEST_METHOD_PARAM_TYPE_ERR(421, "方法参数类型不匹配!"),


    HTTP_ERROR_500(500, "5XX错误"),
    SYSTEM_ERR(500, "系统正忙,请稍后重试!"),


    BUSINESS_ERR(600, "业务执行异常!"),
//
//    NOT_EXIST_USER_OR_ERROR_PWD(10000, "该用户不存在或密码错误"),
//    NOT_PARAM_USER_OR_ERROR_PWD(10006, "用户名或密码为空"),
//    LOGINED_IN(10001, "该用户已登录"),
//    NOT_EXIST_BUSINESS(10002, "该商家不存在"),
//    SHIRO_ERROR(10003, "登录异常"),
//    UNAUTHO_ERROR(10004, "您没有该权限"),
//    REDIS_ERROR(10006, "redis异常"),
//    REDIS_CONNECT_ERROR(10007, "redis连接异常"),
//    USER_FROZEN(40000, "该用户已被冻结");
    ;

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ResultStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

