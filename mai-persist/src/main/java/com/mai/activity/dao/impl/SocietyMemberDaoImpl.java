package com.mai.activity.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mai.activity.dao.SocietyMemberDao;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.society.entity.SocietyMember;

/**
 * Created by denghao on 15/10/5.
 */
@Component
public class SocietyMemberDaoImpl extends BaseDaoImpl implements SocietyMemberDao {
    @Override
    public SocietyMember findSocietymemberByID(String id) throws BaseException {
        SocietyMember sm = null;
        List<SocietyMember> sms = this.findByParam("Mapper.Societymember.findSocietymemberByID",id);
        if(sms!=null && sms.size() > 0){
            sm = sms.get(0);
        }
        return sm;
    }

    @Override
    public Integer updateSocietymemberStatus(Map params) throws BaseException {
                return this.update("Mapper.Societymember.updateSocietymemberStatus",params);
    }

    @Override
    public Page<SocietyMember> findSocietymemberList(Map params,PaginationParameters paginationParameters) throws BaseException {
                return this.findByPage("Mapper.Societymember.findSocietymemberList", params, paginationParameters);
    }

	@Override
	public List<SocietyMember> findSocietymemberList(Map map) throws BaseException {
		// TODO Auto-generated method stub
		return this.findByParam("Mapper.Societymember.findSocietymemberList", map);
	}

    @Override
    public SocietyMember findSocietymemberBySidANDPid(Map params) throws BaseException {
        SocietyMember sm = null;
        List<SocietyMember> sms = this.findByParam("Mapper.Societymember.findSocietymemberBySidANDPid",params);
        if(sms!=null && sms.size() > 0){
            sm = sms.get(0);
        }
        return sm;
    }

    @Override
    public SocietyMember insertSocietyMember(SocietyMember societyMember) throws BaseException {
                this.insert("Mapper.Societymember.insert",societyMember);
            return societyMember;
    }

    @Override
    public Integer getSocietymemberNum(String societyID) throws BaseException {
        SocietyMember societymember=new SocietyMember();
        societymember.setSocietyID(societyID);
        societymember.setMemberStatus(SocietyMember.MEMBERSTATUS_CHECKKED);
        return (Integer) this.getSqlSession().selectOne("Mapper.SocietyMember.getSocietymemberNum",societymember);
    }
}
