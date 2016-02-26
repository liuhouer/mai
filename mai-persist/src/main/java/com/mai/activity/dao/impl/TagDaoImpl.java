package com.mai.activity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mai.activity.dao.TagDao;
import com.mai.activity.entity.Tag;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;

/**
 * Created by denghao on 15/10/5.
 */
@Component
public class TagDaoImpl extends BaseDaoImpl implements TagDao {
    @Override
    public List<Tag> findTagList() throws BaseException {
        return this.findByParam("Mapper.Tag.findTagList",null);
    }

    @Override
    public List<Tag> findTagInfoList() throws BaseException {
        return this.findByParam("Mapper.Tag.findTagInfoList",null);
    }

    @Override
    public Integer updateTag(Tag tag) throws BaseException {
        return this.update("Mapper.Tag.updateTag",tag);
    }

    @Override
    public Tag findTagByID(String tagID) throws BaseException {
        List<Tag> tagList = this.findByParam("Mapper.Tag.findTagByID", tagID);
        if(tagList!=null && tagList.size()>0){
            return tagList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Integer deleteTagByTID(String tid) throws BaseException {
        return this.delete("Mapper.Tag.deleteTagByTID",tid);
    }

    @Override
    public Integer insertBatch(List<Tag> tlist) throws BaseException {
        return this.insert("Mapper.Tag.insertBatch",tlist);
    }
}
