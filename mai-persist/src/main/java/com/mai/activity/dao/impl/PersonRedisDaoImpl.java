package com.mai.activity.dao.impl;

import com.mai.activity.dao.PersonRedisDao;
import com.mai.framework.exception.BaseException;
import com.mai.redis.RCache;
import com.mai.user.entity.Person;
import org.apache.log4j.Logger;
import org.codehaus.jackson.type.TypeReference;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.IOException;

/**
 *
 * Created by fengdzh on 2015/10/4.
 */
@Component
public class PersonRedisDaoImpl implements PersonRedisDao {
    @Autowired
    private RCache shardedRedisClient;
    private String KEY_PERSON_ID= "person.personID.";
    private Logger logger=Logger.getLogger(PersonRedisDaoImpl.class);

    /**
     * 通过personID获取person信息
     *
     * @param personID
     * @return
     * @throws BaseException
     */
    @Override
    public Person getPersonByID(String personID) throws BaseException {
        return get(personID);
    }

    /**
     * 把用户加到缓存
     * @param person
     * @return
     */
    public boolean setPerson(final Person person) throws BaseException {
        logger.info("setPeson to Redis");
        updatePerson(person);
        return true;
    }
    /**
     * 通过key获取
     * <br>------------------------------<br>
     * @param personID
     * @return
     */
    private Person get(final String personID) {
        Person p = (Person) shardedRedisClient.hgetAll(KEY_PERSON_ID+personID, new TypeReference<Person>() {
        });
        return p;
    }

    /**
     * 删除
     * @param key
     */
    public void delete(String key) {
       shardedRedisClient.del(key);
    }
    /**
     * 修改个人基本信息
     *
     * @param person
     * @throws BaseException
     */
    @Override
    public Integer updatePerson(Person person) throws BaseException {
        if(null == person){
            return null;
        }
        try {
            shardedRedisClient.hsetAll(KEY_PERSON_ID+person.getPersonID(),person);
        } catch (IOException e) {
            throw new BaseException(e);
        }
        return null;
    }

}
