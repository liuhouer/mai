package com.mai.activity.dao;

import com.mai.framework.exception.BaseException;
import com.mai.society.entity.Society;
import redis.clients.jedis.Tuple;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Created by denghao on 15/11/2.
 */
public interface SocietyRedisDao {
    /**
     * 通过societyID获取society信息
     *
     * @param societyID
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    public Society getSocietyByID(String societyID) throws BaseException;

    /**
     * 把社团加到缓存
     *
     * @param society
     * @return
     */
    public boolean setSociety(final Society society) throws BaseException;


    /**
     * 删除
     *
     * @param key
     */
    public void delete(String key);


    /**
     * 修改社团信息
     * @param society
     * @return
     * @throws BaseException
     */
    public Integer updateSociety(Society society) throws BaseException ;

    Long insertStatus2RedisSet( String societyID,String status,double score)throws BaseException;

    Long deleteStatusFromSet(String societyID, String status);

    Long insertID2Redis(String societyID,double score) throws BaseException;

    public Integer updateSocietyScore(String societyID,double score) throws BaseException;

    String insertRecommendNo2RedisSet(String societyID, Integer recommendNo) throws BaseException;

    Long deleteRecommendNoFromSet(String societyID);

    Long deleteLocationFromSet(String societyID, String location);

    Long deleteCategoryFromSet(String societyID, String category);

    Long insertLocation2RedisSet(String societyID, String location,double score) throws BaseException;

    Long insertCategory2RedisSet(String societyID,String category,double score) throws BaseException;

    Long insertSchool2RedisSet(String societyID, String schoolID,double score) throws BaseException;

    Long deleteSchoolFromSet(String societyID,String schoolID);

    Long insertTag2RedisSet(String societyID, String tag, double score) throws BaseException;

    Long deleteTagFromSet(String societyID, String tag);
}
