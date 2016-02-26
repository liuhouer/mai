package com.mai.user.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 用户信息
 * Created by fengdzh on 2015/9/11.
 */
public class User implements Serializable {
    //帐户状态：有效
    public static final Integer ISVALID_YES = 1;
    //帐户状态：无效
    public static final Integer ISVALID_NO = 0;
    //帐户状态：sn注册
    public static final Integer ISVALID_SN = -1;

    private static final long serialVersionUID = -2004268302714986484L;
    private String userID;
    private String personID;
    private String phoneNum;
    private String password;
    private Long createTime;
    private Long updateTime;
    private Integer isValid;
    //手机登陆，用户获取的token;
    private String token;
    private Long tokenCreateTime;

    //联合查询 person的信息
    private String personPhoneNum;
    private String personName;
    private String schoolID;
    private String schoolName;

    //用户访问权限设置
    private List<Map<String,String>> permissionList;
    //SocietyID
    private String SocietyID;
    //RoleName
    private String RoleName;

    public Long getTokenCreateTime() {
        return tokenCreateTime;
    }

    public void setTokenCreateTime(Long tokenCreateTime) {
        this.tokenCreateTime = tokenCreateTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPersonPhoneNum() {
        return personPhoneNum;
    }

    public void setPersonPhoneNum(String personPhoneNum) {
        this.personPhoneNum = personPhoneNum;
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

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public List<Map<String, String>> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Map<String, String>> permissionList) {
        this.permissionList = permissionList;
    }

    public String getSocietyID() {
        return SocietyID;
    }

    public void setSocietyID(String societyID) {
        SocietyID = societyID;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }
}
