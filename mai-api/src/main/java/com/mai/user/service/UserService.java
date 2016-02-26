package com.mai.user.service;

import java.util.List;
import java.util.Map;

import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.user.entity.Permission;
import com.mai.user.entity.Person;
import com.mai.user.entity.Role;
import com.mai.user.entity.User;
import com.mai.user.entity.UserroleRef;
import com.mai.vo.ActRankVO;
import com.mai.vo.ExpData;
import com.mai.vo.MixRankVO;
import com.mai.vo.SocRankVO;
import com.mai.vo.UserVO;

/**
 * Created by fengdzh on 2015/9/11.
 */
/**
 * @author sks
 *
 */
public interface UserService {

    /**
     * 添加成员
     *
     * @param user
     * @return
     * @throws BaseException
     */
    public int insertUser(User user) ;
    /**
     * 添加成员
     *
     * @param user
     * @return
     * @throws BaseException
     */
    public int insertPerson(Person p) throws BaseException;
    
    
    /**
     * 根据pid取得user
     *
     * @param user
     * @return
     * @throws BaseException
     */
    public User getUserByPid(String personID) ;
    
    /**
     * 根据pid取得person
     *
     * @param user
     * @return
     * @throws BaseException
     */
    public Person getPersonByID(String personID) ;
    
    /**
     * 更新用户状态
     *
     * @param user
     * @return
     * @throws BaseException
     */
    public int updateUserSt(User p) ;
    
    /**
     * 更新用户密码
     *
     * @param user
     * @return
     * @throws BaseException
     */
    public int updateUserPWD(User p) ;
    
    /**
     * 添加关联关系
     *
     * @param user
     * @return
     * @throws BaseException
     */
    public int insertUserRole(UserroleRef ur) ;

    /**
     * 通过ID获取用户信息
     *
     * @param userID
     * @return
     * @throws BaseException
     */
    public User getUserByID(String userID) throws BaseException;

    /**
     * 通过用户名获取用户
     *
     * @param userName
     * @return
     * @throws BaseException
     */
    public User getUserByUserName(String userName) throws BaseException;

    /**
     * 验证用户身份信息：用户名、密码
     *
     * @param userName
     * @param password
     * @return
     * @throws BaseException
     */
    public User transCheckUserIsValid(String userName, String password) throws BaseException;

    /**
     * 通过手机SN获取用户信息
     *
     * @param phoneSN
     * @return
     * @throws BaseException
     */
    public User getUserByPhoneSN(String phoneSN) throws BaseException;

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     * @throws BaseException
     */
    public User updateUser(User user) throws BaseException;

    /**
     * * * 更新用户信息
     *
     * @param user
     * @return
     * @throws BaseException
     */
    public User updateUserByPersonID(User user) throws BaseException;

    /**
     * 通过手机号获取用户信息
     *
     * @param phoneNum
     * @return
     * @throws BaseException
     */
    public User getUserByPhoneNum(String phoneNum,Integer isvalid) throws BaseException;
    /**
     * 通过手机号获取用户信息
     *
     * @param phoneNum
     * @return
     * @throws BaseException
     */
    public boolean PhoneFlag(String phoneNum,Integer isvalid) throws BaseException;

    /**
     * 通过手机号&密码查找用户
     * @param phoneNum
     * @param passWord
     * @return
     * @throws BaseException
     */
    public User findUserByPhoneNumAndPassWord(String phoneNum,String passWord);

    /**
     * UID获取权限列表
     * @param uid
     * @return
     * @throws BaseException
     */
    public List<Role> findRoleByUserID(String uid) throws BaseException;

    /**
     * 电话号码登录获取权限列表
     * @param phoneNum
     * @return
     * @throws BaseException
     */
    public List<Role> findRoleByPhoneNum(String phoneNum);

    public List<Permission> findPermissionListByRoleID(String roleID);
    
    /**
     * 根据条件进行关联查询
     * @param wheresql
     * @return
     * @throws BaseException
     */
    public List<UserVO> findAll(String wheresql) throws BaseException;
    
    /**
     * 根据条件进行关联查询
     * @param wheresql
     * @return
     * @throws BaseException
     */
    public List<ExpData> findExpData(String wheresql) throws BaseException;
    
    /**
     * 根据条件进行关联查询
     * @param wheresql
     * @return
     * @throws BaseException
     */
    public Page<UserVO> findAllByPage(String wheresql,PaginationParameters paginationParameters) throws BaseException;

    /**
     * 通过手机号判断并返回符合社长资质的User
     * 返回map结构
     * errorcode 为 0 有社长资格，1 为账号（USER）不是有效状态或者不存在，2 已经是其他社团社长
     * user 符合资质的返回USER对象，不符合的为null
     * @param phoneNum
     * @return
     */
    public Map<String,Object> checkPresidentInfo(String phoneNum) throws BaseException;
    
    
	/**
	 * 根据userid和roleid确定唯一的关系
	 * @param userID
	 * @param roleid
	 * @return
	 */
	public UserroleRef findRefByUidAndRoleID(String userID, String roleid);

	public int deleteByID(String id);

    public void saveImpData(String name, String phone, String schoolName, String societyName, String schoolID, String curphone,String shareURL, String shareDESC);

    public String addUser(String J_name, String J_phone,String schoolID,String schoolName,Integer isPresident) throws BaseException;
	
    public List<MixRankVO> findMixRank();
	public List<SocRankVO> findSocRank();
	public List<ActRankVO> findActRank();

    /**
     * token 换User
     * @param token
     * @return
     * @throws BaseException
     */
    public User token2User(String token)throws BaseException;
}
