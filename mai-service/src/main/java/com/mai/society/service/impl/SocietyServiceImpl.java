package com.mai.society.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.mai.activity.dao.*;
import com.mai.activity.entity.Activity;
import com.mai.activity.entity.Log;
import com.mai.activity.service.ActivityService;
import com.mai.framework.utils.UUIDUtil;
import com.mai.society.entity.*;
import com.mai.user.entity.Person;
import com.mai.user.entity.Role;
import com.mai.user.entity.User;
import com.mai.user.entity.UserroleRef;
import com.mai.user.service.PersonService;
import com.mai.user.service.UserService;
import com.mai.util.CommonUtil;
import com.mai.vo.SocietyRunningVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mai.activity.dao.ActivityDao;
import com.mai.activity.dao.LogDao;
import com.mai.activity.dao.SocietyDao;
import com.mai.activity.dao.SocietyMemberDao;
import com.mai.activity.dao.SocietytagRefDao;
import com.mai.activity.dao.UserroleRefDao;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.framework.utils.DateUtil;
import com.mai.society.service.SocietyService;


/**
 * 社团应用
 * Created by fengdzh on 2015/9/16.
 */
@Service("societyService")
public class SocietyServiceImpl implements SocietyService {
	
    @Autowired
    private SocietyDao societydao;

    @Autowired
    private SocietytagRefDao societytagRefDao;

    @Autowired
    private SocietyMemberDao societyMemberDao;

    @Autowired
    private LogDao logDao;

    @Autowired
    private UserroleRefDao userroleRefDao;

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private SystemSocietyFollowDao systemSocietyFollowDao;

    @Autowired
    private SystemSocietyPraiseDao systemSocietyPraiseDao;
    @Autowired
    private UserService userService;
    @Autowired
    private SocietyRedisDao societyRedisDao;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private PersonService personService;

//    @Value("#{configProperties['redis.user.society.list.expired']}")
//    private String user_society_list_expired;

    /**
     * 新增社团
     *
     * @param society
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public int addSociety(Society society) throws BaseException {
        int return_value = societydao.add(society);
        if(return_value>0){
            societyRedisDao.setSociety(society);
            updateRedisSocietyStatus(society.getSocietyID(), "" + society.getStatus(), 0);
            updateRedisSocietyScore(society.getSocietyID(), 0);
//            updateRedisSocietyRecommendNo(society.getSocietyID(), 0);
            updateRedisSocietyLocationID(society.getSocietyID(), society.getLocationID(), 0);
            updateRedisSocietyCategoryID(society.getSocietyID(), society.getSocietyCategoryID(), 0);
            updateRedisSocietySchoolID(society.getSocietyID(),society.getSchoolID(),0);
        }
        return return_value;
    }

    /**
     * 通过ID获取社团信息
     *
     * @param societyID
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public Society getSocietyByID(String societyID) throws BaseException {
        //先从缓存获取
        Society society = societyRedisDao.getSocietyByID(societyID);
        if(null==society){
            society = societydao.getSocietyByID(societyID);
            SystemSocietyPraise systemSocietyPraise = systemSocietyPraiseDao.findSystemSocietyPraiseBySID(societyID);
            if(systemSocietyPraise!=null){
                int systemPraiseNum = systemSocietyPraise.getSocietyPraiseNum()==null?0:systemSocietyPraise.getSocietyPraiseNum();
                society.setSystempraiseNum(systemPraiseNum);
            }
            SystemSocietyFollow systemSocietyFollow = systemSocietyFollowDao.findSystemSocietyFollowBySID(societyID);
            if(systemSocietyFollow!=null){
                int systemFollowNum = systemSocietyFollow.getSocietyFollowNum()==null?0:systemSocietyFollow.getSocietyFollowNum();
                society.setSystemfollowNum(systemFollowNum);
            }
            societyRedisDao.setSociety(society);
            double praiseNum = null == society.getPraiseNum()?0:society.getPraiseNum();
            updateRedisSocietyStatus(society.getSocietyID(), "" + society.getStatus(), praiseNum);
            updateRedisSocietyScore(society.getSocietyID(), praiseNum);
            Integer recommendNo = null == society.getRecommendNo()?0:society.getRecommendNo();
            if(recommendNo <= 0){
                societyRedisDao.deleteRecommendNoFromSet(society.getSocietyID());
            }else{
                updateRedisSocietyRecommendNo(society.getSocietyID(), recommendNo);
            }
            updateRedisSocietyLocationID(society.getSocietyID(), society.getLocationID(), praiseNum);
            updateRedisSocietyCategoryID(society.getSocietyID(), society.getSocietyCategoryID(), praiseNum);
            updateRedisSocietySchoolID(society.getSocietyID(), society.getSchoolID(), praiseNum);
        }
        return society;
    }
    /**
     * 我的社团列表
     *
     * @param adminID
     * @param paginationParameters
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public Page<Society> listMySocietyPage(String adminID, PaginationParameters paginationParameters) throws BaseException {
        return null;
    }

	@Override
	public List<Society> getSocietyListByZT(String status) throws BaseException {
		// TODO Auto-generated method stub
		return societydao.getSocietyListByZT(status);
	}

	
	@Override
	public Page<Society> getSocietyPageByZT(String status,PaginationParameters paginationParameters) throws BaseException {
		// TODO Auto-generated method stub
		return societydao.getSocietyPageByZT(status, paginationParameters);
	}
	
	
    @Override
    public List<SocietytagRef> findSocietytagRefListBySID(String sid) throws BaseException {
            return societytagRefDao.findSocietytagRefBySID(sid);
    }

    @Override
    public Integer updateSocietyInfo(Society society, List<SocietytagRef> societytagRefs) throws BaseException {
        this.societytagRefDao.deleteSocietytagRefBySID(society.getSocietyID());
        if(societytagRefs!=null && societytagRefs.size()>0){
            this.societytagRefDao.insertBatch(societytagRefs);
            String tagids = "";
            for(SocietytagRef societytagRef : societytagRefs){
                tagids += societytagRef.getSocietyTagID() + ",";
            }
            if(tagids.endsWith(",")){
                society.setTagIDs(tagids.substring(0,tagids.length()-1));
            }else{
                society.setTagIDs(tagids);
            }
        }
        if(society.getStatus() == Society.STATUS_RELEASED){
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("societyID",society.getSocietyID());
            params.put("personID",society.getAdminID());
            SocietyMember socAdmin = societyMemberDao.findSocietymemberBySidANDPid(params);
            if(socAdmin == null && society.getMemberNum() >= society.getJoinPersonNum()+1){
                SocietyMember societyMember = new SocietyMember();
                societyMember.setSocietyID(society.getSocietyID());
                societyMember.setSocietyMemberID(UUIDUtil.getUUID());
                societyMember.setCreateTime(Calendar.getInstance().getTimeInMillis());
                societyMember.setPersonID(society.getAdminID());
                societyMember.setMemberStatus(SocietyMember.MEMBERSTATUS_CHECKKED);
                societyMemberDao.insertSocietyMember(societyMember);
                society.setJoinPersonNum(society.getJoinPersonNum() + 1);
            }
            Person person = personService.getPersonByID(society.getAdminID());
            if(person.getIsPresident() == Person.ISPRESIDENT_NO){
                person.setIsPresident(Person.ISPRESIDENT_YES);
                personService.updatePerson(person);
            }
        }
        Integer renturn_val = this.societydao.updateSociety(society);
        if(renturn_val > 0 ){
            SystemSocietyPraise systemSocietyPraise = systemSocietyPraiseDao.findSystemSocietyPraiseBySID(society.getSocietyID());
            if(systemSocietyPraise!=null){
                int systemPraiseNum = systemSocietyPraise.getSocietyPraiseNum()==null?0:systemSocietyPraise.getSocietyPraiseNum();
                society.setSystempraiseNum(systemPraiseNum);
            }
            SystemSocietyFollow systemSocietyFollow = systemSocietyFollowDao.findSystemSocietyFollowBySID(society.getSocietyID());
            if(systemSocietyFollow!=null){
                int systemFollowNum = systemSocietyFollow.getSocietyFollowNum()==null?0:systemSocietyFollow.getSocietyFollowNum();
                society.setSystemfollowNum(systemFollowNum);
            }
            Society oldSociety = societyRedisDao.getSocietyByID(society.getSocietyID());
            this.societyRedisDao.updateSociety(society);
            if(oldSociety != null){
                societyRedisDao.deleteStatusFromSet(oldSociety.getSocietyID(), "" + oldSociety.getStatus());
                societyRedisDao.deleteRecommendNoFromSet(oldSociety.getSocietyID());
                if(!StringUtils.isBlank(oldSociety.getLocationID())){
                    societyRedisDao.deleteLocationFromSet(oldSociety.getSocietyID(),oldSociety.getLocationID());
                }
                if(!StringUtils.isBlank(oldSociety.getSocietyCategoryID())){
                    societyRedisDao.deleteCategoryFromSet(oldSociety.getSocietyID(), oldSociety.getSocietyCategoryID());
                }
                if(!StringUtils.isBlank(oldSociety.getSchoolID())){
                    societyRedisDao.deleteSchoolFromSet(oldSociety.getSocietyID(), oldSociety.getSchoolID());
                }
                if(!StringUtils.isBlank(oldSociety.getTagIDs())){
                    String[] tags = oldSociety.getTagIDs().split(",");
                    for (int i = 0; i < tags.length; i++) {
                        societyRedisDao.deleteTagFromSet(oldSociety.getSocietyID(), tags[i]);
                    }
                }
            }
            double praiseNum = null == society.getPraiseNum()?0:society.getPraiseNum();
            updateRedisSocietyStatus(society.getSocietyID(), "" + society.getStatus(), praiseNum);
            updateRedisSocietyScore(society.getSocietyID(), praiseNum);
            Integer recommendNo = null == society.getRecommendNo() ? 0:society.getRecommendNo();
            if(recommendNo <= 0){
                societyRedisDao.deleteRecommendNoFromSet(society.getSocietyID());
            }else{
                updateRedisSocietyRecommendNo(society.getSocietyID(), recommendNo);
            }
            if(!StringUtils.isBlank(society.getLocationID())){
                updateRedisSocietyLocationID(society.getSocietyID(), society.getLocationID(), praiseNum);
            }
            if(!StringUtils.isBlank(society.getSocietyCategoryID())){
                updateRedisSocietyCategoryID(society.getSocietyID(), society.getSocietyCategoryID(), praiseNum);
            }
            if(!StringUtils.isBlank(society.getSchoolID())){
                updateRedisSocietySchoolID(society.getSocietyID(), society.getSchoolID(), praiseNum);
            }
            if(societytagRefs!=null && societytagRefs.size()>0){
                for(SocietytagRef societytagRef : societytagRefs){
                    updateRedisSocietyTagID(society.getSocietyID(),societytagRef.getSocietyTagID(), praiseNum);
                }
            }
        }
        return renturn_val;
    }

    @Override
    public SocietyMember findSocietymemberByID(String id) throws BaseException {
                    return societyMemberDao.findSocietymemberByID(id);
    }

    @Override
    public Integer updateSocietymemberStatus(Map<String, String> params, Log log) throws BaseException {
        if(log!=null){
            this.logDao.insertLog(log);
        }
            Integer returnint = this.societyMemberDao.updateSocietymemberStatus(params);
            if(returnint > 0){
                Integer joinPersonNum = this.societyMemberDao.getSocietymemberNum(params.get("sid"));
                Society _society = societyRedisDao.getSocietyByID(params.get("sid"));
                _society.setJoinPersonNum(joinPersonNum);
                societyRedisDao.updateSociety(_society);
                this.societydao.updateSocietyJoinPersonNum(params.get("sid"), joinPersonNum);
            }
            return returnint;
    }

    @Override
    public Page<SocietyMember> findSocietymemberListBySID(Map params,PaginationParameters paginationParameters) throws BaseException {
                    return societyMemberDao.findSocietymemberList(params,paginationParameters);
    }

    @Override
    public Society findSocietyByAdminID(String adminID) {
        try {
            return societydao.findSocietyByAdminID(adminID);
        } catch (BaseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Society findSocietyByAdminIDAndValid(String adminID) {
        try {
            return this.societydao.findSocietyByAdminIDAndValid(adminID);
        } catch (BaseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
	public int updateSocietyStatusByID(Society society,String PhoneNum) throws BaseException {
		int status = society.getStatus();
		String st = String.valueOf(status);
        if(Society.STATUS_OFFLINE == status){
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("status", Activity.ACTIVITY_STATUS_OFFLINE);
            params.put("societyID", society.getSocietyID());
            this.activityDao.updateActivityStatusBySocietyID(params);
            List<Activity> activityList = this.activityDao.findActivityBySocietyIDAndValid(society.getSocietyID());
            if(activityList != null && activityList.size() > 0){
                List<Log> logList = new ArrayList<Log>();
                    for(Activity activity : activityList){
                    	Log log = new Log();
                    	log.setCreateTime(Calendar.getInstance().getTimeInMillis());
                    	log.setLogtype(Log.TYPE_ACTIVITY);
                    	log.setLogAuthor(PhoneNum);
                    	log.setLogDesc(Log.ACTIVITY_BATCH_DESC);
                    	String uid = UUIDUtil.getUUID().toString();
                        log.setLogID(uid);
                        log.setActID(activity.getActivityID());
                        logList.add(log);
                        activityService.updateRedisActivity(activity);
                    }
                if(logList.size()>0){
                    this.logDao.insertBatch(logList);
                }
            }
        }

        Integer renturn_val = societydao.updateSocietyStatusByID(st, society.getSocietyID());
		if(renturn_val>0){
            Society _society = societydao.getSocietyByID(society.getSocietyID());
            SystemSocietyPraise systemSocietyPraise = systemSocietyPraiseDao.findSystemSocietyPraiseBySID(society.getSocietyID());
            if(systemSocietyPraise!=null){
                int systemPraiseNum = systemSocietyPraise.getSocietyPraiseNum()==null?0:systemSocietyPraise.getSocietyPraiseNum();
                _society.setSystempraiseNum(systemPraiseNum);
            }
            SystemSocietyFollow systemSocietyFollow = systemSocietyFollowDao.findSystemSocietyFollowBySID(society.getSocietyID());
            if(systemSocietyFollow!=null){
                int systemFollowNum = systemSocietyFollow.getSocietyFollowNum()==null?0:systemSocietyFollow.getSocietyFollowNum();
                _society.setSystemfollowNum(systemFollowNum);
            }
            List<SocietytagRef> societytagRefs = this.findSocietytagRefListBySID(_society.getSocietyID());
            if(societytagRefs!=null && societytagRefs.size()>0){
                String tagids = "";
                for(SocietytagRef societytagRef : societytagRefs){
                    tagids += societytagRef.getSocietyTagID() + ",";
                }
                if(tagids.endsWith(",")){
                    _society.setTagIDs(tagids.substring(0,tagids.length()-1));
                }else{
                    _society.setTagIDs(tagids);
                }
            }
            Society oldSociety = societyRedisDao.getSocietyByID(society.getSocietyID());
            if(oldSociety != null){
                societyRedisDao.deleteStatusFromSet(oldSociety.getSocietyID(),"" + oldSociety.getStatus());
                societyRedisDao.deleteRecommendNoFromSet(oldSociety.getSocietyID());
                if(!StringUtils.isBlank(oldSociety.getLocationID())){
                    societyRedisDao.deleteLocationFromSet(oldSociety.getSocietyID(),oldSociety.getLocationID());
                }
                if(!StringUtils.isBlank(oldSociety.getSocietyCategoryID())){
                    societyRedisDao.deleteCategoryFromSet(oldSociety.getSocietyID(), oldSociety.getSocietyCategoryID());
                }
                if(!StringUtils.isBlank(oldSociety.getSchoolID())){
                    societyRedisDao.deleteSchoolFromSet(oldSociety.getSocietyID(), oldSociety.getSchoolID());
                }
                if(!StringUtils.isBlank(oldSociety.getTagIDs())){
                    String[] tags = oldSociety.getTagIDs().split(",");
                    for (int i = 0; i < tags.length; i++) {
                        societyRedisDao.deleteTagFromSet(oldSociety.getSocietyID(), tags[i]);
                    }
                }
            }
            this.societyRedisDao.updateSociety(_society);
            double praiseNum = null == _society.getPraiseNum()?0:society.getPraiseNum();
            updateRedisSocietyStatus(society.getSocietyID(), "" + society.getStatus(), praiseNum);
            updateRedisSocietyScore(society.getSocietyID(), praiseNum);
            Integer recommendNo = null == society.getRecommendNo()?0:society.getRecommendNo();
            if(recommendNo <= 0){
                societyRedisDao.deleteRecommendNoFromSet(_society.getSocietyID());
            }else{
                updateRedisSocietyRecommendNo(_society.getSocietyID(), recommendNo);
            }
            if(!StringUtils.isBlank(society.getLocationID())){
                updateRedisSocietyLocationID(society.getSocietyID(), society.getLocationID(), praiseNum);
            }
            if(!StringUtils.isBlank(society.getSocietyCategoryID())){
                updateRedisSocietyCategoryID(society.getSocietyID(), society.getSocietyCategoryID(), praiseNum);
            }
            if(!StringUtils.isBlank(society.getSchoolID())){
                updateRedisSocietySchoolID(society.getSocietyID(), society.getSchoolID(), praiseNum);
            }

            if(societytagRefs!=null && societytagRefs.size()>0){
                for(SocietytagRef societytagRef : societytagRefs){
                    updateRedisSocietyTagID(society.getSocietyID(),societytagRef.getSocietyTagID(),praiseNum);
                }
            }
        }
        return renturn_val;
	}

	@Override
	public boolean OnlyOneST(String adminid) {
		// TODO Auto-generated method stub
		
		return societydao.OnlyOneST(adminid);
	}
	
	@Override
	public boolean OnlyOneEffectST(String adminid) {
		// TODO Auto-generated method stub
		return societydao.OnlyOneEffectST(adminid);
	}

    @Override
    public Integer updatePresident(Society society, String oldPUserID, String newPUserID) throws BaseException {
                UserroleRef userroleRef = new UserroleRef();
                userroleRef.setUserRoleRefID(UUIDUtil.getUUID());
                userroleRef.setUserID(newPUserID);
                userroleRef.setRoleID(Role.ROLEID_SHEZHANG);
                this.userroleRefDao.insertUserroleRef(userroleRef);
                this.userroleRefDao.deleteUserroleRefByUID(oldPUserID);
                User user = userService.getUserByID(newPUserID);
                if(StringUtils.isBlank(user.getPassword())){
                    user.setPassword(CommonUtil.md5(user.getPhoneNum()));
                    userService.updateUserPWD(user);
                }
                Integer return_val = this.societydao.updatePresident(society);
                if(return_val>0){
                    Society _society = societydao.getSocietyByID(society.getSocietyID());
                    societyRedisDao.updateSociety(_society);
                    Person oldPerson = personService.getPersonByUID(oldPUserID);
                    if(oldPerson!=null){
                        oldPerson.setIsPresident(Person.ISPRESIDENT_NO);
                        personService.updatePerson(oldPerson);
                    }
                    Person newperson = personService.getPersonByID(society.getAdminID());
                    if(newperson != null){
                        newperson.setIsPresident(Person.ISPRESIDENT_YES);
                        personService.updatePerson(newperson);
                    }
                }
                return return_val;
    }

    @Override
    public Page<SocietyRunningVO> findSocietyRunningByPage(String mywhere, PaginationParameters paginationParameters) throws BaseException {
            return societydao.findSocietyRunningByPage(mywhere, paginationParameters);
    }

    @Override
    public Integer updateRecommendNoByID(Map params) throws BaseException {
        Integer return_val = societydao.updateRecommendNoByID(params);
        if(return_val>0){
            Society _society = societydao.getSocietyByID(params.get("societyID").toString());
            _society.setRecommendNo(Integer.valueOf("" + params.get("recommendNo")));
            societyRedisDao.updateSociety(_society);
            Integer recommendNo = null == _society.getRecommendNo()?0:_society.getRecommendNo();
            if(recommendNo <= 0){
                societyRedisDao.deleteRecommendNoFromSet(_society.getSocietyID());
            }else{
                updateRedisSocietyRecommendNo(_society.getSocietyID(), recommendNo);
            }
        }
        return return_val;
    }

    @Override
    public SystemSocietyFollow insertSystemSocietyFollow(SystemSocietyFollow systemSocietyFollow) throws BaseException {
            systemSocietyFollowDao.insertSystemSocietyFollow(systemSocietyFollow);
            Society society = this.getSocietyByID(systemSocietyFollow.getSocietyID());
            society.setSystemfollowNum(systemSocietyFollow.getSocietyFollowNum());
            societyRedisDao.updateSociety(society);
            return systemSocietyFollow;
    }

    @Override
    public Integer updateSystemSocietyFollow(SystemSocietyFollow systemSocietyFollow) throws BaseException {
            Integer return_val = systemSocietyFollowDao.updateSystemSocietyFollow(systemSocietyFollow);
            if(return_val>0){
                Society society = this.getSocietyByID(systemSocietyFollow.getSocietyID());
                society.setSystemfollowNum(systemSocietyFollow.getSocietyFollowNum());
                societyRedisDao.updateSociety(society);
            }
            return return_val;
    }

    @Override
    public SystemSocietyFollow findSystemSocietyFollowBySID(String societyID) throws BaseException {
        return systemSocietyFollowDao.findSystemSocietyFollowBySID(societyID);
    }

    @Override
    public SystemSocietyPraise insertSystemSocietyPraise(SystemSocietyPraise systemSocietyPraise) throws BaseException {
        systemSocietyPraiseDao.insertSystemSocietyPraise(systemSocietyPraise);
        Society society = this.getSocietyByID(systemSocietyPraise.getSocietyID());
        society.setSystempraiseNum(systemSocietyPraise.getSocietyPraiseNum());
        societyRedisDao.updateSociety(society);
//        double score = null == society.getPraiseNum()?0:society.getPraiseNum();
//        updateRedisSocietyStatus(society.getSocietyID(), "" + society.getStatus(), score + society.getSystempraiseNum());
//        updateRedisSocietyScore(society.getSocietyID(), score + society.getSystempraiseNum());
        return systemSocietyPraise;
    }

    @Override
    public Integer updateSystemSocietyPraise(SystemSocietyPraise systemSocietyPraise) throws BaseException {
        Integer return_value = systemSocietyPraiseDao.updateSystemSocietyPraise(systemSocietyPraise);
            if(return_value > 0) {
                Society society = this.getSocietyByID(systemSocietyPraise.getSocietyID());
                society.setSystempraiseNum(systemSocietyPraise.getSocietyPraiseNum());
                societyRedisDao.updateSociety(society);
//                double score = null == society.getPraiseNum()?0:society.getPraiseNum();
//                updateRedisSocietyStatus(society.getSocietyID(), "" + society.getStatus(), score + society.getSystempraiseNum());
//                updateRedisSocietyScore(society.getSocietyID(), score + society.getSystempraiseNum());
            }
            return return_value;
    }

    @Override
    public SystemSocietyPraise findSystemSocietyPraiseBySID(String societyID) throws BaseException {
        return systemSocietyPraiseDao.findSystemSocietyPraiseBySID(societyID);
    }

	@Override
	public int mdName(String id, String name) throws BaseException {
		// TODO Auto-generated method stub
		//这是处理缓存的。。。。。。。。。。。。。。
		Society society = this.getSocietyByID(id);
        society.setSocietyName(name);
        societyRedisDao.updateSociety(society);
        this.activityDao.updateActivitySocietyNameBySocietyID(id,name);
        List<Activity> activityList = this.activityDao.findActivityBySocietyID(society.getSocietyID());
        if(activityList != null && activityList.size() > 0){
            for(Activity activity : activityList){
                Activity _activity = activityService.getActivityFromRedisByID(activity.getActivityID());
                _activity.setSocietyName(name);
                activityService.updateRedisActivity(_activity);
            }
        }
        //这是处理缓存的。。。。。。。。。。。。。。end
		return societydao.mdName(id, name);
	}

    private void updateRedisSocietyScore(String societyID,double score) throws BaseException{
        societyRedisDao.updateSocietyScore(societyID, score);
    }

    private void updateRedisSocietyStatus(String societyID,String status,double score) throws BaseException{
        societyRedisDao.insertID2Redis(societyID, score);
        societyRedisDao.insertStatus2RedisSet(societyID, status, 0);
    }

    private void updateRedisSocietyRecommendNo(String societyID,Integer recommendNo) throws BaseException{
        societyRedisDao.insertRecommendNo2RedisSet(societyID, Society.RECOMMENDNO_CORRECTED_VALUE + recommendNo);
    }

    private void updateRedisSocietyLocationID(String societyID,String LocationID,double score) throws BaseException{
        societyRedisDao.insertLocation2RedisSet(societyID, LocationID, score);
    }

    private void updateRedisSocietyCategoryID(String societyID,String SocietyCategoryID,double score) throws BaseException{
        societyRedisDao.insertCategory2RedisSet(societyID, SocietyCategoryID, score);
    }

    private void updateRedisSocietySchoolID(String societyID,String SchoolID,double score) throws BaseException{
        societyRedisDao.insertSchool2RedisSet(societyID, SchoolID, score);
    }

    private void updateRedisSocietyTagID(String societyID,String tagID,double score) throws BaseException{
        societyRedisDao.insertTag2RedisSet(societyID, tagID, score);
    }
}
