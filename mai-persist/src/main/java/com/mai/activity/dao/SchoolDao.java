package com.mai.activity.dao;

import java.util.List;

import com.mai.activity.entity.School;
import com.mai.framework.exception.BaseException;

/**
 * Created by fengdzh on 2015/9/23.
 */
public interface SchoolDao {
    /**
     * 模糊查找学校
     *
     * @param school
     * @return
     * @throws BaseException
     */
    public List<School> getSchoolList(School school) throws BaseException;
}
