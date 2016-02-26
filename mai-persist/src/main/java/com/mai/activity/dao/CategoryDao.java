package com.mai.activity.dao;

import java.util.List;

import com.mai.activity.entity.Category;
import com.mai.framework.exception.BaseException;

/**
 * Created by denghao on 15/9/28.
 */
public interface CategoryDao {
    /**
     * 获取所有类型
     *
     * @return
     * @throws BaseException
     */
    public List<Category> getCategoryList() throws BaseException;

    /**
     * 获取所有类型后台管理用
     *
     * @return
     * @throws BaseException
     */
    public List<Category> findCategoryInfoList() throws BaseException;


    /**
     * 修改活动类别
     * @param category
     * @return
     * @throws BaseException
     */
    public Integer updateCategory(Category category) throws BaseException;

    /**
     * 根据ID获取活动类型
     * @param categoryID
     * @return
     * @throws BaseException
     */
    public Category findCategoryByID(String categoryID) throws BaseException;

    /**
     * 根据类型ID删除Category
     * @param cid
     * @throws BaseException
     */
    public Integer deleteCategoryByCID(String cid) throws BaseException;

    /**
     * 批量插入
     * @param clist
     * @throws BaseException
     */
    public Integer insertBatch(List<Category> clist) throws BaseException;
}
