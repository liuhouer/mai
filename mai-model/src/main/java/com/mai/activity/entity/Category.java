package com.mai.activity.entity;

import java.io.Serializable;

/**
 * 分类信息
 * Created by Administrator on 2015/9/7.
 */
public class Category implements Serializable {


    private static final long serialVersionUID = -6141049277191244312L;
    private String categoryID;
    private String categoryName;
    private Integer categoryOrderNum;
    private Integer categoryCount = 0;
    private String imageURL;

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryOrderNum() {
        return categoryOrderNum;
    }

    public void setCategoryOrderNum(Integer categoryOrderNum) {
        this.categoryOrderNum = categoryOrderNum;
    }

    public Integer getCategoryCount() {
        return categoryCount;
    }

    public void setCategoryCount(Integer categoryCount) {
        this.categoryCount = categoryCount;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
