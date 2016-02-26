package com.mai.controller.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mai.society.entity.Society;
import com.mai.society.service.SocietyService;
import com.mai.user.entity.Permission;
import com.mai.user.entity.Role;
import com.mai.user.entity.User;
import com.mai.user.service.UserService;
import com.mai.util.CurrentUser;


@Component
public class BasicSecurityRealm extends AuthorizingRealm{
	private static Log log = LogFactory.getLog(BasicSecurityRealm.class);
	@Autowired
	private UserService userservice;
    @Autowired
    private SocietyService societyService;

	public BasicSecurityRealm(){
		 setCacheManager(new MemoryConstrainedCacheManager());
	}
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		if(null==principals)
			throw new AuthorizationException("PrincipalCollection was null, which should not happen");
		if (principals.isEmpty())
            return null;
        if (principals.fromRealm(getName()).size() <= 0)
            return null;

        String phoneNum = (String) principals.fromRealm(getName()).iterator().next();
        if (phoneNum == null)
            return null;
        List<Role> roleList = userservice.findRoleByPhoneNum(phoneNum);
        if (roleList == null) return null;
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //用户角色
        if(roleList.size()>0){
            List<Map<String,String>> permissionList = new ArrayList<Map<String, String>>();
            List<Permission> permissions = null;
            Map<String,String> permissionMap = null;
            User user = CurrentUser.getCurrentUser();
            for(Role role : roleList){
                info.addRole(role.getRoleNameEn());
                if(role.getRoleNameEn().equals(Role.ROLENAME_PRESIDENT)){
                    Society society = societyService.findSocietyByAdminIDAndValid(user.getPersonID());
                    if(society!=null){
                        user.setSocietyID(society.getSocietyID());
                    }
                }
                permissions = userservice.findPermissionListByRoleID(role.getRoleID());
                if(permissions!=null && permissions.size()>0){
                    for(Permission permission : permissions){
                        permissionMap = new HashMap<String, String>();
                        permissionMap.put("actionName",permission.getActionName());
                        permissionMap.put("actionURL",permission.getActionURL());
                        permissionList.add(permissionMap);
                    }
                }
                user.setRoleName(role.getRoleName());
            }
            user.setPermissionList(permissionList);
        }
		return info;
	}
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)  throws AuthenticationException{
		UsernamePasswordToken userToken = (UsernamePasswordToken) token;
		User suser=userservice.findUserByPhoneNumAndPassWord(String.valueOf(userToken.getUsername()),String.valueOf(userToken.getPassword()));
        if (suser == null) {
            throw new AuthenticationException("The user doesn't exist");
        } else {
        	CurrentUser.setCurrentUser(suser);
        	return new SimpleAuthenticationInfo(suser.getPhoneNum(),suser.getPassword(),getName());
        }
	}
	/** 
     * 更新用户授权信息缓存. 
     */ 
    public void clearCachedAuthorizationInfo(String principal) { 
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName()); 
        clearCachedAuthorizationInfo(principals); 
    }

    /** 
     * 清除所有用户授权信息缓存. 
     */ 
    public void clearAllCachedAuthorizationInfo() { 
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache(); 
        if (cache != null) { 
            for (Object key : cache.keys()) { 
                cache.remove(key); 
            } 
        } 
    }
}
