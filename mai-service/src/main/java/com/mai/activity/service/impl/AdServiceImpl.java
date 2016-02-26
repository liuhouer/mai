package com.mai.activity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mai.activity.dao.AdDao;
import com.mai.app.entity.Advertisement;
import com.mai.activity.service.AdService;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;

/**
 * Created by Administrator on 2015/9/7.
 */
@Service("AdvertisementService")
public class AdServiceImpl implements AdService {
    @Autowired
    private AdDao adDao;
    
	@Override
	public int add(Advertisement lr) {
		// TODO Auto-generated method stub
		return adDao.add(lr);
	}

	@Override
	public Page<Advertisement> findAllByPage(PaginationParameters paginationParameters) throws BaseException {
		// TODO Auto-generated method stub
		return adDao.findAllByPage(paginationParameters);
	}

	@Override
	public int getMaxLevel() {
		// TODO Auto-generated method stub
		return adDao.getMaxLevel();
	}

	@Override
	public Advertisement findByID(String id) {
		// TODO Auto-generated method stub
		return adDao.findByID(id);
	}

	@Override
	public int updateByID(Integer praiseNum, Integer followNum, String ruleid) {
		// TODO Auto-generated method stub
		return adDao.updateByID( praiseNum,  followNum,  ruleid);
	}

	@Override
	public void updateAllStar() {
	}

	@Override
	public int removeByID(String id) {
		// TODO Auto-generated method stub
		return adDao.removeByID(id);
	}

	@Override
	public int update(Advertisement ad) {
		// TODO Auto-generated method stub
		return adDao.update(ad);
	}
}
