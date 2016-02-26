package com.mai.activity.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mai.activity.dao.AdDao;
import com.mai.app.entity.Advertisement;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;

/**
 * 信息
 * Created by bruce on 2015-10-19.
 */
@Component
public class AdDaoImpl extends BaseDaoImpl<Advertisement, Advertisement> implements AdDao {

	@Override
	public int add(Advertisement lr) {
		// TODO Auto-generated method stub
		   int a =0 ; 
		   
		   try {
			   
			   a = this.insert("Mapper.Advertisement.insert", lr);
			   
		   } catch (BaseException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   }
		   
		   return a ;
	}

	@Override
	public Page<Advertisement> findAllByPage(PaginationParameters paginationParameters) throws BaseException {
		// TODO Auto-generated method stub
		return this.findByPage("Mapper.Advertisement.findAllByPage",null,paginationParameters);
	}

	@Override
	public int getMaxLevel() {
		// TODO Auto-generated method stub
		int a = 1;
		return a;
	}

	@Override
	public Advertisement findByID(String id) {
		// TODO Auto-generated method stub
		Advertisement a = null;
		try {
			List<Advertisement> list = this.findByParam("Mapper.Advertisement.findByID",id);
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
			   a = this.update("Mapper.Advertisement.updateByID", map);
		 } catch (BaseException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		 }
		   
		 return a ;
	}

	@Override
	public List<Advertisement> findAll() throws BaseException {
		// TODO Auto-generated method stub
		return this.findByParam("Mapper.Advertisement.findAllByPage",null);
	}

	@Override
	public int removeByID(String id) {
		// TODO Auto-generated method stub
		 int a =0 ; 
		   
		 try {
			   Map map = new HashMap<String,String>();
			   map.put("id", id);
			   a = this.update("Mapper.Advertisement.updateByID", map);
		 } catch (BaseException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		 }
		return a;
	}

	@Override
	public int update(Advertisement ad) {
		// TODO Auto-generated method stub
		 int a =0 ; 
		   
		 try {
			   a = this.update("Mapper.Advertisement.updateModel", ad);
		 } catch (BaseException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		 }
		return a;
	}


}
