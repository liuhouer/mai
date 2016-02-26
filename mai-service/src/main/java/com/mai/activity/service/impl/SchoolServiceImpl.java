package com.mai.activity.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mai.activity.dao.SchoolDao;
import com.mai.activity.entity.School;
import com.mai.activity.service.SchoolService;
import com.mai.framework.exception.BaseException;

/**
 * Created by Administrator on 2015/9/7.
 */
@Service("schoolService")
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolDao schoolDao;
    @Override
    public List<School> getSchoolList(String schoolName) throws BaseException {
        School school = new School();
        school.setSchoolName(schoolName);
        return schoolDao.getSchoolList(school);
    }
	@Override
	public boolean isExist(String schoolName) {
		// TODO Auto-generated method stub
		 School school = new School();
	        school.setSchoolName(schoolName);
	        boolean b = false; 
	        try {
	        	List<School> list  = schoolDao.getSchoolList(school);
	        	if(list!=null&&list.size()>=1){
	        		if(list.get(0)!=null){
	        			b = true;
	        		}
	        	}
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return b;
	}
}
