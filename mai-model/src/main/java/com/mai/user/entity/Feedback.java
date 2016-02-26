package com.mai.user.entity;

import java.io.Serializable;

/**
 * Created by yaojw on 2015/10/20.
 */
public class Feedback implements Serializable {
    private static final long serialVersionUID = 2642546960163343598L;
    // ID
    private String feedbackID;
    // 人员编号
    private String personID;
    // 人员名称
    private String name;
    // 反馈内容
    private String notes;
    // 创建时间
    private Long createTime;
    
    private String showTime;

    public String getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(String feedbackID) {
        this.feedbackID = feedbackID;
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

}
