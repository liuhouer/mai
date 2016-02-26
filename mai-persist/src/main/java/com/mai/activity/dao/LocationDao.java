package com.mai.activity.dao;

import java.util.List;

import com.mai.activity.entity.Location;
import com.mai.framework.exception.BaseException;

/**
 * Created by denghao on 15/10/4.
 */
public interface LocationDao {
    /**
     * 获取所有
     *
     * @return
     * @throws BaseException
     */
    public List<Location> getLocationList() throws BaseException;
}
