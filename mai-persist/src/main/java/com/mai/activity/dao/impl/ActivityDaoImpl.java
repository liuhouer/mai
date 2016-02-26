package com.mai.activity.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mai.vo.ActivityRunningVO;
import org.springframework.stereotype.Repository;

import com.mai.activity.dao.ActivityDao;
import com.mai.activity.entity.Activity;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;

/**
 * 活动dao
 * Created by fengdzh on 2015/9/18.
 */
@Repository
public class ActivityDaoImpl extends BaseDaoImpl implements ActivityDao {

    @Override
    public Page<Activity> findActivityList(String mywhere,PaginationParameters paginationParameters) throws BaseException {
        return this.findByPage("Mapper.Activity.findActivityList",mywhere,paginationParameters);
    }

    @Override
    public Integer updateActivityStatus(Map params) throws BaseException {
            return this.update("Mapper.Activity.updateActivityStatus",params);
    }

    @Override
    public Activity findActivityByID(String id) throws BaseException {
        Activity act = null;
        List<Activity> acts = this.findByParam("Mapper.Activity.findActivityByID",id);
        if(acts!=null && acts.size() > 0){
            act = acts.get(0);
        }
        return act;
    }

    @Override
    public Integer updateActivity(Activity activity) throws BaseException {
        return this.update("Mapper.Activity.updateActivity",activity);
    }

    @Override
    public Activity findActivityByActTitleAndStatus(Map params) throws BaseException {
        Activity act = null;
        List<Activity> acts = this.findByParam("Mapper.Activity.findActivityByActTitleAndStatus",params);
        if(acts!=null && acts.size() > 0){
            act = acts.get(0);
        }
        return act;
    }

    @Override
    public List<Activity> findActivityListByActTitleAndStatus(Map params) throws BaseException {
        return this.findByParam("Mapper.Activity.findActivityByActTitleAndStatus",params);
    }

    public Integer updateActivitySupportInfoByAID(Map params) throws BaseException {
        return this.update("Mapper.Activity.updateActivitySupportInfoByAID",params);
    }

    public Integer updateActivityStatusByAdminID(Map params) throws BaseException {
        return this.update("Mapper.Activity.updateActivityStatusByAdminID",params);
    }

    @Override
    public Integer updateActivityStatusBySocietyID(Map params) throws BaseException {
        return this.update("Mapper.Activity.updateActivityStatusBySocietyID",params);
    }

    @Override
    public List<Activity> findActivityByAdminIDAndValid(String adminID) throws BaseException {
            return this.findByParam("Mapper.Activity.findActivityByAdminIDAndValid",adminID);
    }

    @Override
    public List<Activity> findActivityBySocietyIDAndValid(String societyID) throws BaseException {
        return this.findByParam("Mapper.Activity.findActivityBySocietyIDAndValid",societyID);
    }

    @Override
    public Page<ActivityRunningVO> findActivityRunningByPage(String mywhere, PaginationParameters paginationParameters) throws BaseException {
            return this.findByPage("Mapper.Activity.findActivityRunningByPage",mywhere,paginationParameters);
    }

    @Override
    public Integer updateRecommendNoByID(Map params) throws BaseException {
            return this.update("Mapper.Activity.updateRecommendNoByID",params);
    }

    @Override
    public Page<Activity> findCategoryDetailByPage(String mywhere, PaginationParameters paginationParameters) throws BaseException {
            return this.findByPage("Mapper.Activity.findCategoryDetailByPage",mywhere,paginationParameters);
    }

    @Override
    public Integer updateCategoryNameByCID(Map params) throws BaseException {
            return this.update("Mapper.Activity.updateCategoryNameByCID",params);
    }

    @Override
    public Page<Activity> findTagDetailByPage(String mywhere, PaginationParameters paginationParameters) throws BaseException {
            return this.findByPage("Mapper.Activity.findTagDetailByPage",mywhere,paginationParameters);
    }

    @Override
    public List<Activity> findActivityListByTime(Map<String, Object> params) throws BaseException {
            return this.findByParam("Mapper.Activity.findActivityListByTime",params);
    }

    @Override
    public Integer updateActivityJoinPersonNum(String activityID, Integer joinPersonNum) throws BaseException {
            Activity activity=new Activity();
            activity.setActivityID(activityID);
            activity.setJoinPersonNum(joinPersonNum);
            return this.update("Mapper.Activity.updateActivityJoinPersonNum",activity);
    }

    @Override
    public Integer updateActivitySocietyNameBySocietyID(String societyID,String societyName) throws BaseException {
            Activity activity=new Activity();
            activity.setSocietyID(societyID);
            activity.setSocietyName(societyName);
            return this.update("Mapper.Activity.updateActivitySocietyNameBySocietyID",activity);
    }

    @Override
    public List<Activity> findActivityBySocietyID(String societyID) throws BaseException {
            return this.findByParam("Mapper.Activity.findActivityBySocietyID",societyID);
    }

    /**
     * 新增活动
     *
     * @param activity
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public Activity insertActivity(Activity activity) throws BaseException {
        this.insert("Mapper.Activity.insert", activity);
        return activity;
    }

    /**
     * 分页查找活动
     *
     * @param paginationParameters
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public Page<Activity> findActivityByPage(String personID, PaginationParameters paginationParameters) throws BaseException {
        Map parseMap = new HashMap();
        parseMap.put("personID", personID);
        return this.findByPage("Mapper.Activity.findActivityByPage", parseMap, paginationParameters);
    }
}
