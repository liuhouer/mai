package com.mai.activity.dao;

import com.mai.framework.exception.BaseException;
import com.mai.user.entity.Person;

/**
 * Created by denghao on 15/10/27.
 */
public interface PersonDao {


    public Person getPersonByID(String personID) throws BaseException;

    /**
     * 更新人员信息不包括创建时间和最后一次修改时间
     *
     * @param person
     * @return
     * @throws BaseException
     */
    public Integer updatePerson(Person person) throws BaseException;

    public Person getPersonByUID(String userID) throws BaseException;
}
