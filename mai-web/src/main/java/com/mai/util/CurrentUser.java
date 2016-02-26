package com.mai.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.mai.user.entity.User;


public class CurrentUser {
	public static final void setCurrentUser(User user) {

		Subject currentUser = SecurityUtils.getSubject();

		if (null != currentUser) {

			Session session = currentUser.getSession();

			if (null != session) {
				session.setAttribute("mai_user", user);
			}
		}
	}

	public static final User getCurrentUser() {
		User user = null;
		Subject currentUser = SecurityUtils.getSubject();

		if (null != currentUser) {

			Session session = currentUser.getSession();

			if (null != session) {

				user = (User) session.getAttribute("mai_user");
			}
		}
		return user;
	}

	public static final String getCurrentUserId() {
		User user = getCurrentUser();
		if (user != null)
			return user.getUserID();
		return null;
	}

	public static final Boolean hasRole(String rolename){
		Subject currentUser = SecurityUtils.getSubject();
		return currentUser.hasRole(rolename);
	}

	public static final String getCurrentPersonId() {
		User user = getCurrentUser();
		if (user != null)
			return user.getPersonID();
		return null;
	}

	public static final String getPersonName() {
		User user = getCurrentUser();
		if (user != null)
			return user.getPersonName();
		return null;
	}

	public static final String getSocietyID() {
		User user = getCurrentUser();
		if (user != null)
			return user.getSocietyID();
		return null;
	}

	public static final String getPassWord(){
		User user = getCurrentUser();
		if (user != null)
			return user.getPassword();
		return null;
	}

	public static final String getRoleName(){
		User user = getCurrentUser();
		if (user != null)
			return user.getRoleName();
		return null;
	}

	public static final String getPersonPhoneNum(){
		User user = getCurrentUser();
		if (user != null)
			return user.getPersonPhoneNum();
		return null;
	}

	public static final String getPhoneNum(){
		User user = getCurrentUser();
		if (user != null)
			return user.getPhoneNum();
		return null;
	}
}
