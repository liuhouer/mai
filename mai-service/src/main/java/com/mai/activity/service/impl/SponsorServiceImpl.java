package com.mai.activity.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.mai.activity.dao.SocietyRedisDao;
import com.mai.activity.entity.Activity;
import com.mai.activity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.mai.activity.dao.ActivityDao;
import com.mai.activity.dao.SponsorDao;
import com.mai.activity.entity.Sponsor;
import com.mai.activity.service.SponsorService;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;

/**
 * Created by denghao on 15/10/9.
 */
@Service("sponsorService")
public class SponsorServiceImpl implements SponsorService{

    @Autowired
    private SponsorDao sponsorDao;

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private ActivityService activityService;

    @Override
    public Sponsor addSponsor(Sponsor sponsor) throws BaseException {
                this.sponsorDao.insertSponsor(sponsor);
                if(!StringUtils.isBlank(sponsor.getActivityID())){
                    Map<String,String> params = new HashMap<String,String>();
                    params.put("activityID",sponsor.getActivityID());
                    params.put("supportDescription",sponsor.getSponsorName());
                    activityDao.updateActivitySupportInfoByAID(params);
                }
                return sponsor;
    }

    @Override
    public Integer updateSponsor(Sponsor sponsor) throws BaseException {
                if(!StringUtils.isBlank(sponsor.getActivityID())){
                    Map<String,String> params = new HashMap<String,String>();
                    params.put("activityID",sponsor.getActivityID());
                    params.put("supportDescription", sponsor.getSponsorName());
                    Activity activity = activityService.getActivityFromRedisByID(sponsor.getActivityID());
                    activity.setSupportDescription(sponsor.getSponsorName());
                    activityService.updateRedisActivity(activity);
                    activityDao.updateActivitySupportInfoByAID(params);
                }
                return this.sponsorDao.updateSponsor(sponsor);
    }

    @Override
    public Integer updateSponsorStatusByID(Map<String, String> params) throws BaseException {
                return this.sponsorDao.updateSponsorStatusByID(params);
    }

    @Override
    public Sponsor findSponsorByID(Map<String, String> params) throws BaseException {
                return this.sponsorDao.findSponsorByID(params);
    }

    @Override
    public Page<Sponsor> findSponsorList(Map<String,String> params,PaginationParameters paginationParameters) throws BaseException {
                return this.sponsorDao.findSponsorList(params,paginationParameters);
    }
}
