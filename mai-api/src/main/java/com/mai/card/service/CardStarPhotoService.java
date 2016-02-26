package com.mai.card.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.mai.card.entity.CardStarPhoto;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;

/**
 * Created by bruce on 2015-10-19.
 */
public interface CardStarPhotoService {

public int add(CardStarPhoto n);
	
	public Page<CardStarPhoto> findAllByPage(PaginationParameters paginationParameters,String wheresql) throws BaseException;

	public CardStarPhoto findByID(String id);

	public List<CardStarPhoto> findAll() throws BaseException;


	public int update(CardStarPhoto ad);

}
