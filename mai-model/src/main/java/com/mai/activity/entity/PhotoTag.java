package com.mai.activity.entity;

import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.io.Serializable;

/**
 * 照片标签页
 * Created by denghao on 15/12/3.
 */
public class PhotoTag implements Serializable {
    private static final long serialVersionUID = -3591704536901143585L;

    public static final Integer MAXNUM = 100;

    private String tagID;
    private String tagContent64;
    private Integer orderNum = 99;
    private String photoID;
    private Long createTime;
    private Integer status = 1;
    private String personID;
    private String personName;
    private Double pointX = 0D;
    private Double pointY = 0D;
    private String tagColor;
    private String showtagContent64;

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPhotoID() {
        return photoID;
    }

    public void setPhotoID(String photoID) {
        this.photoID = photoID;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTagID() {
        return tagID;
    }

    public void setTagID(String tagID) {
        this.tagID = tagID;
    }

    public String getTagColor() {
        return tagColor;
    }

    public void setTagColor(String tagColor) {
        this.tagColor = tagColor;
    }

    public Double getPointX() {
        return pointX;
    }

    public void setPointX(Double pointX) {
        this.pointX = pointX;
    }

    public Double getPointY() {
        return pointY;
    }

    public void setPointY(Double pointY) {
        this.pointY = pointY;
    }

    public String getShowtagContent64() {
        try {
            if(showtagContent64 == null){
                showtagContent64 = tagContent64;
            }
            byte[] b = new BASE64Decoder().decodeBuffer(showtagContent64);
            return new String(b);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
    public void setShowtagContent64(String showtagContent64) {
        this.showtagContent64 = showtagContent64;
    }

    public String getTagContent64() {
        return tagContent64;
    }

    public void setTagContent64(String tagContent64) {
        this.tagContent64 = tagContent64;
    }
}
