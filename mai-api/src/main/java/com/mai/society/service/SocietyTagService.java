package com.mai.society.service;

import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.society.entity.Society;
import com.mai.society.entity.SocietyTag;

import java.util.List;

/**
 * Created by denghao on 15/10/5.
 */
public interface SocietyTagService {
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

    /**
     * 获取社团标签信息列表
     *
     * @param mywhere
     * @return
     * @throws BaseException
     */
    public Page<Society> findSocietyTagDetailByPage(String mywhere,PaginationParameters paginationParameters) throws BaseException;
}
