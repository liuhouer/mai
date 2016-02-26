package com.mai.activity.service;


import java.util.List;

import com.mai.activity.entity.Log;
import com.mai.framework.exception.BaseException;

/**
 * Created by Administrator on 2015/9/7.
 */
public interface LogService {

    public List<Log> getLogList(String logtype,String actid) throws BaseException;

    public Log getMockLog() throws BaseException;
    
    public int add(Log log) throws BaseException;
}
