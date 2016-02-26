package com.mai.activity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mai.activity.dao.LocationDao;
import com.mai.activity.entity.Location;
import com.mai.activity.service.LocationService;
import com.mai.framework.exception.BaseException;

/**
 * Created by Administrator on 2015/9/7.
 */
@Service("locationService")
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationDao locationDao;

    @Override
    public List<Location> getLocationList() throws BaseException {
        List<Location> locationList = locationDao.getLocationList();

        return locationList;
    }

    @Override
    public Location getMockLocation() throws BaseException {
        Location location = new Location();
        location.setLocationID("1");
        location.setLocationName("海淀1区");
        return location;
    }
}
