package com.mai.activity.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mai.activity.dao.LogDao;
import com.mai.activity.entity.Log;
import com.mai.activity.service.LogService;
import com.mai.framework.exception.BaseException;

/**
 * Created by Administrator on 2015/9/7.
 */
@Service("logService")
public class LogServiceImpl implements LogService {
    @Autowired LogDao logdao;
	


	@Override
	public int add(Log log) throws BaseException {
		// TODO Auto-generated method stub
		return logdao.insertLog(log);
	}

	@Override
	public List<Log> getLogList(String logtype, String actid) throws BaseException {
		// TODO Auto-generated method stub
		List<Log> list = new ArrayList<Log>();
		if(logtype.contains(",")){
			String [] str  = logtype.split(",");
			for (int i = 0; i < str.length; i++) {
				List<Log> list1 = new ArrayList<Log>();
				list1 = logdao.getLogList(str[i], actid);
				for (Log g:list1) {
					list.add(g);
				}
			}
		}else{
			list = logdao.getLogList(logtype, actid);
		}
		return list;
	}

	@Override
	public Log getMockLog() throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}
}
