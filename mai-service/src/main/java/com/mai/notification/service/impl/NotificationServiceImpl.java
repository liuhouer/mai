package com.mai.notification.service.impl;

import java.util.*;

import com.mai.activity.dao.*;
import com.mai.activity.entity.ActivityFollow;
import com.mai.device.entity.Device;
import com.mai.framework.utils.*;
import com.mai.push.entity.PushClient;
import com.mai.push.entity.PushMessage;
import com.mai.society.entity.SocietyFollow;
import com.mai.user.entity.Person;
import com.mai.util.CommonUtil;
import com.mai.utils.PushUtil;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.mai.activity.entity.Activity;
import com.mai.activity.entity.ActivityMember;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.notification.entity.Notification;
import com.mai.notification.entity.NotificationMain;
import com.mai.notification.service.NotificationService;
import com.mai.society.entity.Society;
import com.mai.society.entity.SocietyMember;

/**
 * Created by fengdzh on 2015/9/14.
 */
@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {
	private static Logger logger= Logger.getLogger(NotificationServiceImpl.class);
	
	@Autowired
	private NotificationDao notificationDao;
	
	@Autowired
	private ActivityDao activityDao;
	
	@Autowired
	private ActivityMemberDao activityMemberDao;
	
	@Autowired
	private SocietyDao societyDao;
	
	@Autowired
	private SocietyMemberDao societyMemberDao;

	@Autowired
	private PersonDao personDao;

	@Autowired
	private DeviceDao deviceDao;

	@Autowired
	private ActivityFollowDao activityFollowDao;

	@Autowired
	private SocietyFollowDao societyFollowDao;

	private PushClient getPushClient(String personID) throws BaseException {
		Person person = personDao.getPersonByID(personID);
		PushClient pushClient=null;
		List<Device> devices = deviceDao.getDeviceInfoByPersonID(personID);
		if(null!=devices && devices.size()>0 ){
			Device device = devices.get(0);
			if(device.getClientID()==null){
				return null;
			}
			pushClient=new PushClient();
			String deviceType = device.getDeviceType()==null?null:device.getDeviceType();
			Integer integer = null;
			if(!StringUtils.isBlank(deviceType)){
				integer=Integer.valueOf(deviceType);
			}
			pushClient.setClientType(integer);
			pushClient.setClientID(device.getClientID());
			pushClient.setPersonName(person.getName());
		}else{
			logger.error("没找到该人[personID="+personID+"]登记的client信息，将不推送消息");
		}
		return pushClient;
	}

    /**
     * 加入通知
     *
     * @param notification
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public Notification insertNotification(Notification notification) throws BaseException {
        notification.setNotificationID(UUIDUtil.getUUID());
        notification.setCreateTime(DateUtil.currentTimestampToLong());
        return notification;
    }

    /**
     * 我的通知列表
     *
     * @param personID
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public Page<Notification> findNotificationByPage(String personID, PaginationParameters paginationParameters) throws BaseException {
        List<Notification> notificationList = new ArrayList<Notification>();
        for (int i = 0; i < 20; i++) {
            Notification mockNotification = getMockNotification();
            mockNotification.setNotificationID(UUIDUtil.getUUID());
            notificationList.add(mockNotification);
        }
        Page<Notification> page = new Page<Notification>();
        page.setDataList(notificationList);
        return page;
    }

    /**
     * 获取通知信息
     *
     * @return
     * @throws BaseException
     */
    private Notification getMockNotification() throws BaseException {
        Notification notification = new Notification();
        notification.setNotificationID(UUIDUtil.getUUID());
        notification.setCreateTime(DateUtil.currentTimestampToLong());
        notification.setFromPersonID("1");
        notification.setFromPersonName("卖卖");
        notification.setIsRead(Notification.NOTIFICATION_IS_READ_NO);
        notification.setNotificationContent("申请加入：想加入，想加入，想加入");
        notification.setNotificationType(Notification.NOTIFICATION_TYPE_ACTIVITY_APPLAY_ENTER);
        return notification;
    }

	@Override
	public int add(Notification n) {
		// TODO Auto-generated method stub
		return notificationDao.add(n);
	}

	@Override
	public int addMain(NotificationMain m) {
		// TODO Auto-generated method stub
		return notificationDao.addMain(m);
	}

	@Override
	public Page<NotificationMain> findAllByPage(PaginationParameters paginationParameters, String wheresql)
			throws BaseException {
		// TODO Auto-generated method stub
		return notificationDao.findAllByPage(paginationParameters, wheresql);
	}

	@Override
	public void sendNotice(String objID, String objType ,String cur_userid,String cont,String toPersonID) {
		// TODO Auto-generated method stub
		if(StringUtils.isNotEmpty(objType)&&StringUtils.isNotEmpty(objID)){
			try {
				//1.根据type查找  活动/社团主体的person
				if(objType.equals(String.valueOf(Notification.NOTIFICATIONTYPE_TYPE_ACTIVITY_NEWS))){//活动

						Activity model = activityDao.findActivityByID(objID);
						if(model!=null){

							Map map = new HashMap<String, String>();
							map.put("activityID", model.getActivityID());
							map.put("memberStatus",ActivityMember.MEMBERSTATUS_CHECKKED);
							
							List<ActivityMember> list = activityMemberDao.findActivitymemberList(map);
							
							if(StringUtils.isNotEmpty(toPersonID)){
								boolean flag = false ; 
								personDao.getPersonByID(toPersonID);
								for (int i = 0; i < list.size(); i++) {
									if(toPersonID.equals(list.get(i).getPersonID())){//人员里面包含这个人 单独发消息
										flag  = true;
										break;
									}
								}
								if(flag){
									//单独给他的消息
									Notification n = new Notification();
									n.setCreateTime(new Date().getTime());
									if(StringUtils.isNotEmpty(cur_userid)){
										n.setFromPersonID(cur_userid);
									}
									n.setFromPersonName(model.getActivityTitle());//活动名称
									n.setTitle(model.getActivityTitle());//活动名称
									n.setHeaderURL(model.getCoverURL());
									n.setIsDeal(0);
									n.setIsRead(0);
									n.setNeedDeal(0);
									n.setNotificationContent(cont);
									n.setNotificationID(UUIDUtil.getUUID());
									n.setNotificationType(Notification.NOTIFICATIONTYPE_TYPE_ACTIVITY_NEWS);
									n.setObjID(objID);
									n.setToPersonID(toPersonID);
									if(notificationDao.add(n)>0){
										this.push2Single(n);
									}
								}
							}else{
								//给所有人发消息
								for (ActivityMember a :list) {
									//依次保存每个人的消息
									Notification n = new Notification();
										n.setCreateTime(new Date().getTime());
										if(StringUtils.isNotEmpty(cur_userid)){
											n.setFromPersonID(cur_userid);
										}
										n.setFromPersonName(model.getActivityTitle());//活动名称
										n.setTitle(model.getActivityTitle());//活动名称
										n.setHeaderURL(model.getCoverURL());
										n.setIsDeal(0);
										n.setIsRead(0);
										n.setNeedDeal(0);
										n.setNotificationContent(cont);
										n.setNotificationID(UUIDUtil.getUUID());
										n.setNotificationType(Notification.NOTIFICATIONTYPE_TYPE_ACTIVITY_NEWS);
										n.setObjID(objID);
										n.setToPersonID(a.getPersonID());
										if(notificationDao.add(n)>0){
											this.push2Single(n);
										}

								}
							}
							

							
						}
					
				}else if(objType.equals(String.valueOf(Notification.NOTIFICATIONTYPE_TYPE_SOCIETY_NEWS))){//社团
						Society model = societyDao.getSocietyByID(objID);
						if(model!=null){

							Map map = new HashMap<String, String>();
							map.put("societyID", model.getSocietyID());
							map.put("memberStatus",SocietyMember.MEMBERSTATUS_CHECKKED);

							List<SocietyMember> list = societyMemberDao.findSocietymemberList(map);
							
							if(StringUtils.isNotEmpty(toPersonID)){
								boolean flag = false ; 
								personDao.getPersonByID(toPersonID);
								for (int i = 0; i < list.size(); i++) {
									if(toPersonID.equals(list.get(i).getPersonID())){//人员里面包含这个人 单独发消息
										flag  = true;
										break;
									}
								}
								
								//单独发消息
								    Notification n = new Notification();
									n.setCreateTime(new Date().getTime());
									if(StringUtils.isNotEmpty(cur_userid)){
										n.setFromPersonID(cur_userid);
									}
									n.setFromPersonName(model.getSocietyName());//活动名称
									n.setTitle(model.getSocietyName());//活动名称
									n.setHeaderURL(model.getSocietyLOGO());
									n.setIsDeal(0);
									n.setIsRead(0);
									n.setNeedDeal(0);
									n.setNotificationContent(cont);
									n.setNotificationID(UUIDUtil.getUUID());
									n.setNotificationType(Notification.NOTIFICATIONTYPE_TYPE_SOCIETY_NEWS);
									n.setObjID(objID);
									n.setToPersonID(toPersonID);
									if(notificationDao.add(n)>0){
										this.push2Single(n);
									}
							}else{
								for (SocietyMember a :list) {
									//通过的才发
									//依次保存每个人的消息
									Notification n = new Notification();
										n.setCreateTime(new Date().getTime());
										if(StringUtils.isNotEmpty(cur_userid)){
											n.setFromPersonID(cur_userid);
										}
										n.setFromPersonName(model.getSocietyName());//活动名称
										n.setTitle(model.getSocietyName());//活动名称
										n.setHeaderURL(model.getSocietyLOGO());
										n.setIsDeal(0);
										n.setIsRead(0);
										n.setNeedDeal(0);
										n.setNotificationContent(cont);
										n.setNotificationID(UUIDUtil.getUUID());
										n.setNotificationType(Notification.NOTIFICATIONTYPE_TYPE_SOCIETY_NEWS);
										n.setObjID(objID);
										n.setToPersonID(a.getPersonID());
										if(notificationDao.add(n)>0){
											this.push2Single(n);
										}
								}
							}
							
						}
					
				}
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public NotificationMain findMainByID(String id) {
		// TODO Auto-generated method stub
		return notificationDao.findMainByID(id);
	}

	@Override
	public int removeMainByID(String id) {
		// TODO Auto-generated method stub
		return notificationDao.removeMainByID(id);
	}

	@Override
	public int updateST(NotificationMain m) {
		// TODO Auto-generated method stub
		return notificationDao.updateST(m);
	}


	/**
	 * 推送离线消息
	 * @param notification
	 * @throws BaseException
	 */
	public void push2Single(Notification notification)throws BaseException{
		String pushBaseURL= PropertiesReaderUtil.getValue("pushServerConfig.properties", "pushServer.pushBaseURL");
		PushClient pushClient = getPushClient(notification.getToPersonID());
		if(null==pushClient){
			return;
		}
		PushMessage pushMessage=new PushMessage();
		ArrayList<PushClient> pushClients = new ArrayList<PushClient>();
		pushClients .add(pushClient);
		pushMessage.setPushClients(pushClients);
		pushMessage.setPushTitle(notification.getTitle());
		pushMessage.setPushMsg(notification.getNotificationContent());
		pushMessage.setMsgType(notification.getNotificationType());
		pushMessage.setPushMessageID(UUIDUtil.getUUID());

		PushUtil.pushToSingleBatch(pushMessage, pushBaseURL);
//		String addURL = "/push/pushToSingleBatch";
//		String uri = pushBaseURL + addURL;
//		String key = "pushMessageString";
//		String value = JsonHelper.collection2json(pushMessage);
//		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(CommonUtil.setNameValuePair(key, value));
//		String returnbody = CommonUtil.post(uri,params);
//		System.out.println(returnbody);
	}

	@Override
	public void push2App(Notification notification) throws BaseException {
		String pushBaseURL= PropertiesReaderUtil.getValue("pushServerConfig.properties", "pushServer.pushBaseURL");
		PushMessage pushMessage=new PushMessage();
		pushMessage.setPushTitle(notification.getTitle());
		pushMessage.setPushMsg(notification.getNotificationContent());
		pushMessage.setMsgType(0);
		pushMessage.setIsTransmission(PushMessage.TRANSMISSION_NO);
		pushMessage.setPushMessageID(UUIDUtil.getUUID());
		PushUtil.pushToApp(pushMessage, pushBaseURL);
	}

	@Override
	public int updateModel(Notification notification) {
		// TODO Auto-generated method stub
		return notificationDao.update(notification);
	}

	@Override
	public List<Notification> getOldStatusList(String toPersonID, Integer notificationType, String fromPersonID) {
		// TODO Auto-generated method stub
		return notificationDao.getOldStatusList(toPersonID,notificationType,fromPersonID);
	}

	@Override
	public void sendFollowNotice(String objID, String objType, String cur_userid, String cont) throws BaseException{
		if(StringUtils.isNotEmpty(objType)&&StringUtils.isNotEmpty(objID)){
				//1.根据type查找  活动/社团主体的person
				if(objType.equals(String.valueOf(Notification.NOTIFICATIONTYPE_TYPE_ACTIVITY_NEWS))){//活动
					Activity activity = activityDao.findActivityByID(objID);
					if(activity!=null){
						List<ActivityFollow> activityFollowList = activityFollowDao.getActivityFollowbyActivityID(activity.getActivityID());
						for (ActivityFollow activityFollow :activityFollowList) {
							//依次保存每个人的消息
							Notification n = new Notification();
							n.setCreateTime(new Date().getTime());
							if(StringUtils.isNotEmpty(cur_userid)){
								n.setFromPersonID(cur_userid);
							}
							n.setFromPersonName(activity.getActivityTitle());//活动名称
							n.setTitle(activity.getActivityTitle());//活动名称
							n.setHeaderURL(activity.getCoverURL());
							n.setIsDeal(0);
							n.setIsRead(0);
							n.setNeedDeal(0);
							n.setNotificationContent(cont);
							n.setNotificationID(UUIDUtil.getUUID());
							n.setNotificationType(Notification.NOTIFICATIONTYPE_TYPE_ACTIVITY_NEWS);
							n.setObjID(objID);
							n.setToPersonID(activityFollow.getPersonID());
							if(notificationDao.add(n)>0){
								this.push2Single(n);
							}

						}
					}
				}else if(objType.equals(String.valueOf(Notification.NOTIFICATIONTYPE_TYPE_SOCIETY_NEWS))){//社团
					Society society = societyDao.getSocietyByID(objID);
					if(society!=null){
						List<SocietyFollow> societyFollowList = societyFollowDao.listSocietyFollowByPage(society.getSocietyID());
						for (SocietyFollow societyFollow :societyFollowList) {
							//通过的才发
							//依次保存每个人的消息
							Notification n = new Notification();
							n.setCreateTime(new Date().getTime());
							if(StringUtils.isNotEmpty(cur_userid)){
								n.setFromPersonID(cur_userid);
							}
							n.setFromPersonName(society.getSocietyName());//社团名称
							n.setTitle(society.getSocietyName());//社团名称
							n.setHeaderURL(society.getSocietyLOGO());
							n.setIsDeal(0);
							n.setIsRead(0);
							n.setNeedDeal(0);
							n.setNotificationContent(cont);
							n.setNotificationID(UUIDUtil.getUUID());
							n.setNotificationType(Notification.NOTIFICATIONTYPE_TYPE_SOCIETY_NEWS);
							n.setObjID(objID);
							n.setToPersonID(societyFollow.getPersonID());
							if(notificationDao.add(n)>0){
								this.push2Single(n);
							}
						}
					}
				}
		}
	}
}
