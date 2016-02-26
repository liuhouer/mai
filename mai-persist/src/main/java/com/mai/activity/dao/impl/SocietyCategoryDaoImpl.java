package com.mai.activity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mai.activity.dao.SocietyCategoryDao;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import com.mai.society.entity.SocietyCategory;

/**
 * Created by denghao on 15/10/5.
 */
@Component
public class SocietyCategoryDaoImpl extends BaseDaoImpl implements SocietyCategoryDao {
    @Override
    public List<SocietyCategory> getSocietyCategoryList() throws BaseException {
        return this.findByParam("Mapper.Societycategory.findSocietyCategoryList", null);
    }

    @Override
    public List<SocietyCategory> findSocietyCategoryInfoList() throws BaseException {
        return this.findByParam("Mapper.Societycategory.findSocietyCategoryInfoList", null);
    }

    @Override
    public Integer updateSocietyCategory(SocietyCategory societyCategory) throws BaseException {
        return this.update("Mapper.Societycategory.updateSocietyCategory",societyCategory);
    }

    @Override
    public SocietyCategory findSocietyCategoryByID(String categoryID) throws BaseException {
            List<SocietyCategory> societyCategoryList = this.findByParam("Mapper.Societycategory.findSocietyCategoryByID", categoryID);
            if(societyCategoryList!=null && societyCategoryList.size()>0){
                return societyCategoryList.get(0);
            }else{
                return null;
            }
    }

    @Override
    public Integer deleteSocietyCategoryByCID(String cid) throws BaseException {
            return this.delete("Mapper.Societycategory.deleteSocietyCategoryByCID",cid);
    }

    @Override
    public Integer insertBatch(List<SocietyCategory> sclist) throws BaseException {
            return this.insert("Mapper.Societycategory.insertBatch",sclist);
    }
}
