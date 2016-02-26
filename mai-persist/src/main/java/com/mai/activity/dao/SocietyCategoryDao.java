package com.mai.activity.dao;

import java.util.List;

import com.mai.framework.exception.BaseException;
import com.mai.society.entity.SocietyCategory;

/**
 * Created by denghao on 15/10/5.
 */
public interface SocietyCategoryDao {
    /**
     * 获取所有类型
     *
     * @return
     * @throws BaseException
     */
    public List<SocietyCategory> getSocietyCategoryList() throws BaseException;


    /**
     * 获取所有类型后台管理用
     *
     * @return
     * @throws BaseException
     */
    public List<SocietyCategory> findSocietyCategoryInfoList() throws BaseException;


    /**
     * 修改社团类别
     * @param societyCategory
     * @return
     * @throws BaseException
     */
    public Integer updateSocietyCategory(SocietyCategory societyCategory) throws BaseException;

    /**
     * 根据ID获取社团类型
     * @param categoryID
     * @return
     * @throws BaseException
     */
    public SocietyCategory findSocietyCategoryByID(String categoryID) throws BaseException;

    /**
     * 根据类型ID删除SocietyCategory
     * @param cid
     * @throws BaseException
     */
    public Integer deleteSocietyCategoryByCID(String cid) throws BaseException;

    /**
     * 批量插入
     * @param sclist
     * @throws BaseException
     */
    public Integer insertBatch(List<SocietyCategory> sclist) throws BaseException;

}
