package com.mai.activity.dao.impl;

import com.mai.activity.dao.PersonCardRedisDao;
import com.mai.card.entity.PersonCard;
import com.mai.framework.exception.BaseException;
import com.mai.redis.RCache;
import org.apache.log4j.Logger;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by denghao on 15/12/29.
 */
@Component
public class PersonCardRedisDaoImpl implements PersonCardRedisDao {
    @Autowired
    private RCache shardedRedisClient;
    private String KEY_PERSONCARD_ID= "personCard.personCardID.";

    private String PERSONCARD_WX_RANK= "personCard.wx.rank";
    private Logger logger=Logger.getLogger(PersonCardRedisDaoImpl.class);

    @Override
    public PersonCard getPersonCardByID(String personCardID) throws BaseException {
        return get(personCardID);
    }

    @Override
    public boolean setPersonCard(PersonCard personCard) throws BaseException {
        logger.info("setSociety to Redis");
        updatePersonCard(personCard);
        return true;
    }

    /**
     * 通过key获取
     * <br>------------------------------<br>
     * @param personCardID
     * @return
     */
    private PersonCard get(final String personCardID) {
        PersonCard p = (PersonCard) shardedRedisClient.hgetAll(KEY_PERSONCARD_ID+personCardID, new TypeReference<PersonCard>(){});
        return p;
    }

    @Override
    public void delete(String key) {
        shardedRedisClient.del(key);
    }

    @Override
    public Integer updatePersonCard(PersonCard personCard) throws BaseException {
        try {
            shardedRedisClient.hsetAll(KEY_PERSONCARD_ID+personCard.getPersonCardID(),personCard);
        } catch (IOException e) {
            throw new BaseException(e);
        }
        return null;
    }

    @Override
    public Integer updatePersonCardWXScore(String personCardID, double score) throws BaseException {
        try {
            shardedRedisClient.zadd(PERSONCARD_WX_RANK, score, personCardID);
        } catch (IOException e) {
            throw new BaseException(e);
        }
        return null;
    }
}
