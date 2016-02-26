package com.mai.society.entity;

import java.io.Serializable;

/**
 * 社团关注
 * Created by fengdzh on 2015/10/8.
 */
public class SocietyFollow implements Serializable {

    private static final long serialVersionUID = 8179701848594853982L;
    private String societyFollowID;
    private String societyID;
    private String personID;
    private Long createTime;

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

    public String getSocietyFollowID() {
        return societyFollowID;
    }

    public void setSocietyFollowID(String societyFollowID) {
        this.societyFollowID = societyFollowID;
    }

    public String getSocietyID() {
        return societyID;
    }

    public void setSocietyID(String societyID) {
        this.societyID = societyID;
    }
}
