package com.mai.activity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mai.activity.dao.UserroleRefDao;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import com.mai.user.entity.UserroleRef;

/**
 * Created by denghao on 15/10/6.
 */
@Component
public class UserroleRefDaoImpl extends BaseDaoImpl implements UserroleRefDao {
    @Override
    public List<UserroleRef> findUserroleRef(UserroleRef userroleRef) throws BaseException {
                    return this.findByParam("Mapper.User_role_ref.findUserroleRef",userroleRef);
    }

    @Override
    public Integer insertBatch(List<UserroleRef> urrlist) throws BaseException {
                    return this.insert("Mapper.User_role_ref.insertBatch",urrlist);
    }

    @Override
    public Integer deleteUserroleRefByUID(String userID) throws BaseException {
                    return this.delete("Mapper.User_role_ref.deleteUserroleRefByUID",userID);
    }

    @Override
    public UserroleRef insertUserroleRef(UserroleRef userroleRef) throws BaseException {
        this.insert("Mapper.User_role_ref.insert", userroleRef);
        return userroleRef;
    }
}
