package com.mai.activity.service;


import com.mai.activity.entity.Location;
import com.mai.framework.exception.BaseException;

import java.util.List;

/**
 * Created by Administrator on 2015/9/7.
 */
public interface LocationService {
    public List<Location> getLocationList() throws BaseException;

    public Location getMockLocation() throws BaseException;
}
