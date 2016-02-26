package com.mai.activity.dao.impl;

import com.mai.activity.dao.SystemSocietyFollowDao;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import com.mai.society.entity.SystemSocietyFollow;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by denghao on 15/10/22.
 */
@Component
public class SystemSocietyFollowDaoImpl extends BaseDaoImpl implements SystemSocietyFollowDao {
    @Override
    public SystemSocietyFollow insertSystemSocietyFollow(SystemSocietyFollow systemSocietyFollow) throws BaseException {
                    this.insert("Mapper.SystemSocietyFollow.insert",systemSocietyFollow);
                    return systemSocietyFollow;
    }

    @Override
    public Integer updateSystemSocietyFollow(SystemSocietyFollow systemSocietyFollow) throws BaseException {
                    return this.update("Mapper.SystemSocietyFollow.updateSystemSocietyFollow",systemSocietyFollow);
    }

    @Override
    public SystemSocietyFollow findSystemSocietyFollowBySID(String societyID) throws BaseException {
        List<SystemSocietyFollow> list = this.findByParam("Mapper.SystemSocietyFollow.findSystemSocietyFollowBySID",societyID);
            if(list!=null && list.size()>0){
                return list.get(0);
            }else{
                return null;
            }
    }
}
