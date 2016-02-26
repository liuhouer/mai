package com.mai.activity.dao;

import com.mai.activity.entity.ActivityDetail;
import com.mai.framework.exception.BaseException;


/**
 * Created by denghao on 15/9/30.
 */
public interface ActivityDetailDao {

    public ActivityDetail insertActivityDetail(ActivityDetail activityDetail) throws BaseException;

    public ActivityDetail findActivityDetailByID(String id) throws BaseException;

    public Integer updateActivityDetailByID(ActivityDetail activityDetail) throws BaseException;
}
