package com.mai.activity.entity;

import java.io.Serializable;

/**
 * 活动详情页面
 * Created by fengdzh on 2015/9/18.
 */
public class ActivityDetail implements Serializable {

    private static final long serialVersionUID = -656034506329851498L;
    private String activityDetailID;
    private String activityDetail;
    private String activityDetailHerf;
    private Long createTime;
    private String createPersonID;
    private String createPersonName;

    public String getActivityDetail() {
        return activityDetail;
    }

    public void setActivityDetail(String activityDetail) {
        this.activityDetail = activityDetail;
    }

    public String getActivityDetailID() {
        return activityDetailID;
    }

    public void setActivityDetailID(String activityDetailID) {
        this.activityDetailID = activityDetailID;
    }

    public String getCreatePersonID() {
        return createPersonID;
    }

    public void setCreatePersonID(String createPersonID) {
        this.createPersonID = createPersonID;
    }

    public String getCreatePersonName() {
        return createPersonName;
    }

    public void setCreatePersonName(String createPersonName) {
        this.createPersonName = createPersonName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getActivityDetailHerf() {
        return activityDetailHerf;
    }

    public void setActivityDetailHerf(String activityDetailHerf) {
        this.activityDetailHerf = activityDetailHerf;
    }
}
