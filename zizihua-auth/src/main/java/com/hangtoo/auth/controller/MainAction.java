package com.hangtoo.auth.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hangtoo.auth.entity.SysMenu;
import com.hangtoo.auth.entity.SysMenuBtn;
import com.hangtoo.auth.entity.SysUser;
import com.hangtoo.auth.page.SysUserModel;
import com.hangtoo.auth.service.SysMenuBtnService;
import com.hangtoo.auth.service.SysMenuService;
import com.hangtoo.auth.service.SysUserService;
import com.hangtoo.auth.util.AuthUtils;
import com.hangtoo.auth.util.TreeUtil;
import com.hangtoo.base.annotation.Auth;
import com.hangtoo.base.entity.BaseEntity.DELETED;
import com.hangtoo.base.entity.BaseEntity.STATE;
import com.hangtoo.base.entity.TreeNode;
import com.hangtoo.base.util.Constant.SuperAdmin;
import com.hangtoo.base.util.HtmlUtil;
import com.hangtoo.base.util.MethodUtil;
import com.hangtoo.base.util.URLUtils;
import com.hangtoo.base.web.BaseAction;
import com.hangtoo.util.DateUtils;
import com.hangtoo.util.RSAUtils;

@Controller
@Component
public class MainAction extends BaseAction {

	
	private final static Logger log= Logger.getLogger(MainAction.class);
	private String message = null;
	// Servrice start
	@Autowired(required=false) 
	private SysMenuService sysMenuService; 
	
	@Autowired(required=false) 
	private SysUserService sysUserService; 
	
	@Autowired(required=false) 
	private SysMenuBtnService sysMenuBtnService;
	
/*	@Reference(version="1.0.0",check=false)
	private DemoService demoService;*/
	
	
	/**
	 * 登录页面
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@Auth(verifyLogin=false,verifyURL=false)
	@RequestMapping("/login")
	public ModelAndView  login(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = getRootMap();
		
		//System.out.println(demoService.sayHello("login:"+System.currentTimeMillis()));
		//返回n和e到页面上，有这两个值就能得出公钥
		context.put("n", RSAUtils.getModulus());
		context.put("e", RSAUtils.getPublicExponent());
		
		return forword("login", context);
	}
	
	
	
	/**
	 * 检查用户名称
	 * 
	 * @param user
	 * @param req
	 * @return
	 * @throws Exception 
	 */
	@Auth(verifyLogin=false,verifyURL=false)
	@RequestMapping("/checkuser")
	public void checkuser(SysUser user, HttpServletRequest req,HttpServletResponse response) throws Exception {
		
		String decryptpwd = RSAUtils.decryptPrivate(user.getPwd());
		
		SysUser u = sysUserService.queryLogin(user.getEmail(), MethodUtil.MD5(decryptpwd));
		
		if (u != null) {
				message = "用户: " + u.getNickName() + "登录成功";
				//-------------------------------------------------------
				//登录次数加1 修改登录时间
				int loginCount = 0;
				if(u.getLoginCount() != null){
					loginCount = u.getLoginCount();
				}
				u.setLoginCount(loginCount+1);
				u.setLoginTime(DateUtils.now());
				sysUserService.update(u);
				//设置User到Session
				AuthUtils.setUser(req,u);
				//记录成功登录日志
				log.debug(message);
				sendSuccessMessage(response,message);
				//-------------------------------------------------------
		} else {
			sendFailureMessage(response, "用户名或密码错误!");
		}
	}
	
	/**
	 * 登录
	 * @param email 邮箱登录账号
	 * @param pwd 密码
	 * @param verifyCode 验证码
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Auth(verifyLogin=false,verifyURL=false)
	@RequestMapping("/toLogin")
	public void  toLogin(String email,String pwd,String verifyCode,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String vcode  = AuthUtils.getValidateCode(request);
		AuthUtils.removeValidateCode(request);//清除验证码，确保验证码只能用一次
		if(StringUtils.isBlank(verifyCode)){
			sendFailureMessage(response, "验证码不能为空.");
			return;
		}
		//判断验证码是否正确
		if(!verifyCode.toLowerCase().equals(vcode)){
			sendFailureMessage(response, "验证码输入错误.");
			return;
		}
		if(StringUtils.isBlank(email)){
			sendFailureMessage(response, "账号不能为空.");
			return;
		}
		if(StringUtils.isBlank(pwd)){
			sendFailureMessage(response, "密码不能为空.");
			return;
		}
		String msg = "用户登录日志:";
		SysUser user = sysUserService.queryLogin(email, MethodUtil.MD5(pwd));
		if(user == null){
			//记录错误登录日志
			log.debug(msg+"["+email+"]"+"账号或者密码输入错误.");
			sendFailureMessage(response, "账号或者密码输入错误.");
			return;
		}
		if(STATE.DISABLE.key == user.getState()){
			sendFailureMessage(response, "账号已被禁用.");
			return;
		}
		//登录次数加1 修改登录时间
		int loginCount = 0;
		if(user.getLoginCount() != null){
			loginCount = user.getLoginCount();
		}
		user.setLoginCount(loginCount+1);
		user.setLoginTime(DateUtils.now());
		sysUserService.update(user);
		//设置User到Session
		AuthUtils.setUser(request,user);
		//记录成功登录日志
		log.debug(msg+"["+email+"]"+"登录成功");
		sendSuccessMessage(response, "登录成功.");
	}
	
	
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Auth(verifyLogin=false,verifyURL=false)
	@RequestMapping("/logout")
	public void  logout(HttpServletRequest request,HttpServletResponse response) throws Exception{
		AuthUtils.removeUser(request);
		response.sendRedirect("login.shtml");
	}
	
	/**
	 * 获取Action下的按钮
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Auth(verifyURL=false)
	@RequestMapping("/getActionBtn")
	public void  getActionBtn(String url,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		List<String> actionTypes = new ArrayList<String>();
		//判断是否超级管理员
		if(AuthUtils.isAdmin(request)){
			result.put("allType", true);
		}else{
			String menuUrl = URLUtils.getReqUri(url);
			menuUrl = StringUtils.remove(menuUrl,request.getContextPath());
			//获取权限按钮
			actionTypes = AuthUtils.getMemuBtnListVal(request, StringUtils.trim(menuUrl));
			result.put("allType", false);
			result.put("types", actionTypes);
		}
		result.put(SUCCESS, true);
		HtmlUtil.writerJson(response, result);
	}
	 
	/**
	 * 修改密码
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@Auth(verifyURL=false)
	@RequestMapping("/modifyPwd")
	public void modifyPwd(String oldPwd,String newPwd,HttpServletRequest request,HttpServletResponse response) throws Exception{
		SysUser user = AuthUtils.getUser(request);
		if(user == null){
			sendFailureMessage(response, "对不起,登录超时.");
			return;
		}
		SysUser bean  = sysUserService.queryById(user.getId());
		if(bean.getId() == null || DELETED.YES.key == bean.getDeleted()){
			sendFailureMessage(response, "对不起,用户不存在.");
			return;
		}
		if(StringUtils.isBlank(newPwd)){
			sendFailureMessage(response, "密码不能为空.");
			return;
		}
		//不是超级管理员，匹配旧密码
		if(!MethodUtil.ecompareMD5(oldPwd,bean.getPwd())){
			sendFailureMessage(response, "旧密码输入不匹配.");
			return;
		}
		bean.setPwd(MethodUtil.MD5(newPwd));
		sysUserService.update(bean);
		sendSuccessMessage(response, "Save success.");
	}
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@Auth(verifyURL=false)
	@RequestMapping("/main") 
	public ModelAndView  main(HttpServletRequest request){
		Map<String,Object>  context = getRootMap();
		SysUser user = AuthUtils.getUser(request);
		List<SysMenu> rootMenus = null;
		List<SysMenu> childMenus = null;
		List<SysMenuBtn> childBtns = null;
		//超级管理员
		if(user != null && SuperAdmin.YES.key ==  user.getSuperAdmin()){
			rootMenus = sysMenuService.getRootMenu(null);// 查询所有根节点
			childMenus = sysMenuService.getChildMenu();//查询所有子节点
		}else{
			rootMenus = sysMenuService.getRootMenuByUser(user.getId() );//根节点
			childMenus = sysMenuService.getChildMenuByUser(user.getId());//子节点
			childBtns = sysMenuBtnService.getMenuBtnByUser(user.getId());//按钮操作
			buildData(childMenus,childBtns,request); //构建必要的数据
		}
		context.put("user", user);
		context.put("menuList", treeMenu(rootMenus,childMenus));
		return forword("main/main",context); 
	}
	
	/**
	 * 构建树形数据
	 * @return
	 */
	private List<TreeNode> treeMenu(List<SysMenu> rootMenus,List<SysMenu> childMenus){
		TreeUtil util = new TreeUtil(rootMenus,childMenus);
		return util.getTreeNode();
	}
	
	/**
	 * 构建树形数据
	 * @return
	 */
	private void buildData(List<SysMenu> childMenus,List<SysMenuBtn> childBtns,HttpServletRequest request){
		//能够访问的url列表
		List<String> accessUrls  = new ArrayList<String>();
		//菜单对应的按钮
		Map<String,List<String>> menuBtnMap = new HashMap<String,List<String>>(); 
		for(SysMenu menu: childMenus){
			//判断URL是否为空
			if(StringUtils.isNotBlank(menu.getUrl())){
				
				log.debug("------------------"+menu.getUrl());
				
				List<String> btnTypes = new ArrayList<String>();
				for(SysMenuBtn btn  : childBtns){
					if(menu.getId().equals(btn.getMenuid())){
						btnTypes.add(btn.getBtnType());
						URLUtils.getBtnAccessUrls(menu.getUrl(), btn.getActionUrls(),accessUrls);
						
						log.debug(btn.getBtnType());
					}
				}
				menuBtnMap.put(menu.getUrl(), btnTypes);
				URLUtils.getBtnAccessUrls(menu.getUrl(), menu.getActions(),accessUrls);
				accessUrls.add(menu.getUrl());
				
			}
		}
		AuthUtils.setAccessUrl(request, accessUrls);//设置可访问的URL
		AuthUtils.setMemuBtnMap(request, menuBtnMap); //设置可用的按钮
	}
}
