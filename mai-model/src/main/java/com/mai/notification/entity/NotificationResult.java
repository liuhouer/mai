package com.mai.notification.entity;

import java.io.Serializable;

/**
 * 通知实体
 * Created by fengdzh on 2015/9/14.
 */
public class NotificationResult implements Serializable {

    private static final long serialVersionUID = 2656607945011720007L;

    private Integer isDeal;

    private Integer dealResultCode;

    private String dealResult;

    private Integer isAlert;

    private String alertMessage;

    private Integer returnNum;

    public Integer getReturnNum() {
        return returnNum;
    }

    public void setReturnNum(Integer returnNum) {
        this.returnNum = returnNum;
    }

    public Integer getDealResultCode() {
        return dealResultCode;
    }

    public void setDealResultCode(Integer dealResultCode) {
        this.dealResultCode = dealResultCode;
    }

    public Integer getIsDeal() {
        return isDeal;
    }

    public void setIsDeal(Integer isDeal) {
        this.isDeal = isDeal;
    }

    public String getDealResult() {
        return dealResult;
    }

    public void setDealResult(String dealResult) {
        this.dealResult = dealResult;

    }

    public Integer getIsAlert() {
        return isAlert;
    }

    public void setIsAlert(Integer isAlert) {
        this.isAlert = isAlert;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }
}
