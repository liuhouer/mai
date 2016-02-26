package com.mai.activity.dao.impl;

import org.springframework.stereotype.Component;

import com.mai.activity.dao.FeedbackDao;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.user.entity.Feedback;

/**
 * 信息
 * Created by bruce on 2015-10-22.
 */
@Component
public class FeedbackDaoImpl extends BaseDaoImpl<Feedback, Feedback> implements FeedbackDao {

	@Override
	public Page<Feedback> findAllByPage(PaginationParameters paginationParameters) throws BaseException {
		// TODO Auto-generated method stub
		return this.findByPage("Mapper.Feedback.findAllByPage",null,paginationParameters);
	}



}
