package com.mai.activity.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/9/7.
 */
public class School implements Serializable {

    private static final long serialVersionUID = 271048869419766644L;
    private String schoolID;
    private String schoolName;
    private Integer schoolOrderNum;

    public Integer getSchoolOrderNum() {
        return schoolOrderNum;
    }

    public void setSchoolOrderNum(Integer schoolOrderNum) {
        this.schoolOrderNum = schoolOrderNum;
    }

    public String getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(String schoolID) {
        this.schoolID = schoolID;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
