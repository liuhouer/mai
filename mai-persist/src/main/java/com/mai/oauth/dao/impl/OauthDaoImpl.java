package com.mai.oauth.dao.impl;

import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import com.mai.framework.utils.DateUtil;
import com.mai.framework.utils.UUIDUtil;
import com.mai.oauth.dao.OauthDao;
import com.mai.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Oauth服务
 * Created by fengdzh on 2015/9/26.
 */
@Repository
public class OauthDaoImpl extends BaseDaoImpl<User,User> implements OauthDao {
    @Override
    public User token2User(String token) throws BaseException {
        User user=new User();
        String personID=null;
        user.setToken(token);
        List<User> userList = this.findByParam("Mapper.Oauth.token2User", user);
        User resUser=null;
        if(null!=userList && userList.size()>0){
            resUser= userList.get(0);
        }
        return resUser;
    }
}
