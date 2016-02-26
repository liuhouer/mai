package com.mai.activity.dao;

import java.util.List;

import com.mai.activity.entity.Log;
import com.mai.framework.exception.BaseException;

/**
 * Created by fengdzh on 2015/9/23.
 */
public interface LogDao {
    /**
     * 模糊查找学校
     * @param logtype
     * @param actid
     * @return
     * @throws BaseException
     */
    public List<Log> getLogList(String logtype,String actid) throws BaseException;
    
    
    /**
     * 新增活动
     *
     * @param log
     * @return
     * @throws BaseException
     */
    public int insertLog(Log log) throws BaseException;

    /**
     * 批量插入
     * @param logList
     * @throws BaseException
     */
    public Integer insertBatch(List<Log> logList) throws BaseException;

}
