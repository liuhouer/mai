package com.mai.notification.entity;

import java.io.Serializable;

public class NotificationMain implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1067678003495541073L;

	/**
	 * 未发布-草稿状态
	 */
	@SuppressWarnings("unused")
	public  static  final Integer STATUS_DRAFT = 0;
	/**
	 * 发布- 已发布
	 */
	@SuppressWarnings("unused")
	public  static  final Integer STATUS_PUBLISHED = 1;
	
	private String notificationMainID;

	private String notificationContent;

	private Long createTime;
	
	private String showTime;

	private String personID;

	private Integer status;

	private String objID;

	private Integer objType;
	
	private String objName;
	
	private String societyID;


	public String getNotificationMainID() {
		return notificationMainID;	
	}
	
	public void setNotificationMainID(String notificationMainID) {
		this.notificationMainID = notificationMainID;
	}
	public String getNotificationContent() {
		return notificationContent;	
	}
	
	public void setNotificationContent(String notificationContent) {
		this.notificationContent = notificationContent;
	}
	public Long getCreateTime() {
		return createTime;	
	}
	
	public String getPersonID() {
		return personID;	
	}
	
	public void setPersonID(String personID) {
		this.personID = personID;
	}
	public Integer getStatus() {
		return status;	
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getObjID() {
		return objID;	
	}
	
	public void setObjID(String objID) {
		this.objID = objID;
	}
	public Integer getObjType() {
		return objType;	
	}
	
	public void setObjType(Integer objType) {
		this.objType = objType;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getSocietyID() {
		return societyID;
	}

	public void setSocietyID(String societyID) {
		this.societyID = societyID;
	}

	/*这里是mybatis部分代码
	
	notificationMainID,notificationContent,createTime,personID,status,objID,objType,	

		#{notificationMainID},	#{notificationContent},	#{createTime},	#{personID},	#{status},	#{objID},	#{objType},	
	
	
	
	
	*/
}