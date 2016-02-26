package com.mai.activity.entity;

import java.io.Serializable;

/**
 * Created by denghao on 15/10/9.
 */
public class Sponsor implements Serializable {
    private static final long serialVersionUID = -8731340892052271336L;

    //赞助状态：审核中
    public static final Integer STATUS_CHECKED = 0;
    //赞助状态：关闭
    public static final Integer STATUS_CLOSE = -1;
    //赞助状态：正常
    public static final Integer STATUS_NORMAL = 1;

    private String sponsorID;
    private String sponsorName;
    private String activityID;
    private Long createTime;
    private Long updateTime;
    private Long appTime;
    private String phoneNum;
    private String adminID;
    private String adminName;
    private Integer sponsorStatus;
    private String sponsorNote;


    private String showUpdateTime;
    private String showAppTime;
    private String showCreateTime;
    private String activityTitle;

    public Integer getSponsorStatus() {
        return sponsorStatus;
    }

    public void setSponsorStatus(Integer sponsorStatus) {
        this.sponsorStatus = sponsorStatus;
    }

    public String getActivityID() {
        return activityID;
    }

    public void setActivityID(String activityID) {
        this.activityID = activityID;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getSponsorID() {
        return sponsorID;
    }

    public void setSponsorID(String sponsorID) {
        this.sponsorID = sponsorID;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public String getSponsorNote() {
        return sponsorNote;
    }

    public void setSponsorNote(String sponsorNote) {
        this.sponsorNote = sponsorNote;
    }

    public String getShowCreateTime() {
        return showCreateTime;
    }

    public void setShowCreateTime(String showCreateTime) {
        this.showCreateTime = showCreateTime;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getShowAppTime() {
        return showAppTime;
    }

    public void setShowAppTime(String showAppTime) {
        this.showAppTime = showAppTime;
    }

    public String getShowUpdateTime() {
        return showUpdateTime;
    }

    public void setShowUpdateTime(String showUpdateTime) {
        this.showUpdateTime = showUpdateTime;
    }

    public Long getAppTime() {
        return appTime;
    }

    public void setAppTime(Long appTime) {
        this.appTime = appTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
