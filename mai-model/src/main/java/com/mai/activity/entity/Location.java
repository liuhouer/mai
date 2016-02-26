package com.mai.activity.entity;

import java.io.Serializable;

/**
 * 所在地
 * Created by Administrator on 2015/9/7.
 */
public class Location implements Serializable {

    private static final long serialVersionUID = -7711258846131081998L;
    private String locationID;
    private String locationName;
    private String parentLocationID;
    private Integer orderNum;

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getParentLocationID() {
        return parentLocationID;
    }

    public void setParentLocationID(String parentLocationID) {
        this.parentLocationID = parentLocationID;
    }
}
