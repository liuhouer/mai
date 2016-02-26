package com.mai.activity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mai.activity.dao.PhotoTagDao;
import com.mai.activity.entity.PhotoTag;
import com.mai.activity.service.PhotoTagService;
import com.mai.framework.exception.BaseException;

/**
 * Created by bruce on 2015/9/11.
 */
@Service("photoTagService")
public class PhotoTagServiceImpl implements PhotoTagService {
	
	 @Autowired
	 private PhotoTagDao phototagDao;

	@Override
	public Integer insertBatch(List<PhotoTag> photoTagList)
			throws BaseException {
		// TODO Auto-generated method stub
		return phototagDao.insertBatch(photoTagList);
	}

	@Override
	public Integer addModel(PhotoTag model) throws BaseException {
		// TODO Auto-generated method stub
		return phototagDao.addModel(model);
	}

	@Override
	public List<PhotoTag> findAllByPhotoID(String photoID) throws BaseException {
		// TODO Auto-generated method stub
		return phototagDao.findAllByPhotoID(photoID);
	}

	@Override
	public Integer delModel(String tagID) throws BaseException {
		// TODO Auto-generated method stub
		return phototagDao.delModel(tagID);
	}

	@Override
	public Integer delTagsByPhotoID(String photoID) throws BaseException {
		// TODO Auto-generated method stub
		return phototagDao.delTagsByPhotoID(photoID);
	}

}
