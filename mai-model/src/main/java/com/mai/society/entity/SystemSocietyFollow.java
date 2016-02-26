package com.mai.society.entity;

import java.io.Serializable;

/**
 * Created by denghao on 15/10/22.
 */
public class SystemSocietyFollow implements Serializable {
    private static final long serialVersionUID = 839786823163661847L;

    private String systemSocietyFollowID;
    private String societyID;
    private Integer societyFollowNum;
    private String personID;
    private Long createTime;

    public String getSystemSocietyFollowID() {
        return systemSocietyFollowID;
    }

    public void setSystemSocietyFollowID(String systemSocietyFollowID) {
        this.systemSocietyFollowID = systemSocietyFollowID;
    }

    public String getSocietyID() {
        return societyID;
    }

    public void setSocietyID(String societyID) {
        this.societyID = societyID;
    }

    public Integer getSocietyFollowNum() {
        return societyFollowNum;
    }

    public void setSocietyFollowNum(Integer societyFollowNum) {
        this.societyFollowNum = societyFollowNum;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
