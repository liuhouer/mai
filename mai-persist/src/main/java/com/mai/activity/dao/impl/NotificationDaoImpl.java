package com.mai.activity.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mai.activity.dao.NotificationDao;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.notification.entity.Notification;
import com.mai.notification.entity.NotificationMain;

/**
 * Created by bruce on 2015/10/23.
 */
@Component
public class NotificationDaoImpl extends BaseDaoImpl implements NotificationDao {
	

	@Override
	public int add(Notification n) {
		// TODO Auto-generated method stub
		int a  = 0 ; 
		try {
			a = this.insert("Mapper.Notification.insert",n);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public int addMain(NotificationMain m) {
		// TODO Auto-generated method stub
		
		int a  = 0 ; 
		try {
			a = this.insert("Mapper.NotificationMain.insert",m);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	
	/*
	 * 
	 * 
                return this.findByParam("Mapper.Permisson.findPermissonListByRID",roleID);

	 * 
	 * */

	@Override
	public Page<NotificationMain> findAllByPage(PaginationParameters paginationParameters, String wheresql)
			throws BaseException {
		// TODO Auto-generated method stub
		return this.findByPage("Mapper.NotificationMain.findAllByPage", wheresql, paginationParameters);
	}

	@Override
	public Notification findByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notification> findAll() throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int removeByID(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Notification ad) {
		// TODO Auto-generated method stub
		int a  = 0 ; 
		try {
			a = this.update("Mapper.Notification.updateModel",ad);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public NotificationMain findMainByID(String id) {
		// TODO Auto-generated method stub
		NotificationMain m = null;
		try {
			List<NotificationMain> list = this.findByParam("Mapper.NotificationMain.findMainById",id);
			if(list!=null&&list.size()>0){
				m = list.get(0);
			}
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return m;
	}

	@Override
	public int removeMainByID(String id) {
		// TODO Auto-generated method stub
		int a =  0 ;
		try {
			a = this.update("Mapper.NotificationMain.removeMainById", id);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public int updateST(NotificationMain m) {
		// TODO Auto-generated method stub
		int a =  0 ;
		try {
			Map map  = new HashMap<String,String>();
			map.put("status", m.getStatus());
			map.put("id", m.getNotificationMainID());
			a = this.update("Mapper.NotificationMain.updateSTById", map);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public List<Notification> getOldStatusList(String toPersonID, Integer notificationType, String fromPersonID) {
		// TODO Auto-generated method stub
		List<Notification> list = null;
		try {
			Map map = new HashMap<String,String>();
			map.put("toPersonID", toPersonID);
			map.put("notificationType",notificationType);
			map.put("fromPersonID", fromPersonID);
			
			list = this.findByParam("Mapper.Notification.getOldStatusList",map);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return list;
	}






}
