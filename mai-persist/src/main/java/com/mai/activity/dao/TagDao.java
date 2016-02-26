package com.mai.activity.dao;

import java.util.List;

import com.mai.activity.entity.Tag;
import com.mai.framework.exception.BaseException;

/**
 * Created by denghao on 15/10/5.
 */
public interface TagDao {
    /**
     * 获取所有活动标签
     *
     * @return
     * @throws BaseException
     */
    public List<Tag> findTagList() throws BaseException;

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
}
