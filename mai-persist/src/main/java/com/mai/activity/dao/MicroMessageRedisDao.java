package com.mai.activity.dao;

import com.mai.redis.RCache;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * CardStarPhotoRedisDao
 *
 * @author Yao Jinwei
 * @date 2015/12/23
 */
public interface MicroMessageRedisDao {

    /**
     * 获取微信票据号
     * @return
     */
    public String getWeixinJsTicket();

    /**
     * 增加微信票据号
     * @param ticket
     */
    public void insertWeixinJsTicket(String ticket);

}
