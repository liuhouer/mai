package com.mai.activity.dao;

import java.util.Map;

import com.mai.activity.entity.Sponsor;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;

/**
 * Created by denghao on 15/10/9.
 */
public interface SponsorDao {

    public Sponsor insertSponsor(Sponsor sponsor) throws BaseException;

    public Integer updateSponsor(Sponsor sponsor) throws BaseException;

    public Integer updateSponsorStatusByID(Map<String,String> params) throws BaseException;

    public Sponsor findSponsorByID(Map<String,String> params) throws BaseException;

    public Page<Sponsor> findSponsorList(Map<String,String> params,PaginationParameters paginationParameters) throws BaseException;
}
