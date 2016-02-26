package com.mai.activity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mai.activity.dao.CardStarPhotoDao;
import com.mai.card.entity.CardStarPhoto;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;

/**
 * Created by bruce on 2015/12/21.
 */
@Component
public class CardStarPhotoDaoImpl extends BaseDaoImpl<CardStarPhoto, CardStarPhoto> implements CardStarPhotoDao {

	@Override
	public int add(CardStarPhoto n) {
		// TODO Auto-generated method stub
		int a =0 ; 
		   
		   try {
			   
			   a = this.insert("Mapper.CardStarPhoto.insert", n);
			   
		   } catch (BaseException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   }
		   
		   return a ;
	}

	@Override
	public Page<CardStarPhoto> findAllByPage(
			PaginationParameters paginationParameters, String wheresql)
			throws BaseException {
		// TODO Auto-generated method stub
		return this.findByPage("Mapper.CardStarPhoto.findByPage",wheresql,paginationParameters);
	}

	@Override
	public CardStarPhoto findByID(String id) {
		// TODO Auto-generated method stub
		CardStarPhoto a = null;
		try {
			List<CardStarPhoto> list = this.findByParam("Mapper.CardStarPhoto.findByID",id);
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
	public List<CardStarPhoto> findAll() throws BaseException {
		// TODO Auto-generated method stub
		return this.findByParam("Mapper.CardStarPhoto.findAllByPage",null);
	}

	@Override
	public int update(CardStarPhoto ad) {
		// TODO Auto-generated method stub
		 int a =0 ; 
		   
		 try {
			   a = this.update("Mapper.CardStarPhoto.updateModel", ad);
		 } catch (BaseException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		 }
		return a;
	}
}
