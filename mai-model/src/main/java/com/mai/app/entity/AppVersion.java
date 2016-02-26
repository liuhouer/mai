package com.mai.app.entity;

import java.io.Serializable;

/**
 * app版本管理
 * Created by fengdzh on 2015/10/17.
 */
public class AppVersion implements Serializable {

    private static final long serialVersionUID = 3636268568610970153L;
    private String appVersionID;
    private String appType;
    private String appVersionNo;
    private String nextAppVersonID;
    private String nextVersonNo;
    private Integer needUpdate;
    private String updateNote;
    private String updateURL;
    private Long createTime;

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getAppVersionID() {
        return appVersionID;
    }

    public void setAppVersionID(String appVersionID) {
        this.appVersionID = appVersionID;
    }

    public String getAppVersionNo() {
        return appVersionNo;
    }

    public void setAppVersionNo(String appVersionNo) {
        this.appVersionNo = appVersionNo;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getNeedUpdate() {
        return needUpdate;
    }

    public void setNeedUpdate(Integer needUpdate) {
        this.needUpdate = needUpdate;
    }

    public String getNextAppVersonID() {
        return nextAppVersonID;
    }

    public void setNextAppVersonID(String nextAppVersonID) {
        this.nextAppVersonID = nextAppVersonID;
    }

    public String getNextVersonNo() {
        return nextVersonNo;
    }

    public void setNextVersonNo(String nextVersonNo) {
        this.nextVersonNo = nextVersonNo;
    }

    public String getUpdateNote() {
        return updateNote;
    }

    public void setUpdateNote(String updateNote) {
        this.updateNote = updateNote;
    }

    public String getUpdateURL() {
        return updateURL;
    }

    public void setUpdateURL(String updateURL) {
        this.updateURL = updateURL;
    }
}
