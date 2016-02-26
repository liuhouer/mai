package com.mai.vo;

import java.io.Serializable;

public class ActRankVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8430456233344444895L;
	public String activityTitle;
	public String societyName;
	public String schoolName;
	public String joinPersonNum;
	public String followNum;
	public String getActivityTitle() {
		return activityTitle;
	}
	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}
	public String getSocietyName() {
		return societyName;
	}
	public void setSocietyName(String societyName) {
		this.societyName = societyName;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getJoinPersonNum() {
		return joinPersonNum;
	}
	public void setJoinPersonNum(String joinPersonNum) {
		this.joinPersonNum = joinPersonNum;
	}
	public String getFollowNum() {
		return followNum;
	}
	public void setFollowNum(String followNum) {
		this.followNum = followNum;
	}
	

	
	
	
}
