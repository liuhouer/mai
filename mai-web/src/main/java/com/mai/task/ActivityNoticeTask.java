package com.mai.task;

import com.mai.activity.entity.Activity;
import com.mai.activity.service.ActivityService;
import com.mai.framework.exception.BaseException;
import com.mai.notification.entity.Notification;
import com.mai.notification.service.NotificationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by denghao on 15/11/30.
 */
public class ActivityNoticeTask {
    private static final Logger logger = Logger.getLogger(ActivityNoticeTask.class);
    @Autowired
    private ActivityService activityService;
    @Autowired
    private NotificationService notificationService;

    public void runTask() throws BaseException {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, 1);
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("startTime", ca.getTimeInMillis());
        ca.set(Calendar.HOUR_OF_DAY, 23);
        ca.set(Calendar.MINUTE, 59);
        ca.set(Calendar.SECOND, 59);
        ca.set(Calendar.MILLISECOND, 0);
        params.put("endTime", ca.getTimeInMillis());
        params.put("actStatus" ,Activity.ACTIVITY_STATUS_READY);

        List<Activity> activityList = activityService.findActivityListByTime(params);

        if(activityList!=null && activityList.size()>0){
            for(Activity activity:activityList){
                //给报名活动的人员发消息
                notificationService.sendNotice(activity.getActivityID(),String.valueOf(Notification.NOTIFICATIONTYPE_TYPE_ACTIVITY_NEWS),activity.getAdminID(),MessageFormat.format(Notification.NOTIFICATION_CONTENT_ACTIVITY_APPLY_TIP, activity.getActivityTitle()),null);
                //给关注活动的人员发消息
                notificationService.sendFollowNotice(activity.getActivityID(),String.valueOf(Notification.NOTIFICATIONTYPE_TYPE_ACTIVITY_NEWS),activity.getAdminID(),MessageFormat.format(Notification.NOTIFICATION_CONTENT_ACTIVITY_APPLY_FOLLOW, activity.getActivityTitle()));
                logger.info("ActivityNoticeTask task : " + activity.getActivityID());
            }
        }
    }
}
