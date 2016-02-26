package com.mai.activity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mai.activity.dao.SocietyTagDao;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import com.mai.society.entity.SocietyTag;

/**
 * Created by denghao on 15/10/5.
 */
@Component
public class SocietyTagDaoImpl extends BaseDaoImpl implements SocietyTagDao {
    @Override
    public List<SocietyTag> findSTagList() throws BaseException {
            return this.findByParam("Mapper.SocietyTag.findSTagList",null);
    }

    @Override
    public List<SocietyTag> findSocietyTagInfoList() throws BaseException {
            return this.findByParam("Mapper.SocietyTag.findSocietyTagInfoList",null);
    }

    @Override
    public Integer updateSocietyTag(SocietyTag societyTag) throws BaseException {
            return this.update("Mapper.SocietyTag.updateSocietyTag",societyTag);
    }

    @Override
    public SocietyTag findSocietyTagByID(String tagID) throws BaseException {
        List<SocietyTag> societyTagList = this.findByParam("Mapper.SocietyTag.findSocietyTagByID", tagID);
        if(societyTagList!=null && societyTagList.size()>0){
            return societyTagList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Integer deleteSocietyTagByTID(String tid) throws BaseException {
            return this.delete("Mapper.SocietyTag.deleteSocietyTagByTID",tid);
    }

    @Override
    public Integer insertBatch(List<SocietyTag> stlist) throws BaseException {
            return this.insert("Mapper.SocietyTag.insertBatch",stlist);
    }
}
