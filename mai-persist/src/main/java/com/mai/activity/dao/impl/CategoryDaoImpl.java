package com.mai.activity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mai.activity.dao.CategoryDao;
import com.mai.activity.entity.Category;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;

/**
 * Created by denghao on 15/9/28.
 */
@Component
public class CategoryDaoImpl extends BaseDaoImpl implements CategoryDao {

    public List<Category> getCategoryList() throws BaseException {
        return this.findByParam("Mapper.Category.findCategoryList", null);
    }

    @Override
    public List<Category> findCategoryInfoList() throws BaseException {
            return this.findByParam("Mapper.Category.findCategoryInfoList",null);
    }

    @Override
    public Integer updateCategory(Category category) throws BaseException {
            return this.update("Mapper.Category.updateCategory",category);
    }

    @Override
    public Category findCategoryByID(String categoryID) throws BaseException {
        List<Category> categoryList = this.findByParam("Mapper.Category.findCategoryByID", categoryID);
        if(categoryList!=null && categoryList.size()>0){
            return categoryList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Integer deleteCategoryByCID(String cid) throws BaseException {
            return this.delete("Mapper.Category.deleteCategoryByCID",cid);
    }

    @Override
    public Integer insertBatch(List<Category> clist) throws BaseException {
            return this.insert("Mapper.Category.insertBatch",clist);
    }
}
