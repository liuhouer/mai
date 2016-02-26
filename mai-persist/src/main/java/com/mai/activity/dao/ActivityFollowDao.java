package com.mai.activity.dao;

import com.mai.activity.entity.ActivityFollow;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;

import java.util.List;

/**
 * 关注
 * Created by fengdzh on 2015/10/8.
 */
public interface ActivityFollowDao {

    /**
     * 分页
     * @param activityID
     * @param paginationParameters
     * @return
     * @throws BaseException
     */
    public Page<ActivityFollow> getActivityFollowbyActivityID(String activityID, PaginationParameters paginationParameters) throws BaseException;

    /**
     * 不分页
     * @param activityID
     * @return
     * @throws BaseException
     */
    public List<ActivityFollow> getActivityFollowbyActivityID(String activityID) throws BaseException;
}
