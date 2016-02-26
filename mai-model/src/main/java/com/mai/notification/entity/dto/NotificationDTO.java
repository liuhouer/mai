package com.mai.notification.entity.dto;

import java.io.Serializable;

/**
 * Created by denghao on 15/12/1.
 */
public class NotificationDTO implements Serializable {
    private static final long serialVersionUID = -4617973020080686399L;

    private String objID;
    private String objType;
    private String cur_userid;
    private String cont;

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public String getCur_userid() {
        return cur_userid;
    }

    public void setCur_userid(String cur_userid) {
        this.cur_userid = cur_userid;
    }

    public String getObjID() {
        return objID;
    }

    public void setObjID(String objID) {
        this.objID = objID;
    }
}
