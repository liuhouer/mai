package com.mai.vo;

import java.io.Serializable;

/**
 * 用户导入模板---
 * @author bruce
 * 2015-10-12
 */
public class UserTemp implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4109605762415290095L;
	
	private String roleName;
    private String name;
    private String phoneNum;
    
    //added 导入字段 2015-10-27
    private String schoolID;
    private String schoolName;
    public UserTemp() {
		super();
	}
	private String societyName;
    
	public String getRoleName() {
		return roleName;
	}
	public UserTemp(String roleName, String name, String phoneNum,
			String schoolID, String schoolName, String societyName) {
		super();
		this.roleName = roleName;
		this.name = name;
		this.phoneNum = phoneNum;
		this.schoolID = schoolID;
		this.schoolName = schoolName;
		this.societyName = societyName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getSchoolID() {
		return schoolID;
	}
	public void setSchoolID(String schoolID) {
		this.schoolID = schoolID;
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
    
    

}
