package com.mai.activity.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mai.activity.dao.UserDao;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.user.entity.Person;
import com.mai.user.entity.User;
import com.mai.user.entity.UserroleRef;
import com.mai.vo.ActRankVO;
import com.mai.vo.ExpData;
import com.mai.vo.MixRankVO;
import com.mai.vo.SocRankVO;
import com.mai.vo.UserVO;

/**
 * Created by denghao on 15/10/7.
 */
@Component
public class UserDaoImpl extends BaseDaoImpl implements UserDao{
    @Override
    public User findUserByPhoneNumAndIsValid(Map params) throws BaseException {
            User user = null;
            List<User> ulist = this.findByParam("Mapper.User.findUserByPhoneNumAndIsValid",params);
            if(ulist!=null && ulist.size() > 0){
                user = ulist.get(0);
            }
            return user;
    }

	@Override
	public List<User> findUserByPhone(Map params) throws BaseException {
		// TODO Auto-generated method stub
          List<User> ulist = this.findByParam("Mapper.User.findUserByPhoneNumAndIsValid",params);
          return ulist;
	}

    @Override
    public User findUserByPhoneNumAndPassword(Map params) throws BaseException {
        User user = null;
        List<User> ulist = this.findByParam("Mapper.User.findUserByPhoneNumAndPassword",params);
        if(ulist!=null && ulist.size() > 0){
            user = ulist.get(0);
        }
        return user;
    }

	@Override
	public List<UserVO> findAll(String wheresql) throws BaseException {
		// TODO Auto-generated method stub
		return this.findByParam("Mapper.User.findAll",wheresql);
	}
	
	@Override
	public Page<UserVO> findAllByPage(String wheresql, PaginationParameters paginationParameters) throws BaseException {
		// TODO Auto-generated method stub
		return this.findByPage("Mapper.User.findAllByPage",wheresql,paginationParameters);
	}

	@Override
	public int add(User user) {
		// TODO Auto-generated method stub
	   int a =0 ; 
	   
	   try {
		   
		   a = this.insert("Mapper.User.insert", user);
		   
	   } catch (BaseException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
	   
	   return a ;
	}

	@Override
	public int addPerson(Person p) {
		// TODO Auto-generated method stub
		 int a =0 ; 
		   
		 try {
			   
			 a = this.insert("Mapper.Person.insert", p);
		 } catch (BaseException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		 }
		   
		 return a ;
	}

	@Override
	public int addUserRole(UserroleRef ur) {
		// TODO Auto-generated method stub
				 int a =0 ; 
				   
				 try {
					   
					 a = this.insert("Mapper.User_role_ref.insert", ur);
				 } catch (BaseException e) {
					   // TODO Auto-generated catch block
					   e.printStackTrace();
				 }
				   
		  return a ;
	}

	@Override
	public int updateUserSt(User p) {
		// TODO Auto-generated method stub
		 int a =0 ; 
		   
		 try {
			   Map map = new HashMap<String,String>();
			   map.put("status", p.getIsValid());
			   map.put("id", p.getUserID());
			   a = this.update("Mapper.User.updateST", map);
		 } catch (BaseException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		 }
		   
		 return a ;
	}

	@Override
	public User getUserByPid(String personID) {
		// TODO Auto-generated method stub
		User p = null;
		  try {
			List<User> ulist = this.findByParam("Mapper.User.getUserByPid",personID);
			if(ulist!=null&&ulist.size()>0){
				p  = ulist.get(0);
			}
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public int updateUserPWD(User p) {
		// TODO Auto-generated method stub
		 int a =0 ; 
		   
		 try {
			   Map map = new HashMap<String,String>();
			   map.put("pwd", p.getPassword());
			   map.put("id", p.getUserID());
			   a = this.update("Mapper.User.updatePWD", map);
		 } catch (BaseException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		 }
		   
		 return a ;
	}

	@Override
	public UserroleRef findRefByUidAndRoleID(String userID, String roleid) {
		// TODO Auto-generated method stub
		UserroleRef ur = new UserroleRef();
		try{
			Map map  = new HashMap<String,String>();
			map.put("userID", userID);
			map.put("roleID", roleid);
			List<UserroleRef> ulist = this.findByParam("Mapper.User_role_ref.getRefByUidAndRoleID",map);
			if(ulist!=null&&ulist.size()>0){
				ur = ulist.get(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ur;
	}

	@Override
	public int deleteByID(String id) {
		// TODO Auto-generated method stub
		int a = 0 ;
		try {
			 a = this.delete("Mapper.User_role_ref.deleteRefByID", id);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a ;
	}

	@Override
	public Person getPersonByID(String personID) {
		// TODO Auto-generated method stub
		Person p = null;
		  try {
			List<Person> ulist = this.findByParam("Mapper.Person.getPersonByID",personID);
			if(ulist!=null&&ulist.size()>0){
				p  = ulist.get(0);
			}
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public User getUserByID(String userID) {
		// TODO Auto-generated method stub
				User u = null;
				  try {
					List<User> ulist = this.findByParam("Mapper.User.getUserByID",userID);
					if(ulist!=null&&ulist.size()>0){
						u  = ulist.get(0);
					}
				} catch (BaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return u;
	}

	@Override
	public List<ExpData> findExpData(String wheresql) throws BaseException {
		// TODO Auto-generated method stub
		 return this.findByParam("Mapper.User.findExpData",wheresql);
	}

	@Override
	public List<MixRankVO> findMixRank() {
		// TODO Auto-generated method stub
		 List<MixRankVO>  list = null;
		 try {
			 list = this.findByParam("Mapper.User.findMixRank",null);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return list ;
	}

	@Override
	public List<SocRankVO> findSocRank() {
		// TODO Auto-generated method stub
				 List<SocRankVO>  list = null;
				 try {
					 list = this.findByParam("Mapper.User.findSocRank",null);
				} catch (BaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 return list ;
	}

	@Override
	public List<ActRankVO> findActRank() {
		// TODO Auto-generated method stub
				 List<ActRankVO>  list = null;
				 try {
					 list = this.findByParam("Mapper.User.findActRank",null);
				} catch (BaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 return list ;
	}

	
}
