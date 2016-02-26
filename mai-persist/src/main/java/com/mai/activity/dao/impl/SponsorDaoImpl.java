package com.mai.activity.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mai.activity.dao.SponsorDao;
import com.mai.activity.entity.Sponsor;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;

/**
 * Created by denghao on 15/10/9.
 */
@Component
public class SponsorDaoImpl extends BaseDaoImpl implements SponsorDao {
    @Override
    public Sponsor insertSponsor(Sponsor sponsor) throws BaseException {
            this.insert("Mapper.Sponsor.insert",sponsor);
        return sponsor;
    }

    @Override
    public Integer updateSponsor(Sponsor sponsor) throws BaseException {
            return this.update("Mapper.Sponsor.updateSponsor",sponsor);
    }

    @Override
    public Integer updateSponsorStatusByID(Map<String, String> params) throws BaseException {
            return this.update("Mapper.Sponsor.updateSponsorStatusByID",params);
    }

    @Override
    public Sponsor findSponsorByID(Map<String, String> params) throws BaseException {
            Sponsor sponsor = null;
            List<Sponsor> sponsorList = this.findByParam("Mapper.Sponsor.findSponsorByID",params);
            if(sponsorList!=null && sponsorList.size()>0){
                sponsor = sponsorList.get(0);
            }
        return sponsor;
    }

    @Override
    public Page<Sponsor> findSponsorList(Map<String,String> params,PaginationParameters paginationParameters) throws BaseException {
        return this.findByPage("Mapper.Sponsor.findSponsorList", params, paginationParameters);
    }
}
