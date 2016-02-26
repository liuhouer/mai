package com.mai.activity.service;

import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.user.entity.Feedback;

/**
 * Created by bruce on 2015-10-19.
 */
public interface FeedbackService {


	public Page<Feedback> findAllByPage(PaginationParameters paginationParameters) throws BaseException;

	
}
