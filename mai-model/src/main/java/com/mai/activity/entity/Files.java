package com.mai.activity.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2015/9/7.
 */
public class Files implements Serializable {
    private String fileID;
    private String storeAddress;
    private String smallPicPath;
    private String middlePicPath;
    private String fileName;
    private Integer fileType;
    private String fileSuffix;
    private String companyID;
    private long fileSize;
    private Timestamp createTime;
    //是否需要审核
    private Boolean isCheck;

    public Boolean getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Boolean isCheck) {
        this.isCheck = isCheck;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getMiddlePicPath() {
        return middlePicPath;
    }

    public void setMiddlePicPath(String middlePicPath) {
        this.middlePicPath = middlePicPath;
    }

    public String getSmallPicPath() {
        return smallPicPath;
    }

    public void setSmallPicPath(String smallPicPath) {
        this.smallPicPath = smallPicPath;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }
}
