package com.mai.device.service;

import com.mai.device.entity.Device;
import com.mai.framework.exception.BaseException;

/**
 * 设备信息
 * Created by fengdzh on 2015/9/17.
 */
public interface DeviceService {
    /**
     * 新增设备信息
     *
     * @param deviceInfo
     * @return
     * @throws BaseException
     */
    public Device insertDevice(Device deviceInfo) throws BaseException;

    /**
     * 删除设备
     *
     * @param deviceID
     * @return
     * @throws BaseException
     */
    public Device deleteDevice(String deviceID) throws BaseException;

    /**
     * 通过personID获取设备ID
     *
     * @param personID
     * @return
     * @throws BaseException
     */
    public Device getDeviceInfoByPersonID(String personID) throws BaseException;
}
