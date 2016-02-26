package com.mai.activity.entity;

import java.io.Serializable;

/**
 * Created by denghao on 15/10/24.
 */
public class SystemActivityFollow implements Serializable {
    private static final long serialVersionUID = 2055562709841988084L;

    private String systemActivityFollowID;
    private String activityID;
    private Integer activityFollowNum;
    private String personID;
    private Long createTime;

    public Integer getActivityFollowNum() {
        return activityFollowNum;
    }

    public void setActivityFollowNum(Integer activityFollowNum) {
        this.activityFollowNum = activityFollowNum;
    }

    public String getActivityID() {
        return activityID;
    }

    public void setActivityID(String activityID) {
        this.activityID = activityID;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getSystemActivityFollowID() {
        return systemActivityFollowID;
    }

    public void setSystemActivityFollowID(String systemActivityFollowID) {
        this.systemActivityFollowID = systemActivityFollowID;
    }
}
