package com.mai.activity.dao.impl;

import com.mai.activity.dao.DeviceDao;
import com.mai.device.entity.Device;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by denghao on 15/10/27.
 */
@Component
public class DeviceDaoImpl extends BaseDaoImpl implements DeviceDao {
    @Override
    public List<Device> getDeviceInfoByPersonID(String personID) throws BaseException {
            return this.findByParam("Mapper.Device.getDeviceInfoByPersonID",personID);
    }
}
