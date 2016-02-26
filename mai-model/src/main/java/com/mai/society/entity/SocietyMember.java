package com.mai.society.entity;

import java.io.Serializable;

/**
 * 社团成员信息
 * Created by fengdzh on 2015/10/5.
 */
public class SocietyMember implements Serializable {

    private static final long serialVersionUID = 7551578434811219657L;

    //未审核
    public static final Integer MEMBERSTATUS_CHECKKED = 1;
    //审核通过
    public static final Integer MEMBERSTATUS_NORMAL = 0;
    //审核不通过
    public static final Integer MEMBERSTATUS_ISVALID_NOT = -1;

    private String societyMemberID;
    private String societyID;
    private String personID;
    private Integer memberStatus;
    private Long createTime;
    private String applayNote;

    //显示人员信息来自person
    private String name;
    private String gender;
    private String schoolID;
    private String schoolName;
    private String phoneNum;
    private String headerURL;

    //显示时间格式
    private String showCreateTime;

    public String getApplayNote() {
        return applayNote;
    }

    public void setApplayNote(String applayNote) {
        this.applayNote = applayNote;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(Integer memberStatus) {
        this.memberStatus = memberStatus;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeaderURL() {
        return headerURL;
    }

    public void setHeaderURL(String headerURL) {
        this.headerURL = headerURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
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

    public String getShowCreateTime() {
        return showCreateTime;
    }

    public void setShowCreateTime(String showCreateTime) {
        this.showCreateTime = showCreateTime;
    }

    public String getSocietyMemberID() {
        return societyMemberID;
    }

    public void setSocietyMemberID(String societyMemberID) {
        this.societyMemberID = societyMemberID;
    }
}
