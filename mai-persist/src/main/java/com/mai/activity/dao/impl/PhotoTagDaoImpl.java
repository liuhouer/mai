package com.mai.activity.dao.impl;

import com.mai.activity.dao.PhotoTagDao;
import com.mai.activity.entity.PhotoTag;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by denghao on 15/12/7.
 */
@Repository
public class PhotoTagDaoImpl extends BaseDaoImpl implements PhotoTagDao {
    @Override
    public Integer insertBatch(List<PhotoTag> photoTagList) throws BaseException {
        return this.insert("Mapper.PhotoTag.insertBatch", photoTagList);
    }

	@Override
	public Integer addModel(PhotoTag model) throws BaseException {
		// TODO Auto-generated method stub
		return this.insert("Mapper.PhotoTag.insert", model);
	}

	@Override
	public List<PhotoTag> findAllByPhotoID(String photoID) throws BaseException {
		// TODO Auto-generated method stub
		return this.findByParam("Mapper.PhotoTag.findAllByPhotoID", photoID);
	}

	@Override
	public Integer delModel(String tagID) throws BaseException {
		// TODO Auto-generated method stub
		return this.delete("Mapper.PhotoTag.deleteByID", tagID);
	}

	@Override
	public Integer delTagsByPhotoID(String photoID) throws BaseException {
		// TODO Auto-generated method stub
		return this.delete("Mapper.PhotoTag.deleteByphotoID", photoID);
	}
}
