package com.mai.activity.dao;

import java.util.List;

import com.mai.framework.exception.BaseException;
import com.mai.user.entity.Permission;

/**
 * Created by denghao on 15/10/6.
 */
public interface PermissionDao {

    public Permission insertPermission(Permission permission)  throws BaseException;

    public List<Permission> findPermissonListByRID(String roleID) throws BaseException;

    public Permission findPermissonByID(String id) throws BaseException;

    public Integer updatePermission(Permission permission) throws BaseException;
}
