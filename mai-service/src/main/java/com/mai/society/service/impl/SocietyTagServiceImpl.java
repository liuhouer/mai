package com.mai.society.service.impl;

import java.util.List;

import com.mai.activity.dao.SocietyDao;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.society.entity.Society;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mai.activity.dao.SocietyTagDao;
import com.mai.framework.exception.BaseException;
import com.mai.society.entity.SocietyTag;
import com.mai.society.service.SocietyTagService;

/**
 * Created by denghao on 15/10/5.
 */
@Service("societyTagService")
public class SocietyTagServiceImpl implements SocietyTagService{
    @Autowired
    private SocietyTagDao societyTagDao;

    @Autowired
    private SocietyDao societyDao;

    @Override
    public List<SocietyTag> findSTagList() throws BaseException {
        return societyTagDao.findSTagList();

    }

    @Override
    public Integer deleteSocietyTagByTID(String tid) throws BaseException {
                return this.societyTagDao.deleteSocietyTagByTID(tid);
    }

    @Override
    public List<SocietyTag> findSocietyTagInfoList() throws BaseException {
                return this.societyTagDao.findSocietyTagInfoList();
    }

    @Override
    public Integer updateSocietyTag(SocietyTag societyTag) throws BaseException {
                return this.societyTagDao.updateSocietyTag(societyTag);
    }

    @Override
    public SocietyTag findSocietyTagByID(String tagID) throws BaseException {
                return this.societyTagDao.findSocietyTagByID(tagID);
    }

    @Override
    public Integer insertBatch(List<SocietyTag> stlist) throws BaseException {
                return this.societyTagDao.insertBatch(stlist);
    }

    @Override
    public Page<Society> findSocietyTagDetailByPage(String mywhere, PaginationParameters paginationParameters) throws BaseException {
                return this.societyDao.findSocietyTagDetailByPage(mywhere,paginationParameters);
    }


}
