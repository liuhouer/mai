package com.mai.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.mai.activity.dao.PersonDao;
import com.mai.activity.dao.PersonRedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mai.framework.exception.BaseException;
import com.mai.framework.utils.DateUtil;
import com.mai.framework.utils.UUIDUtil;
import com.mai.user.dto.UserDTO;
import com.mai.user.entity.Person;
import com.mai.user.entity.User;
import com.mai.user.service.PersonService;
import com.mai.user.service.UserService;

/**
 * Created by Administrator on 2015/9/7.
 */
@Service("personService")
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRedisDao personRedisDao;

    @Autowired
    private PersonDao personDao;

//    @Override
//    public Person updateRedisPerson(Person person) throws BaseException {
//            personRedisDao.updatePerson(person);
//            return person;
//    }

    @Override
    public Person getRedisPersonByID(String personID) throws BaseException {
            Person person = personRedisDao.getPersonByID(personID);
            if(person == null){
                person = personDao.getPersonByID(personID);
            }
            return person;
    }

    @Override
    public Integer updatePerson(Person person) throws BaseException {
            Integer reint = personDao.updatePerson(person);
            personRedisDao.updatePerson(person);
            return reint;
    }

    @Override
    public Person getPersonByID(String personID) throws BaseException {
        return personDao.getPersonByID(personID);
    }

    @Override
    public Person getPersonByUID(String userID) throws BaseException {
            return personDao.getPersonByUID(userID);
    }
}
