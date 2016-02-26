package com.mai.activity.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mai.activity.dao.PhotoDao;
import com.mai.activity.entity.Photo;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.vo.PhotoVO;

/**
 * 信息
 * Created by bruce on 2015-10-20.
 */
@Component
public class PhotoDaoImpl extends BaseDaoImpl implements PhotoDao {

	@Override
	public int add(Photo lr) {
		// TODO Auto-generated method stub
		   int a =0 ; 
		   
		   try {
			   
			   a = this.insert("Mapper.Photo.insert", lr);
			   
		   } catch (BaseException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   }
		   
		   return a ;
	}

	@Override
	public Page<PhotoVO> findAllByPage(PaginationParameters paginationParameters) throws BaseException {
		// TODO Auto-generated method stub
		return this.findByPage("Mapper.Photo.findAllByPage",null,paginationParameters);
	}

	@Override
	public int getMaxLevel() {
		// TODO Auto-generated method stub
		int a = 1;
		return a;
	}

	@Override
	public Photo findByID(String id) {
		// TODO Auto-generated method stub
		Photo a = null;
		try {
			List<Photo> list = this.findByParam("Mapper.Photo.findByID",id);
			if(list!=null && list.size()>0){
				a = list.get(0);
			}
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return a;
	}

	@Override
	public int updateByID(Integer praiseNum, Integer followNum, String ruleid) {
		// TODO Auto-generated method stub
		 int a =0 ; 
		   
		 try {
			   Map map = new HashMap<String,String>();
			   map.put("praiseNum", praiseNum);
			   map.put("followNum", followNum);
			   map.put("id", ruleid);
			   a = this.update("Mapper.Photo.updateByID", map);
		 } catch (BaseException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		 }
		   
		 return a ;
	}

	@Override
	public List<PhotoVO> findAll() throws BaseException {
		// TODO Auto-generated method stub
		return this.findByParam("Mapper.Photo.findAllByPage",null);
	}

	@Override
	public int updateST(String photoID, String status) {
		// TODO Auto-generated method stub
		 int a =0 ; 
		   
		 try {
			   Map map = new HashMap<String,String>();
			   map.put("photoID", photoID);
			   map.put("status", status);
			   a = this.update("Mapper.Photo.updateST", map);
		 } catch (BaseException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		 }
		   
		 return a ;
	}

	@Override
	public int updateRE(String photoID, String status) {
		// TODO Auto-generated method stub
		 int a =0 ; 
		   
		 try {
			   Map map = new HashMap<String,String>();
			   map.put("photoID", photoID);
			   map.put("status", status);
			   a = this.update("Mapper.Photo.updateRE", map);
		 } catch (BaseException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		 }
		   
		 return a ;
	}

	@Override
	public List<Photo> findPhotoByWH() throws BaseException {
		return this.findByParam("Mapper.Photo.findPhotoByWH",null);
	}

	@Override
	public Integer updateWH(Photo photo) throws BaseException {
		return this.update("Mapper.Photo.updateWH",photo);
	}

	@Override
	public Page<Photo> findWallByPage(PaginationParameters paginationParameters,String wheresql)
			throws BaseException {
		// TODO Auto-generated method stub
		return this.findByPage("Mapper.Photo.findWallByPage",wheresql,paginationParameters);
	}

	@Override
	public Integer delPic(String photoID) throws BaseException {
		// TODO Auto-generated method stub
		return this.delete("Mapper.Photo.delPicByID", photoID);
	}

	@Override
	public List<Photo> findPhotoByActivityID(String activityID) throws BaseException {
				Photo photo = new Photo();
				photo.setActivityID(activityID);
		return this.findByParam("Mapper.Photo.findPhotoByActivityID",photo);
	}


}
