package com.mai.user.entity;

import java.io.Serializable;

/**
 * Created by denghao on 15/10/6.
 */
public class Permission implements Serializable {
    private static final long serialVersionUID = -4708955357791568516L;

    private String permissionID;
    private String actionName;
    private String actionURL;
    private Long editTime;
    private String roleID;
    private String Note;
    private Integer orderNum = 0;

    private String showEditTime;

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionURL() {
        return actionURL;
    }

    public void setActionURL(String actionURL) {
        this.actionURL = actionURL;
    }

    public Long getEditTime() {
        return editTime;
    }

    public void setEditTime(Long editTime) {
        this.editTime = editTime;
    }

    public String getPermissionID() {
        return permissionID;
    }

    public void setPermissionID(String permissionID) {
        this.permissionID = permissionID;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getShowEditTime() {
        return showEditTime;
    }

    public void setShowEditTime(String showEditTime) {
        this.showEditTime = showEditTime;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
