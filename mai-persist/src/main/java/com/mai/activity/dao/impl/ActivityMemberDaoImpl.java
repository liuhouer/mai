package com.mai.activity.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mai.activity.dao.ActivityMemberDao;
import com.mai.activity.entity.ActivityMember;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;

/**
 * Created by denghao on 15/10/3.
 */
@Repository
public class ActivityMemberDaoImpl extends BaseDaoImpl implements ActivityMemberDao {
    @Override
    public ActivityMember findActivitymemberByID(String id) throws BaseException {
        ActivityMember actm = null;
        List<ActivityMember> actms = this.findByParam("Mapper.Activitymember.findActivitymemberByID",id);
        if(actms!=null && actms.size() > 0){
            actm = actms.get(0);
        }
        return actm;
    }

    @Override
    public Integer updateActivitymemberStatus(Map params) throws BaseException {
        return this.update("Mapper.Activitymember.updateActivitymemberStatus",params);
    }

    @Override
    public Page<ActivityMember> findActivitymemberList(Map params,PaginationParameters paginationParameters) throws BaseException {
        return this.findByPage("Mapper.Activitymember.findActivitymemberList", params,paginationParameters);
    }

	@Override
	public List<ActivityMember> findActivitymemberList(Map params) throws BaseException {
		// TODO Auto-generated method stub
		
		return this.findByParam("Mapper.Activitymember.findActivitymemberList", params);
	}

    public Integer findActivitymemberCountByStatus(Map<String, String> params) throws BaseException {
            List<Integer> counts = this.findByParam("Mapper.Activitymember.findActivitymemberCountByStatus",params);
            if(counts!=null && counts.size()>0){
                return counts.get(0);
            }else{
                return 0;
            }
    }

    @Override
    public Integer updateActMemberVALIDNOTbyActID(Map<String,String> params) throws BaseException {
            return this.update("Mapper.Activitymember.updateActMemberVALIDNOTbyActID",params);
    }

    @Override
    public Integer getActivitymemberNum(String activityID) throws BaseException {
            ActivityMember activityMember = new ActivityMember();
            activityMember.setActivityID(activityID);
            activityMember.setMemberStatus(ActivityMember.MEMBERSTATUS_CHECKKED);
            return (Integer) this.getSqlSession().selectOne("apper.Activitymember.getActivitymemberNum",activityMember);
    }
}
