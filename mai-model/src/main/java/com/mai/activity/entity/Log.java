package com.mai.activity.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/9/7.
 */
public class Log implements Serializable {

    private static final long serialVersionUID = -1673606735093988700L;
    
    /**
	 * 后台单个添加审核通过
	 *
	 */
	public static final String  MSG_ADMIN_ADD = "后台单个添加审核通过";
	
	 /**
		 * 后台批量导入添加审核通过
		 *
		 */
	public static final String  MSG_ADMIN_IMPORT = "后台批量导入添加审核通过";


	public static final String MSG_ADMIN_ACTIVITY_MEMBERSTATUS_ISVALID_NOT = "系统自动审核不通过";
		

	/**
	 * 管理类型-社团
	 *
	 */
	public static final int TYPE_SOCIETY = 1;
	
	/**
	 * 管理类型-社团-后台添加
	 *
	 */
	public static final int TYPE_SOCIETY_IMPORT = 13;
	
	/**
	 * 管理类型-社团-下架
	 *
	 */
	public static final int TYPE_SOCIETY_OFFLINE = 11;
	/**
	 * 管理类型-社团-不通过
	 *
	 */
	public static final int TYPE_SOCIETY_DISPASS = 12;

	/**
	 * 管理类型-活动
	 *
	 */
	public static final int TYPE_ACTIVITY = 2;

	/**
	 * 管理类型-运营
	 *
	 */
	public static final int TYPE_OPERATE = 3;

	/**
	 * 管理类型-赞助
	 *
	 */
	public static final int TYPE_SPONSOR = 4;

	//申请活动
	public static final int TYPE_ACTIVITY_MEMBER = 5;

	//申请社团
	public static final int TYPE_SOCIETY_MEMBER = 6;

	//活动批量下架默认
	public static final String ACTIVITY_BATCH_DESC = "由于社团下架导致活动下架";

    private String logID;
    private String logDesc;
    private Long createTime;
    private String logAuthor;
    private int logtype;
    private String actID;

	private String showCreateTime;
    
	public String getActID() {
		return actID;
	}
	public void setActID(String actID) {
		this.actID = actID;
	}
	public String getLogID() {
		return logID;
	}
	public void setLogID(String logID) {
		this.logID = logID;
	}
	public String getLogDesc() {
		return logDesc;
	}
	public void setLogDesc(String logDesc) {
		this.logDesc = logDesc;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public String getLogAuthor() {
		return logAuthor;
	}
	public void setLogAuthor(String logAuthor) {
		this.logAuthor = logAuthor;
	}
	public int getLogtype() {
		return logtype;
	}
	public void setLogtype(int logtype) {
		this.logtype = logtype;
	}

	public String getShowCreateTime() {
		return showCreateTime;
	}

	public void setShowCreateTime(String showCreateTime) {
		this.showCreateTime = showCreateTime;
	}
}
