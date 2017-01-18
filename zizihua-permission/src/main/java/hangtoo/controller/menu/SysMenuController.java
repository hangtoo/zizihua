package hangtoo.controller.menu;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hangtoo.base.util.Constant.SuperAdmin;
import com.hangtoo.base.util.HtmlUtil;
import com.hangtoo.base.util.SessionUtils;
import com.hangtoo.base.web.BaseAction;

import hangtoo.entity.menu.SysMenu;
import hangtoo.entity.user.SysUser;
import hangtoo.page.menu.SysMenuPage;
import hangtoo.service.menu.SysMenuService;
 
/**
 * 
 * <br>
 * <b>功能：</b>SysMenuController<br>
 * <b>作者：</b>hlf<br>
 * <b>日期：</b> Tue Jan 17 17:32:42 CST 2017 <br>
 * <b>版权所有： 2014，hangtoo.com<br>
 */ 
@Controller
@RequestMapping("/sysMenu") 
public class SysMenuController extends BaseAction{
	
	private final static Logger log= Logger.getLogger(SysMenuController.class);
	
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
	public ModelAndView  list(SysMenuPage page,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("hangtoo/menu/list",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  datalist(SysMenuPage page,HttpServletResponse response) throws Exception{
		List<SysMenu> dataList = sysMenuService.queryByList(page);
		//设置页面数据
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("total",page.getPager().getRowCount());
		jsonMap.put("rows", dataList);
		HtmlUtil.writerJson(response, jsonMap);
	}
	
	///TODO被修改过/////
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
		SysMenu sysmenu=(SysMenu) super.json2Obj(request,SysMenu.class);
		
		if(sysmenu.getId()==null||StringUtils.isBlank(sysmenu.getId().toString())){
			sysmenu.setDeleted(0);
			sysMenuService.add(sysmenu);
		}else{
			sysMenuService.update(sysmenu);
		}
		sendSuccessStatus(response, "保存成功~");
	}
	
	
	@RequestMapping("/getId")
	public void getId(String id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap();
		SysMenu entity  = sysMenuService.queryById(id);
		if(entity  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		context.put(SUCCESS, true);
		context.put("data", entity);
		HtmlUtil.writerJson(response, context);
	}
	
	///TODO被修改过/////
	
	@RequestMapping("/delete")
	public void delete(HttpServletRequest request,HttpServletResponse response) throws Exception{
		SysMenu sysmenu=(SysMenu) super.json2Obj(request,SysMenu.class);
		sysMenuService.delete(sysmenu.getId());
		sendSuccessStatus(response, "删除成功");
	}
	
	///////////TODO手动添加//////////
	
	
	/**
	 * 获取主页左边的二级树菜单的
	 * getChildMenus
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/getChildMenus") 
	public void  getChildMenus(SysMenuPage page,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Object obj=SessionUtils.getUser(request);
		

		//设置页面数据
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		
		SysMenu rootMenus=sysMenuService.queryById(page.getId());
		if(obj!=null&&obj instanceof SysUser){
			SysUser user=(SysUser)obj;
			List<SysMenu> childMenus =null;
			if(user.getSuperadmin() != SuperAdmin.YES.key){
				childMenus = sysMenuService.queryMenuByUserIdAndParentId(user.getId(),page.getId());
			}else{
				childMenus = sysMenuService.queryMenuByUserIdAndParentId(null,page.getId());
			}
			
			List<Map<String,String>> ret=new ArrayList();
			Field[] fields = SysMenu.class.getDeclaredFields();
			for(SysMenu sysMenu:childMenus){
				Map<String,String> json=new HashMap<>();
				for (Field f : fields) {
					f.setAccessible(true);
					json.put(f.getName(), String.valueOf(f.get(sysMenu)));
				}
				
				//根据菜单要求进行属性变更
				json.put("id","menu_"+sysMenu.getId());
				json.put("target","navtab");

				ret.add(json);
			}
			jsonMap.put("name",rootMenus.getName());
			jsonMap.put("children", ret);
		
		}
		
		HtmlUtil.writerJson(response, jsonMap);
	}
	
	/**
	 * 所有需要编辑的树菜单列表
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataTree") 
	public void  dataTree(SysMenuPage page,HttpServletResponse response) throws Exception{
		List<SysMenu> dataList = sysMenuService.queryByList(page);
		//设置页面数据
		HtmlUtil.writerJson(response, dataList);
	}
	
}
