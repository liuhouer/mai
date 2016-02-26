package com.mai.activity.dao;

import com.mai.activity.entity.SystemActivityFollow;
import com.mai.framework.exception.BaseException;

/**
 * Created by denghao on 15/10/24.
 */
public interface SystemActivityFollowDao {
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
}
