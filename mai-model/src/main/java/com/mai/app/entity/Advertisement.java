package com.mai.app.entity;

import java.io.Serializable;

public class Advertisement implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String advertisementID;

	private String title;

	private Long startTime;

	private Long endTime;
	
	private String showStartTime;

	private String showEndTime;

	private String imageURL;

	private String toURL;

	private Integer isEnable;


	public String getAdvertisementID() {
		return advertisementID;	
	}
	
	public void setAdvertisementID(String advertisementID) {
		this.advertisementID = advertisementID;
	}
	public String getTitle() {
		return title;	
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getStartTime() {
		return startTime;	
	}
	
	public void setStartTime(long l) {
		this.startTime = l;
	}
	public Long getEndTime() {
		return endTime;	
	}
	
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public String getImageURL() {
		return imageURL;	
	}
	
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getToURL() {
		return toURL;	
	}
	
	public void setToURL(String toURL) {
		this.toURL = toURL;
	}
	public Integer getIsEnable() {
		return isEnable;	
	}
	
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	public String getShowStartTime() {
		return showStartTime;
	}

	public void setShowStartTime(String showStartTime) {
		this.showStartTime = showStartTime;
	}

	public String getShowEndTime() {
		return showEndTime;
	}

	public void setShowEndTime(String showEndTime) {
		this.showEndTime = showEndTime;
	}

	
	/*这里是mybatis部分代码
	
	advertisementID,title,startTime,endTime,imageURL,toURL,isEnable,	

		#{advertisementID},	#{title},	#{startTime},	#{endTime},	#{imageURL},	#{toURL},	#{isEnable},	
	
	
	
	
	*/
}
