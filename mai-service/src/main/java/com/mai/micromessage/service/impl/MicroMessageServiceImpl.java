package com.mai.micromessage.service.impl;

import com.mai.activity.dao.MicroMessageRedisDao;
import com.mai.framework.utils.StringUtils;
import com.mai.micromessage.oauth.JsApi;
import com.mai.micromessage.service.MicroMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * MicroMessageServiceImpl
 *
 * @author Yao Jinwei
 * @date 2015/12/23
 */
@Service("microMessageService")
public class MicroMessageServiceImpl implements MicroMessageService {

    @Autowired
    private MicroMessageRedisDao microMessageRedisDao;
    @Autowired
    private JsApi jsApi;

    @Override
    public String getMicroMessageAccessToken() throws Exception {
        String jsapi_ticket = microMessageRedisDao.getWeixinJsTicket();
        if(StringUtils.isBlank(jsapi_ticket)){
            jsapi_ticket = jsApi.getJsApiToken();
            microMessageRedisDao.insertWeixinJsTicket(jsapi_ticket);
        }
        return jsapi_ticket;
    }

    @Override
    public String getMicroMessageSignature(String timestamp, String noncestr, String jsapi_ticket, String url) throws InterruptedException, ExecutionException, IOException {
        String sign = jsApi.getSignature(timestamp,noncestr,jsapi_ticket,url);
        return sign;
    }

    @Override
    public String getMicroMessageAppid(){
        return jsApi.getAppid();
    }
}
