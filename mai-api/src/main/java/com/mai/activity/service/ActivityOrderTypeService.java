package com.mai.activity.service;

import com.mai.activity.entity.ActivityOrderType;
import com.mai.framework.exception.BaseException;

import java.util.List;

/**
 * 活动排序规则
 * Created by fengdzh on 2015/9/10.
 */
public interface ActivityOrderTypeService {
    /**
     * 获取活动排序信息
     *
     * @return
     * @throws BaseException
     */
    public List<ActivityOrderType> getActivityOrderTypeList() throws BaseException;
}
