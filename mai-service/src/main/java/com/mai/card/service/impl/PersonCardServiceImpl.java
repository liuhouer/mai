package com.mai.card.service.impl;

import com.mai.activity.dao.PersonCardDao;
import com.mai.activity.dao.PersonCardRedisDao;
import com.mai.app.dto.AppMessageInfoDTO;
import com.mai.card.entity.PersonCard;
import com.mai.card.service.PersonCardService;
import com.mai.framework.exception.BaseException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bruce on 2015-10-19.
 */
@Service
public class PersonCardServiceImpl implements PersonCardService {
    private static final Logger logger = Logger.getLogger(PersonCardServiceImpl.class);

    @Autowired
    private PersonCardDao cardDao;
    @Autowired
    private PersonCardRedisDao cardRedisDao;

    @Override
    public PersonCard findByID(String id) throws BaseException {
        PersonCard personCard = null;
        try {
            personCard = cardRedisDao.getPersonCardByID(id);
        } catch (Exception e) {
            logger.error(e);
        }
        if (personCard == null) {
            personCard = cardDao.findByID(id);
            try {
                cardRedisDao.setPersonCard(personCard);
            } catch (Exception e) {
                logger.error(e);
            }
        }
        return personCard;
    }

    @Override
    public int transIncreaseViewNum(String personCardID) throws BaseException {
        PersonCard ad = new PersonCard();
        ad.setPersonCardID(personCardID);
        int reval = cardDao.transIncreaseViewNum(ad);
        try {
            PersonCard personCard = cardRedisDao.getPersonCardByID(personCardID);
            if (personCard == null) {
                personCard = cardDao.findByID(personCardID);
                cardRedisDao.setPersonCard(personCard);
            } else {
                personCard.setViewNum(personCard.getViewNum() + 1);
                cardRedisDao.setPersonCard(personCard);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return reval;
    }

    @Override
    public int transIncreaseShareNum(String personCardID, String targetType) throws BaseException {
        PersonCard personCard = new PersonCard();
        personCard.setPersonCardID(personCardID);
        if (PersonCard.SHARE_TYPE_IN_WEIXIN.equals(targetType)) {
            personCard.setShareInWxNum(1);
        }
        if (PersonCard.SHARE_TYPE_IN_CIRCLE.equals(targetType)) {
            personCard.setShareInCircleNum(1);
        }
        int reint = cardDao.transIncreaseShareNum(personCard);

        try {
            PersonCard personCard1 = cardRedisDao.getPersonCardByID(personCardID);
            if (personCard1 == null) {
                personCard1 = cardDao.findByID(personCardID);
                cardRedisDao.setPersonCard(personCard1);
            } else {
                if (PersonCard.SHARE_TYPE_IN_WEIXIN.equals(targetType)) {
                    personCard1.setShareInWxNum(personCard1.getShareInWxNum() + 1);
                    cardRedisDao.setPersonCard(personCard1);
                }
                if (PersonCard.SHARE_TYPE_IN_CIRCLE.equals(targetType)) {
                    personCard1.setShareInCircleNum(personCard1.getShareInCircleNum() + 1);
                    cardRedisDao.setPersonCard(personCard1);
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return reint;


    }

    @Override
    public AppMessageInfoDTO getAppMessageInfoDTO(PersonCard ad) {
        AppMessageInfoDTO dto = new AppMessageInfoDTO();
        if (null != ad) {
            dto.setPageID(ad.getPersonCardID());
            dto.setTitle(ad.getCardStarName() + "和" + ad.getPersonName() + "么么哒啦！");
            dto.setDescription(ad.getCardStarName() + "在年底贺岁档探班中自爆藏了十年的秘密...");
            dto.setIsPreview(false);
            dto.setPicID(ad.getFinalPhotoURL());
        }
        return dto;
    }

    @Override
    public PersonCard getScoreByPhoneNum(String phoneNum) throws BaseException {
        return cardDao.getScoreByPhoneNum(phoneNum);
    }

    @Override
    public int transIncreaseLotteryNum(String personCardID) throws BaseException {
        PersonCard ad = new PersonCard();
        ad.setPersonCardID(personCardID);
        return cardDao.transIncreaseLotteryNum(ad);
    }

    @Override
    public PersonCard getScoreByPersonID(String personID) throws BaseException {
        return cardDao.getScoreByPersonID(personID);
    }
}
