package com.mai.notification.service;

import java.util.List;

import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.notification.entity.Notification;
import com.mai.notification.entity.NotificationMain;


/**
 * 通知接口
 * Created by fengdzh on 2015/9/14.
 */
public interface NotificationService {
    /**
     * 加入通知
     *
     * @param notification
     * @return
     * @throws BaseException
     */
    public Notification insertNotification(Notification notification) throws BaseException;

    /**
     * 我的通知列表
     *
     * @param personID
     * @param paginationParameters
     * @return
     * @throws BaseException
     */
    public Page<Notification> findNotificationByPage(String personID, PaginationParameters paginationParameters) throws BaseException;

    
    public int add(Notification n);
	
	public int addMain(NotificationMain m);

	public Page<NotificationMain> findAllByPage(PaginationParameters paginationParameters,String wheresql) throws BaseException;

	/**
	 * 根据消息类型和ID给每位成员发送消息
	 * @param objID
	 * @param objType
	 * @param cur_userid
	 * @param cont
	 * @param toPersonID==发送给单独者的id
	 */
	public void sendNotice(String objID, String objType,String cur_userid,String cont,String toPersonID);

	public NotificationMain findMainByID(String id);

	public int removeMainByID(String id);

	public int updateST(NotificationMain m);

	public void push2Single(Notification notification)throws BaseException;

	public void push2App(Notification notification)throws BaseException;

	public int updateModel(Notification notification);

	/**
	 * 根据条件取得以前用户的申请记录
	 * @param toPersonID
	 * @param notificationType
	 * @param fromPersonID
	 * @return
	 */
	public List<Notification> getOldStatusList(String toPersonID , Integer notificationType ,String fromPersonID);

	/**
	 * 根据消息类型和ID给每位关注人员发送消息
	 * @param objID
	 * @param objType
	 * @param cur_userid
	 * @param cont
	 */
	public void sendFollowNotice(String objID, String objType,String cur_userid,String cont) throws BaseException;

}
