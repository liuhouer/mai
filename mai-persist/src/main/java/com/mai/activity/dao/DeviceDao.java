package com.mai.activity.dao;

import com.mai.device.entity.Device;
import com.mai.framework.exception.BaseException;

import java.util.List;

/**
 * Created by denghao on 15/10/27.
 */
public interface DeviceDao {

    List<Device> getDeviceInfoByPersonID(String personID) throws BaseException;
}
