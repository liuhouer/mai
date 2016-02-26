package com.mai.activity.dao;

import java.util.List;

import com.mai.activity.entity.ActivitytagRef;
import com.mai.framework.exception.BaseException;

/**
 * Created by denghao on 15/10/5.
 */
public interface ActivitytagRefDao {
    /**
     * 根据活动ID获取TAG选择内容
     *
     * @return
     * @throws BaseException
     */
    public List<ActivitytagRef> findActivitytagRefByActID(String actid) throws BaseException;

    /**
     * 批量插入
     * @param atrlist
     * @throws BaseException
     */
    public Integer insertBatch(List<ActivitytagRef> atrlist) throws BaseException;

    /**
     * 根据活动ID删除TAG
     * @param actid
     * @throws BaseException
     */
    public Integer deleteActivitytagRefByActID(String actid) throws BaseException;

}
