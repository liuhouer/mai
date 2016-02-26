package com.mai.card.entity;

import java.io.Serializable;

/**
 * CardStarPhoto
 * 明星照片
 * @author Yao Jinwei
 * @date 2015/12/19
 */
public class CardStarPhoto implements Serializable {

    private static final long serialVersionUID = -361049901566127454L;
    public static final Integer CARDSTARPHOTO_ISDELETE_YES = 1;
    public static final Integer CARDSTARPHOTO_ISDELETE_NO = 0;

    private String cardStarPhotoID;
    // 明星姓名
    private String cardStarName;
    // 照片URL
    private String photoURL;
    private String color;
    private String audioURL;

    //缩略图URL
    private String photoThumbnailURL;
    private Integer width;
    private Integer height;
    // 默认位移x   0：中间  1：右  -1：左
    private Integer dx;
    // 默认位移y   0：中  1：上  -1：下
    private Integer dy;
    // 默认缩放  相对相机窗口高度比例
    private Double ratio;
    private Long createTime;
    private String personID;
    // 排序号
    private Integer orderNum;
    private Integer isDelete;
    //是否禁用
    private Integer isForbidden;
    
    private String showTime;


    public String getCardStarPhotoID() {
        return cardStarPhotoID;
    }

    public void setCardStarPhotoID(String cardStarPhotoID) {
        this.cardStarPhotoID = cardStarPhotoID;
    }

    public String getCardStarName() {
        return cardStarName;
    }

    public void setCardStarName(String cardStarName) {
        this.cardStarName = cardStarName;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAudioURL() {
        return audioURL;
    }

    public void setAudioURL(String audioURL) {
        this.audioURL = audioURL;
    }

    public String getPhotoThumbnailURL() {
        return photoThumbnailURL;
    }

    public void setPhotoThumbnailURL(String photoThumbnailURL) {
        this.photoThumbnailURL = photoThumbnailURL;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getDx() {
        return dx;
    }

    public void setDx(Integer dx) {
        this.dx = dx;
    }

    public Integer getDy() {
        return dy;
    }

    public void setDy(Integer dy) {
        this.dy = dy;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsForbidden() {
        return isForbidden;
    }

    public void setIsForbidden(Integer isForbidden) {
        this.isForbidden = isForbidden;
    }

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
}
