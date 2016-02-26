package com.mai.activity.entity;

import java.io.Serializable;

/**
 * 活动排序应用
 * Created by fengdzh on 2015/9/10.
 */
public class ActivityOrderType implements Serializable {
    private static final long serialVersionUID = -8227042560655808656L;
    private String activityOrderTypeID;
    private String activityOrderTypeName;
    private Integer activityOrderTypeOrder;
    private String imageURL;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getActivityOrderTypeID() {
        return activityOrderTypeID;
    }

    public void setActivityOrderTypeID(String activityOrderTypeID) {
        this.activityOrderTypeID = activityOrderTypeID;
    }

    public String getActivityOrderTypeName() {
        return activityOrderTypeName;
    }

    public void setActivityOrderTypeName(String activityOrderTypeName) {
        this.activityOrderTypeName = activityOrderTypeName;
    }

    public Integer getActivityOrderTypeOrder() {
        return activityOrderTypeOrder;
    }

    public void setActivityOrderTypeOrder(Integer activityOrderTypeOrder) {
        this.activityOrderTypeOrder = activityOrderTypeOrder;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
