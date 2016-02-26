package com.mai.activity.dao;

import java.util.List;
import java.util.Map;

import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.society.entity.Society;
import com.mai.vo.SocietyRunningVO;

/**
 * Created by bruce on 2015/9/29.
 */
public interface SocietyDao {
    /**
     * 模糊查找
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

    
    public int updateSocietyStatusByID(String status,String id) ;
    
    /**
     * 单个查找
     *
     * @param id
     * @return
     * @throws BaseException
     */
    public  Society getSocietyByID(String id) throws BaseException;


    /**
     * 更新社团信息
     *
     * @param society
     * @return
     * @throws BaseException
     */
    public Integer updateSociety(Society society) throws BaseException;

    public Society findSocietyByAdminID(String adminID) throws BaseException;
    
    /**
     * @param adminID
     * @return
     * 看看社长是不是只有一个社团
     */
    public boolean OnlyOneST(String adminID);
    
    /**
     * @param adminID
     * @return
     * 看看社长是不是只有一个有效社团
     */
    public boolean OnlyOneEffectST(String adminID);


    /**
     * 更新社长相关信息
     * @param society
     * @return
     * @throws BaseException
     */
    public Integer updatePresident(Society society) throws BaseException;

    /**
     * 根据PersonID返回有效状态(status>0)的社团信息
     * @param adminID
     * @return
     * @throws BaseException
     */
    public Society findSocietyByAdminIDAndValid(String adminID) throws BaseException;

	public List<Society> getSocietyListByZTOver0() throws BaseException;

    /**
     * 获取社团运营列表
     *
     * @param mywhere
     * @return
     * @throws BaseException
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
     * 更新类型name
     * @param params
     * @return
     * @throws BaseException
     */
    public Integer updateSocietyCategoryNameBySCID(Map params) throws BaseException;


    /**
     * 获取社团类别信息列表
     *
     * @param mywhere
     * @return
     * @throws BaseException
     */
    public Page<Society> findSocietyCategoryDetailByPage(String mywhere,PaginationParameters paginationParameters) throws BaseException;

    /**
     * 获取社团标签信息列表
     *
     * @param mywhere
     * @return
     * @throws BaseException
     */
    public Page<Society> findSocietyTagDetailByPage(String mywhere,PaginationParameters paginationParameters) throws BaseException;

	public int add(Society society);

    /**
     * 更新加入人数
     * @param societyID
     * @param joinPersonNum
     * @return
     * @throws BaseException
     */
    public Integer updateSocietyJoinPersonNum(String societyID,Integer joinPersonNum)throws BaseException;

	public int mdName(String id, String name);
}
