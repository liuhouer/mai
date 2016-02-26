package com.mai.activity.dao;

import java.util.List;

import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.notification.entity.Notification;
import com.mai.notification.entity.NotificationMain;

/**
 * Created by bruce on 2015/10/23.
 */
public interface NotificationDao {

	public int add(Notification n);
	
	public int addMain(NotificationMain m);

	public Page<NotificationMain> findAllByPage(PaginationParameters paginationParameters,String wheresql) throws BaseException;

	public Notification findByID(String id);

	public List<Notification> findAll() throws BaseException;

	public int removeByID(String id);

	public int update(Notification ad);

	public NotificationMain findMainByID(String id);

	public int removeMainByID(String id);

	public int updateST(NotificationMain m);

	public List<Notification> getOldStatusList(String toPersonID, Integer notificationType, String fromPersonID);



}
