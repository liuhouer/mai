package com.mai.activity.dao;

import java.util.List;
import java.util.Map;

import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.society.entity.SocietyMember;

/**
 * Created by denghao on 15/10/5.
 */
public interface SocietyMemberDao {
    public SocietyMember findSocietymemberByID(String id) throws BaseException;

    public Integer updateSocietymemberStatus(Map params) throws BaseException;

    public Page<SocietyMember> findSocietymemberList(Map params,PaginationParameters paginationParameters) throws BaseException;

	public List<SocietyMember> findSocietymemberList(Map map) throws BaseException;

    public SocietyMember findSocietymemberBySidANDPid(Map params) throws BaseException;

    /**
     * 新增人员
     *
     * @param societyMember
     * @return
     * @throws BaseException
     */

    public SocietyMember insertSocietyMember(SocietyMember societyMember) throws BaseException;

    /**
     * 获取社团成员数
     * @param societyID
     * @return
     * @throws BaseException
     */
    public Integer getSocietymemberNum(String societyID)throws BaseException;
}
