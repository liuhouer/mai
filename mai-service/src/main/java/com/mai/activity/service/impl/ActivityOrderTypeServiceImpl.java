package com.mai.activity.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mai.activity.entity.ActivityOrderType;
import com.mai.activity.service.ActivityOrderTypeService;
import com.mai.framework.exception.BaseException;

/**
 * Created by fengdzh on 2015/9/10.
 */
@Service("activityOrderTypeService")
public class ActivityOrderTypeServiceImpl implements ActivityOrderTypeService {
    /**
     * 获取活动排序信息
     *
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    public List<ActivityOrderType> getActivityOrderTypeList() throws BaseException {
        List<ActivityOrderType> activityOrderTypeList = new ArrayList<ActivityOrderType>();
        ActivityOrderType activityOrderType = new ActivityOrderType();
        activityOrderType.setActivityOrderTypeID("1");
        activityOrderType.setActivityOrderTypeName("最新");
        activityOrderTypeList.add(activityOrderType);


        ActivityOrderType activityOrderType2 = new ActivityOrderType();
        activityOrderType2.setActivityOrderTypeID("2");
        activityOrderType2.setActivityOrderTypeName("最火");
        activityOrderTypeList.add(activityOrderType2);

        ActivityOrderType activityOrderType3 = new ActivityOrderType();
        activityOrderType3.setActivityOrderTypeID("3");
        activityOrderType3.setActivityOrderTypeName("最近");
        activityOrderTypeList.add(activityOrderType3);
        return activityOrderTypeList;
    }
}
