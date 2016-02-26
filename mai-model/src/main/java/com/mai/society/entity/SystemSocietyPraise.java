package com.mai.society.entity;

import java.io.Serializable;

/**
 * Created by denghao on 15/10/22.
 */
public class SystemSocietyPraise implements Serializable {
    private static final long serialVersionUID = 2969252952645802564L;

    private String systemSocietyPraiseID;
    private String societyID;
    private Integer societyPraiseNum;
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

    public Integer getSocietyPraiseNum() {
        return societyPraiseNum;
    }

    public void setSocietyPraiseNum(Integer societyPraiseNum) {
        this.societyPraiseNum = societyPraiseNum;
    }

    public String getSystemSocietyPraiseID() {
        return systemSocietyPraiseID;
    }

    public void setSystemSocietyPraiseID(String systemSocietyPraiseID) {
        this.systemSocietyPraiseID = systemSocietyPraiseID;
    }
}
