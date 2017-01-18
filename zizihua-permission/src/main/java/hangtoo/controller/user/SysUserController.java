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

	
}
