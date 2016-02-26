package com.mai.activity.dao;

import java.util.List;

import com.mai.framework.exception.BaseException;
import com.mai.society.entity.SocietyTag;

/**
 * Created by denghao on 15/10/5.
 */
public interface SocietyTagDao {
    /**
     * 获取所有
     *
     * @return
     * @throws BaseException
     */
    public List<SocietyTag> findSTagList() throws BaseException;

    /**
     * 获取所有标签后台管理用
     *
     * @return
     * @throws BaseException
     */
    public List<SocietyTag> findSocietyTagInfoList() throws BaseException;


    /**
     * 修改社团标签
     * @param societyTag
     * @return
     * @throws BaseException
     */
    public Integer updateSocietyTag(SocietyTag societyTag) throws BaseException;

    /**
     * 根据ID获取社团标签
     * @param tagID
     * @return
     * @throws BaseException
     */
    public SocietyTag findSocietyTagByID(String tagID) throws BaseException;

    /**
     * 根据类型ID删除SocietyTag
     * @param tid
     * @throws BaseException
     */
    public Integer deleteSocietyTagByTID(String tid) throws BaseException;

    /**
     * 批量插入
     * @param stlist
     * @throws BaseException
     */
    public Integer insertBatch(List<SocietyTag> stlist) throws BaseException;
}
