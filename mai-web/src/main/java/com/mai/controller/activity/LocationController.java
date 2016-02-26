package com.mai.controller.activity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mai.activity.entity.Location;
import com.mai.activity.service.LocationService;
import com.mai.framework.exception.BaseException;

/**
 * Created by Administrator on 2015/9/7.
 */
@Controller
@RequestMapping(value = "location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @RequestMapping(value = "getLocationList")
    @ResponseBody
    public List<Location> getLocationList() throws BaseException {
        return locationService.getLocationList();
    }
}
