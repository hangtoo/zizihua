package hangtoo.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hangtoo.base.annotation.Auth;
import com.hangtoo.base.util.HtmlUtil;
import com.hangtoo.base.util.MethodUtil;
import com.hangtoo.base.util.SessionUtils;
import com.hangtoo.base.web.BaseAction;
import com.hangtoo.util.DateUtils;
import com.hangtoo.util.RSAUtils;

import hangtoo.entity.menu.SysMenu;
import hangtoo.entity.user.SysUser;
import hangtoo.page.user.SysUserPage;
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
@RequestMapping("/sysUser") 
public class SysUserController extends BaseAction{
	
	private final static Logger log= Logger.getLogger(SysUserController.class);
	
	@Autowired
	private SysUserService sysUserService; 
	
	@Autowired
	private SysMenuService sysMenuService; 
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(SysUserPage page,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("hangtoo/user/list",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  datalist(SysUserPage page,HttpServletResponse response) throws Exception{
		List<SysUser> dataList = sysUserService.queryByList(page);
		//设置页面数据
		HtmlUtil.writerJson(response, dataList);
	}
	
	/**
	 * 添加或修改数据
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public void save(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap<String,Object>();
		SysUser entity=(SysUser) super.json2Obj(request,SysUser.class);
		if(entity.getId()==null||StringUtils.isBlank(entity.getId().toString())){
			entity.setDeleted(0);
			sysUserService.add(entity);
		}else{
			sysUserService.update(entity);
		}
		sendSuccessStatus(response, "保存成功~");
	}
	
	
	@RequestMapping("/getId")
	public void getId(String id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap();
		SysUser entity  = sysUserService.queryById(id);
		if(entity  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		context.put(SUCCESS, true);
		context.put("data", entity);
		HtmlUtil.writerJson(response, context);
	}
	
	
	
	@RequestMapping("/delete")
	public void delete(HttpServletRequest request,HttpServletResponse response) throws Exception{
		SysUser entity=(SysUser) super.json2Obj(request,SysUser.class);
		sysUserService.delete(entity.getId());
		sendSuccessStatus(response, "删除成功");
	}

	
	///////////手动添加//////////
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
		
		return forword("hangtoo/user/login", context);
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
			return forword("hangtoo/menu/index",context); 
		}
		
		return forword("redirect:/sysUser/login.shtml", context);
		
	}
	
}
