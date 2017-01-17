package com.hangtoo.auth.util;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.hangtoo.auth.entity.SysUser;
import com.hangtoo.base.util.Constant.SuperAdmin;
import com.hangtoo.base.util.SessionUtils;

/**
 * 
 * Cookie 工具类
 *
 */
public final class AuthUtils extends SessionUtils{
	
	protected static final Logger logger = Logger.getLogger(AuthUtils.class);
	
	private static final String SESSION_USER = "session_user";

	/**
	  * 设置session的值
	  * @param request
	  * @param key
	  * @param value
	  */
	 public static void setAttr(HttpServletRequest request,String key,Object value){
		 request.getSession(true).setAttribute(key, value);
	 }
	 
	 
	 /**
	  * 获取session的值
	  * @param request
	  * @param key
	  * @param value
	  */
	 public static Object getAttr(HttpServletRequest request,String key){
		 return request.getSession(true).getAttribute(key);
	 }
	 
	 /**
	  * 删除Session值
	  * @param request
	  * @param key
	  */
	 public static void removeAttr(HttpServletRequest request,String key){
		 request.getSession(true).removeAttribute(key);
	 }
	 
	 /**
	  * 设置用户信息 到session
	  * @param request
	  * @param user
	  */
	 public static void setUser(HttpServletRequest request,SysUser user){
		 request.getSession(true).setAttribute(SESSION_USER, user);
	 }
	 
	 
	 /**
	  * 从session中获取用户信息
	  * @param request
	  * @return SysUser
	  */
	 public static SysUser getUser(HttpServletRequest request){
		return (SysUser)request.getSession(true).getAttribute(SESSION_USER);
	 }
	 
	 /**
	  * 从session中获取用户信息
	  * @param request
	  * @return SysUser
	  */
	 public static Integer getUserId(HttpServletRequest request){
		 SysUser user = getUser(request);
		 if(user != null){
			 return user.getId();
		 }
		return null; 
	 }
	 
	 
	 /**
	  * 从session中获取用户信息
	  * @param request
	  * @return SysUser
	  */
	 public static void removeUser(HttpServletRequest request){
		removeAttr(request, SESSION_USER);
	 }
	 
	 /**
	  * 判断当前登录用户是否超级管理员
	  * @param request
	  * @return
	  */
	 public static boolean isAdmin(HttpServletRequest request){ //判断登录用户是否超级管理员
		 SysUser user =  getUser(request);
		 if(user == null  || user.getSuperAdmin() != SuperAdmin.YES.key){
			 return false;
		 }
		 return true;
	 }
	 
	
}