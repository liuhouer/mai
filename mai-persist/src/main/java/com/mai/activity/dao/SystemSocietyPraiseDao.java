package com.mai.activity.dao;

import com.mai.framework.exception.BaseException;
import com.mai.society.entity.SystemSocietyPraise;

/**
 * Created by denghao on 15/10/22.
 */
public interface SystemSocietyPraiseDao {
    /**
     * 新增社团点赞更新值
     *
     * @param systemSocietyPraise
     * @return
     * @throws BaseException
     */
    public SystemSocietyPraise insertSystemSocietyPraise(SystemSocietyPraise systemSocietyPraise) throws BaseException;

    /**
     * 修改社团点赞更新值
     *
     * @param systemSocietyPraise
     * @return
     * @throws BaseException
     */
    public Integer updateSystemSocietyPraise(SystemSocietyPraise systemSocietyPraise) throws BaseException;

    /**
     * 查找社团点赞更新值
     *
     * @param societyID
     * @return
     * @throws BaseException
     */
    public SystemSocietyPraise findSystemSocietyPraiseBySID(String societyID) throws BaseException;
}
