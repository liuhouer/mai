package com.mai.activity.dao;

import com.mai.framework.exception.BaseException;
import com.mai.activity.entity.Activity;
import redis.clients.jedis.Tuple;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by fengdzh on 2015/10/4.
 */
public interface ActivityRedisDao {
    /**
     * 通过activityID获取activity信息
     *
     * @param activityID
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    public Activity getActivityByID(String activityID) throws BaseException;

    /**
     * 把活动加到缓存
     *
     * @param activity
     * @return
     */
    public boolean setActivity(final Activity activity) throws BaseException;


    /**
     * 删除
     *
     * @param key
     */
    public void delete(String key);


    /**
     * 修改活动基本信息
     *
     * @param activity
     * @throws com.mai.framework.exception.BaseException
     */
    public Integer updateActivity(Activity activity) throws BaseException;

    //    public void flushIDStore(){
//        Set<String>  keys = shardedRedisClient.
//        shardedRedisClient.del(new HashSet<String>());//.set(ACTIVITY_ID_LIST +)
//    }

    void clearActivityRedis();

    Long insertID2Redis(String activityID) throws BaseException;

    Object insertStartTimeOrder2RedisSet(String activityID, Long time);
    Object insertStartTime2RedisSortSet(String activityID, Long time) throws IOException;
    Object insertHotNumOrder2RedisSet(String activityID, Integer hotNum);

    Long insertLocation2RedisSet(String activityID, String location);

    Long insertTag2RedisSet(String activityID, String tag );

    Long insertCategory2RedisSet(String activityID,String category);

    Long insertStatus2RedisSet( String activityID,String status);

    Long insertSchool2RedisSet( String activityID,String school);

    Long deleteRecommendNoFromSet(String activityID);

    Boolean deleteRecommendNoOrderFromSet(String activityID);

    Object insertDistance2RedisSortSet( String activityID,String deviceSN, double distance, int seconds) throws IOException;


    Object insertRecommendNo2RedisSet(String activityID, Integer recommendNo) throws IOException;

    Object insertRecommendNoOrder2RedisSet(String activityID, Integer recommendNo);

    Long deleteStartTimeFromSortSet(String activityID);

    Boolean deleteStartTimeOrderFromSet(String activityID);

    Boolean deleteHotNumOrderFromSet(String activityID);

    Long deleteLocationFromSet(String activityID, String location);

    Long deleteTagFromSet(String activityID, String tag);

    Long deleteCategoryFromSet(String activityID, String category);

    Long deleteStatusFromSet(String activityID, String status);

    Long deleteSchoolFromSet(String activityID, String schoolID);

    Boolean deleteDistanceOrderFromSet(String activityID, String deviceSN);

    void initCondition(String deviceSN);

    Set<String> listAllActivityID() throws IOException;

    ActivityRedisDao findByStartTime(String deviceSN, String startTime, String endTime) throws IOException;

    ActivityRedisDao findByStartTime(String desc, String source, String deviceSN, String startTime, String endTime) throws IOException;

    ActivityRedisDao findByTag(String deviceSN, String tag);

    ActivityRedisDao findByLocation(String deviceSN, String location);

    ActivityRedisDao findByCategory(String deviceSN, String category);

    ActivityRedisDao findByStatus(String deviceSN, String category);

    ActivityRedisDao findBySchool(String deviceSN, String status);

    Long sortByTime(String deviceSN);

    Long sortByTime(String deviceSN, String key,String descKey,  Boolean desc);

    Long sortByHotNum(String deviceSN);

    Long sortByHotNum(String deviceSN, String key,String descKey,  Boolean desc);

    Long sortByRecommendNo(String deviceSN);

    Long sortByRecommendNo(String deviceSN, String key,String descKey,  Boolean desc);

    Long sortByDistance(String deviceSN);

    Long sortByDistance(String deviceSN, String key,String descKey,  Boolean desc);

    void sortByDefaultOrder() throws IOException;

    void sortByDefaultOrder(String deviceSN) throws IOException;

    Long getTotalSizeByDeviceSNInZset(String deviceSN) throws IOException;

    Long getTotalSizeByDeviceSN(String deviceSN) throws IOException;

    String getConditionType(String deviceSN);

    List<String> findByPageByDeviceSN(String deviceSN, int start, int end) throws IOException;

    Set<String> findByPageByDeviceSNInZset(String deviceSN, int start, int end) throws IOException;

    void clearByDeviceSN(String deviceSN);

    void refreshExpireByDeviceSN(String deviceSN, int second);

    boolean existConditionByDeviceSN(String deviceSN);

    void copyFromDefaultStore(String deviceSN);

    String getDefaultSortedKey();

    String getDefaultWaitSortKey();

    void setDefaultSortedKey(String key);
}