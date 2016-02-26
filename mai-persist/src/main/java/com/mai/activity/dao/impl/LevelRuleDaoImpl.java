package com.mai.activity.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.mai.activity.dao.LevelRuleDao;
import com.mai.activity.entity.LevelRule;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;

/**
 * 信息
 * Created by bruce on 2015-10-19.
 */
@Component
public class LevelRuleDaoImpl extends BaseDaoImpl<LevelRule, LevelRule> implements LevelRuleDao {

	@Override
	public int add(LevelRule lr) {
		// TODO Auto-generated method stub
		   int a =0 ; 
		   
		   try {
			   
			   a = this.insert("Mapper.LevelRule.insert", lr);
			   
		   } catch (BaseException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   }
		   
		   return a ;
	}

	@Override
	public Page<LevelRule> findAllByPage(PaginationParameters paginationParameters) throws BaseException {
		// TODO Auto-generated method stub
		return this.findByPage("Mapper.LevelRule.findAllByPage",null,paginationParameters);
	}

	@Override
	public int getMaxLevel() {
		// TODO Auto-generated method stub
		int a = 1;
		try {
			List<LevelRule> list = this.findByParam("Mapper.LevelRule.getMaxLevel",null);
			if(list!=null && list.size()>0){
				LevelRule model  = list.get(0);
				String aa = "";
				if(model!=null){
					aa = model.getMax();
				}
				if(StringUtils.isNotEmpty(aa)){
					a = Integer.parseInt(aa)+1;
				}
			}
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			a = 1;
		}
		return a;
	}

	@Override
	public LevelRule findByID(String ruleid) {
		// TODO Auto-generated method stub
		LevelRule a = null;
		try {
			List<LevelRule> list = this.findByParam("Mapper.LevelRule.findByID",ruleid);
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
			   a = this.update("Mapper.LevelRule.updateByID", map);
		 } catch (BaseException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		 }
		   
		 return a ;
	}

	@Override
	public List<LevelRule> findAll() throws BaseException {
		// TODO Auto-generated method stub
		return this.findByParam("Mapper.LevelRule.findAllByPage",null);
	}


}
