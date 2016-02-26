package com.mai.activity.dao.impl;

import com.mai.activity.dao.ActivityFollowDao;
import com.mai.activity.entity.ActivityFollow;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fengdzh on 2015/10/8.
 */
@Component
public class ActivityFollowDaoImpl extends BaseDaoImpl<ActivityFollow,ActivityFollow> implements ActivityFollowDao {

    /**
     * 分页
     * @param activityID
     * @param paginationParameters
     * @return
     * @throws BaseException
     */
    public Page<ActivityFollow> getActivityFollowbyActivityID(String activityID,PaginationParameters paginationParameters) throws BaseException {
        ActivityFollow activityFollow=new ActivityFollow();
        activityFollow.setActivityID(activityID);
        return this.findByPage("Mapper.ActivityFollow.getActivityFollowbyActivityID",activityFollow,paginationParameters);
    }

    /**
     * 不分页
     * @param activityID
     * @return
     * @throws BaseException
     */
    public List<ActivityFollow> getActivityFollowbyActivityID(String activityID) throws BaseException {
        ActivityFollow activityFollow=new ActivityFollow();
        activityFollow.setActivityID(activityID);
        return this.findByParam("Mapper.ActivityFollow.getActivityFollowbyActivityID", activityFollow);
    }
}
