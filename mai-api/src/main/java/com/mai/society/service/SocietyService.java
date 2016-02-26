package com.mai.society.service;

import java.util.List;
import java.util.Map;

import com.mai.activity.entity.Log;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.society.entity.*;
import com.mai.vo.SocietyRunningVO;

/**
 * 社团处理类
 * Created by fengdzh on 2015/9/16.
 */
public interface SocietyService {
    /**
     * 新增社团
     *
     * @param society
     * @return
     * @throws BaseException
     */
    public int addSociety(Society society) throws BaseException;

    /**
     * gengxin社团,PhoneNum为当前操作用户的userPhoneNum
     * @param society
     * @param PhoneNum
     * @return
     * @throws BaseException
     */
    public int updateSocietyStatusByID(Society society,String PhoneNum) throws BaseException;

    /**
     * 通过ID获取社团信息
     *
     * @param societyID
     * @return
     * @throws BaseException
     */
    public Society getSocietyByID(String societyID) throws BaseException;

    /**
     * 我的社团列表
     *
     * @param adminID
     * @param paginationParameters
     * @return
     * @throws BaseException
     */
    public Page<Society> listMySocietyPage(String adminID, PaginationParameters paginationParameters) throws BaseException;
    
    
    /**
     * 根据状态获取社团列表
     *
     * @param status
     * @return
     * @throws BaseException
     */
    public List<Society> getSocietyListByZT(String status) throws BaseException;
    
    /**
     * 根据状态获取社团列表
     *
     * @param status
     * @return
     * @throws BaseException
     */
    public Page<Society> getSocietyPageByZT(String status,PaginationParameters paginationParameters) throws BaseException;


    public List<SocietytagRef> findSocietytagRefListBySID(String sid) throws BaseException;

    /**
     * 更新活动信息
     * @param society
     * @param societytagRefs
     * @return
     * @throws BaseException
     */
    public Integer updateSocietyInfo(Society society,List<SocietytagRef> societytagRefs) throws BaseException;


    public SocietyMember findSocietymemberByID(String id) throws BaseException;

    public Integer updateSocietymemberStatus(Map<String,String> params,Log log) throws BaseException;

    public Page<SocietyMember> findSocietymemberListBySID(Map params,PaginationParameters paginationParameters) throws BaseException;

    public Society findSocietyByAdminID(String adminID);

    /**
     * 有效状态的社团信息，status > 0
     * @param adminID
     * @return
     */
    public Society findSocietyByAdminIDAndValid(String adminID);
    
    /**
     * @param adminid
     * @return
     * 看看社长是不是只有一个社团
     */
    public boolean OnlyOneST(String adminid);
    
    /**
     * @param adminid
     * @return
     * 看看社长是不是只有一个有效的社团
     */
    public boolean OnlyOneEffectST(String adminid);

    /**
     * 更新社长信息
     * @param society
     * @param oldPUserID
     * @param newPUserID
     * @return
     * @throws BaseException
     */
    public Integer updatePresident(Society society,String oldPUserID,String newPUserID) throws BaseException;

    /**
     * 获取社团运营列表
     * @param mywhere
     * @param paginationParameters
     * @return
     */
    public Page<SocietyRunningVO> findSocietyRunningByPage(String mywhere,PaginationParameters paginationParameters) throws BaseException;

    /**
     * 更新推荐位
     * @param params
     * @return
     * @throws BaseException
     */
    public Integer updateRecommendNoByID(Map params) throws BaseException;

    /**
     * 新增社团关注更新值
     *
     * @param systemSocietyFollow
     * @return
     * @throws BaseException
     */
    public SystemSocietyFollow insertSystemSocietyFollow(SystemSocietyFollow systemSocietyFollow) throws BaseException;

    /**
     * 修改社团关注更新值
     *
     * @param systemSocietyFollow
     * @return
     * @throws BaseException
     */
    public Integer updateSystemSocietyFollow(SystemSocietyFollow systemSocietyFollow) throws BaseException;

    /**
     * 查找社团关注更新值
     *
     * @param societyID
     * @return
     * @throws BaseException
     */
    public SystemSocietyFollow findSystemSocietyFollowBySID(String societyID) throws BaseException;

    /**
     * 新增社团点赞更新值
     *
     * @param systemSocietyPraise
     * @return
     * @throws BaseException
     */
    public SystemSocietyPraise insertSystemSocietyPraise(SystemSocietyPraise systemSocietyPraise) throws BaseException;

    /**
     * 修改社团点赞更新值
     *
     * @param systemSocietyPraise
     * @return
     * @throws BaseException
     */
    public Integer updateSystemSocietyPraise(SystemSocietyPraise systemSocietyPraise) throws BaseException;

    /**
     * 查找社团点赞更新值
     *
     * @param societyID
     * @return
     * @throws BaseException
     */
    public SystemSocietyPraise findSystemSocietyPraiseBySID(String societyID) throws BaseException;

	/**根据id修改社团名
	 * @param id
	 * @param name
	 * @return
	 * @throws BaseException 
	 */
	public int mdName(String id, String name) throws BaseException;
}
