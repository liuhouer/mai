package com.mai.activity.dao;

import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.society.entity.SocietyFollow;

import java.util.List;

/**
 *
 * Created by fengdzh on 2015/10/10.
 */
public interface SocietyFollowDao {

    /**
     * 分页展示关注信息
     * @param societyID
     * @param paginationParameters
     * @return
     * @throws BaseException
     */
    public Page<SocietyFollow> listSocietyFollowByPage(String societyID, PaginationParameters paginationParameters)throws BaseException;

    /**
     * 不分页展示关注信息
     *
     * @param societyID
     * @return
     * @throws BaseException
     */
    public List<SocietyFollow> listSocietyFollowByPage(String societyID) throws BaseException;

}
