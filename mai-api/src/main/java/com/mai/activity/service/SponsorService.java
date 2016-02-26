package com.mai.activity.service;

import com.mai.activity.entity.Sponsor;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;

import java.util.List;
import java.util.Map;

/**
 * Created by denghao on 15/10/9.
 */
public interface SponsorService {
    public Sponsor addSponsor(Sponsor sponsor) throws BaseException;

    public Integer updateSponsor(Sponsor sponsor) throws BaseException;

    public Integer updateSponsorStatusByID(Map<String,String> params) throws BaseException;

    public Sponsor findSponsorByID(Map<String,String> params) throws BaseException;

    public Page<Sponsor> findSponsorList(Map<String,String> params,PaginationParameters paginationParameters) throws BaseException;
}
