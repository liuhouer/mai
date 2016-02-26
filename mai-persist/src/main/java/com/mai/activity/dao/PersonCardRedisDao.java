package com.mai.activity.dao;

import com.mai.card.entity.PersonCard;
import com.mai.framework.exception.BaseException;

/**
 * Created by denghao on 15/12/29.
 */
public interface PersonCardRedisDao {

    /**
     * 通过personCardID获取PersonCard信息
     *
     * @param personCardID
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    public PersonCard getPersonCardByID(String personCardID) throws BaseException;

    /**
     * 把明星卡片加到缓存
     *
     * @param personCard
     * @return
     */
    public boolean setPersonCard(final PersonCard personCard) throws BaseException;


    /**
     * 删除
     *
     * @param key
     */
    public void delete(String key);


    /**
     * 修改卡片信息
     * @param personCard
     * @return
     * @throws BaseException
     */
    public Integer updatePersonCard(PersonCard personCard) throws BaseException ;


    public Integer updatePersonCardWXScore(String personCardID,double score) throws BaseException;
}
