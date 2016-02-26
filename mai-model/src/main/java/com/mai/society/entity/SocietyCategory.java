package com.mai.society.entity;

import java.io.Serializable;

/**
 * Created by fengdzh on 2015/10/5.
 */
public class SocietyCategory implements Serializable {
    private static final long serialVersionUID = 6291927822317677844L;
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
