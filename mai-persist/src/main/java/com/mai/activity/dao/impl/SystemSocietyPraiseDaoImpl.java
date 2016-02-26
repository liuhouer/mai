package com.mai.activity.dao.impl;

import com.mai.activity.dao.SystemSocietyPraiseDao;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import com.mai.society.entity.SystemSocietyPraise;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by denghao on 15/10/22.
 */
@Component
public class SystemSocietyPraiseDaoImpl extends BaseDaoImpl implements SystemSocietyPraiseDao {

    @Override
    public SystemSocietyPraise insertSystemSocietyPraise(SystemSocietyPraise systemSocietyPraise) throws BaseException {
                    this.insert("Mapper.SystemSocietyPraise.insert",systemSocietyPraise);
                    return systemSocietyPraise;
    }

    @Override
    public Integer updateSystemSocietyPraise(SystemSocietyPraise systemSocietyPraise) throws BaseException {
                    return this.update("Mapper.SystemSocietyPraise.updateSystemSocietyPraise",systemSocietyPraise);
    }

    @Override
    public SystemSocietyPraise findSystemSocietyPraiseBySID(String societyID) throws BaseException {
        List<SystemSocietyPraise> list = this.findByParam("Mapper.SystemSocietyPraise.findSystemSocietyPraiseBySID",societyID);
        if(list!=null && list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }
}
