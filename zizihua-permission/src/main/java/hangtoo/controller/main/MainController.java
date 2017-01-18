package hangtoo.controller.main;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hangtoo.base.annotation.Auth;
import com.hangtoo.base.util.HtmlUtil;
import com.hangtoo.base.util.MethodUtil;
import com.hangtoo.base.util.SessionUtils;
import com.hangtoo.base.util.Constant.SuperAdmin;
import com.hangtoo.base.web.BaseAction;
import com.hangtoo.util.DateUtils;
import com.hangtoo.util.RSAUtils;

import hangtoo.entity.menu.SysMenu;
import hangtoo.entity.user.SysUser;
import hangtoo.page.menu.SysMenuPage;
import hangtoo.service.menu.SysMenuService;
import hangtoo.service.user.SysUserService;
 
/**
 * 
 * <br>
 * <b>功能：</b>SysUserController<br>
 * <b>作者：</b>hlf<br>
 * <b>日期：</b> Tue Jan 17 17:05:46 CST 2017 <br>
 * <b>版权所有： 2014，hangtoo.com<br>
 */ 
@Controller
@Component
public class MainController extends BaseAction{
	
	private final static Logger log= Logger.getLogger(MainController.class);
	
	@Autowired
	private SysUserService sysUserService; 
	
	@Autowired
	private SysMenuService sysMenuService; 
	
	/**
	 * 登录页面
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@Auth(verifyLogin=false,verifyURL=false)
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public ModelAndView  login(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = getRootMap();
		
		//返回n和e到页面上，有这两个值就能得出公钥
		context.put("n", RSAUtils.getModulus());
		context.put("e", RSAUtils.getPublicExponent());
		
		return forword("hangtoo/main/login", context);
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
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public void login(SysUser user, HttpServletRequest req,HttpServletResponse response) throws Exception {
		
		String decryptpwd = RSAUtils.decryptPrivate(user.getPwd());
		
		SysUser u = sysUserService.queryByEmail(user.getEmail());
		
		if (u != null&&u.getPwd().equals(MethodUtil.MD5(decryptpwd))) {
				String message = "用户: " + u.getNickname() + "登录成功";
				//-------------------------------------------------------
				//登录次数加1 修改登录时间
				int loginCount = 0;
				if(u.getLogincount() != null){
					loginCount = u.getLogincount();
				}
				u.setLogincount(loginCount+1);
				u.setLogintime(DateUtils.now());
				sysUserService.update(u);
				//设置User到Session
				SessionUtils.setUser(req,u);
				//记录成功登录日志
				log.debug(message);
				sendSuccessMessage(response,message);
				//-------------------------------------------------------
		} else {
			sendFailureMessage(response, "用户名或密码错误!");
		}
	}
	
	
	/**
	 * index 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/index") 
	public ModelAndView  index(HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		
		Object obj=SessionUtils.getUser(request);
		if(obj!=null&&obj instanceof SysUser){
			SysUser user=(SysUser)obj;
			List<SysMenu> rootMenus = sysMenuService.queryRootMenu();
			context.put("menuList", rootMenus);
			rootMenus = sysMenuService.queryRootMenuByUserId(user);
			return forword("hangtoo/main/index",context); 
		}
		
		return forword("redirect:/login.shtml", context);
		
	}
	
	

	
}
