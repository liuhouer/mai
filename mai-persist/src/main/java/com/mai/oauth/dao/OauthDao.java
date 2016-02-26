package com.mai.oauth.dao;

import com.mai.framework.exception.BaseException;
import com.mai.user.entity.User;

/**
 * Created by fengdzh on 2015/9/26.
 */
public interface OauthDao {

        /**
         * token 换User
         * @param token
         * @return
         * @throws BaseException
         */
        public User token2User(String token)throws BaseException;
}