package com.mai.activity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mai.activity.dao.SchoolDao;
import com.mai.activity.entity.School;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;

/**
 * 学校信息
 * Created by fengdzh on 2015/9/23.
 */
@Component
public class SchoolDaoImpl extends BaseDaoImpl<School, School> implements SchoolDao {
    /**
     * 模糊查找学校
     *
     * @param school
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public List<School> getSchoolList(School school) throws BaseException {
        return this.findByParam("Mapper.School.getSchoolList", school);
    }
}
