package com.mai.activity.dao.impl;

import org.codehaus.jackson.type.TypeReference;
import com.mai.activity.dao.SocietyRedisDao;
import com.mai.framework.exception.BaseException;
import com.mai.redis.RCache;
import com.mai.society.entity.Society;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Tuple;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Created by denghao on 15/11/2.
 */
@Component
public class SocietyRedisDaoImpl implements SocietyRedisDao {
    @Autowired
    private RCache shardedRedisClient;
    private String KEY_SOCIETY_ID= "society.societyID.";
    private Logger logger=Logger.getLogger(SocietyRedisDaoImpl.class);

    // 查询条件,查询结果,排序前是set类型,排序后变为list
    private String SOCIETY_CONDITION_SET = "society.condition.set.";
    // 1.全部的id集合,需要初始化,不需更新(除非物理删除)
    private String SOCIETY_ID_LIST = "society.id.list.";
    private String SOCIETY_RANK= "society.rank";
    // 地区,需要初始化
    private String SOCIETY_LOCATION_SET = "society.location.set.";
    // 类别,需要初始化
    private String SOCIETY_CATEGORY_SET = "society.category.set.";

    // 学校
    private String SOCIETY_SCHOOL_SET = "society.school.set.";
    // 标签,需要初始化
    private String SOCIETY_TAG_SET = "society.tag.set.";


    // PraiseNum SET集合,用作排序,需要初始化
    private String SOCIETY_PRAISENUM_ORDER_SET = "society.praiseNum.hash.";


    // 状态
    private String SOCIETY_STATUS_SET = "society.status.set.";

    private String SOCIETY_RECOMMENDNO_ORDER = "society.recommendno.order";
    /**
     * 通过societyID获取society信息
     *
     * @param societyID
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public Society getSocietyByID(String societyID) throws BaseException {
        return get(societyID);
    }

    /**
     * 把社团加到缓存
     * @param society
     * @return
     */
    public boolean setSociety(final Society society) throws BaseException {
        logger.info("setSociety to Redis");
        updateSociety(society);
        return true;
    }
    /**
     * 通过key获取
     * <br>------------------------------<br>
     * @param societyID
     * @return
     */
    private Society get(final String societyID) {
        Society p = (Society) shardedRedisClient.hgetAll(KEY_SOCIETY_ID+societyID, new TypeReference<Society>(){});
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
     * 修改社团信息
     * @param society
     * @return
     * @throws BaseException
     */
    @Override
    public Integer updateSociety(Society society) throws BaseException {
        try {
            shardedRedisClient.hsetAll(KEY_SOCIETY_ID+society.getSocietyID(),society);
        } catch (IOException e) {
            throw new BaseException(e);
        }
        return null;
    }

    @Override
    public Long insertStatus2RedisSet(String societyID, String status, double score) throws BaseException {
        try {
            shardedRedisClient.zadd(SOCIETY_STATUS_SET + status, score, societyID);
        } catch (IOException e) {
            throw new BaseException(e);
        }
        return null;
    }

    @Override
    public Long deleteStatusFromSet(String societyID, String status) {
        return shardedRedisClient.zrem(SOCIETY_STATUS_SET + status, societyID);
    }

    @Override
    public Long insertID2Redis(String societyID, double score) throws BaseException {
        try {
            shardedRedisClient.zadd(SOCIETY_ID_LIST,score, societyID);
        } catch (IOException e) {
            throw new BaseException(e);
        }
        return null;
    }

    @Override
    public Integer updateSocietyScore(String societyID,double score) throws BaseException {
        try {
            shardedRedisClient.zadd(SOCIETY_RANK, score, societyID);
        } catch (IOException e) {
            throw new BaseException(e);
        }
        return null;
    }

    @Override
    public String insertRecommendNo2RedisSet(String societyID, Integer recommendNo) throws BaseException{
        try {
            shardedRedisClient.zadd(SOCIETY_RECOMMENDNO_ORDER, recommendNo, societyID);
        } catch (IOException e) {
            throw new BaseException(e);
        }
        return null;
    }

    @Override
    public Long deleteRecommendNoFromSet(String societyID) {
        return shardedRedisClient.zrem(SOCIETY_RECOMMENDNO_ORDER, societyID);
    }

    @Override
    public Long deleteLocationFromSet(String societyID, String location) {
        return shardedRedisClient.zrem(SOCIETY_LOCATION_SET+location, societyID);
    }

    @Override
    public Long deleteCategoryFromSet(String societyID, String category) {
        return shardedRedisClient.zrem(SOCIETY_CATEGORY_SET + category, societyID);
    }

    @Override
    public Long insertLocation2RedisSet(String societyID, String location,double score)  throws BaseException{
        try {
            shardedRedisClient.zadd(SOCIETY_LOCATION_SET + location, score, societyID);
        } catch (IOException e) {
            throw new BaseException(e);
        }
        return null;
    }

    @Override
    public Long insertCategory2RedisSet(String societyID, String category,double score)  throws BaseException{
        try {
            shardedRedisClient.zadd(SOCIETY_CATEGORY_SET + category, score, societyID);
        } catch (IOException e) {
            throw new BaseException(e);
        }
        return null;
    }

    @Override
    public Long insertSchool2RedisSet(String societyID, String schoolID, double score) throws BaseException {
        try {
            shardedRedisClient.zadd(SOCIETY_SCHOOL_SET + schoolID, score, societyID);
        } catch (IOException e) {
            throw new BaseException(e);
        }
        return null;
    }

    @Override
    public Long deleteSchoolFromSet(String societyID,String schoolID) {
        return shardedRedisClient.zrem(SOCIETY_SCHOOL_SET + schoolID, societyID);
    }

    @Override
    public Long insertTag2RedisSet(String societyID, String tag, double score) throws BaseException{
        try {
            shardedRedisClient.zadd(SOCIETY_TAG_SET + tag, score, societyID);
        } catch (IOException e) {
            throw new BaseException(e);
        }
        return null;
    }

    @Override
    public Long deleteTagFromSet(String societyID, String tag) {
        return shardedRedisClient.zrem(SOCIETY_TAG_SET + tag, societyID);
    }

}
