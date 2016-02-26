package com.mai.activity.service;


import com.mai.activity.entity.School;
import com.mai.framework.exception.BaseException;

import java.util.List;

/**
 * Created by Administrator on 2015/9/7.
 */
public interface SchoolService {
    public List<School> getSchoolList(String schoolName) throws BaseException;

	/**
	 * 判断学校名是否有效
	 * @param schoolName
	 * @return
	 */
	public boolean isExist(String schoolName);

}
