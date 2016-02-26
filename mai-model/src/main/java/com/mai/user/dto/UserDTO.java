package com.mai.user.dto;

import java.io.Serializable;

/**
 * 新增用户、更新用户信息使用
 * Created by fengdzh on 2015/9/11.
 */
public class UserDTO implements Serializable {

    //手机端类型：ios
    public static final String PHONECLIENTTYPE_IOS = "ios";
    //手机端类型：android
    public static final String PHONECLIENTTYPE_ANDROID = "android";
    private static final long serialVersionUID = 4788543346146881493L;
    //手机端类型
    String phoneClientType;
    //设备token
    private String deviceToken;
    //手机号
    private String phoneNum;
    //手机验证码
    private String radomNum;

    public String getPhoneClientType() {
        return phoneClientType;
    }

    public void setPhoneClientType(String phoneClientType) {
        this.phoneClientType = phoneClientType;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getRadomNum() {
        return radomNum;
    }

    public void setRadomNum(String radomNum) {
        this.radomNum = radomNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

}
