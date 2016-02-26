package com.mai.activity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mai.activity.dao.LocationDao;
import com.mai.activity.entity.Location;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;

/**
 * Created by denghao on 15/10/4.
 */
@Component
public class LocationDaoImpl extends BaseDaoImpl implements LocationDao {
    @Override
    public List<Location> getLocationList() throws BaseException {
        return this.findByParam("Mapper.Location.findLocationList",null);
    }
}
