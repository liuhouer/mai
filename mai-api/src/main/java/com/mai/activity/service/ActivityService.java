package com.mai.activity.service;


import com.mai.activity.dto.ActivityDTO;
import com.mai.activity.entity.*;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.user.entity.Person;
import com.mai.vo.ActivityRunningVO;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/7.
 */
public interface ActivityService {

    /**
     * 活动详情
     *
     * @return
     * @throws BaseException
     */
    public String getActivityDetail(String activityID) throws BaseException;

    /**
     * 加入活动内
     *
     * @param personID
     * @param activityID
     * @param applayNote
     * @return
     * @throws BaseException
     */
    public Integer transJoinInActivity(String personID, String activityID, String applayNote) throws BaseException;

    /**
     * 我的活动列表
     *
     * @param personID
     * @param paginationParameters
     * @return
     * @throws BaseException
     */
    public Page<Activity> findMyActivityByPage(String personID, PaginationParameters paginationParameters) throws BaseException;

    /**
     * 获取活动成员列表
     *
     * @param activityID
     * @param paginationParameters
     * @return
     * @throws BaseException
     */
    public Page<Person> findActivityJoinPersonList(String activityID, PaginationParameters paginationParameters) throws BaseException;

    /**
     * 默认展示活动
     *
     * @param paginationParameters
     * @return
     * @throws BaseException
     */
    public Page<Activity> findActivityByPage(ActivityDTO activityDTO, PaginationParameters paginationParameters) throws BaseException;

    /**
     * 默认展示活动
     *
     * @param startTime
     * @param endTime
     * @param paginationParameters
     * @return
     * @throws BaseException
     */
    public Page<Activity> findActivityByPage(Long startTime, Long endTime, PaginationParameters paginationParameters) throws BaseException;

    /**
     * 默认展示活动-- 按照分类
     *
     * @param paginationParameters
     * @return
     * @throws BaseException
     */
    public Page<Activity> findActivityByCategory(String cetegoryID, PaginationParameters paginationParameters) throws BaseException;

    /**
     * 默认展示活动-- 按照我的学校
     *
     * @param paginationParameters
     * @return
     * @throws BaseException
     */
    public Page<Activity> findActivityByPersonIDAsSchool(String personID, PaginationParameters paginationParameters) throws BaseException;

    /**
     * 学校活动（学校社团组织的活动）
     *
     * @param schoolID
     * @param paginationParameters
     * @return
     * @throws BaseException
     */
    public Page<Activity> findActivityByMySchool(String schoolID, PaginationParameters paginationParameters) throws BaseException;

    /**
     * 按照时间排序
     *
     * @param paginationParameters
     * @return
     * @throws BaseException
     */
    public Page<Activity> findActivityOrderByTime(PaginationParameters paginationParameters) throws BaseException;

    /**
     * 按照火热程度
     *
     * @param paginationParameters
     * @return
     * @throws BaseException
     */
    public Page<Activity> findActivityOrderByHot(PaginationParameters paginationParameters) throws BaseException;

    /**
     * 按照距离：经纬度
     *
     * @param gpsLatitude
     * @param pgslongitude
     * @param paginationParameters
     * @return
     * @throws BaseException
     */
    public Page<Activity> findActivityOrderByGPS(String gpsLatitude, String pgslongitude, PaginationParameters paginationParameters) throws BaseException;

    /**
     * 根据活动ID获取活动详情
     *
     * @param activityID
     * @return
     * @throws BaseException
     */
    public Activity getActivityByID(String activityID) throws BaseException;

    /**
     * 添加活动
     *
     * @param activity
     * @return
     * @throws BaseException
     */
    public Integer addActivity(Activity activity, ActivityDetail activityDetail,List<ActivitytagRef> activitytagRefs) throws BaseException;


    /**
     * 查询活动列表
     *
     * @param mywhere
     * @return
     * @throws BaseException
     */
    public Page<Activity> listActivityList(String mywhere, PaginationParameters paginationParameters) throws BaseException;

    /**
     * 修改状态位
     *
     * @param params
     * @return
     * @throws BaseException
     */
    public Integer updateActivityStatus(Map params,Log log) throws BaseException;

    /**
     * 获取活动信息
     *
     * @param id
     * @return
     * @throws BaseException
     */
    public Activity findActivityByID(String id) throws BaseException;

    /**
     * 更新活动信息
     *
     * @param activity
     * @return
     * @throws BaseException
     */
    public Integer updateActivity(Activity activity,ActivityDetail activityDetail,List<ActivitytagRef> activitytagRefs) throws BaseException;

    public ActivityMember findActivitymemberByID(String id) throws BaseException;

    public Integer updateActivitymemberStatus(Map<String,String> params,Log log,String actid,int person_isvalid_not,String UserName) throws BaseException;

    public Page<ActivityMember> findActivitymemberListByActID(Map params, PaginationParameters paginationParameters) throws BaseException;

    public List<ActivitytagRef> findActivitytagRefListByActID(String actid) throws BaseException;

    public Activity findActivityByActTitleAndStatus(Map params) throws BaseException;

    public List<Activity> findActivityListByActTitleAndStatus(Map params) throws BaseException;

    /**
     * 获取活动运营列表
     *
     * @param mywhere
     * @return
     * @throws BaseException
     */
    public Page<ActivityRunningVO> findActivityRunningByPage(String mywhere,PaginationParameters paginationParameters) throws BaseException;

    /**
     * 更新推荐位
     * @param params
     * @return
     * @throws BaseException
     */
    public Integer updateRecommendNoByID(Map params) throws BaseException;

    /**
     * 新增活动关注更新值
     *
     * @param systemActivityFollow
     * @return
     * @throws BaseException
     */
    public SystemActivityFollow insertSystemActivityFollow(SystemActivityFollow systemActivityFollow) throws BaseException;

    /**
     * 修改活动关注更新值
     *
     * @param systemActivityFollow
     * @return
     * @throws BaseException
     */
    public Integer updateSystemActivityFollow(SystemActivityFollow systemActivityFollow) throws BaseException;

    /**
     * 查找活动关注更新值
     *
     * @param activityID
     * @return
     * @throws BaseException
     */
    public SystemActivityFollow findSystemActivityFollowByAID(String activityID) throws BaseException;


    public Integer findActivitymemberCountByStatus(Map<String,String> params) throws BaseException;

    /**
     * 根据时间段，获取开始时间符合要求的活动
     * @param params
     * @return
     * @throws BaseException
     */
    public List<Activity> findActivityListByTime(Map<String,Object> params) throws BaseException;

    public Integer updateActMemberVALIDNOTbyActID(Activity activity) throws BaseException;

    public Activity getActivityFromRedisByID(String activityID) throws BaseException;

    public void updateRedisActivity(Activity activity) throws BaseException;
}