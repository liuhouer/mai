package com.mai.activity.entity;

import java.io.Serializable;

/**
 * 星级信息
 * Created by bruce on 2015/10/19.
 */
public class LevelRule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 127256731111725516L;
	
	private String ruleID;

	private String level;

	private Integer praiseNum;

	private Integer followNum;

	private Long createTime;
	
	private String max;
	

	public String getRuleID() {
		return ruleID;
	}

	public void setRuleID(String ruleID) {
		this.ruleID = ruleID;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
	}

	public Integer getFollowNum() {
		return followNum;
	}

	public void setFollowNum(Integer followNum) {
		this.followNum = followNum;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}
	
	

}
