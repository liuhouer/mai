package com.mai.micromessage.oauth;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mai.framework.utils.HttpPostUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * JsApi
 *
 * @author Yao Jinwei
 * @date 2015/12/23
 */
public class JsApi {
    private static final Logger logger = Logger.getLogger(JsApi.class);

    private String ticketUrl;
    private String appid;
    private String secret;
    private String jsapiticketurl;

    public String getJsapiticketurl() {
        return jsapiticketurl;
    }

    public void setJsapiticketurl(String jsapiticketurl) {
        this.jsapiticketurl = jsapiticketurl;
    }

    public String getTicketUrl() {
        return ticketUrl;
    }

    public void setTicketUrl(String ticketUrl) {
        this.ticketUrl = ticketUrl;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getAppid() {
        return appid;
    }

    private String getAccessToken() throws Exception {
        logger.error("weixin getAccessToken start...");
        Map<String, String> paras = new HashMap<String, String>();
        paras.put("appid", appid);
        paras.put("secret", secret);
        paras.put("grant_type", "client_credential");
        String json = HttpPostUtil.post(ticketUrl, paras).getResponseText();
        logger.error("weixin getAccessToken end!" + json);
        if (StringUtils.isNotBlank(json)) {
            JSONObject object = JSONObject.parseObject(json);
            if (object.containsKey("errcode")) {
                int errcode = object.getIntValue("errcode");
                if(errcode != 0){
                    return "";
                }
            }
        }
        return (String)JSON.parseObject(json,Map.class).get("access_token");
    }

    public String getJsApiToken() throws Exception {
        logger.error("weixin getJsApiToken start...");
        Map<String, String> paras = new HashMap<String, String>();
        paras.put("type", "jsapi");
        paras.put("access_token", getAccessToken());
        String json = HttpPostUtil.post(jsapiticketurl, paras).getResponseText();
        logger.error("weixin getJsApiToken end!" + json);
        if (StringUtils.isNotBlank(json)) {
            JSONObject object = JSONObject.parseObject(json);

            if (object.containsKey("errcode")) {
                int errcode = object.getIntValue("errcode");
                if(errcode != 0){
                    return "";
                }
            }
        }
        return (String) JSON.parseObject(json, Map.class).get("ticket");
    }

    /**
     * 构造签名
     * @param params
     * @param encode
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    public String createSign(Map<String, String> params, boolean encode) throws UnsupportedEncodingException {
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuffer temp = new StringBuffer();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = params.get(key);
            String valueString = "";
            if (null != value) {
                valueString = value.toString();
            }
            if (encode) {
                temp.append(URLEncoder.encode(valueString, "UTF-8"));
            } else {
                temp.append(valueString);
            }
        }
        return temp.toString();
    }

    /**
     * 支付签名
     * @param timestamp
     * @param noncestr
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public String getSignature(String timestamp, String noncestr, String jsapi_ticket, String url) throws IOException, ExecutionException, InterruptedException {
        Map<String, String> paras = new HashMap<String, String>();
        paras.put("jsapi_ticket", jsapi_ticket);
        paras.put("noncestr", noncestr);
        paras.put("timestamp", timestamp);
        paras.put("url", url);
        // appid、timestamp、noncestr、package 以及 appkey。
        String string1 = createSign(paras, false);
        String sign = DigestUtils.shaHex(string1);
        return sign;
    }

}
