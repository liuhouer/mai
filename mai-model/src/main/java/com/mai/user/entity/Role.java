package com.mai.user.entity;

import java.io.Serializable;

/**
 * Created by denghao on 15/10/6.
 */
public class Role implements Serializable{
    private static final long serialVersionUID = 211859343179471482L;

    public static final String ROLENAME_ADMIN = "admin";
    public static final String ROLENAME_SOCIETY = "society";
    public static final String ROLENAME_PERSON = "person";
    public static final String ROLENAME_ACTIVITY = "activity";
    public static final String ROLENAME_PRESIDENT = "president";
    public static final String ROLENAME_SPONSOR = "sponsor";
    
    public static final String SOCIETY_RUNNING = "society_running";
    public static final String ACTIVITY_RUNNING = "activity_running";
    public static final String RUNNING = "running";

    public static final String ROLEID_SHEZHANG = "7748189293320030394";

    private String roleID;
    private String roleName;
    private Long createTime;
    private Integer roleStatus;
    private String roleNote;
    private String roleNameEn;

    private String showCreateTime;

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleNote() {
        return roleNote;
    }

    public void setRoleNote(String roleNote) {
        this.roleNote = roleNote;
    }

    public Integer getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(Integer roleStatus) {
        this.roleStatus = roleStatus;
    }

    public String getShowCreateTime() {
        return showCreateTime;
    }

    public void setShowCreateTime(String showCreateTime) {
        this.showCreateTime = showCreateTime;
    }

    public String getRoleNameEn() {
        return roleNameEn;
    }

    public void setRoleNameEn(String roleNameEn) {
        this.roleNameEn = roleNameEn;
    }
}
