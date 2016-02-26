package com.mai.activity.dao;

import java.util.List;

import com.mai.activity.entity.LevelRule;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;

/**
 * Created by bruce on 2015/9/29.
 */
public interface LevelRuleDao {

	public int add(LevelRule lr);

	public Page<LevelRule> findAllByPage(PaginationParameters paginationParameters) throws BaseException;

	public int getMaxLevel();

	public LevelRule findByID(String ruleid);

	public int updateByID(Integer praiseNum, Integer followNum, String ruleid);

	public List<LevelRule> findAll() throws BaseException;



}
