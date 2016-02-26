package com.mai.micromessage.service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * MicromessageService
 *
 * @author Yao Jinwei
 * @date 2015/12/23
 */
public interface MicroMessageService {

    public String getMicroMessageAccessToken() throws Exception;

    public String getMicroMessageSignature(String timestamp, String noncestr, String jsapi_ticket, String url) throws InterruptedException, ExecutionException, IOException;

    public String getMicroMessageAppid();
}
