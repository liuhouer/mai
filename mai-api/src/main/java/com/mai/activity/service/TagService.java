package com.mai.activity.service;


import com.mai.activity.entity.Activity;
import com.mai.activity.entity.Tag;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;

import java.util.List;

/**
 * Created by Administrator on 2015/9/7.
 */
public interface TagService {

    public List<Tag> getTagList() throws BaseException;

    /**
     * 获取所有标签后台管理用
     *
     * @return
     * @throws BaseException
     */
    public List<Tag> findTagInfoList() throws BaseException;


    /**
     * 修改活动标签
     * @param tag
     * @return
     * @throws BaseException
     */
    public Integer updateTag(Tag tag) throws BaseException;

    /**
     * 根据ID获取活动标签
     * @param tagID
     * @return
     * @throws BaseException
     */
    public Tag findTagByID(String tagID) throws BaseException;

    /**
     * 根据类型ID删除Tag
     * @param tid
     * @throws BaseException
     */
    public Integer deleteTagByTID(String tid) throws BaseException;

    /**
     * 批量插入
     * @param tlist
     * @throws BaseException
     */
    public Integer insertBatch(List<Tag> tlist) throws BaseException;

    /**
     * 获取社团标签信息列表
     *
     * @param mywhere
     * @return
     * @throws BaseException
     */
    public Page<Activity> findTagDetailByPage(String mywhere,PaginationParameters paginationParameters) throws BaseException;
}
