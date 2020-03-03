package com.sl.practice.enums;


import com.sl.practice.base.exception.BusinessException;

/**
 * @author: liyapu
 * @description:  通用 0,1状态都可用此
 * @date 2020-02-27 19:30
 */
public enum StatusEnum {
    NO(0, "否,无效,不是新的,非正本,下拉,未确认"),
    YES(1, "是,有效，新的，正本，新增的，已经确认");
    private int code;
    private String desc;

    StatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static void checkCode(Integer code){
        if(null == code){
            throw BusinessException.PARAM_ERR;
        }
        StatusEnum[] values = StatusEnum.values();
        StatusEnum se = null;
        for(StatusEnum statusEnum : values){
            if(statusEnum.getCode() == code.intValue()){
                se = statusEnum;
                return;
            }
        }
        throw BusinessException.PARAM_ERR;
    }
}
