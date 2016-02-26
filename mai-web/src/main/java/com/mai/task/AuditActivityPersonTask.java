package com.mai.task;

import com.mai.activity.entity.Activity;
import com.mai.activity.entity.ActivityMember;
import com.mai.activity.service.ActivityService;
import com.mai.framework.exception.BaseException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by denghao on 15/10/28.
 */

public class AuditActivityPersonTask {
    private static final Logger logger = Logger.getLogger(AuditActivityPersonTask.class);

    @Autowired
    private ActivityService activityService;

    public void runTask() throws BaseException {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, -1);
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
        params.put("endTime",ca.getTimeInMillis());

        List<Activity> activityList = activityService.findActivityListByTime(params);
        if(activityList!=null && activityList.size()>0){
            for(Activity activity:activityList){
                activityService.updateActMemberVALIDNOTbyActID(activity);
                logger.info("AuditActivityPersonTask task : " + activity.getActivityID());
            }
        }
    }

}
