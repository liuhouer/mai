package com.mai.user.service;


import com.mai.framework.exception.BaseException;
import com.mai.user.dto.UserDTO;
import com.mai.user.entity.Person;

import java.util.List;

/**
 * Created by Administrator on 2015/9/7.
 */
public interface PersonService {
    /**
     * 修改前端Person缓存
     *
     * @param person
     * @return
     * @throws BaseException
     */
//    public Person updateRedisPerson(Person person) throws BaseException;

    /**
     * 通过ID查找Person
     *
     * @param personID
     * @return
     * @throws BaseException
     */
    public Person getRedisPersonByID(String personID) throws BaseException;

    /**
     * 更新人员信息不包括创建时间和最后一次修改时间
     *
     * @param person
     * @return
     * @throws BaseException
     */
    public Integer updatePerson(Person person) throws BaseException;

    /**
     * 通过ID查找Person
     *
     * @param personID
     * @return
     * @throws BaseException
     */
    public Person getPersonByID(String personID) throws BaseException;

    public Person getPersonByUID(String userID) throws BaseException;


}
