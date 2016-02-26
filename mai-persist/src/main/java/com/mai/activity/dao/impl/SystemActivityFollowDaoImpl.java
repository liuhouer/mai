package com.mai.activity.dao.impl;

import com.mai.activity.dao.SystemActivityFollowDao;
import com.mai.activity.entity.SystemActivityFollow;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by denghao on 15/10/24.
 */
@Component
public class SystemActivityFollowDaoImpl extends BaseDaoImpl implements SystemActivityFollowDao {
    @Override
    public SystemActivityFollow insertSystemActivityFollow(SystemActivityFollow systemActivityFollow) throws BaseException {
                    this.insert("Mapper.SystemActivityFollow.insert",systemActivityFollow);
                    return systemActivityFollow;
    }

    @Override
    public Integer updateSystemActivityFollow(SystemActivityFollow systemActivityFollow) throws BaseException {
                return this.update("Mapper.SystemActivityFollow.updateSystemActivityFollow",systemActivityFollow);
    }

    @Override
    public SystemActivityFollow findSystemActivityFollowByAID(String activityID) throws BaseException {
        List<SystemActivityFollow> list = this.findByParam("Mapper.SystemActivityFollow.findSystemActivityFollowByAID",activityID);
        if(list!=null && list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }
}
