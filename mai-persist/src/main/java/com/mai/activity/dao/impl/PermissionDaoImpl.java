package com.mai.activity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mai.activity.dao.PermissionDao;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import com.mai.user.entity.Permission;

/**
 * Created by denghao on 15/10/6.
 */
@Component
public class PermissionDaoImpl extends BaseDaoImpl implements PermissionDao {
    @Override
    public Permission insertPermission(Permission permission) throws BaseException {
                this.insert("Mapper.Permisson.insert",permission);
                return permission;
    }

    @Override
    public List<Permission> findPermissonListByRID(String roleID) throws BaseException {
                return this.findByParam("Mapper.Permisson.findPermissonListByRID",roleID);
    }

    @Override
    public Permission findPermissonByID(String id) throws BaseException {
                Permission permission = null;
                List<Permission> plist = this.findByParam("Mapper.Permisson.findPermissonByID",id);
                if(plist!=null && plist.size()>0){
                    permission = plist.get(0);
                }
                return permission;
    }

    @Override
    public Integer updatePermission(Permission permission) throws BaseException {
                return this.update("Mapper.Permisson.updatePermission",permission);
    }
}
