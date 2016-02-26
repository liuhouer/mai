package com.mai.user.entity;

import java.io.Serializable;

/**
 * 用户信息
 * Created by Administrator on 2015/8/31.
 */
public class Person implements Serializable {

    private static final long serialVersionUID = -5202280828383308522L;
    /**
     * 默认签名
     *
     */
    public static String PERSONALSIGN = "没有签名，不代表我没有态度～";
    //是否社长：是
    public static final Integer ISPRESIDENT_YES = 1;
    //是否社长：否
    public static final Integer ISPRESIDENT_NO = 0;
    //是否是自定义姓名 ：是
    public static final Integer ISCUSTOMNAME_YES = 1;
    //是否是自定义姓名：否
    public static final Integer ISCUSTOMNAME_NO = 0;
    private String personID;
    private String name;
    //手机号码
    private String phoneNum;
    //性别
    private String gender;
    //头像
    private String headerURL;
    //学校ID
    private String schoolID;
    //学校名称
    private String schoolName;
    //个性签名
    private String signature;
    //创建时间
    private Long createTime;
    //更新时间
    private Long updateTime;
    //最后操作时间
    private Long operateTime;
    //有效状态
    private Integer isValid;
    //是否社长
    private Integer isPresident = 0;
    // 是否是自定义姓名
    private Integer isCustomName;

    public Integer getIsCustomName() {
        return isCustomName;
    }

    public void setIsCustomName(Integer isCustomName) {
        this.isCustomName = isCustomName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public Long getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Long operateTime) {
        this.operateTime = operateTime;
    }

    public Integer getIsPresident() {
        return isPresident;
    }

    public void setIsPresident(Integer isPresident) {
        this.isPresident = isPresident;
    }
}
