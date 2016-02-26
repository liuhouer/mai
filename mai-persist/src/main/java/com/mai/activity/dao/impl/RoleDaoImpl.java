package com.mai.activity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mai.activity.dao.RoleDao;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import com.mai.user.entity.Role;

/**
 * Created by denghao on 15/10/6.
 */
@Component
public class RoleDaoImpl extends BaseDaoImpl implements RoleDao {

    @Override
    public Role insertRole(Role role) throws BaseException {
            this.insert("Mapper.Role.insert",role);
            return role;
    }

    @Override
    public List<Role> findRoleListByRName(String rolename) throws BaseException {
            return this.findByParam("Mapper.Role.findRoleListByRName",rolename);
    }

    @Override
    public Role findRoleByID(String id) throws BaseException {
             Role role = null;
             List<Role> rlist = this.findByParam("Mapper.Role.findRoleByID",id);
             if(rlist!=null && rlist.size()>0){
                role = rlist.get(0);
             }
             return role;
    }

    @Override
    public Integer updateRole(Role role) throws BaseException {
            return this.update("Mapper.Role.updateRole",role);
    }

    @Override
    public List<Role> findRoleByUserID(String uid) throws BaseException {
            return this.findByParam("Mapper.Role.findRoleByUserID",uid);
    }

    @Override
    public List<Role> findRoleByPhoneNum(String phoneNum) throws BaseException {
            return this.findByParam("Mapper.Role.findRoleByPhoneNum",phoneNum);
    }
}
