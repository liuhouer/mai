package com.mai.activity.dao;

import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.user.entity.Feedback;

/**
 * Created by bruce on 2015/10/22.
 */
public interface FeedbackDao {
	
	public Page<Feedback> findAllByPage(PaginationParameters paginationParameters) throws BaseException;


}
