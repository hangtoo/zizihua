package hangtoo.controller.menu;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

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
		return forword("hangtoo/menu/sysMenu",context); 
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
	
	/**
	 * 添加或修改数据
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public void save(SysMenu entity,Integer[] typeIds,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap<String,Object>();
		if(entity.getId()==null||StringUtils.isBlank(entity.getId().toString())){
			sysMenuService.add(entity);
		}else{
			sysMenuService.update(entity);
		}
		sendSuccessMessage(response, "保存成功~");
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
	
	
	
	@RequestMapping("/delete")
	public void delete(String[] id,HttpServletResponse response) throws Exception{
		sysMenuService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}
	
	///////////手动添加//////////
	
	
	/**
	 * getChildMenus
	 * @param url
	 * @param classifyId
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
				json.put("id","menu_"+sysMenu.getId());
				json.put("target","navtab");

				ret.add(json);
			}
			jsonMap.put("name",rootMenus.getName());
			jsonMap.put("children", ret);
		
		}
		
		HtmlUtil.writerJson(response, jsonMap);
	}
	
}
