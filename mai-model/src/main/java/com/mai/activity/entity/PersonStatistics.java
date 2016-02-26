package com.mai.activity.entity;

import java.io.Serializable;

/**
 * 成员活动情况统计
 * Created by fengdzh on 2015/10/2.
 */
public class PersonStatistics implements Serializable {

    private static final long serialVersionUID = -2795571385642448506L;

    private String personstatusID;
    private String personID;
    private Integer followActivityNum;
    private Integer joinActivityNum;
    private Integer followSocietyNum;
    private Integer joinSocietyNum;


    public Integer getFollowActivityNum() {
        return followActivityNum;
    }

    public void setFollowActivityNum(Integer followActivityNum) {
        this.followActivityNum = followActivityNum;
    }

    public Integer getFollowSocietyNum() {
        return followSocietyNum;
    }

    public void setFollowSocietyNum(Integer followSocietyNum) {
        this.followSocietyNum = followSocietyNum;
    }

    public Integer getJoinActivityNum() {
        return joinActivityNum;
    }

    public void setJoinActivityNum(Integer joinActivityNum) {
        this.joinActivityNum = joinActivityNum;
    }

    public Integer getJoinSocietyNum() {
        return joinSocietyNum;
    }

    public void setJoinSocietyNum(Integer joinSocietyNum) {
        this.joinSocietyNum = joinSocietyNum;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getPersonstatusID() {
        return personstatusID;
    }

    public void setPersonstatusID(String personstatusID) {
        this.personstatusID = personstatusID;
    }
}
