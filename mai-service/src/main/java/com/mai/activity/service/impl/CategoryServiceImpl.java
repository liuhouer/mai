package com.mai.activity.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mai.activity.dao.ActivityDao;
import com.mai.activity.entity.Activity;
import com.mai.activity.service.ActivityService;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mai.activity.dao.CategoryDao;
import com.mai.activity.entity.Category;
import com.mai.activity.service.CategoryService;
import com.mai.framework.exception.BaseException;

/**
 * Created by Administrator on 2015/9/7.
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ActivityDao activityDao;

    @Override
    public List<Category> getCategoryList() throws BaseException {
        List<Category> categoryList = categoryDao.getCategoryList();
        return categoryList;
    }

    @Override
    public Category getMockCategory() throws BaseException {
        Category category = new Category();
        category.setCategoryID("1");
        category.setCategoryName("运动类");
        return category;
    }

    @Override
    public List<Category> findCategoryInfoList() throws BaseException {
       return categoryDao.findCategoryInfoList();
    }

    @Override
    public Integer updateCategory(Category category) throws BaseException {
        if(categoryDao.updateCategory(category)>0){
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("categoryID",category.getCategoryID());
            params.put("categoryName",category.getCategoryName());
            return activityDao.updateCategoryNameByCID(params);
        }else{
            return 0;
        }
    }

    @Override
    public Category findCategoryByID(String categoryID) throws BaseException {
        return categoryDao.findCategoryByID(categoryID);
    }

    @Override
    public Integer deleteCategoryByCID(String cid) throws BaseException {
        return categoryDao.deleteCategoryByCID(cid);
    }

    @Override
    public Integer insertBatch(List<Category> clist) throws BaseException {
        return categoryDao.insertBatch(clist);
    }

    @Override
    public Page<Activity> findCategoryDetailByPage(String mywhere, PaginationParameters paginationParameters) throws BaseException {
        return this.activityDao.findCategoryDetailByPage(mywhere, paginationParameters);
    }
}
