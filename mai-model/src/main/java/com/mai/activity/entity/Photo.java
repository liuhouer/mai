package com.mai.activity.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 图册内容
 * Created by fengdzh on 2015/9/11.
 */
public class Photo implements Serializable {

    public static final Integer ISVALID_YES = 1;
    public static final Integer ISVALID_NOT = 0;
    public static final Integer ISREPORTED_DEFAULT = 0;
    private static final long serialVersionUID = -3546815286021363493L;
    //id
    private String photoID;
    //图片URL:key值，如果使用的话，需要到服务端请求大图地址
    private String photoURL;
    //图片URL:缩略图
    private String photoThumbnailURL;
    //社团ID
    private String societyID;
    //活动ID
    private String activityID;
    //是否有效
    private Integer isValid;
    //是否被举报
    private Integer isReported;
    private Double width = 0D;
    private Double height = 0D;
    //创建人ID
    private String personID;
    //创建人姓名
    private String personName;
    //创建时间
    private Long createTime;

    private String notes;
    
    private String showTime;
    
    private Integer tagsize;

    //图片标签，动态添加
    private List<PhotoTag> photoTagList;

    public String getPhotoThumbnailURL() {
        return photoThumbnailURL;
    }

    public void setPhotoThumbnailURL(String photoThumbnailURL) {
        this.photoThumbnailURL = photoThumbnailURL;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
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

    public Integer getIsReported() {
        return isReported;
    }

    public void setIsReported(Integer isReported) {
        this.isReported = isReported;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getActivityID() {
        return activityID;
    }

    public void setActivityID(String activityID) {
        this.activityID = activityID;
    }

    public String getSocietyID() {
        return societyID;
    }

    public void setSocietyID(String societyID) {
        this.societyID = societyID;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPhotoID() {
        return photoID;
    }

    public void setPhotoID(String photoID) {
        this.photoID = photoID;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public List<PhotoTag> getPhotoTagList() {
        return photoTagList;
    }

    public void setPhotoTagList(List<PhotoTag> photoTagList) {
        this.photoTagList = photoTagList;
    }

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public Integer getTagsize() {
		return tagsize;
	}

	public void setTagsize(Integer tagsize) {
		this.tagsize = tagsize;
	}
}
