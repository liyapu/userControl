package com.sl.practice.base.utils;


import org.apache.commons.lang3.StringUtils;

public class CommonUtil {

    // 严格一些的校验
//    public static String phoneRegex ="^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[0-9]))\\d{8}$";
    //经常有新手机号出来，宽泛校验
    public static String phoneRegex = "^(1[3-9])\\d{9}$";

    public static String emailRegex = "^\\s*?(.+)@(.+?)\\s*$";

    /**
     * 验证手机号(1开头的11位数字)
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone){
        if(StringUtils.isBlank(phone) || phone.length() != 11){
            return false;
        }
        return phone.matches(phoneRegex);
    }

    public static boolean isNotPhone(String phone){
        return !isPhone(phone);
    }

    /**
     * 宽泛验证合法邮箱
     */
    public static boolean isEmail(String email){
        if(StringUtils.isBlank(email)){
            return false;
        }
        return email.matches(emailRegex);
    }

    public static boolean isNotEmail(String email){
        return !isEmail(email);
    }

    /**
     * 校验密码为 [6,12]
     * @param password
     * @return
     */
    public static boolean validPassword(String password){
        if(StringUtils.isBlank(password)){
            return false;
        }
        int len = password.length();
        if(len >= 6 && len <= 12){
            return true;
        }
        return false;
    }


}


