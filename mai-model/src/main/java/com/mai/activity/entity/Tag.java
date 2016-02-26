package com.mai.activity.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/9/7.
 */
public class Tag implements Serializable {

    private static final long serialVersionUID = -1673606735093988700L;
    private String tagID;
    private String tagName;
    private Integer orderNum;
    private Integer tagCount = 0;

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getTagID() {
        return tagID;
    }

    public void setTagID(String tagID) {
        this.tagID = tagID;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getTagCount() {
        return tagCount;
    }

    public void setTagCount(Integer tagCount) {
        this.tagCount = tagCount;
    }
}
