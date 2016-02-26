package com.mai.activity.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mai.activity.dao.SocietyDao;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.society.entity.Society;
import com.mai.vo.SocietyRunningVO;

/**
 * 信息
 * Created by bruce on 2015/9/29.
 */
@Component
public class SocietyDaoImpl extends BaseDaoImpl implements SocietyDao {
    /**
     * 根据状态为查询社团
     *
     * @param status
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    public List<Society> getSocietyListByZT(String  status) throws BaseException {
        return this.findByParam("Mapper.Society.getSocietyListByZT", status);
    }

	public int updateSocietyStatusByID(String status,String id) {
		// TODO Auto-generated method stub
		Map map = new HashMap<String, String>();
		map.put("status", status);
		map.put("id", id);
		int a = 0;
		 try {
			a =  this.update("Mapper.Society.updateSocietyStatusByID",map);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return  a;
	}

	public Society getSocietyByID(String id) throws BaseException {
		// TODO Auto-generated method stub
		List<Society> list = this.findByParam("Mapper.Society.getSocietyByID", id);
		Society model = null;
		if(list.size()>0){
			model = list.get(0);
		}
		return model;
	}

	@Override
	public Integer updateSociety(Society society) throws BaseException {
			return this.update("Mapper.Society.updateSociety",society);
	}

	@Override
	public Society findSocietyByAdminID(String adminID) throws BaseException {
				Society society = null;
				List<Society> societyList = this.findByParam("Mapper.Society.findSocietyByAdminID",adminID);
				if(societyList!=null && societyList.size()>0){
					society = societyList.get(0);
				}
				return society;
	}

	@Override
	public boolean OnlyOneST(String adminID) {
		// TODO Auto-generated method stub
		boolean a = false;
		try {
			
			List<Society> societyList = this.findByParam("Mapper.Society.findSocietyByAdminID",adminID);
			if( societyList.size()==1){
				a = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return a;
	}

	
	@Override
	public boolean OnlyOneEffectST(String adminID) {
		// TODO Auto-generated method stub
				boolean a = false;
				try {
					
					List<Society> societyList = this.findByParam("Mapper.Society.findSocietyByAdminIDAndSt",adminID);
					if( societyList.size()>=1){
						a = true;
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return a;
	}
	
	@Override
	public Page<Society> getSocietyPageByZT(String status,PaginationParameters paginationParameters) throws BaseException {
		// TODO Auto-generated method stub
		return this.findByPage("Mapper.Society.getSocietyListByZT",status,paginationParameters);
	}

	@Override
	public Integer updatePresident(Society society) throws BaseException {
			return this.update("Mapper.Society.updatePresident",society);
	}

	@Override
	public Society findSocietyByAdminIDAndValid(String adminID) throws BaseException {
			Society society = null;
			List<Society> societyList = this.findByParam("Mapper.Society.findSocietyByAdminIDAndSt",adminID);
			if(societyList!=null && societyList.size()>0){
				society = societyList.get(0);
			}
			return society;
	}

	@Override
	public List<Society> getSocietyListByZTOver0() throws BaseException {
		// TODO Auto-generated method stub
		return this.findByParam("Mapper.Society.getSocietyListByZTOver0", null);
	}

	@Override
	public Page<SocietyRunningVO> findSocietyRunningByPage(String mywhere, PaginationParameters paginationParameters) throws BaseException {
			return this.findByPage("Mapper.Society.findSocietyRunningByPage",mywhere,paginationParameters);
	}

	@Override
	public Integer updateRecommendNoByID(Map params) throws BaseException {
			return this.update("Mapper.Society.updateRecommendNoByID",params);
	}

	@Override
	public Integer updateSocietyCategoryNameBySCID(Map params) throws BaseException {
			return this.update("Mapper.Society.updateSocietyCategoryNameBySCID",params);
	}

	@Override
	public Page<Society> findSocietyCategoryDetailByPage(String mywhere, PaginationParameters paginationParameters) throws BaseException {
			return this.findByPage("Mapper.Society.findSocietyCategoryDetailByPage",mywhere,paginationParameters);
	}

	@Override
	public Page<Society> findSocietyTagDetailByPage(String mywhere, PaginationParameters paginationParameters) throws BaseException {
			return this.findByPage("Mapper.Society.findSocietyTagDetailByPage",mywhere,paginationParameters);
	}

	@Override
	public int add(Society society) {
		// TODO Auto-generated method stub
		 int a =0 ; 
		   
		   try {
			   
			   a = this.insert("Mapper.Society.insert", society);
			   
		   } catch (BaseException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   }
		   
		 return a ;
	}

	@Override
	public Integer updateSocietyJoinPersonNum(String societyID, Integer joinPersonNum) throws BaseException {
		Society society=new Society();
		society.setSocietyID(societyID);
		society.setJoinPersonNum(joinPersonNum);
		return this.update("Mapper.Society.updateSocietyJoinPersonNum",society);
	}

	@Override
	public int mdName(String id, String name) {
		// TODO Auto-generated method stub
		int a = 0;
		try{
			Map map = new HashMap<String,String>();
			map.put("id", id);
			map.put("name", name);
			a = this.update("Mapper.Society.mdSocietyNameByID",map);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return a;
	}


}
