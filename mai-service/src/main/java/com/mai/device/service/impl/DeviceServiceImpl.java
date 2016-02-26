package com.mai.device.service.impl;

import org.springframework.stereotype.Service;

import com.mai.device.entity.Device;
import com.mai.device.service.DeviceService;
import com.mai.framework.exception.BaseException;

/**
 * 设备信息
 * Created by fengdzh on 2015/9/17.
 */
@Service
public class DeviceServiceImpl implements DeviceService {
    /**
     * 新增设备信息
     *
     * @param deviceInfo
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public Device insertDevice(Device deviceInfo) throws BaseException {
        return null;
    }

    /**
     * 删除设备
     *
     * @param deviceID
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public Device deleteDevice(String deviceID) throws BaseException {
        return null;
    }

    /**
     * 通过personID获取设备ID
     *
     * @param personID
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public Device getDeviceInfoByPersonID(String personID) throws BaseException {
        return null;
    }
}
