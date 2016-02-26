package com.mai.activity.dao.impl;

import com.mai.activity.dao.SocietyFollowDao;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.society.entity.SocietyFollow;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fengdzh on 2015/10/10.
 */
@Component
public class SocietyFollowDaoImpl extends BaseDaoImpl<SocietyFollow,SocietyFollow> implements SocietyFollowDao {

    /**
     * 分页展示关注信息
     *
     * @param societyID
     * @param paginationParameters
     * @return
     * @throws BaseException
     */
    @Override
    public Page<SocietyFollow> listSocietyFollowByPage(String societyID, PaginationParameters paginationParameters) throws BaseException {
        SocietyFollow societyFollow=new SocietyFollow();
        societyFollow.setSocietyID(societyID);
        return this.findByPage("Mapper.SocietyFollow.listSocietyFollowByPage",societyFollow,paginationParameters);
    }

    /**
     * 不分页展示关注信息
     *
     * @param societyID
     * @return
     * @throws BaseException
     */
    @Override
    public List<SocietyFollow> listSocietyFollowByPage(String societyID) throws BaseException {
        SocietyFollow societyFollow=new SocietyFollow();
        societyFollow.setSocietyID(societyID);
        return this.findByParam("Mapper.SocietyFollow.listSocietyFollowByPage", societyFollow);
    }
}
