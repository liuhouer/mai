package com.mai.activity.entity;

import java.io.Serializable;

/**
 * 图片举报
 */
public class PhotoReport implements Serializable {

    public static final Integer PHOTO_REPORT_STATUS_UNCHECK = 0;
    public static final Integer PHOTO_REPORT_STATUS_DELETE = 1;
    public static final Integer PHOTO_REPORT_STATUS_IGNORE = 2;
    private static final long serialVersionUID = -1371751285312674318L;
    //id
    private String photoReportID;
    // 图片ID
    private String photoID;
    // 举报人ID
    private String personID;
    // 举报内容
    private String notes;
    // 创建时间
    private long createTime;
    
    /**
     * // 状态 0:待审核 1:删除 2:忽略
     */
    private Integer status;

    public String getPhotoReportID() {
        return photoReportID;
    }

    public void setPhotoReportID(String photoReportID) {
        this.photoReportID = photoReportID;
    }

    public String getPhotoID() {
        return photoID;
    }

    public void setPhotoID(String photoID) {
        this.photoID = photoID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
