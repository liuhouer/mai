package com.mai.activity.dao.impl;

import com.mai.activity.dao.MicroMessageRedisDao;
import com.mai.redis.RCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * CardStarPhotoRedisDaoImpl
 *
 * @author Yao Jinwei
 * @date 2015/12/23
 */
@Repository
public class MicroMessageRedisDaoImpl implements MicroMessageRedisDao {
    @Autowired
    private RCache shardedRedisClient;
    // 微信票据
    private String WEIXIN_JS_TICKET_KEY = "micromessage.js.ticket";

    private Integer ticketTimeout = 7000;


    /**
     * 获取微信票据号
     * @return
     */
    @Override
    public String getWeixinJsTicket(){
        return (String)shardedRedisClient.getStr(WEIXIN_JS_TICKET_KEY);
    }

    /**
     * 增加微信票据号
     * @param ticket
     */
    @Override
    public void insertWeixinJsTicket(String ticket){
        shardedRedisClient.set(WEIXIN_JS_TICKET_KEY, ticket, ticketTimeout);
    }
}
