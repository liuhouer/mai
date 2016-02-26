package com.mai.society.entity;

import java.io.Serializable;

/**
 * 点赞
 * Created by fengdzh on 2015/10/12.
 */
public class SocietyPraise implements Serializable {

    private static final long serialVersionUID = 7506899468698676234L;

    private String societyPraiseID;
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

    public String getSocietyID() {
        return societyID;
    }

    public void setSocietyID(String societyID) {
        this.societyID = societyID;
    }

    public String getSocietyPraiseID() {
        return societyPraiseID;
    }

    public void setSocietyPraiseID(String societyPraiseID) {
        this.societyPraiseID = societyPraiseID;
    }
}
