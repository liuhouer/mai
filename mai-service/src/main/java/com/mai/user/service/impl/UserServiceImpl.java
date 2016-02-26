package com.mai.user.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mai.activity.dao.*;
import com.mai.activity.entity.Log;
import com.mai.activity.service.LogService;
import com.mai.framework.utils.UUIDUtil;
import com.mai.oauth.dao.OauthDao;
import com.mai.util.CommonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.framework.utils.DateUtil;
import com.mai.society.entity.Society;
import com.mai.user.entity.Permission;
import com.mai.user.entity.Person;
import com.mai.user.entity.Role;
import com.mai.user.entity.User;
import com.mai.user.entity.UserroleRef;
import com.mai.user.service.UserService;
import com.mai.vo.ActRankVO;
import com.mai.vo.ExpData;
import com.mai.vo.MixRankVO;
import com.mai.vo.SocRankVO;
import com.mai.vo.UserVO;

/**
 * 用户信息
 * Created by fengdzh on 2015/9/11.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private SocietyDao societyDao;

    @Autowired
    private LogService logService;

    @Autowired
    private SocietyRedisDao societyRedisDao;

    @Autowired
    private PersonRedisDao personRedisDao;

    @Autowired
    private OauthDao oauthDao;

    /**
     * 添加成员
     *
     * @param user
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public int insertUser(User user) {
        
        return userDao.add(user);
    }

    /**
     * 通过ID获取用户信息
     *
     * @param userID
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public User getUserByID(String userID) throws BaseException {
        return userDao.getUserByID(userID);
    }

    /**
     * 通过用户名获取用户
     *
     * @param userName
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public User getUserByUserName(String userName) throws BaseException {
        return null;
    }

    /**
     * 验证用户身份信息：用户名、密码
     *
     * @param userName
     * @param password
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public User transCheckUserIsValid(String userName, String password) throws BaseException {
        return null;
    }

    /**
     * 通过手机SN获取用户信息
     *
     * @param phoneSN
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public User getUserByPhoneSN(String phoneSN) throws BaseException {
        return null;
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public User updateUser(User user) throws BaseException {
        return null;
    }

    /**
     * * * 更新用户信息
     *
     * @param user
     * @return
     * @throws BaseException
     */
    public User updateUserByPersonID(User user) throws BaseException {
        return null;
    }

    @Override
    public User getUserByPhoneNum(String phoneNum,Integer isvalid) throws BaseException {
                Map<String,Object> params = new HashMap<String,Object>();
                params.put("phoneNum",phoneNum);
                if(isvalid!=null){
                    params.put("isValid",isvalid);
                }
                return userDao.findUserByPhoneNumAndIsValid(params);
    }

    
    @Override
	public boolean PhoneFlag(String phoneNum, Integer isvalid) throws BaseException {
		// TODO Auto-generated method stub
    	
    	boolean flag = false;
    	  Map<String,Object> params = new HashMap<String,Object>();
          params.put("phoneNum",phoneNum);
          if(isvalid!=null){
              params.put("isValid",isvalid);
          }
          List<User> list = userDao.findUserByPhone(params);
          //查不到用户
          if(list==null||list.size()==0){
        	  flag = true;
          }
          //查到用户并有且仅有一个
//          if(list!=null){
//        	  if(list.size()==1){
//        		  flag = true;
//        	  }
//          }
		return flag;
	}

    @Override
    public User findUserByPhoneNumAndPassWord(String phoneNum, String passWord){
        try {
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("phoneNum",phoneNum);
            params.put("isValid",User.ISVALID_YES);
            params.put("password",passWord);
            return userDao.findUserByPhoneNumAndPassword(params);
        } catch (BaseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Role> findRoleByUserID(String uid) throws BaseException {
                return roleDao.findRoleByUserID(uid);
    }

    @Override
    public List<Role> findRoleByPhoneNum(String phoneNum){
        try {
            return roleDao.findRoleByPhoneNum(phoneNum);
        } catch (BaseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Permission> findPermissionListByRoleID(String roleID) {
        try {
            return permissionDao.findPermissonListByRID(roleID);
        } catch (BaseException e) {
            e.printStackTrace();
            return null;
        }
    }


	@Override
	public List<UserVO> findAll(String wheresql) throws BaseException {
		// TODO Auto-generated method stub
		return userDao.findAll(wheresql);
	}
	
	@Override
	public Page<UserVO> findAllByPage(String wheresql, PaginationParameters paginationParameters) throws BaseException {
		// TODO Auto-generated method stub
		return userDao.findAllByPage(wheresql, paginationParameters);
	}
    @Override
    public Map<String,Object> checkPresidentInfo(String phoneNum) throws BaseException{

            Map<String,Object> map = new HashMap<String,Object>();
            User user = this.getUserByPhoneNum(phoneNum, 1);
            if(user!=null){
                List<Role> role = roleDao.findRoleByPhoneNum(phoneNum);
                if(role==null || role.size() == 0){
                    Society society = this.societyDao.findSocietyByAdminIDAndValid(user.getPersonID());
                    if(society==null){
                        map.put("errorcode",0);
                        map.put("user",user);
                    }else{
                        map.put("errorcode",2);
                    }
                }else{
                    map.put("errorcode",3);
                }
            }else{
                map.put("errorcode",1);
            }
            return map;
    }

    @Override
	public int insertPerson(Person p) throws BaseException{
		// TODO Auto-generated method stub
        Integer rint = userDao.addPerson(p);
        if(rint>0){
            personRedisDao.updatePerson(p);
        }
		return rint;
	}

	@Override
	public int insertUserRole(UserroleRef ur) {
		// TODO Auto-generated method stub
		return userDao.addUserRole(ur);
	}

	@Override
	public int updateUserSt(User p) {
		// TODO Auto-generated method stub
		return userDao.updateUserSt(p);
	}

	@Override
	public User getUserByPid(String personID) {
		// TODO Auto-generated method stub
		return userDao.getUserByPid(personID);
	}

	@Override
	public int updateUserPWD(User p) {
		// TODO Auto-generated method stub
		return userDao.updateUserPWD(p);
	}

	@Override
	public UserroleRef findRefByUidAndRoleID(String userID, String roleid) {
		// TODO Auto-generated method stub
		return userDao.findRefByUidAndRoleID(userID, roleid);
	}

	@Override
	public int deleteByID(String id) {
		// TODO Auto-generated method stub
		return userDao.deleteByID(id);
	}

    @Override
    public void saveImpData(String name, String phone, String schoolName, String societyName, String schoolID, String curphone, String shareURL, String shareDESC) {
        // TODO Auto-generated method stub
        //添加用户
        try{
            String uid = addUser(name, phone,schoolID,schoolName,Person.ISPRESIDENT_YES);
            //添加关系
            UserroleRef ur = new UserroleRef();
            ur.setUserID(uid);
            ur.setRoleID(Role.ROLEID_SHEZHANG);
            ur.setUserRoleRefID(UUIDUtil.getUUID());
            this.insertUserRole(ur);

            String societyid = UUIDUtil.getUUID();
            //添加社团
            Society s = new Society();
            s.setSchoolID(schoolID);
            s.setSchoolName(schoolName);
            s.setSocietyName(societyName);
            s.setCreateTime(new Date().getTime());
            s.setPhoneNum(phone);
            s.setLevel(1);
            s.setShareURL(shareURL + societyid);
//            s.setShareDESC(shareDESC);
            User u1 = this.getUserByID(uid);
            if(u1!=null){
                Person p = this.getPersonByID(u1.getPersonID());
                s.setAdminID(u1.getPersonID());
                if(p!=null){
                    s.setAdminName(p.getName());
                }
            }
            s.setStatus(Society.STATUS_NORMAL);
            s.setSocietyID(societyid);
            addSociety(s);
            //添加log日志
            Log log = new Log();
            log.setLogID(UUIDUtil.getUUID());
            log.setCreateTime(new Date().getTime());
            log.setLogAuthor(curphone);
            log.setLogDesc(societyName+Log.MSG_ADMIN_IMPORT);
            log.setLogtype(Log.TYPE_SOCIETY_IMPORT);
            log.setActID(societyid);
            logService.add(log);
        }catch(Exception e){
            e.printStackTrace();
        }
    }



    /**
     * 插入用户
     * @param J_name
     * @param J_phone
     * @return
     */
    public String addUser(String J_name, String J_phone,String schoolID,String schoolName,Integer isPresident) throws BaseException{
        String pid = UUIDUtil.getUUID();
        String uid =  UUIDUtil.getUUID();
        User u = new User();
        u.setUserID(uid);
        u.setPersonID(pid);
        u.setPersonName(J_name);
        u.setPhoneNum(J_phone);
        u.setCreateTime(DateUtil.currentTimestampToLong());
        u.setUpdateTime(DateUtil.currentTimestampToLong());
        u.setIsValid(User.ISVALID_YES);
        u.setPassword(CommonUtil.md5(J_phone));//设置默认密码为手机号

        this.insertUser(u);

        Person p = new Person();
        p.setPersonID(pid);
        p.setPhoneNum(J_phone);
        p.setName(J_name);
        p.setCreateTime(DateUtil.currentTimestampToLong());
        p.setUpdateTime(DateUtil.currentTimestampToLong());
        if(org.apache.commons.lang.StringUtils.isNotEmpty(schoolID)){
            p.setSchoolID(schoolID);
        }
        if(org.apache.commons.lang.StringUtils.isNotEmpty(schoolName)){
            p.setSchoolName(schoolName);
        }
        p.setSignature(Person.PERSONALSIGN);
        p.setIsValid(User.ISVALID_YES);
        if(isPresident !=null){
            p.setIsPresident(isPresident);
        }else{
            p.setIsPresident(Person.ISPRESIDENT_NO);
        }
        this.insertPerson(p);
        return uid;
    }

    @Override
	public Person getPersonByID(String personID) {
		// TODO Auto-generated method stub
		return userDao.getPersonByID(personID);
	}

    private int addSociety(Society society) throws BaseException {
        int return_value = societyDao.add(society);
        if(return_value>0){
            societyRedisDao.setSociety(society);
            updateRedisSocietyStatus(society.getSocietyID(), "" + society.getStatus(), 0);
            updateRedisSocietyScore(society.getSocietyID(), 0);
        }
        return return_value;
    }

    private void updateRedisSocietyScore(String societyID,double score) throws BaseException{
        societyRedisDao.updateSocietyScore(societyID, score);
    }

    private void updateRedisSocietyStatus(String societyID,String status,double score) throws BaseException{
        societyRedisDao.insertID2Redis(societyID, score);
        societyRedisDao.insertStatus2RedisSet(societyID, status, score);
    }

	@Override
	public List<ExpData> findExpData(String wheresql) throws BaseException {
		// TODO Auto-generated method stub
		return userDao.findExpData(wheresql);
	}

	@Override
	public List<MixRankVO> findMixRank() {
		// TODO Auto-generated method stub
		return userDao.findMixRank();
	}

	@Override
	public List<SocRankVO> findSocRank() {
		// TODO Auto-generated method stub
		return userDao.findSocRank();
	}

	@Override
	public List<ActRankVO> findActRank() {
		// TODO Auto-generated method stub
		return userDao.findActRank();
	}

    @Override
    public User token2User(String token) throws BaseException {
        return oauthDao.token2User(token);
    }


}
