package com.mai.activity.dao;

import java.util.List;
import java.util.Map;

import com.mai.activity.entity.Activity;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.vo.ActivityRunningVO;

/**
 * 活动
 * Created by fengdzh on 2015/9/18.
 */

public interface ActivityDao {
    /**
     * 新增活动
     *
     * @param activity
     * @return
     * @throws BaseException
     */
    public Activity insertActivity(Activity activity) throws BaseException;

    /**
     * 分页查找活动
     *
     * @param paginationParameters
     * @return
     * @throws BaseException
     */
    public Page<Activity> findActivityByPage(String personID, PaginationParameters paginationParameters) throws BaseException;


    /**
     * 查找活动所有
     *
     * @param mywhere
     * @return
     * @throws BaseException
     */
    public Page<Activity> findActivityList(String mywhere,PaginationParameters paginationParameters) throws BaseException;

    /**
     * 修改状态位
     *
     * @param params
     * @return
     * @throws BaseException
     */
    public Integer updateActivityStatus(Map params) throws BaseException;

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
    public Integer updateActivity(Activity activity) throws BaseException;

    public Activity findActivityByActTitleAndStatus(Map params) throws BaseException;

    public List<Activity> findActivityListByActTitleAndStatus(Map params) throws BaseException;

    public Integer updateActivitySupportInfoByAID(Map params) throws BaseException;

    /**
     * 根据社长PERSONID批量修改状态
     *
     * @param params
     * @return
     * @throws BaseException
     */
    public Integer updateActivityStatusByAdminID(Map params) throws BaseException;

    /**
     * 根据社团ID批量修改状态
     *
     * @param params
     * @return
     * @throws BaseException
     */
    public Integer updateActivityStatusBySocietyID(Map params) throws BaseException;

    /**
     * 获取当前adminID的所有未下架活动
     * @param adminID
     * @return
     * @throws BaseException
     */
    public List<Activity> findActivityByAdminIDAndValid(String adminID) throws BaseException;


    /**
     * 获取当前societyID的所有未下架活动
     * @param societyID
     * @return
     * @throws BaseException
     */
    public List<Activity> findActivityBySocietyIDAndValid(String societyID) throws BaseException;


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
     * 获取活动类别信息列表
     *
     * @param mywhere
     * @return
     * @throws BaseException
     */
    public Page<Activity> findCategoryDetailByPage(String mywhere,PaginationParameters paginationParameters) throws BaseException;


    /**
     * 更新类型名称
     * @param params
     * @return
     * @throws BaseException
     */
    public Integer updateCategoryNameByCID(Map params) throws BaseException;

    /**
     * 获取社团标签信息列表
     *
     * @param mywhere
     * @return
     * @throws BaseException
     */
    public Page<Activity> findTagDetailByPage(String mywhere,PaginationParameters paginationParameters) throws BaseException;


    /**
     * 根据时间段，获取开始时间符合要求的活动
     * @param params
     * @return
     * @throws BaseException
     */
    public List<Activity> findActivityListByTime(Map<String,Object> params) throws BaseException;

    /**
     * 更新加入人数
     * @param activityID
     * @param joinPersonNum
     * @return
     * @throws BaseException
     */
    public Integer updateActivityJoinPersonNum(String activityID,Integer joinPersonNum)throws BaseException;

    /**
     * 更新活动名称用
     * @param params
     * @return
     * @throws BaseException
     */
    public Integer updateActivitySocietyNameBySocietyID(String societyID,String societyName) throws BaseException;

    /**
     * 获取当前societyID的所有活动
     * @param societyID
     * @return
     * @throws BaseException
     */
    public List<Activity> findActivityBySocietyID(String societyID) throws BaseException;

}
