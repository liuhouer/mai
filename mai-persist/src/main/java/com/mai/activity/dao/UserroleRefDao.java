package com.mai.activity.dao;

import java.util.List;

import com.mai.framework.exception.BaseException;
import com.mai.user.entity.UserroleRef;

/**
 * Created by denghao on 15/10/6.
 */
public interface UserroleRefDao {

    /**
     * 根据userroleRef中的userID，roleID组装查询条件，默认查询全部
     *
     * @return
     * @throws BaseException
     */
    public List<UserroleRef> findUserroleRef(UserroleRef userroleRef) throws BaseException;

    /**
     * 批量插入
     * @param urrlist
     * @throws BaseException
     */
    public Integer insertBatch(List<UserroleRef> urrlist) throws BaseException;

    /**
     * 根据用户ID删除权限
     * @param userID
     * @throws BaseException
     */
    public Integer deleteUserroleRefByUID(String userID) throws BaseException;

    public UserroleRef insertUserroleRef(UserroleRef userroleRef) throws BaseException;

}
