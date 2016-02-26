package com.mai.activity.dao.impl;

import com.mai.activity.dao.CardStarPhotoDao;
import com.mai.activity.dao.PersonCardDao;
import com.mai.card.entity.CardStarPhoto;
import com.mai.card.entity.PersonCard;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by bruce on 2015/12/21.
 */
@Component
public class PersonCardDaoImpl extends BaseDaoImpl<PersonCard, PersonCard> implements PersonCardDao {

    @Override
    public PersonCard findByID(String id) {
        // TODO Auto-generated method stub
        PersonCard a = null;
        try {
            List<PersonCard> list = this.findByParam("Mapper.PersonCard.findByID",id);
            if(list!=null && list.size()>0){
                a = list.get(0);
            }
        } catch (BaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return a;
    }

    @Override
    public int transIncreaseViewNum(PersonCard ad) throws BaseException {
        return this.update("Mapper.PersonCard.transIncreaseViewNum",ad);
    }

    @Override
    public int transIncreaseShareNum(PersonCard ad) throws BaseException {
        return this.update("Mapper.PersonCard.transIncreaseShareNum",ad);
    }

    @Override
    public PersonCard getScoreByPhoneNum(String phoneNum) throws BaseException {
         return this.getOne("Mapper.PersonCard.getScoreByPhoneNum",phoneNum);
    }

    @Override
    public int transIncreaseLotteryNum(PersonCard personCard) throws BaseException {
        return this.update("Mapper.PersonCard.transIncreaseLotteryNum",personCard);
    }

    @Override
    public PersonCard getScoreByPersonID(String personID) throws BaseException {
        return this.getOne("Mapper.PersonCard.getScoreByPersonID",personID);
    }


}
