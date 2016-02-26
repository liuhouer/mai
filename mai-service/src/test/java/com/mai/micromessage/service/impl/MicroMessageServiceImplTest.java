package com.mai.micromessage.service.impl;

import com.mai.micromessage.service.MicroMessageService;
import junit.framework.TestCase;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-*.xml","file:/src/main/resources/spring/spring-*.xml", "file:/src/test/resources/spring/spring-*.xml"})
public class MicroMessageServiceImplTest extends TestCase {

    @Autowired
    private MicroMessageService microMessageService ;
    @Test
    public void testGetMicroMessageAccessToken() throws Exception {
        String url = "http://web.maitongxue.com/index.html";

        long mtime = System.currentTimeMillis()/1000;
        String timestamp = Long.toString(mtime);//System.currentTimeMillis() + "";
        String nonceStr = RandomStringUtils.random(8, "123456789"); // 8位随机数
        String jsapi_ticket="";//="sM4AOVdWfPE4DxkXGEs8VH_k_7TfipBZNEW-qAV9Yjn111-xwI8p-Dh0i6RiwWi6Dgc8yYwqu1e578Zfk6k_Mg";//"sM4AOVdWfPE4DxkXGEs8VH_k_7TfipBZNEW-qAV9YjmvfjqVw0-53Ko9zQ-4YFoHhOws_AmGL3strDUNb33vyA";

        String token = microMessageService.getMicroMessageAccessToken();
        String signature = microMessageService.getMicroMessageSignature(timestamp, nonceStr, jsapi_ticket, url);
        System.out.println("token:" + token);
        System.out.println("signature:" + signature);
    }
}