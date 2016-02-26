package com.mai.device.entity;

import java.io.Serializable;

/**
 * 设备信息
 * Created by fengdzh on 2015/9/17.
 */
public class Device implements Serializable {
    //设备：ios
    public static final String DEVICETYPE_IOS = "1";
    //设备：android
    public static final String DEVICETYPE_ANDROID = "0";

    private static final long serialVersionUID = 3259679677331472277L;
    private String deviceID;
    private String deviceSN;
    private String personID;
    private String deviceType;
    private Long createTime;
    private String clientID;

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDeviceSN() {
        return deviceSN;
    }

    public void setDeviceSN(String deviceSN) {
        this.deviceSN = deviceSN;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}
