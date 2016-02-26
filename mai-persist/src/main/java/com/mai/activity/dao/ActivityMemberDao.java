package com.mai.activity.dao;

import java.util.List;
import java.util.Map;

import com.mai.activity.entity.ActivityMember;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;

/**
 * Created by denghao on 15/10/3.
 */
public interface ActivityMemberDao {
    public ActivityMember findActivitymemberByID(String id) throws BaseException;

    public Integer updateActivitymemberStatus(Map params) throws BaseException;

    public Page<ActivityMember> findActivitymemberList(Map params,PaginationParameters paginationParameters) throws BaseException;
    
    public List<ActivityMember> findActivitymemberList(Map params) throws BaseException;

    public Integer findActivitymemberCountByStatus(Map<String,String> params) throws BaseException;

    public Integer updateActMemberVALIDNOTbyActID(Map<String,String> params) throws BaseException;

    /**
     * 获取活动成员数
     * @param activityID
     * @return
     * @throws BaseException
     */
    public Integer getActivitymemberNum(String activityID)throws BaseException;
}
