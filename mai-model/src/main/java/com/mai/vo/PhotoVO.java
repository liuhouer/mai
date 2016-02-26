package com.mai.vo;

import java.io.Serializable;

/**
 * 图片举报
 */
public class PhotoVO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7583345156623148796L;
	//id
    private String photoReportID;
    // 图片ID
    private String photoID;
    // 举报人ID
    private String personID;
   
    // 创建时间
    private String createTime;
    
    //图片URL
    private String photoURL;
    
  
    
    /**
     * // 状态 0:待审核 1:删除 2:忽略
     */
    private Integer status;
    
    //举报内容
    private String notes;
    
    //举报人姓名
    private String personName;

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


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}


}
