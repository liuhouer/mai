package com.mai.activity.dao;

import com.mai.framework.exception.BaseException;
import com.mai.user.entity.Person;

/**
 * Created by fengdzh on 2015/10/4.
 */
public interface PersonRedisDao{
        /**
         * 通过personID获取person信息
         *
         * @param personID
         * @return
         * @throws BaseException
         */
        public Person getPersonByID(String personID) throws BaseException;

        /**
         * 把用户加到缓存
         * @param person
         * @return
         */
        public boolean setPerson(final Person person) throws BaseException;


        /**
         * 删除
         * @param key
         */
        public void delete(String key);


        /**
         * 修改个人基本信息
         *
         * @param person
         * @throws BaseException
         */
        public Integer updatePerson(Person person) throws BaseException;

}
