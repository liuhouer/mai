package com.mai.activity.service.impl;

import java.util.List;

import com.mai.activity.dao.ActivityDao;
import com.mai.activity.entity.Activity;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mai.activity.dao.TagDao;
import com.mai.activity.entity.Tag;
import com.mai.activity.service.TagService;
import com.mai.framework.exception.BaseException;

/**
 * Created by Administrator on 2015/9/7.
 */
@Service("tagService")
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;

    @Autowired
    private ActivityDao activityDao;

    @Override
    public List<Tag> getTagList() throws BaseException {
        List<Tag> tagList = tagDao.findTagList();
        return tagList;
    }

    @Override
    public List<Tag> findTagInfoList() throws BaseException {
            return tagDao.findTagInfoList();
    }

    @Override
    public Integer updateTag(Tag tag) throws BaseException {
            return tagDao.updateTag(tag);
    }

    @Override
    public Tag findTagByID(String tagID) throws BaseException {
            return tagDao.findTagByID(tagID);
    }

    @Override
    public Integer deleteTagByTID(String tid) throws BaseException {
            return tagDao.deleteTagByTID(tid);
    }

    @Override
    public Integer insertBatch(List<Tag> tlist) throws BaseException {
            return tagDao.insertBatch(tlist);
    }

    @Override
    public Page<Activity> findTagDetailByPage(String mywhere, PaginationParameters paginationParameters) throws BaseException {
            return activityDao.findTagDetailByPage(mywhere,paginationParameters);
    }
}
