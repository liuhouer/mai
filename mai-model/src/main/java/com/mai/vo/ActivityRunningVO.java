package com.mai.vo;

import java.io.Serializable;

/**
 * Created by denghao on 15/10/24.
 */
public class ActivityRunningVO implements Serializable{
    private static final long serialVersionUID = -240901241636460687L;

    private String activityID;

    private String societyName;

    private String activityTitle;

    private Integer followNum;

    private Integer recommendNo;

    private String systemActivityFollowID;

    private Integer activityFollowNum;

    private String showStartTime;

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

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public Integer getFollowNum() {
        return followNum;
    }

    public void setFollowNum(Integer followNum) {
        this.followNum = followNum;
    }

    public Integer getRecommendNo() {
        return recommendNo;
    }

    public void setRecommendNo(Integer recommendNo) {
        this.recommendNo = recommendNo;
    }

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public String getSystemActivityFollowID() {
        return systemActivityFollowID;
    }

    public void setSystemActivityFollowID(String systemActivityFollowID) {
        this.systemActivityFollowID = systemActivityFollowID;
    }

    public String getShowStartTime() {
        return showStartTime;
    }

    public void setShowStartTime(String showStartTime) {
        this.showStartTime = showStartTime;
    }
}
