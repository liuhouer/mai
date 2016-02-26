package com.mai.activity.dao;

import java.util.List;
import java.util.Map;

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
public interface UserDao {

    public User findUserByPhoneNumAndIsValid(Map params) throws BaseException;
    
    public List<User> findUserByPhone(Map params) throws BaseException;

    public User findUserByPhoneNumAndPassword(Map params) throws BaseException;
    
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
    
    public int add(User user);
    
    public int addPerson(Person p);
    
    /**
     * 根据pid取得user
     *
     * @param user
     * @return
     * @throws BaseException
     */
    public User getUserByPid(String personID) ;
    
    /**
     * 添加关联关系
     *
     * @param user
     * @return
     * @throws BaseException
     */
    public int addUserRole(UserroleRef ur) ;
    
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
	public int updateUserPWD(User p);

	public UserroleRef findRefByUidAndRoleID(String userID, String roleid);

	public int deleteByID(String id);

	public Person getPersonByID(String personID);

	public User getUserByID(String userID);

	public List<MixRankVO> findMixRank();

	public List<SocRankVO> findSocRank();

	public List<ActRankVO> findActRank();
}
