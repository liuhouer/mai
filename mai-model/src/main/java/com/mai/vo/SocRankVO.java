package com.mai.vo;

import java.io.Serializable;

public class SocRankVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8430456233344444895L;
	public String societyName;
	public String schoolName;
	public String joinPersonNum;
	public String actNum;
	public String actmNum;
	public String followNum;
	public String praiseNum;
	
	public String getSocietyName() {
		return societyName;
	}
	public void setSocietyName(String societyName) {
		this.societyName = societyName;
	}
	public String getJoinPersonNum() {
		return joinPersonNum;
	}
	public void setJoinPersonNum(String joinPersonNum) {
		this.joinPersonNum = joinPersonNum;
	}
	public String getActNum() {
		return actNum;
	}
	public void setActNum(String actNum) {
		this.actNum = actNum;
	}
	public String getActmNum() {
		return actmNum;
	}
	public void setActmNum(String actmNum) {
		this.actmNum = actmNum;
	}
	public String getFollowNum() {
		return followNum;
	}
	public void setFollowNum(String followNum) {
		this.followNum = followNum;
	}
	public String getPraiseNum() {
		return praiseNum;
	}
	public void setPraiseNum(String praiseNum) {
		this.praiseNum = praiseNum;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSchoolName() {
		return schoolName;
	}
}