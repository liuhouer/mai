package com.mai.activity.dao;

import java.util.List;

import com.mai.framework.exception.BaseException;
import com.mai.user.entity.Role;

/**
 * Created by denghao on 15/10/6.
 */
public interface RoleDao {
    public Role insertRole(Role role)  throws BaseException;

    public List<Role> findRoleListByRName(String rolename) throws BaseException;

    public Role findRoleByID(String id) throws BaseException;

    public Integer updateRole(Role role) throws BaseException;

    public List<Role> findRoleByUserID(String uid) throws BaseException;

    public List<Role> findRoleByPhoneNum(String phoneNum) throws BaseException;
}
