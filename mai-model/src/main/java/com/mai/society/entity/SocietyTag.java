package com.mai.society.entity;

import java.io.Serializable;

/**
 * Created by fengdzh on 2015/10/5.
 */
public class SocietyTag implements Serializable {
    private static final long serialVersionUID = -3009325795712019944L;
    private String tagID;
    private String tagName;
    private Integer orderNum;
    private Integer scoietyTagCount = 0;

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

    public Integer getScoietyTagCount() {
        return scoietyTagCount;
    }

    public void setScoietyTagCount(Integer scoietyTagCount) {
        this.scoietyTagCount = scoietyTagCount;
    }
}
