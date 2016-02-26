package com.mai.activity.dao;

import java.util.List;

import com.mai.framework.exception.BaseException;
import com.mai.society.entity.SocietytagRef;

/**
 * Created by denghao on 15/10/5.
 */
public interface SocietytagRefDao {
    /**
     * 根据社团ID获取TAG选择内容
     *
     * @return
     * @throws BaseException
     */
    public List<SocietytagRef> findSocietytagRefBySID(String sid) throws BaseException;

    /**
     * 批量插入
     * @param stlist
     * @throws BaseException
     */
    public Integer insertBatch(List<SocietytagRef> stlist) throws BaseException;

    /**
     * 根据社团ID删除TAG
     * @param sid
     * @throws BaseException
     */
    public Integer deleteSocietytagRefBySID(String sid) throws BaseException;
}
