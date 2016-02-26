package com.mai.activity.dao.impl;

import com.mai.framework.exception.BaseException;
import com.mai.redis.RCache;
import com.mai.activity.dao.ActivityRedisDao;
import com.mai.activity.entity.Activity;
import org.apache.log4j.Logger;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;

import java.io.IOException;
import java.util.*;

/**
 *
 * Created by yaojw 2015/10/30
 */
@Repository("activityRedisDao")
public class ActivityRedisDaoImpl implements ActivityRedisDao {
    @Autowired
    private RCache shardedRedisClient;
    // 默认排序的主键
    private String DEFAULT_SORTED_KEY = "{activity}.default.sorted.key";
    private String DEFAULT_SORTLIST_KEY_1="{activity}.default.sortlist.1";
    private String DEFAULT_SORTLIST_KEY_2="{activity}.default.sortlist.2";
    // 实体记录
    private String KEY_ACTIVITY_ID= "{activity}.activityID.";
    private Logger logger=Logger.getLogger(ActivityRedisDaoImpl.class);

    // 查询条件,排序前是set类型
    private String ACTIVITY_CONDITION_SET = "{activity}.condition.set.";
    // 查询结果,,排序后变为list
    private String ACTIVITY_RESULT_LIST = "{activity}.result.list.";

    // 时间set集合, 临时存放时间条件查询结果activity.condition.set.
    private String ACTIVITY_DEVICE_TEMP_SET = "{activity}.starttime.set.";

    // 临时存放未开始的活动
    private String ACTIVITY_UNSTART_TEMP_SET = "{activity}.unstart.temp.list";
    // 临时存放已开始的活动
    private String ACTIVITY_STARTED_TEMP_SET = "{activity}.started.temp.list";
    // 临时存放推荐的活动
    private String ACTIVITY_RECOMMOND_TEMP_SET = "{activity}.recommond.temp.list";
    // 临时存放未推荐的活动
    private String ACTIVITY_UNRECOMMOND_TEMP_SET = "{activity}.unrecommond.temp.set";

    // 设备距离,用于排序
    private String ACTIVITY_DISTANCE_DEVICE_SET ="{activity}.distance.device.set.";
    /******************************lua脚本定义，无修改，无需更新*********************************/
    private String SORT_BY_DEFAULT_SCRIPT="\n" +
            "local recommendNoList = redis.call('zrevrangeByScore',KEYS[2],'+inf','(0')\n" +
            "redis.log(redis.LOG_WARNING, '1')\n" +
            "for i,key in pairs(recommendNoList) do\n" +
            "\t redis.call('sadd','temp'..ARGV[1],key)\n" +
            "end\n" +
            "redis.log(redis.LOG_WARNING, '2')\n" +
            "redis.call('sinterstore',KEYS[3],'temp'..ARGV[1],KEYS[1])\n" +
            "redis.log(redis.LOG_WARNING, '3')\n" +
            "redis.call('del','temp'..ARGV[1])\n" +
            "redis.log(redis.LOG_WARNING, '4')\n" +
            "\n" +
            "local commondList =  redis.call('sort',KEYS[3],'by',KEYS[5]..'*','desc')\n" +
            "\n" +
            "redis.log(redis.LOG_WARNING, '5')\n" +
            "local unRecommondNoList = redis.call('zrangeByScore',KEYS[2],'-inf','0')\n" +
            "\n" +
            "for i,key in pairs(unRecommondNoList) do\n" +
            "\t redis.call('sadd','temp'..ARGV[1],key)\n" +
            "end\n" +
            "\n" +
            "redis.call('sinterstore',KEYS[4],'temp'..ARGV[1],KEYS[1])\n" +
            "\n" +
            "redis.call('del','temp'..ARGV[1])\n" +
            "\n" +
            "\n" +
            "\n" +
            "local unstartList = redis.call('zrangeByScore',KEYS[6],'('.. ARGV[2] , '+inf')\n" +
            "\n" +
            "for i,key in pairs(unstartList) do\n" +
            "\t redis.call('sadd','temp'..ARGV[1],key)\n" +
            "end\n" +
            "\n" +
            "redis.call('sinterstore',KEYS[7],'temp'..ARGV[1],KEYS[4])\n" +
            "redis.call('del','temp'..ARGV[1])\n" +
            "\n" +
            "local unstartSortList =  redis.call('sort',KEYS[7],'by',KEYS[9]..'*')\n" +
            "\n" +
            "\n" +
            "local startedList = redis.call('zrangeByScore',KEYS[6],'-inf',ARGV[2])\n" +
            "\n" +
            "for i,key in pairs(startedList) do\n" +
            "\t redis.call('sadd','temp'..ARGV[1],key)\n" +
            "end\n" +
            "\n" +
            "redis.call('sinterstore',KEYS[8],'temp'..ARGV[1],KEYS[4])\n" +
            "redis.call('del','temp'..ARGV[1])\n" +
            "\n" +
            "local startedSortList =  redis.call('sort',KEYS[8],'by',KEYS[9]..'*')\n" +
            "redis.call('del',KEYS[10])\n" +
            "for i,v in pairs(commondList) do\n" +
            "    redis.call('rpush',KEYS[10],v)\n" +
            "end\n" +
            "\n" +
            "for i,v in pairs(unstartSortList) do\n" +
            "    redis.call('rpush',KEYS[10],v)\n" +
            "end\n" +
            "\n" +
            "for i,v in pairs(startedSortList) do\n" +
            "    redis.call('rpush',KEYS[10],v)\n" +
            "end\n" +
            "\n";

    private String COPY_FROM_DEFAULT_LIST = "local defaultList = redis.call('lrange',KEYS[1],0,-1)\n" +
            "redis.call('del', KEYS[2])\n" +
            "for i,key in pairs(defaultList) do\n" +
            "\t redis.call('lpush', KEYS[2],key)\n" +
            "end";

    /******************************以下内容需要初始化,并且要随活动主对象改变而改变*********************************/
    // 1.全部的id集合,需要初始化,不需更新(除非物理删除)
    private String ACTIVITY_ID_LIST = "{activity}.id.list.";

    // 2.时间顺序总集,用于条件抽取,需要初始化
    private String ACTIVITY_STARTTIME_SORTSET = "{activity}.starttime.sortset.";

    // 3.时间SET集合,用作排序,需要初始化
    private String ACTIVITY_STARTTIME_ORDER_SET = "{activity}.starttime.hash.";

    // 4.热度,用于排序,需要初始化
    private String ACTIVITY_HOTNUM_SORTSET = "{activity}.hotnum.sortset.";

    // 5.地区,需要初始化
    private String ACTIVITY_LOCATION_SET = "{activity}.location.set.";
    // 6.标签,需要初始化
    private String ACTIVITY_TAG_SET = "{activity}.tag.set.";
    // 7.类别,需要初始化
    private String ACTIVITY_CATEGORY_SET = "{activity}.category.set.";
    // 8.状态,需要初始化
    private String ACTIVITY_STATUS_SET = "{activity}.status.set.";
    // 9.学校,需要初始化
    private String ACTIVITY_SCHOOL_SET = "{activity}.school.set.";

    // 10.推荐,用于条件查询,需要初始化
    private String ACTIVITY_RECOMMENDNO_SORT_SET = "{activity}.recommendno.sortset.";

    // 11.推荐,用于排序,需要初始化
    private String ACTIVITY_RECOMMENDNO_ORDER_SET = "{activity}.recommendno.order.set.";

    // 12.默认排序
    private String ACTIVITY_DEFAULT_ORDER_SET = "{activity}.default.order.set.";
    /**
     * 通过activityID获取activity信息
     *
     * @param activityID
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public Activity getActivityByID(String activityID) throws BaseException {
        return get(activityID);
    }

    /**
     * 把用户加到缓存
     * @param activity
     * @return
     */
    public boolean setActivity(final Activity activity) throws BaseException {
        logger.debug("setActivity to Redis");
        updateActivity(activity);
        return true;
    }
    /**
     * 通过key获取
     * <br>------------------------------<br>
     * @param activityID
     * @return
     */
    private Activity get(final String activityID) {
        //shardedRedisClient.
        Activity p = (Activity) shardedRedisClient.hgetAll(KEY_ACTIVITY_ID+activityID, new TypeReference<Activity>() {
        });
        return p;
    }

    /**
     * 删除
     * @param key
     */
    public void delete(String key) {
        shardedRedisClient.del(key);
    }
    /**
     * 修改活动基本信息
     *
     * @param activity
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public Integer updateActivity(Activity activity) throws BaseException {
        try {
            shardedRedisClient.hsetAll(KEY_ACTIVITY_ID+activity.getActivityID(),activity);
        } catch (IOException e) {
            throw new BaseException(e);
        }
        return null;
    }

    /**
     * 初始化id缓存
     */
//    public void flushIDStore(){
//        Set<String>  keys = shardedRedisClient.
//        shardedRedisClient.del(new HashSet<String>());//.set(ACTIVITY_ID_LIST +)
//    }

    @Override
    public void clearActivityRedis(){
        shardedRedisClient.del(ACTIVITY_ID_LIST);
    }
    /**
     * 1.新增或修改指定活动到id集合
     */
    @Override
    public Long insertID2Redis(String activityID) throws BaseException {
        return shardedRedisClient.sadd(ACTIVITY_ID_LIST, activityID);
    }

    /**
     * 2.新增或修改指定活动到时间条件(starttime sortset)集合
     */
    @Override
    public Object insertStartTime2RedisSortSet(String activityID, Long time) throws IOException {
        return shardedRedisClient.zadd(ACTIVITY_STARTTIME_SORTSET, time, activityID);
    }


    /**
     * 3.新增或修改指定活动到时间排序(starttime order)集合
     */
    @Override
    public Object insertStartTimeOrder2RedisSet(String activityID, Long time){
        return shardedRedisClient.set(ACTIVITY_STARTTIME_ORDER_SET + activityID, "" + time);
    }

    /**
     * 4.新增或修改指定活动到热度(hotnum)集合
     */
    @Override
    public Object insertHotNumOrder2RedisSet(String activityID, Integer hotNum) {
        return shardedRedisClient.set(ACTIVITY_HOTNUM_SORTSET + activityID, "" + hotNum);
    }

    /**
     * 5.新增或修改指定活动到地区(location)集合
     */
    @Override
    public Long insertLocation2RedisSet( String activityID,String location){
        return shardedRedisClient.sadd(ACTIVITY_LOCATION_SET+location, activityID);
    }

    /**
     * 6.新增或修改指定活动到标签(tag)集合
     */
    @Override
    public Long insertTag2RedisSet(String activityID, String tag ){
        return shardedRedisClient.sadd(ACTIVITY_TAG_SET+tag, activityID);
    }

    /**
     * 7.新增或修改指定活动到类别(category)集合
     */
    @Override
    public Long insertCategory2RedisSet(String activityID, String category ){
        return shardedRedisClient.sadd(ACTIVITY_CATEGORY_SET+category, activityID);
    }

    /**
     * 8.新增或修改指定活动到状态(status)集合
     */
    @Override
    public Long insertStatus2RedisSet( String activityID,String status){
        return shardedRedisClient.sadd(ACTIVITY_STATUS_SET+status, activityID);
    }

    /**
     * 9.新增或修改指定活动到学校(school)集合
     */
    @Override
    public Long insertSchool2RedisSet( String activityID,String school){
        return shardedRedisClient.sadd(ACTIVITY_SCHOOL_SET + school, activityID);
    }

    /**
     * 10.新增或修改指定活动到推荐值(条件)集合
     */
    @Override
    public Object insertRecommendNo2RedisSet(String activityID, Integer recommendNo) throws IOException {
        return shardedRedisClient.zadd(ACTIVITY_RECOMMENDNO_SORT_SET, recommendNo, activityID);
    }
    /**
     * 11.新增或修改指定活动到推荐值(排序)集合
     */
    @Override
    public Object insertRecommendNoOrder2RedisSet(String activityID, Integer recommendNo){
        return shardedRedisClient.set(ACTIVITY_RECOMMENDNO_ORDER_SET + activityID, "" + recommendNo);
    }

    /**
     * 2.从时间范围条件集合删除指定活动
     * @param activityID
     * @return
     */
    @Override
    public Long deleteStartTimeFromSortSet(String activityID){
        return shardedRedisClient.zrem(ACTIVITY_STARTTIME_SORTSET,activityID);
    }

    /**
     * 3.从时间排序集合删除指定活动
     * @param activityID
     * @return
     */
    @Override
    public Boolean deleteStartTimeOrderFromSet(String activityID){
        return shardedRedisClient.del(ACTIVITY_STARTTIME_ORDER_SET+activityID);
    }

    /**
     * 4.从热度集合删除指定活动
     * @param activityID
     * @return
     */
    @Override
    public Boolean deleteHotNumOrderFromSet(String activityID){
        return shardedRedisClient.del(ACTIVITY_HOTNUM_SORTSET + activityID);
    }

    /**
     * 5.从地区集合中删除指定活动
     * @param activityID
     * @return
     */
    @Override
    public Long deleteLocationFromSet(String activityID, String location){
        return shardedRedisClient.srem(ACTIVITY_LOCATION_SET + location, activityID);
    }

    /**
     * 6.从标签集合中删除指定活动
     * @param activityID
     * @return
     */
    @Override
    public Long deleteTagFromSet(String activityID, String tag){
        return shardedRedisClient.srem(ACTIVITY_TAG_SET + tag, activityID);
    }

    /**
     * 7.从类别集合中删除指定活动
     * @param activityID
     * @return
     */
    @Override
    public Long deleteCategoryFromSet(String activityID, String category){
        return shardedRedisClient.srem(ACTIVITY_CATEGORY_SET+category, activityID);
    }

    /**
     * 8.从状态集合中删除指定活动
     * @param activityID
     * @return
     */
    @Override
    public Long deleteStatusFromSet(String activityID, String status){
        return shardedRedisClient.srem(ACTIVITY_STATUS_SET+status, activityID);
    }

    /**
     * 9.从学校集合中删除指定活动
     * @param schoolID
     * @return
     */
    @Override
    public Long deleteSchoolFromSet(String activityID, String schoolID){
        return shardedRedisClient.srem(ACTIVITY_SCHOOL_SET + schoolID,activityID);
    }

    /**
     * 10.从推荐值集合删除指定活动
     * @param activityID
     * @return
     */
    @Override
    public Long deleteRecommendNoFromSet(String activityID){
        return shardedRedisClient.zrem(ACTIVITY_RECOMMENDNO_SORT_SET, activityID);
    }

    /**
     * 11.从时间推荐值排序集合删除指定活动
     * @param activityID
     * @return
     */
    @Override
    public Boolean deleteRecommendNoOrderFromSet(String activityID){
        return shardedRedisClient.del(ACTIVITY_RECOMMENDNO_ORDER_SET +activityID);
    }

    /**
     * 新增或修改设备距离缓存
     */
    @Override
    public Object insertDistance2RedisSortSet( String activityID, String deviceSN, double distance, int seconds) throws IOException {
        return shardedRedisClient.set(ACTIVITY_DISTANCE_DEVICE_SET + deviceSN + activityID, "" + distance, seconds);
    }

    /**
     * 删除距离排序
     * @param activityID
     * @return
     */
    @Override
    public Boolean deleteDistanceOrderFromSet(String activityID, String deviceSN){
        return shardedRedisClient.del(ACTIVITY_DISTANCE_DEVICE_SET + deviceSN + activityID);
    }
    /**
     * 获取完整互动id列表
     * @param deviceSN
     */
    @Override
    public void initCondition(String deviceSN){
        shardedRedisClient.sunionstore(ACTIVITY_CONDITION_SET + deviceSN, ACTIVITY_ID_LIST);
    }

    /**
     * 列出所有的社团id
     * @return
     * @throws IOException
     */
    @Override
    public Set<String> listAllActivityID() throws IOException {
        return shardedRedisClient.smembers(ACTIVITY_ID_LIST);
    }
    /**
     * 根据时间条件过滤
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public ActivityRedisDao findByStartTime(String deviceSN, String startTime, String endTime) throws IOException {
        findByStartTime(ACTIVITY_CONDITION_SET + deviceSN, ACTIVITY_CONDITION_SET + deviceSN, deviceSN,startTime,endTime);
        return this;
    }

    @Override
    public ActivityRedisDao findByStartTime(String desc, String source, String deviceSN, String startTime, String endTime) throws IOException {
        Set<Tuple> set = shardedRedisClient.zrangeByScoreWithScores(ACTIVITY_STARTTIME_SORTSET, startTime, endTime);

        Iterator<Tuple> it = set.iterator();
        Map<String , Double> map = new HashMap();
        List members  = new ArrayList();
        while (it.hasNext()) {
            Tuple tuple = it.next();
            // todo 在这里进行二次计算
            map.put(tuple.getElement(),tuple.getScore());
            shardedRedisClient.sadd(ACTIVITY_DEVICE_TEMP_SET + deviceSN, tuple.getElement().toString());
            //shardedRedisClient.set(ACTIVITY_DEVICE_TEMP_SET+tuple.getElement().toString() + deviceSN,tuple.getScore());
        }
        shardedRedisClient.sinterstore(desc, ACTIVITY_DEVICE_TEMP_SET + deviceSN, source);
        shardedRedisClient.del(ACTIVITY_DEVICE_TEMP_SET + deviceSN);
        return this;
    }


    /**
     * 根据tag过滤
     * @param deviceSN
     * @param tag
     */
    @Override
    public ActivityRedisDao findByTag(String deviceSN, String tag){
        shardedRedisClient.sinterstore(ACTIVITY_CONDITION_SET + deviceSN, ACTIVITY_TAG_SET + tag, ACTIVITY_CONDITION_SET + deviceSN);
        return this;
    }

    /**
     * 根据location过滤
     * @param deviceSN
     * @param location
     */
    @Override
    public ActivityRedisDao findByLocation(String deviceSN, String location){
        shardedRedisClient.sinterstore(ACTIVITY_CONDITION_SET + deviceSN, ACTIVITY_LOCATION_SET + location, ACTIVITY_CONDITION_SET + deviceSN);
        return this;
    }

    /**
     * 根据location过滤
     * @param deviceSN
     * @param category
     */
    @Override
    public ActivityRedisDao findByCategory(String deviceSN, String category){
        shardedRedisClient.sinterstore(ACTIVITY_CONDITION_SET +deviceSN, ACTIVITY_CATEGORY_SET + category, ACTIVITY_CONDITION_SET + deviceSN);
        return this;
    }

    /**
     * 根据状态过滤
     * @param deviceSN
     * @param status
     */
    @Override
    public ActivityRedisDao findByStatus(String deviceSN, String status){
        shardedRedisClient.sinterstore(ACTIVITY_CONDITION_SET +deviceSN, ACTIVITY_STATUS_SET + status, ACTIVITY_CONDITION_SET + deviceSN);
        return this;
    }

    /**
     * 根据学校过滤
     * @param deviceSN
     * @param school
     */
    @Override
    public ActivityRedisDao findBySchool(String deviceSN, String school){
        shardedRedisClient.sinterstore(ACTIVITY_CONDITION_SET +deviceSN, ACTIVITY_SCHOOL_SET + school, ACTIVITY_CONDITION_SET + deviceSN);
        return this;
    }
    /**
     * 按时间排序
     * @param deviceSN
     * @return
     */
    @Override
    public Long sortByTime(String deviceSN){
        return sortByTime(deviceSN, ACTIVITY_CONDITION_SET + deviceSN, ACTIVITY_RESULT_LIST + deviceSN, true);
    }

    /**
     * 按时间排序
     * @param deviceSN
     * @return
     */
    @Override
    public Long sortByTime(String deviceSN, String key,String descKey, Boolean desc) {
        SortingParams params = new SortingParams();
        //params.by(KEY_ACTIVITY_ID +"*->startTime");
        params.by(ACTIVITY_STARTTIME_ORDER_SET +"*");
        params.get("#");
        if(desc) {
            params.desc();
        }
        return shardedRedisClient.sort(key, params, descKey);
    }
    /**
     * 按热度排序
     * @param deviceSN
     * @return
     */
    @Override
    public Long sortByHotNum(String deviceSN){
        return sortByHotNum(deviceSN,ACTIVITY_CONDITION_SET + deviceSN,ACTIVITY_RESULT_LIST + deviceSN, true);
    }
    /**
     * 按热度排序
     * @param deviceSN
     * @return
     */
    @Override
    public Long sortByHotNum(String deviceSN, String key,String descKey, Boolean desc){
        SortingParams params = new SortingParams();
        params.by(ACTIVITY_HOTNUM_SORTSET+"*");
        params.get("#");
        if(desc) {
            params.desc();
        }
        return shardedRedisClient.sort(key, params, descKey);
    }
    /**
     * 按推荐值排序
     * @param deviceSN
     * @return
     */
    @Override
    public Long sortByRecommendNo(String deviceSN){
        return sortByRecommendNo(deviceSN, ACTIVITY_CONDITION_SET + deviceSN,ACTIVITY_RESULT_LIST + deviceSN, true);
    }

    /**
     * 按推荐值排序
     * @param deviceSN
     * @param key
     * @return
     */
    @Override
    public Long sortByRecommendNo(String deviceSN, String key,String descKey,  Boolean desc){
        SortingParams params = new SortingParams();
        params.by(ACTIVITY_RECOMMENDNO_ORDER_SET +"*");
        params.get("#");
        if(desc) {
            params.desc();
        }
        return shardedRedisClient.sort(key, params, descKey);
    }
    /**
     * 按按距离排序
     * @param deviceSN
     * @return
     */
    @Override
    public Long sortByDistance(String deviceSN){
        return sortByDistance(deviceSN,ACTIVITY_CONDITION_SET + deviceSN,ACTIVITY_RESULT_LIST + deviceSN, false);
    }

    /**
     * 按按距离排序
     * @param deviceSN
     * @param key
     * @return
     */
    @Override
    public Long sortByDistance(String deviceSN, String key, String descKey, Boolean desc){
        SortingParams params = new SortingParams();
        params.by(ACTIVITY_DISTANCE_DEVICE_SET + deviceSN +"*");
        params.get("#");
        if(desc) {
            params.desc();
        }
        else {
            params.asc();
        }
        return shardedRedisClient.sort(key, params, descKey);
    }



    /**
     * 过滤出推荐活动
     * @param deviceSN
     */
    private void filterRecommendNo(String deviceSN){

        // 过滤出推荐
        Set<String> set = shardedRedisClient.zrevrangeByScore(ACTIVITY_RECOMMENDNO_SORT_SET, "+inf", "(0");

        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String tuple = it.next();
            // todo 在这里进行二次计算
            shardedRedisClient.sadd(ACTIVITY_DEVICE_TEMP_SET + deviceSN, tuple.toString());
        }

        shardedRedisClient.sinterstore(ACTIVITY_RECOMMOND_TEMP_SET + deviceSN, ACTIVITY_DEVICE_TEMP_SET + deviceSN, ACTIVITY_CONDITION_SET + deviceSN);
        shardedRedisClient.del(ACTIVITY_DEVICE_TEMP_SET + deviceSN);

        sortByRecommendNo(deviceSN,ACTIVITY_RECOMMOND_TEMP_SET + deviceSN,ACTIVITY_RECOMMOND_TEMP_SET + deviceSN,true);
    }

    /**
     * 过滤出为推荐的活动
     * @param deviceSN
     */
    private void filterUnRecommondNo(String deviceSN){
        // 过滤出推荐
        Set<String> set = shardedRedisClient.zrangeByScore(ACTIVITY_RECOMMENDNO_SORT_SET, "-inf", "0");

        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String tuple = it.next();
            // todo 在这里进行二次计算
            shardedRedisClient.sadd(ACTIVITY_DEVICE_TEMP_SET + deviceSN, tuple.toString());
        }
        shardedRedisClient.sinterstore(ACTIVITY_UNRECOMMOND_TEMP_SET + deviceSN, ACTIVITY_DEVICE_TEMP_SET + deviceSN, ACTIVITY_CONDITION_SET + deviceSN);
        shardedRedisClient.del(ACTIVITY_DEVICE_TEMP_SET + deviceSN);
    }
    /**
     * 过滤出未开始的活动
     * @param deviceSN
     */
    private void filterUnStartByStartTime(String deviceSN) throws IOException {
        Date date = new Date();
        findByStartTime(ACTIVITY_UNSTART_TEMP_SET + deviceSN,ACTIVITY_UNRECOMMOND_TEMP_SET + deviceSN, deviceSN, "(" + date.getTime(), "+inf" );
        sortByTime(deviceSN, ACTIVITY_UNSTART_TEMP_SET + deviceSN,ACTIVITY_UNSTART_TEMP_SET + deviceSN, false);

    }

    /**
     * 过滤出已开始的活动
     * @param deviceSN
     */
    private void filterStartedByStartTime(String deviceSN) throws IOException {
        Date date = new Date();
        findByStartTime(ACTIVITY_STARTED_TEMP_SET + deviceSN ,  ACTIVITY_UNRECOMMOND_TEMP_SET + deviceSN, deviceSN,  "-inf" , "" + date.getTime());
        sortByTime(deviceSN, ACTIVITY_STARTED_TEMP_SET + deviceSN,ACTIVITY_STARTED_TEMP_SET + deviceSN, true);
    }

    /**
     *
     */
    @Override
    public void sortByDefaultOrder() throws IOException {

//        String deviceSN = "";
//        // todo 1.过滤出推荐活动
//        filterRecommendNo(deviceSN);
//        // todo 2.过滤出未推荐活动
//        filterUnRecommondNo(deviceSN);
//        // todo 3.过滤出未开始的活动
//        filterUnStartByStartTime(deviceSN);
//        // todo 4.过滤出已开始的活动
//        filterStartedByStartTime(deviceSN);
//        // todo 5.拼接结果集 推荐+未开始+已开始
//        List commondList = shardedRedisClient.lrange(ACTIVITY_RECOMMOND_TEMP_SET + deviceSN,0,-1);
//        List unstartList = shardedRedisClient.lrange(ACTIVITY_UNSTART_TEMP_SET + deviceSN,0,-1);
//        List startedList = shardedRedisClient.lrange(ACTIVITY_STARTED_TEMP_SET + deviceSN,0,-1);
//        commondList.addAll(unstartList);
//        commondList.addAll(startedList);
//        if(commondList.size() > 0){
//            String[] arr = (String[])commondList.toArray(new String[commondList.size()]);
//            shardedRedisClient.del(ACTIVITY_DEFAULT_ORDER_SET+deviceSN);
//            shardedRedisClient.rpush(ACTIVITY_DEFAULT_ORDER_SET + deviceSN, arr);
//        }
//        else{
//            shardedRedisClient.del(ACTIVITY_DEFAULT_ORDER_SET+deviceSN);
//            shardedRedisClient.lpush(ACTIVITY_DEFAULT_ORDER_SET+deviceSN, "" );
//            shardedRedisClient.rpop(ACTIVITY_DEFAULT_ORDER_SET+deviceSN);
//        }
//        shardedRedisClient.del(ACTIVITY_RECOMMOND_TEMP_SET + deviceSN);
//        shardedRedisClient.del(ACTIVITY_UNRECOMMOND_TEMP_SET + deviceSN);
//        shardedRedisClient.del(ACTIVITY_UNSTART_TEMP_SET + deviceSN);
//        shardedRedisClient.del(ACTIVITY_STARTED_TEMP_SET + deviceSN);
    }
    /**
     * 按默认顺序排序
     * 1.过滤出推荐活动
     * 2.过滤出未推荐活动
     * 3.未开始的活动按开始时间升序排列
     * 4.已开始的活动按开始时间降序排列
     * @param deviceSN
     * @return
     */
    @Override
    public void sortByDefaultOrder(String deviceSN) throws IOException {
//        List commondList = shardedRedisClient.lrange(ACTIVITY_DEFAULT_ORDER_SET,0,-1);
//        if(commondList.size() > 0){
//            String[] arr = (String[])commondList.toArray(new String[commondList.size()]);
//            shardedRedisClient.del(ACTIVITY_CONDITION_SET+deviceSN);
//            shardedRedisClient.rpush(ACTIVITY_CONDITION_SET + deviceSN, arr);
//        }
//        else{
//            shardedRedisClient.del(ACTIVITY_CONDITION_SET+deviceSN);
//            shardedRedisClient.lpush(ACTIVITY_CONDITION_SET+deviceSN, "" );
//            shardedRedisClient.rpop(ACTIVITY_CONDITION_SET+deviceSN);
//        }
        // todo 1.过滤出推荐活动
//        filterRecommendNo(deviceSN);
//        // todo 2.过滤出未推荐活动
//        filterUnRecommondNo(deviceSN);
//        // todo 3.过滤出未开始的活动
//        filterUnStartByStartTime(deviceSN);
//        // todo 4.过滤出已开始的活动
//        filterStartedByStartTime(deviceSN);
//        // todo 5.拼接结果集 推荐+未开始+已开始
//        List commondList = shardedRedisClient.lrange(ACTIVITY_RECOMMOND_TEMP_SET + deviceSN,0,-1);
//        List unstartList = shardedRedisClient.lrange(ACTIVITY_UNSTART_TEMP_SET + deviceSN,0,-1);
//        List startedList = shardedRedisClient.lrange(ACTIVITY_STARTED_TEMP_SET + deviceSN,0,-1);
//        commondList.addAll(unstartList);
//        commondList.addAll(startedList);
//        if(commondList.size() > 0){
//            String[] arr = (String[])commondList.toArray(new String[commondList.size()]);
//            shardedRedisClient.del(ACTIVITY_RESULT_LIST+deviceSN);
//            shardedRedisClient.rpush(ACTIVITY_RESULT_LIST + deviceSN, arr);
//        }
//        else{
//            shardedRedisClient.del(ACTIVITY_RESULT_LIST+deviceSN);
//            shardedRedisClient.lpush(ACTIVITY_RESULT_LIST+deviceSN, "" );
//            shardedRedisClient.rpop(ACTIVITY_RESULT_LIST+deviceSN);
//        }
//        shardedRedisClient.del(ACTIVITY_RECOMMOND_TEMP_SET + deviceSN);
//        shardedRedisClient.del(ACTIVITY_UNRECOMMOND_TEMP_SET + deviceSN);
//        shardedRedisClient.del(ACTIVITY_UNSTART_TEMP_SET + deviceSN);
//        shardedRedisClient.del(ACTIVITY_STARTED_TEMP_SET + deviceSN);
        Date date = new Date();
        shardedRedisClient.eval(SORT_BY_DEFAULT_SCRIPT, ACTIVITY_ID_LIST, 10, ACTIVITY_CONDITION_SET + deviceSN, ACTIVITY_RECOMMENDNO_SORT_SET, ACTIVITY_RECOMMOND_TEMP_SET + deviceSN,
                ACTIVITY_UNRECOMMOND_TEMP_SET + deviceSN, ACTIVITY_RECOMMENDNO_ORDER_SET, ACTIVITY_STARTTIME_SORTSET, ACTIVITY_UNSTART_TEMP_SET + deviceSN, ACTIVITY_STARTED_TEMP_SET + deviceSN, ACTIVITY_STARTTIME_ORDER_SET,
                ACTIVITY_RESULT_LIST+ deviceSN, deviceSN, "" + date.getTime());
    }


    @Override
    public Long getTotalSizeByDeviceSNInZset(String deviceSN) throws IOException {
        return shardedRedisClient.zlexcount(ACTIVITY_RESULT_LIST + deviceSN, "-", "+");
    }

    /**
     *
     * @param deviceSN
     * @return
     * @throws IOException
     */
    @Override
    public Long getTotalSizeByDeviceSN(String deviceSN) throws IOException {
        return shardedRedisClient.llen(ACTIVITY_RESULT_LIST + deviceSN);
    }

    /**
     * h
     * @param deviceSN
     * @return
     */
    @Override
    public String getConditionType(String deviceSN){
        return shardedRedisClient.type(ACTIVITY_RESULT_LIST + deviceSN);
    }

    /**
     * 分页查找
     * @param deviceSN
     * @param start
     * @param end
     * @return
     */
    @Override
    public List<String> findByPageByDeviceSN(String deviceSN, int start, int end) throws IOException {
        return shardedRedisClient.lrange(ACTIVITY_RESULT_LIST + deviceSN, start, end);
    }

    /**
     * 分页查找
     * @param deviceSN
     * @param start
     * @param end
     * @return
     */
    @Override
    public Set<String> findByPageByDeviceSNInZset(String deviceSN, int start, int end) throws IOException {
        return shardedRedisClient.zrevrange(ACTIVITY_RESULT_LIST + deviceSN, start, end);
    }

    /**
     *
     * @param deviceSN
     */
    @Override
    public void clearByDeviceSN(String deviceSN){
        shardedRedisClient.del(ACTIVITY_CONDITION_SET + deviceSN);
        shardedRedisClient.del(ACTIVITY_RESULT_LIST + deviceSN);
    }

    /**
     * 刷新设备缓存中的设备过期时间
     * @param deviceSN
     * @param second
     */
    @Override
    public void refreshExpireByDeviceSN(String deviceSN, int second){
        shardedRedisClient.expire(ACTIVITY_CONDITION_SET + deviceSN,second);
        shardedRedisClient.expire(ACTIVITY_RESULT_LIST + deviceSN,second);
    }
    @Override
    public boolean existConditionByDeviceSN(String deviceSN){
        return shardedRedisClient.exists(ACTIVITY_RESULT_LIST + deviceSN);
    }

    /**
     * 复制默认的排序列表
     */
    @Override
    public void copyFromDefaultStore(String deviceSN){
        String key = getDefaultSortedKey();
        shardedRedisClient.eval(COPY_FROM_DEFAULT_LIST, ACTIVITY_ID_LIST, 2,ACTIVITY_RESULT_LIST + key, ACTIVITY_RESULT_LIST + deviceSN);
    }

    /**
     * 获取当前默认状态的排序号
     * @return
     */
    @Override
    public String getDefaultSortedKey(){
        return (String)shardedRedisClient.getStr(DEFAULT_SORTED_KEY);
    }

    /**
     * 获取当前处于等待状态的排序key
     * @return
     */
    @Override
    public String getDefaultWaitSortKey(){
        String key = (String)shardedRedisClient.getStr(DEFAULT_SORTED_KEY);
        if(DEFAULT_SORTLIST_KEY_1.equals(key)){
            return DEFAULT_SORTLIST_KEY_2;
        }
        else{
            return DEFAULT_SORTLIST_KEY_1;
        }
    }

    /**
     * 设置默认的已排序的key
     * @param key
     */
    @Override
    public void setDefaultSortedKey(String key){
        shardedRedisClient.set(DEFAULT_SORTED_KEY, key);
    }
}
