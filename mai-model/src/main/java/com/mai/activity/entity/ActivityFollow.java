package com.mai.activity.entity;

import java.io.Serializable;

/**
 * Created by fengdzh on 2015/10/8.
 */
public class ActivityFollow implements Serializable {

    private static final long serialVersionUID = -5938903514718154148L;
    private String activityFollowID;
    private String activityID;
    private String personID;
    private Long createTime;

    public String getActivityFollowID() {
        return activityFollowID;
    }

    public void setActivityFollowID(String activityFollowID) {
        this.activityFollowID = activityFollowID;
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
}
