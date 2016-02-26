package com.mai.vo;

import java.io.Serializable;

/**
 * Created by denghao on 15/10/21.
 */
public class SocietyRunningVO implements Serializable {
    private static final long serialVersionUID = 471002033132731025L;

    private String societyID;

    private String societyName;

    private Integer level;

    private Integer followNum;

    private Integer praiseNum;

    private String societyLOGO;

    private Integer recommendNo;

    private String systemSocietyFollowID;

    private Integer societyFollowNum;

    private String systemSocietyPraiseID;

    private Integer societyPraiseNum;

    public Integer getFollowNum() {
        return followNum;
    }

    public void setFollowNum(Integer followNum) {
        this.followNum = followNum;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public Integer getRecommendNo() {
        return recommendNo;
    }

    public void setRecommendNo(Integer recommendNo) {
        this.recommendNo = recommendNo;
    }

    public Integer getSocietyFollowNum() {
        return societyFollowNum;
    }

    public void setSocietyFollowNum(Integer societyFollowNum) {
        this.societyFollowNum = societyFollowNum;
    }

    public String getSocietyID() {
        return societyID;
    }

    public void setSocietyID(String societyID) {
        this.societyID = societyID;
    }

    public String getSocietyLOGO() {
        return societyLOGO;
    }

    public void setSocietyLOGO(String societyLOGO) {
        this.societyLOGO = societyLOGO;
    }

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public String getSystemSocietyFollowID() {
        return systemSocietyFollowID;
    }

    public void setSystemSocietyFollowID(String systemSocietyFollowID) {
        this.systemSocietyFollowID = systemSocietyFollowID;
    }

    public String getSystemSocietyPraiseID() {
        return systemSocietyPraiseID;
    }

    public void setSystemSocietyPraiseID(String systemSocietyPraiseID) {
        this.systemSocietyPraiseID = systemSocietyPraiseID;
    }

    public Integer getSocietyPraiseNum() {
        return societyPraiseNum;
    }

    public void setSocietyPraiseNum(Integer societyPraiseNum) {
        this.societyPraiseNum = societyPraiseNum;
    }
}
