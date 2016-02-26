package com.mai.app.dto;

import java.io.Serializable;

/**
 * AppMessageInfoDTO
 *
 * @author Yao Jinwei
 * @date 2015/12/24
 */
public class AppMessageInfoDTO implements Serializable {
    private static final long serialVersionUID = -6161535667839784010L;

    // 分享页面主键值
    private String pageID;
    // 页面类型，暂未使用
    private Integer pageType;
    // 是否是预览，暂未使用
    private Boolean isPreview;
    // 分享title
    private String title;
    // 分享文字摘要
    private String description;
    // 分享小图
    private String picID;

    public String getPageID() {
        return pageID;
    }

    public void setPageID(String pageID) {
        this.pageID = pageID;
    }

    public Integer getPageType() {
        return pageType;
    }

    public void setPageType(Integer pageType) {
        this.pageType = pageType;
    }

    public Boolean getIsPreview() {
        return isPreview;
    }

    public void setIsPreview(Boolean isPreview) {
        this.isPreview = isPreview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicID() {
        return picID;
    }

    public void setPicID(String picID) {
        this.picID = picID;
    }
}
