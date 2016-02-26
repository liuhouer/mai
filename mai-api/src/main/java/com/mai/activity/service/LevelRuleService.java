package com.mai.activity.service;

import com.mai.activity.entity.LevelRule;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;

/**
 * Created by bruce on 2015-10-19.
 */
public interface LevelRuleService {

	public int add(LevelRule lr);

	public Page<LevelRule> findAllByPage(PaginationParameters paginationParameters) throws BaseException;

	public int getMaxLevel();

	public LevelRule findByID(String ruleid);

	public int updateByID(Integer praiseNum, Integer followNum, String ruleid);
	
	/**
	 * 批量更新所有社团的星级
	 */
	public void updateAllStar();
	
}
