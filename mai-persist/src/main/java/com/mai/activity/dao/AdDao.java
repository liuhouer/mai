package com.mai.activity.dao;

import java.util.List;

import com.mai.app.entity.Advertisement;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;

/**
 * Created by bruce on 2015/9/29.
 */
public interface AdDao {

	public int add(Advertisement lr);

	public Page<Advertisement> findAllByPage(PaginationParameters paginationParameters) throws BaseException;

	public int getMaxLevel();

	public Advertisement findByID(String ruleid);

	public int updateByID(Integer praiseNum, Integer followNum, String ruleid);

	public List<Advertisement> findAll() throws BaseException;

	public int removeByID(String id);

	public int update(Advertisement ad);



}
