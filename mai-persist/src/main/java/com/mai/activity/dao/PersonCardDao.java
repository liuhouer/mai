package com.mai.activity.dao;

import com.mai.card.entity.CardStarPhoto;
import com.mai.card.entity.PersonCard;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;

import java.util.List;

/**
 * Created by bruce on 2015/10/23.
 */
public interface PersonCardDao {

    PersonCard findByID(String id);

    int transIncreaseViewNum(PersonCard personCard) throws BaseException;

    int transIncreaseShareNum(PersonCard personCard) throws BaseException;

    public PersonCard getScoreByPhoneNum(String phoneNum) throws BaseException;

    int transIncreaseLotteryNum(PersonCard personCard) throws BaseException;

    public PersonCard getScoreByPersonID(String personID) throws BaseException;

}
