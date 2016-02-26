package com.mai.activity.service;

import com.mai.activity.entity.Activity;
import com.mai.activity.entity.Category;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;

import java.util.List;

/**
 * Created by Administrator on 2015/9/7.
 */
public interface CategoryService {
    public List<Category> getCategoryList() throws BaseException;

    public Category getMockCategory() throws BaseException;

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

    /**
     * 获取活动类别信息列表
     *
     * @param mywhere
     * @return
     * @throws BaseException
     */
    public Page<Activity> findCategoryDetailByPage(String mywhere,PaginationParameters paginationParameters) throws BaseException;
}
