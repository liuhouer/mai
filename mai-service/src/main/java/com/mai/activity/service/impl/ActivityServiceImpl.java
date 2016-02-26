package com.mai.activity.service.impl;


import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mai.activity.dao.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.mai.activity.dto.ActivityDTO;
import com.mai.activity.entity.Activity;
import com.mai.activity.entity.ActivityDetail;
import com.mai.activity.entity.ActivityMember;
import com.mai.activity.entity.ActivitytagRef;
import com.mai.activity.entity.Log;
import com.mai.activity.entity.SystemActivityFollow;
import com.mai.activity.entity.Tag;
import com.mai.activity.service.ActivityService;
import com.mai.activity.service.CategoryService;
import com.mai.activity.service.LocationService;
import com.mai.activity.service.SchoolService;
import com.mai.activity.service.TagService;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.framework.utils.DateUtil;
import com.mai.framework.utils.UUIDUtil;
import com.mai.notification.entity.Notification;
import com.mai.notification.service.NotificationService;
import com.mai.user.entity.Person;
import com.mai.user.service.PersonService;
import com.mai.vo.ActivityRunningVO;

/**
 * Created by fengdzh on 2015/9/7.
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
    private static final Logger logger = Logger.getLogger(ActivityServiceImpl.class);
    @Autowired
    private NotificationService noticeService;
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private ActivityDetailDao activityDetailDao;
    @Autowired
    private LogDao logDao;
    @Autowired
    private ActivityMemberDao activityMemberDao;
    @Autowired
    private ActivitytagRefDao activitytagRefDao;
    @Autowired
    private NotificationDao notificationDao;
    @Autowired
    private SystemActivityFollowDao systemActivityFollowDao;
    @Autowired
    private ActivityRedisDao activityRedisDao;

    /**
     * 活动详情
     *
     * @return
     * @throws BaseException
     */
    public String getActivityDetail(String activityDetailID) throws BaseException {
        ActivityDetail actd = this.activityDetailDao.findActivityDetailByID(activityDetailID);
        if(actd!=null){
            return actd.getActivityDetail();
        }else{
            return "";
        }
    }
    /**
     * 加入活动内
     *
     * @param personID
     * @param activityID
     * @param applayNote
     * @return
     * @throws BaseException
     */
    public Integer transJoinInActivity(String personID, String activityID, String applayNote) throws BaseException {
        //TODO 待实现
        return 1;
    }
    /**
     * 我的活动列表
     *
     * @param personID
     * @param paginationParameters
     * @return
     * @throws BaseException
     */
    public Page<Activity> findMyActivityByPage(String personID, PaginationParameters paginationParameters) throws BaseException {
        return activityDao.findActivityByPage(personID, paginationParameters);
    }

    /**
     * 获取活动成员列表
     *
     * @param activityID
     * @param paginationParameters
     * @return
     * @throws BaseException
     */
    public Page<Person> findActivityJoinPersonList(String activityID, PaginationParameters paginationParameters) throws BaseException {
//        List<Person> mockPersonList = personService.getMockPersonList();
//        Page<Person> personPage = new Page<Person>();
//        personPage.setDataList(mockPersonList);
//        return personPage;
            return null;
    }
    /**
     * 默认展示活动
     *
     * @param paginationParameters
     * @return
     * @throws BaseException
     */
    public Page<Activity> findActivityByPage(ActivityDTO activityDTO, PaginationParameters paginationParameters) throws BaseException {
        return null;
    }

    /**
     * 默认展示活动
     *
     * @param startTime
     * @param endTime
     * @param paginationParameters
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public Page<Activity> findActivityByPage(Long startTime, Long endTime, PaginationParameters paginationParameters) throws BaseException {
        return null;
    }

    /**
     * 默认展示活动-- 按照分类
     *
     * @param cetegoryID
     * @param paginationParameters
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public Page<Activity> findActivityByCategory(String cetegoryID, PaginationParameters paginationParameters) throws BaseException {
        return null;
    }

    /**
     * 默认展示活动-- 按照我的学校
     *
     * @param personID
     * @param paginationParameters
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public Page<Activity> findActivityByPersonIDAsSchool(String personID, PaginationParameters paginationParameters) throws BaseException {
        return null;
    }

    /**
     * 学校活动（学校社团组织的活动）
     *
     * @param schoolID
     * @param paginationParameters
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public Page<Activity> findActivityByMySchool(String schoolID, PaginationParameters paginationParameters) throws BaseException {
        return null;
    }

    /**
     * 按照时间排序
     *
     * @param paginationParameters
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public Page<Activity> findActivityOrderByTime(PaginationParameters paginationParameters) throws BaseException {
        return null;
    }

    /**
     * 按照火热程度
     *
     * @param paginationParameters
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public Page<Activity> findActivityOrderByHot(PaginationParameters paginationParameters) throws BaseException {
        return null;
    }

    /**
     * 按照距离：经纬度
     *
     * @param gpsLatitude
     * @param pgslongitude
     * @param paginationParameters
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public Page<Activity> findActivityOrderByGPS(String gpsLatitude, String pgslongitude, PaginationParameters paginationParameters) throws BaseException {
        return null;
    }

    /*
   * @param activityID
   * @return
   * @throws BaseException
   */
    public Activity getActivityByID(String activityID) throws BaseException {
        Activity activity = activityDao.findActivityByID(activityID);
        List<ActivitytagRef> activitytagRefs = this.findActivitytagRefListByActID(activity.getActivityID());
        String tagids = "";
        for(ActivitytagRef activitytagRef : activitytagRefs){
            tagids += activitytagRef.getTagID() + ",";
        }
        if(tagids.endsWith(",")){
            activity.setTagIDs(tagids.substring(0,tagids.length()-1));
        }else{
            activity.setTagIDs(tagids);
        }
        return activity;
    }

    @Override
    public Integer addActivity(Activity activity, ActivityDetail activityDetail,List<ActivitytagRef> activitytagRefs) throws BaseException {
            this.activityDao.insertActivity(activity);
            this.activityDetailDao.insertActivityDetail(activityDetail);
            if(activitytagRefs!=null && activitytagRefs.size()>0){
                this.activitytagRefDao.insertBatch(activitytagRefs);
                String tagids = "";
                for(ActivitytagRef activitytagRef : activitytagRefs){
                    tagids += activitytagRef.getTagID() + ",";
                }
                if(tagids.endsWith(",")){
                    activity.setTagIDs(tagids.substring(0,tagids.length()-1));
                }else{
                    activity.setTagIDs(tagids);
                }
            }
            this.updateRedisActivity(activity);
            return 1;
    }


    @Override
    public Page<Activity> listActivityList(String mywhere, PaginationParameters paginationParameters) throws BaseException {
        return this.activityDao.findActivityList(mywhere, paginationParameters);
    }

    @Override
    public Integer updateActivityStatus(Map params,Log log) throws BaseException {
        if(log!=null){
            this.logDao.insertLog(log);
        }

        Integer return_val = this.activityDao.updateActivityStatus(params);
        Activity activity = this.getActivityByID("" + params.get("id"));
        this.updateRedisActivity(activity);
        return return_val;

    }

    @Override
    public Activity findActivityByID(String id) throws BaseException {
        Activity act = this.activityDao.findActivityByID(id);
        if(act!=null && !StringUtils.isBlank(act.getActivityDetailID())){
            ActivityDetail actd = this.activityDetailDao.findActivityDetailByID(act.getActivityDetailID());
            if(actd!=null){
                act.setActivityDetailContent(actd.getActivityDetail());
            }
        }
        return act;
    }

    @Override
    public Integer updateActivity(Activity activity,ActivityDetail activityDetail,List<ActivitytagRef> activitytagRefs) throws BaseException {
                if(activitytagRefs!=null && activitytagRefs.size()>0){
                    String tagids = "";
                    for(ActivitytagRef activitytagRef : activitytagRefs){
                        tagids += activitytagRef.getTagID() + ",";
                    }
                    if(tagids.endsWith(",")){
                        activity.setTagIDs(tagids.substring(0,tagids.length()-1));
                    }else{
                        activity.setTagIDs(tagids);
                    }
                }
                this.updateRedisActivity(activity);

               Integer return_value = this.activityDao.updateActivity(activity);
               this.activitytagRefDao.deleteActivitytagRefByActID(activity.getActivityID());
               if(activitytagRefs!=null && activitytagRefs.size()>0){
                    this.activitytagRefDao.insertBatch(activitytagRefs);
               }
               this.activityDetailDao.updateActivityDetailByID(activityDetail);

            return return_value;
    }

    @Override
    public ActivityMember findActivitymemberByID(String id) throws BaseException {
                return this.activityMemberDao.findActivitymemberByID(id);
    }

    @Override
    public Integer updateActivitymemberStatus(Map<String,String> params,Log log,String actid,int person_isvalid_not,String UserName) throws BaseException {
        if(log!=null){
            this.logDao.insertLog(log);
        }
            Integer returnval = 0;
            if(person_isvalid_not == 1 || person_isvalid_not == 0){
                returnval = this.activityMemberDao.updateActivitymemberStatus(params);
                    if(returnval>0){
                        Integer joinPersonNum = this.activityMemberDao.getActivitymemberNum(actid);
                        Activity activity = this.getActivityFromRedisByID(actid);
                        activity.setJoinPersonNum(joinPersonNum);
                        this.updateRedisActivity(activity);
                        this.activityDao.updateActivityJoinPersonNum(actid,joinPersonNum);
                    }
            }
                if(person_isvalid_not != 0){
                    Map<String,String> _map = new HashMap<String,String>();
                    _map.put("activityID",actid);
                    _map.put("memberStatus",String.valueOf(ActivityMember.MEMBERSTATUS_NORMAL));
                    List<ActivityMember> activityMembers = activityMemberDao.findActivitymemberList(_map);
                    List<Log> logs = new ArrayList<Log>();
                    Log _log = null;
                    Notification n = null;
                    Activity model = activityDao.findActivityByID(actid);
                    String cont = "" ;
                    if(model.getActivityStatus() == Activity.ACTIVITY_STATUS_OFFLINE){
                        cont = MessageFormat.format(Notification.NOTIFICATION_CONTENT_ACTIVITY_AUDIT, model.getActivityTitle(), Notification.NOTIFICATION_CONTENT_ACTIVITY_OFFLINE);
                    }else{
                        cont = MessageFormat.format(Notification.NOTIFICATION_CONTENT_ACTIVITY_AUDIT, model.getActivityTitle(), Notification.NOTIFICATION_CONTENT_ACTIVITY_RUNNING);
                    }
                    for(ActivityMember activityMember : activityMembers){
                            _log = new Log();
                            _log.setLogID(UUIDUtil.getUUID());
                            _log.setLogAuthor(UserName);
                            _log.setActID(activityMember.getActivitymemberID());
                            _log.setLogtype(Log.TYPE_ACTIVITY_MEMBER);
                            _log.setCreateTime(Calendar.getInstance().getTimeInMillis());
                            _log.setLogDesc(Log.MSG_ADMIN_ACTIVITY_MEMBERSTATUS_ISVALID_NOT);
                            logs.add(_log);
                            //自动审核不通过--------依次保存每个人的消息
							if(model!=null){
                                n = new Notification();
                                n.setTitle(model.getActivityTitle());
								n.setCreateTime(new Date().getTime());
								n.setFromPersonID(model.getActivityTitle());
								n.setFromPersonName(model.getActivityTitle());//活动名称
								n.setHeaderURL(model.getCoverURL());
								n.setIsDeal(0);
								n.setIsRead(0);
								n.setNeedDeal(0);
								n.setNotificationContent(cont);
								n.setNotificationID(UUIDUtil.getUUID());
								n.setNotificationType(Notification.NOTIFICATIONTYPE_TYPE_ACTIVITY_NEWS);
								n.setObjID(model.getActivityID());
								n.setToPersonID(activityMember.getPersonID());
								if(notificationDao.add(n)>0){
									noticeService.push2Single(n);
								}
							}
							 //自动审核不通过--------依次保存每个人的消息
                    }
                    _map = new HashMap<String,String>();
                    _map.put("activityID",actid);
                    _map.put("status",String.valueOf(ActivityMember.MEMBERSTATUS_ISVALID_NOT));
                    this.activityMemberDao.updateActMemberVALIDNOTbyActID(_map);
                    returnval = this.logDao.insertBatch(logs);
                }
        return returnval;
    }

    @Override
    public Page<ActivityMember> findActivitymemberListByActID(Map params,PaginationParameters paginationParameters) throws BaseException {
                return this.activityMemberDao.findActivitymemberList(params, paginationParameters);
    }

    @Override
    public List<ActivitytagRef> findActivitytagRefListByActID(String actid) throws BaseException {
                return this.activitytagRefDao.findActivitytagRefByActID(actid);
    }

    @Override
    public Activity findActivityByActTitleAndStatus(Map params) throws BaseException {
                return this.activityDao.findActivityByActTitleAndStatus(params);
    }

    @Override
    public List<Activity> findActivityListByActTitleAndStatus(Map params) throws BaseException {
                return this.activityDao.findActivityListByActTitleAndStatus(params);
    }

    @Override
    public Page<ActivityRunningVO> findActivityRunningByPage(String mywhere, PaginationParameters paginationParameters) throws BaseException {
                return this.activityDao.findActivityRunningByPage(mywhere, paginationParameters);
    }

    @Override
    public Integer updateRecommendNoByID(Map params) throws BaseException {
        Activity activity = this.getActivityFromRedisByID("" + params.get("activityID"));
        activity.setRecommendNo(Integer.valueOf("" + params.get("recommendNo")));
        this.updateRedisActivity(activity);
        return this.activityDao.updateRecommendNoByID(params);
    }

    @Override
    public SystemActivityFollow insertSystemActivityFollow(SystemActivityFollow systemActivityFollow) throws BaseException {
        Activity activity = this.getActivityFromRedisByID(systemActivityFollow.getActivityID());
        activity.setSystemfollowNum(systemActivityFollow.getActivityFollowNum());
        this.updateRedisActivity(activity);
                return this.systemActivityFollowDao.insertSystemActivityFollow(systemActivityFollow);
    }

    @Override
    public Integer updateSystemActivityFollow(SystemActivityFollow systemActivityFollow) throws BaseException {
        Activity activity = this.getActivityFromRedisByID(systemActivityFollow.getActivityID());
        activity.setSystemfollowNum(systemActivityFollow.getActivityFollowNum());
        this.updateRedisActivity(activity);
                return this.systemActivityFollowDao.updateSystemActivityFollow(systemActivityFollow);
    }

    @Override
    public SystemActivityFollow findSystemActivityFollowByAID(String activityID) throws BaseException {
                return this.systemActivityFollowDao.findSystemActivityFollowByAID(activityID);

    }

    @Override
    public Integer findActivitymemberCountByStatus(Map<String, String> params) throws BaseException {
                return this.activityMemberDao.findActivitymemberCountByStatus(params);
    }

    @Override
    public List<Activity> findActivityListByTime(Map<String, Object> params) throws BaseException {
                return this.activityDao.findActivityListByTime(params);
    }

    @Override
    public Integer updateActMemberVALIDNOTbyActID(Activity activity) throws BaseException {
        Map<String,String>_map = new HashMap<String,String>();
        _map.put("activityID",activity.getActivityID());
        _map.put("memberStatus",String.valueOf(ActivityMember.MEMBERSTATUS_NORMAL));
        List<ActivityMember> activityMembers = activityMemberDao.findActivitymemberList(_map);
        List<Log> logs = new ArrayList<Log>();
        Log _log = null;
        Notification n = null;
        String cont = "" ;
        if(activity.getActivityStatus() == Activity.ACTIVITY_STATUS_OFFLINE){
            cont = MessageFormat.format(Notification.NOTIFICATION_CONTENT_ACTIVITY_AUDIT, activity.getActivityTitle(), Notification.NOTIFICATION_CONTENT_ACTIVITY_OFFLINE);
        }else{
            cont = MessageFormat.format(Notification.NOTIFICATION_CONTENT_ACTIVITY_AUDIT, activity.getActivityTitle(), Notification.NOTIFICATION_CONTENT_ACTIVITY_RUNNING);
        }
        for(ActivityMember activityMember : activityMembers){
            _log = new Log();
            _log.setLogID(UUIDUtil.getUUID());
            _log.setLogAuthor("system");
            _log.setActID(activityMember.getActivitymemberID());
            _log.setLogtype(Log.TYPE_ACTIVITY_MEMBER);
            _log.setCreateTime(Calendar.getInstance().getTimeInMillis());
            _log.setLogDesc(Log.MSG_ADMIN_ACTIVITY_MEMBERSTATUS_ISVALID_NOT);
            logs.add(_log);
            //自动审核不通过--------依次保存每个人的消息
            if(activity!=null){
                n = new Notification();
                n.setTitle(activity.getActivityTitle());
                n.setCreateTime(new Date().getTime());
                n.setFromPersonID(activity.getActivityTitle());
                n.setFromPersonName(activity.getActivityTitle());//活动名称
                n.setHeaderURL(activity.getCoverURL());
                n.setIsDeal(0);
                n.setIsRead(0);
                n.setNeedDeal(0);
                n.setNotificationContent(cont);
                n.setNotificationID(UUIDUtil.getUUID());
                n.setNotificationType(Notification.NOTIFICATIONTYPE_TYPE_ACTIVITY_NEWS);
                n.setObjID(activity.getActivityID());
                n.setToPersonID(activityMember.getPersonID());
                if(notificationDao.add(n)>0){
                    noticeService.push2Single(n);
                }
            }
            //自动审核不通过--------依次保存每个人的消息
        }
        _map = new HashMap<String,String>();
        _map.put("activityID",activity.getActivityID());
        _map.put("status",String.valueOf(ActivityMember.MEMBERSTATUS_ISVALID_NOT));
        this.activityMemberDao.updateActMemberVALIDNOTbyActID(_map);
        return this.logDao.insertBatch(logs);
    }

    @Override
    public Activity getActivityFromRedisByID(String activityID) throws BaseException {
        //先从缓存获取
        Activity activity = activityRedisDao.getActivityByID(activityID);
        if (null == activity) {
            activity = activityDao.findActivityByID(activityID);
            List<ActivitytagRef> activitytagRefs = this.findActivitytagRefListByActID(activity.getActivityID());
            String tagids = "";
            for(ActivitytagRef activitytagRef : activitytagRefs){
                tagids += activitytagRef.getTagID() + ",";
            }
            if(tagids.endsWith(",")){
                activity.setTagIDs(tagids.substring(0,tagids.length()-1));
            }else{
                activity.setTagIDs(tagids);
            }
        }
        return activity;
    }

    /**
     * 跟新活动主缓存
     * @param activity
     * @throws BaseException
     */
    public void updateRedisActivity(Activity activity) throws BaseException {
        Activity oldActivity = getActivityFromRedisByID(activity.getActivityID());
        boolean forseFlush = false;
        if (oldActivity == null) {
            forseFlush = true;
        }
        //
        Long oldStartTime = oldActivity.getStartTime();
        Integer oldFollowNum = oldActivity.getFollowNum();

        String oldLocation = oldActivity.getLocation();
        String oldTags = oldActivity.getTagIDs();
        String oldCategoryID = oldActivity.getCategoryID();
        Integer oldStatus = oldActivity.getActivityStatus();
        String oldSchool = oldActivity.getSchoolID();
        Integer oldRecommendNo = oldActivity.getRecommendNo();
        // 1.更新主体和id集合
        activityRedisDao.setActivity(activity);
        activityRedisDao.insertID2Redis(activity.getActivityID());
        // 2.更新开始时间 3.更新时间排序集合
        if (forseFlush || (oldStartTime != null && !oldStartTime.equals(activity.getStartTime()))
                || (oldStartTime == null && null != activity.getStartTime())
                ) {
            activityRedisDao.deleteStartTimeFromSortSet(activity.getActivityID());
            try {
                activityRedisDao.insertStartTime2RedisSortSet(activity.getActivityID(), activity.getStartTime());
            } catch (IOException e) {
                logger.error(e);
            }
            activityRedisDao.deleteStartTimeOrderFromSet(activity.getActivityID());
            activityRedisDao.insertStartTimeOrder2RedisSet(activity.getActivityID(), activity.getStartTime());
        }

        // 4.更新热度
        if (forseFlush || (oldFollowNum != null && !oldFollowNum.equals(activity.getFollowNum()))
                || (oldFollowNum == null && null != activity.getFollowNum())
                ) {
            activityRedisDao.deleteHotNumOrderFromSet(activity.getActivityID());
            activityRedisDao.insertHotNumOrder2RedisSet(activity.getActivityID(), null == activity.getFollowNum() ? 0 : activity.getFollowNum());
        }
        // 5.更新地区
        if (forseFlush || (oldLocation != null && !oldLocation.equals(activity.getLocation()))
                || (oldLocation == null && null != activity.getLocation())
                ) {
            activityRedisDao.deleteLocationFromSet(activity.getActivityID(), oldLocation);
            activityRedisDao.insertLocation2RedisSet(activity.getActivityID(), activity.getLocation());
        }
        // 6.更新类别
        if (forseFlush || (oldCategoryID != null && !oldCategoryID.equals(activity.getCategoryID()))
                || (oldCategoryID == null && null != activity.getCategoryID())
                ) {
            activityRedisDao.deleteCategoryFromSet(activity.getActivityID(), oldCategoryID);
            activityRedisDao.insertCategory2RedisSet(activity.getActivityID(), activity.getCategoryID());
        }
        // 7.更新状态
        if (forseFlush || (oldStatus != null && !oldStatus.equals(activity.getActivityStatus()))
                || (oldStatus == null && null != activity.getActivityStatus())
                ) {
            activityRedisDao.deleteStatusFromSet(activity.getActivityID(), "" + oldStatus);
            activityRedisDao.insertStatus2RedisSet(activity.getActivityID(), "" + activity.getActivityStatus());
        }
        // 8.更新标签
        if (forseFlush || (oldTags != null && !oldTags.equals(activity.getTagIDs()))
                || (oldTags == null && null != activity.getTagIDs())
                ) {
            if (!StringUtils.isBlank(oldTags)) {
                String[] tags = oldTags.split(",");
                for (int i = 0; i < tags.length; i++) {
                    activityRedisDao.deleteTagFromSet(activity.getActivityID(), tags[i]);
                }
            }
            if (!StringUtils.isBlank(activity.getTagIDs())) {
                String[] tags = activity.getTagIDs().split(",");
                for (int i = 0; i < tags.length; i++) {
                    activityRedisDao.insertCategory2RedisSet(activity.getActivityID(), tags[i]);
                }
            }
        }
        // 9.更新学校
        if (forseFlush || (oldSchool != null && !oldSchool.equals(activity.getSchoolID()))
                || (oldSchool == null && null != activity.getSchoolID())
                ) {
            activityRedisDao.deleteSchoolFromSet(activity.getActivityID(), oldSchool);
            activityRedisDao.insertSchool2RedisSet(activity.getActivityID(), activity.getSchoolID());
        }
        // 10.推荐值集合(条件)
        // 11.推荐值集合（排序）
        if (forseFlush || (oldRecommendNo != null && !oldRecommendNo.equals(activity.getRecommendNo()))
                || (oldRecommendNo == null && null != activity.getRecommendNo())
                ) {
            activityRedisDao.deleteRecommendNoFromSet(activity.getActivityID());
            activityRedisDao.deleteRecommendNoOrderFromSet(activity.getActivityID());
            try {
                activityRedisDao.insertRecommendNo2RedisSet(activity.getActivityID(), activity.getRecommendNo());
                activityRedisDao.insertRecommendNoOrder2RedisSet(activity.getActivityID(), null == activity.getRecommendNo() ? 0 : activity.getRecommendNo());
            } catch (IOException e) {
                logger.error(e);
            }
        }
        // 12.处理默认排序   只有修改开始时间和推荐值才会影响排序  ，移动端不需要此处理
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    activityRedisDao.sortByDefaultOrder();
//                } catch (IOException e) {
//                    logger.error("加载活动默认排序:" + e);
//                }
//            }
//        }).start();
    }
}
