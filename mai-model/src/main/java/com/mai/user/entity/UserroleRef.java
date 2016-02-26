package com.mai.user.entity;

import java.io.Serializable;

/**
 * Created by denghao on 15/10/6.
 */
public class UserroleRef implements Serializable{
    private static final long serialVersionUID = -929506706049936695L;

    private String userRoleRefID;
    private String userID;
    private String roleID;

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserRoleRefID() {
        return userRoleRefID;
    }

    public void setUserRoleRefID(String userRoleRefID) {
        this.userRoleRefID = userRoleRefID;
    }
}
