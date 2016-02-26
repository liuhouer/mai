package com.mai.society.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mai.activity.dao.SocietyDao;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.society.entity.Society;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mai.activity.dao.SocietyCategoryDao;
import com.mai.framework.exception.BaseException;
import com.mai.society.entity.SocietyCategory;
import com.mai.society.service.SocietyCategoryService;

/**
 * Created by denghao on 15/10/5.
 */
@Service("societyCategoryService")
public class SocietyCategoryServiceImpl implements SocietyCategoryService {

    @Autowired
    private SocietyCategoryDao societyCategoryDao;

    @Autowired
    private SocietyDao societyDao;

    @Override
    public List<SocietyCategory> getSocietyCategoryList() throws BaseException {
            return societyCategoryDao.getSocietyCategoryList();
    }

    @Override
    public List<SocietyCategory> findSocietyCategoryInfoList() throws BaseException {
            return societyCategoryDao.findSocietyCategoryInfoList();
    }

    @Override
    public Integer updateSocietyCategory(SocietyCategory societyCategory) throws BaseException {
            if(societyCategoryDao.updateSocietyCategory(societyCategory)>0){
                Map<String,Object> params = new HashMap<String,Object>();
                params.put("societyCategoryName",societyCategory.getCategoryName());
                params.put("societyCategoryID",societyCategory.getCategoryID());
                return societyDao.updateSocietyCategoryNameBySCID(params);
            }else{
                return 0;
            }
    }

    @Override
    public SocietyCategory findSocietyCategoryByID(String categoryID) throws BaseException {
            return societyCategoryDao.findSocietyCategoryByID(categoryID);
    }

    @Override
    public Integer deleteSocietyCategoryByCID(String cid) throws BaseException {
            return societyCategoryDao.deleteSocietyCategoryByCID(cid);
    }

    @Override
    public Integer insertBatch(List<SocietyCategory> sclist) throws BaseException {
            return societyCategoryDao.insertBatch(sclist);
    }

    @Override
    public Page<Society> findSocietyCategoryDetailByPage(String mywhere, PaginationParameters paginationParameters) throws BaseException {
            return societyDao.findSocietyCategoryDetailByPage(mywhere,paginationParameters);
    }
}
