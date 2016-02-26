package com.mai.service;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mai.activity.entity.Activity;
import com.mai.activity.service.ActivityService;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;


/**
 *
 * Created by fengdzh on 2015/9/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-*.xml","file:/src/main/resources/spring/spring-*.xml", "file:/src/test/resources/spring/spring-*.xml"})
public class TestActivityService {
    private static final Logger logger = Logger.getLogger(TestActivityService.class);
    @Autowired
    private ActivityService activityService;

    /**
     * 获取我的活动
     *
     * @throws BaseException
     */
    @Test
    public void findMyActivityByPage() throws BaseException {
        String personID = "";
        PaginationParameters paginationParameters = new PaginationParameters();
        Page<Activity> myActivityByPage = activityService.findMyActivityByPage(personID, paginationParameters);
        logger.error("myActivityByPage:" + myActivityByPage.getDataList().size() + " 条记录");
    }
}
