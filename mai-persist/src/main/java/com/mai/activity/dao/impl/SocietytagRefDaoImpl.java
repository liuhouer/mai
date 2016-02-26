package com.mai.activity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mai.activity.dao.SocietytagRefDao;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import com.mai.society.entity.SocietytagRef;

/**
 * Created by denghao on 15/10/5.
 */
@Component
public class SocietytagRefDaoImpl extends BaseDaoImpl implements SocietytagRefDao {
    @Override
    public List<SocietytagRef> findSocietytagRefBySID(String sid) throws BaseException {
                return this.findByParam("Mapper.SocietytagRef.findSocietytagRefBySID",sid);
    }

    @Override
    public Integer insertBatch(List<SocietytagRef> stlist) throws BaseException {
                return this.insert("Mapper.SocietytagRef.insertBatch",stlist);
    }

    @Override
    public Integer deleteSocietytagRefBySID(String sid) throws BaseException {
                return this.delete("Mapper.SocietytagRef.deleteSocietytagRefBySID",sid);
    }
}
