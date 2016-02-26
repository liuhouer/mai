package com.mai.society.entity;

import java.io.Serializable;

/**
 * 社团标签关系表
 * Created by fengdzh on 2015/10/5.
 */
public class SocietytagRef implements Serializable {

    private static final long serialVersionUID = 1213164801322285413L;
    private String societytagrefID;
    private String societyID;
    private String societyTagID;

    public String getSocietyID() {
        return societyID;
    }

    public void setSocietyID(String societyID) {
        this.societyID = societyID;
    }

    public String getSocietyTagID() {
        return societyTagID;
    }

    public void setSocietyTagID(String societyTagID) {
        this.societyTagID = societyTagID;
    }

    public String getSocietytagrefID() {
        return societytagrefID;
    }

    public void setSocietytagrefID(String societytagrefID) {
        this.societytagrefID = societytagrefID;
    }
}
