package com.mai.card.service;

import com.mai.app.dto.AppMessageInfoDTO;
import com.mai.card.entity.CardStarPhoto;
import com.mai.card.entity.PersonCard;
import com.mai.framework.exception.BaseException;

/**
 * PersonCardService
 *
 * @author Yao Jinwei
 * @date 2015/12/24
 */
public interface PersonCardService {

    public PersonCard findByID(String id) throws BaseException;

    int transIncreaseViewNum(String personCardID) throws BaseException;

    int transIncreaseShareNum(String personCardID, String targetType) throws BaseException;

    AppMessageInfoDTO getAppMessageInfoDTO(PersonCard ad);

    public PersonCard getScoreByPhoneNum(String phoneNum) throws BaseException;

    int transIncreaseLotteryNum(String personCardID) throws BaseException;

    public PersonCard getScoreByPersonID(String personID) throws BaseException;
}
