package com.mai.activity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mai.activity.dao.FeedbackDao;
import com.mai.activity.service.FeedbackService;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.user.entity.Feedback;


/**
 * @author bruce 2015-10-22
 *
 */
@Service("FeedbackvertisementService")
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackDao feedbackDao;

	@Override
	public Page<Feedback> findAllByPage(PaginationParameters paginationParameters) throws BaseException {
		// TODO Auto-generated method stub
		return feedbackDao.findAllByPage(paginationParameters);
	}

}
