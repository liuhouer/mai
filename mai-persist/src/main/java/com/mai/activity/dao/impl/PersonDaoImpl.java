package com.mai.activity.dao.impl;

import com.mai.activity.dao.PersonDao;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import com.mai.user.entity.Person;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by denghao on 15/10/27.
 */
@Component
public class PersonDaoImpl extends BaseDaoImpl<Person,Person> implements PersonDao {
    @Override
    public Person getPersonByID(String personID) throws BaseException {
            return this.getOne("Mapper.Person.getPersonByID",personID);
    }

    @Override
    public Integer updatePerson(Person person) throws BaseException {
            return this.update("Mapper.Person.updatePerson",person);
    }

    @Override
    public Person getPersonByUID(String userID) throws BaseException {
            return this.getOne("Mapper.Person.getPersonByUID",userID);
    }
}
