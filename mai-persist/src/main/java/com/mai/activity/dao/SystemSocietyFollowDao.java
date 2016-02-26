package com.mai.activity.dao;

import com.mai.framework.exception.BaseException;
import com.mai.society.entity.SystemSocietyFollow;

/**
 * Created by denghao on 15/10/22.
 */
public interface SystemSocietyFollowDao {
    /**
     * 新增社团关注更新值
     *
     * @param systemSocietyFollow
     * @return
     * @throws BaseException
     */
    public SystemSocietyFollow insertSystemSocietyFollow(SystemSocietyFollow systemSocietyFollow) throws BaseException;

    /**
     * 修改社团关注更新值
     *
     * @param systemSocietyFollow
     * @return
     * @throws BaseException
     */
    public Integer updateSystemSocietyFollow(SystemSocietyFollow systemSocietyFollow) throws BaseException;

    /**
     * 查找社团关注更新值
     *
     * @param societyID
     * @return
     * @throws BaseException
     */
    public SystemSocietyFollow findSystemSocietyFollowBySID(String societyID) throws BaseException;
}
