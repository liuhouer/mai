package com.mai.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by fengdzh on 2015/9/19.
 */

import com.mai.activity.dao.ActivityDao;
import com.mai.activity.entity.Activity;
import com.mai.activity.entity.Tag;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.framework.utils.DateUtil;
import com.mai.framework.utils.UUIDUtil;

/**
 * Created by fengdzh on 2015/9/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/spring/*.xml")
public class TestActivityDao {
    private static final Logger logger = Logger.getLogger(TestActivityDao.class);
    @Autowired
    private ActivityDao activityDao;

    @Test
    public void testFindActivityByPage() throws BaseException {
        PaginationParameters paginationParameters = new PaginationParameters();
        String personID = "";
        Page<Activity> activityByPage = activityDao.findActivityByPage(personID, paginationParameters);
        logger.info("activityByPage: " + activityByPage.getTotalSize() + " count ");
    }

    @Test
    public void testInsertActivity() throws BaseException {
        Page<Activity> activityMock = findActivityMock();
        List<Activity> dataList = activityMock.getDataList();
        for (Activity activity : dataList) {
            activity.setActivityID(UUIDUtil.getUUID());
            activityDao.insertActivity(activity);
        }
    }

    private Page<Activity> findActivityMock() throws BaseException {
        List<Activity> activityList = new ArrayList<Activity>();
        Activity a1 = getActivityByIDMock("");
        a1.setActivityID("0");
        a1.setNeedCheck(true);
        activityList.add(a1);

        Activity a2 = getActivityByIDMock("");
        a2.setRecommendNo(null);
        a2.setCoverURL("http://wimg.huodongxing.com/logo/201509/2298028937800/432069646095164_v2.jpg@!wmlogo");
        a2.setActivityTitle("Webpower开放日：邮件营销带你开启“完美”旅程");

        activityList.add(a2);
        a2.setActivityID("1");

        Activity a3 = getActivityByIDMock("");
        a3.setRecommendNo(null);
        a3.setCoverURL("http://wimg.huodongxing.com/logo/201509/8298894786400/492075677518000_v2.jpg@!wmlogo");
        a3.setActivityTitle("IT168科技沙龙之iPhone6S发布会翻译直播+点评");
        a3.setNeedCheck(true);
        a3.setActivityID("2");
        activityList.add(a3);


        Activity a4 = getActivityByIDMock("");
        activityList.add(a4);
        a4.setActivityTitle("创业分享会NO.8报名：企业级服务如何做？");
        a4.setActivityDetail("创业分享会NO.8报名：企业级服务如何做？");
        a4.setCoverURL("http://wimg.huodongxing.com/logo/201508/1296128889000/682056453811900_v2.jpg@!wmlogo");
        a4.setActivityID("3");

        Activity a5 = getActivityByIDMock("");
        a5.setActivityDetail("互联网+时代，酒店及餐饮企业新三板上市筹划与资本创新");
        a5.setActivityTitle("互联网+时代，酒店及餐饮企业新三板上市筹划与资本创新");
        activityList.add(a5);
        a5.setActivityID("4");
        a5.setCoverURL("http://wimg.huodongxing.com/logo/201508/1296128889000/682056453811900_v2.jpg@!wmlogo");

        Activity a6 = getActivityByIDMock("");
        a6.setActivityDetail("2015中国（鄂尔多斯）国际煤化工展览会/发展论坛");
        a6.setActivityTitle("2015中国（鄂尔多斯）国际煤化工展览会/发展论坛");
        activityList.add(a6);
        a6.setActivityID("5");
        a6.setCoverURL("http://wimg.huodongxing.com/logo/201504/7277870989900/891929660098487_v2.jpg@!wmlogo");

        Activity a7 = getActivityByIDMock("");
        a7.setActivityID("6");
        a7.setActivityDetail("户外活动爬山\n" +
                "介绍\n" +
                "云蒙山国家森林公园位于密云县西部。云蒙山，古称云梦山，是京郊有名的自然风景区。公园总面积2208公顷，主峰海拔1414米。园内景观资源丰富，是一座具有山岳风光特征的北京郊外风景名胜区，有北方“小黄山”美称\n" +
                "内容\n" +
                "大家东直门7:00集合，乘坐916去怀柔，路程2个小时左右，组团爬山，愉快的进行，下午18:00返程");
        a7.setActivityTitle("云蒙山爬山旅行");
        activityList.add(a7);
        a7.setCoverURL("http://wimg.huodongxing.com/logo/201509/3299039109900/852076661488026_v2.jpg@!wmlogo");


        Page<Activity> page = new Page<Activity>();
        page.setDataList(activityList);
        return page;
    }

    /*
   * @param activityID
   * @return
   * @throws BaseException
   */
    public Activity getActivityByIDMock(String activityID) throws BaseException {
        Activity activity = new Activity();
        activity.setActivityID(UUIDUtil.getUUID());
        activity.setActivityTitle("【天坛】40人起培训套餐");
        activity.setActivityDetail("http://www.apple.com");
        ArrayList<Tag> tags = new ArrayList<Tag>();
        activity.setActivityStatus(Activity.ACTIVITY_STATUS_READY);
        activity.setCategoryID("1");
        activity.setCategoryName("活动");
        activity.setCoverURL("http://img.yaokaihui.com/86104691/cf41a44a3bdb044c6912e82fb168dede.jpg");
        activity.setCreateTime(DateUtil.currentDate().getTime());
        activity.setHotNum(2);
        activity.setIsInner(false);
        activity.setMaxPersonNum(20);
        activity.setRecommendNo(1);
        activity.setLocation("海淀区");
        activity.setAddress("海淀黄庄");
        Double d = Math.random() * 10;
        activity.setDistance(d.intValue() + ".7 km ");
        activity.setStartTime(DateUtil.currentTimestampToLong());
        activity.setEndTime(DateUtil.currentTimestampToLong() + 23456234);
        activity.setSupportDescription("由麦同学赞助");
        activity.setSocietyName("摄影协会");
        activity.setSocietyID("234");
        activity.setGpsLongitude("39.9307814555");
        activity.setGpsLatitude("116.3895468968");
        Double random = Math.random() * 1000;
        activity.setFollowNum(random.intValue());
        activity.setNotes("注意事项：请穿冲锋衣");
        //followed
        Double f = Math.random() * 10;
        if (f.intValue() % 2 == 0) {
            activity.setIsFollow(Activity.ISFOLLOWED_YES);
        }
        return activity;
    }


}
