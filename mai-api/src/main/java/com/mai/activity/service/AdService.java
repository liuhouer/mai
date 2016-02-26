package com.mai.activity.service;

import com.mai.app.entity.Advertisement;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;

/**
 * Created by bruce on 2015-10-19.
 */
public interface AdService {

	public int add(Advertisement lr);

	public Page<Advertisement> findAllByPage(PaginationParameters paginationParameters) throws BaseException;

	public int getMaxLevel();

	public Advertisement findByID(String ruleid);

	public int updateByID(Integer praiseNum, Integer followNum, String ruleid);
	
	/**
	 * 批量更新所有社团的星级
	 */
	public void updateAllStar();

	public int removeByID(String id);

	public int update(Advertisement ad);
	
}
