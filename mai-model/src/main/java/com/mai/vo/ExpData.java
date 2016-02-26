package com.mai.vo;

import java.io.Serializable;
import java.util.Date;

public class ExpData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8430456233344444895L;
	public String userid;
	public String phone;
	public String name;
	public String roleName;
	public String schoolName;
	public String societyName;
	public String createTime;
	public Integer intST;
	public String status;
	public String operateTime;
	public String societyID;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSocietyName() {
		return societyName;
	}
	public void setSocietyName(String societyName) {
		this.societyName = societyName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Integer getIntST() {
		return intST;
	}
	public void setIntST(Integer intST) {
		this.intST = intST;
	}
	public String getSocietyID() {
		return societyID;
	}
	public void setSocietyID(String societyID) {
		this.societyID = societyID;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
}
