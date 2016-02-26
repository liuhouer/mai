package com.mai.card.service.impl;

import com.mai.activity.dao.CardStarPhotoDao;
import com.mai.card.entity.CardStarPhoto;
import com.mai.card.service.CardStarPhotoService;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bruce on 2015-10-19.
 */
@Service
public class CardStarPhotoServiceImpl implements CardStarPhotoService {

	@Autowired
	private CardStarPhotoDao cardDao;

	@Override
	public int add(CardStarPhoto n) {
		// TODO Auto-generated method stub
		return cardDao.add(n);
	}

	@Override
	public Page<CardStarPhoto> findAllByPage(
			PaginationParameters paginationParameters, String wheresql)
			throws BaseException {
		// TODO Auto-generated method stub
		return cardDao.findAllByPage(paginationParameters, wheresql);
	}

	@Override
	public CardStarPhoto findByID(String id) {
		// TODO Auto-generated method stub
		return cardDao.findByID(id);
	}

	@Override
	public List<CardStarPhoto> findAll() throws BaseException {
		// TODO Auto-generated method stub
		return cardDao.findAll();
	}

	@Override
	public int update(CardStarPhoto ad) {
		// TODO Auto-generated method stub
		return cardDao.update(ad);
	}


}
