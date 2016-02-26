package com.mai.card.entity;

import java.io.Serializable;

/**
 * CardMaterial
 * 贺卡边框
 * @author Yao Jinwei
 * @date 2015/12/18
 */
public class CardFrame implements Serializable {

    private static final long serialVersionUID = 6654328096133391825L;
    public static final Integer CARDFRAME_ISDELETE_YES = 1;
    public static final Integer CARDFRAME_ISDELETE_NO = 0;

    //id
    private String cardFrameID;
    //图片URL
    private String photoURL;
    //缩略图URL
    private String photoThumbnailURL;
    private Integer width;
    private Integer height;
    private Long createTime;
    private String personID;
    private Integer isDelete;

    public String getCardFrameID() {
        return cardFrameID;
    }

    public void setCardFrameID(String cardFrameID) {
        this.cardFrameID = cardFrameID;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
