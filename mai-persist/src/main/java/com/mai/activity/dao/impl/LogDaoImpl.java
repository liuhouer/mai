package com.mai.activity.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mai.activity.dao.LogDao;
import com.mai.activity.entity.Log;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;

/**
 * 学校信息
 * Created by fengdzh on 2015/9/23.
 */
@Component
public class LogDaoImpl extends BaseDaoImpl<Log, Log> implements LogDao {
	/**
	 * 模糊查找学校
	 * @param logtype
	 * @param actid
	 * @return
	 * @throws BaseException
	 */
    public List<Log> getLogList(String logtype,String actid) throws BaseException {
    	Map map = new HashMap<String,String>();
    	map.put("logtype", logtype);
    	map.put("actid"  , actid);
        return this.findByParam("Mapper.Log.getLogList", map);
    }

	public int insertLog(Log log) throws BaseException {
		// TODO Auto-generated method stub
		return this.insert("Mapper.Log.insert", log);
	}

	@Override
	public Integer insertBatch(List<Log> logList) throws BaseException {
		return this.insert("Mapper.Log.insertBatch",logList);
	}
}
