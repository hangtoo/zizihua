package com.hangtoo.auth.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hangtoo.auth.entity.SysMenu;
import com.hangtoo.auth.entity.SysRole;
import com.hangtoo.auth.entity.SysRoleRel;
import com.hangtoo.auth.entity.SysRoleRel.RelType;
import com.hangtoo.auth.page.SysRoleModel;
import com.hangtoo.auth.service.SysMenuService;
import com.hangtoo.auth.service.SysRoleRelService;
import com.hangtoo.auth.service.SysRoleService;
import com.hangtoo.base.util.HtmlUtil;
import com.hangtoo.base.web.BaseAction;
 
@Controller
@RequestMapping("/sysRole") 
public class SysRoleAction extends BaseAction{
	
	// Servrice start
	@Autowired(required=false) 
	private SysRoleService sysRoleService; 
	
	// Servrice start
	@Autowired(required=false) 
	private SysMenuService sysMenuService; 
	@Autowired(required=false) 
	private SysRoleRelService sysRoleRelService;
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/role")
	public ModelAndView  list(SysRoleModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("sys/sysRole",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  datalist(SysRoleModel model,HttpServletResponse response) throws Exception{
		List<SysRole> dataList = sysRoleService.queryByList(model);
		//设置页面数据
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("total",model.getPager().getRowCount());
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
	public void save(SysRole bean,Integer[] menuIds,Integer[] btnIds,HttpServletResponse response) throws Exception{
		if(bean.getId() == null){
			sysRoleService.add(bean,menuIds,btnIds);
		}else{
			sysRoleService.update(bean,menuIds,btnIds);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap<String,Object> ();
		SysRole bean  = sysRoleService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		//获取权限关联的菜单
		Integer[] menuIds = null;
		List<SysMenu> menuList =  sysMenuService.getMenuByRoleId(id);
		if(menuList != null){
			menuIds = new Integer[menuList.size()];
			int i = 0;
			for(SysMenu item : menuList){
				menuIds[i] = item.getId();
				i++;
			}
		}
		//获取权限下关联的按钮
		Integer[] btnIds = null;
		List<SysRoleRel>  btnList =sysRoleRelService.queryByRoleId(id, RelType.BTN.key);
		if(btnList != null){
			btnIds = new Integer[btnList.size()];
			int i = 0;
			for(SysRoleRel item : btnList){
				btnIds[i] = item.getObjId();
				i++;
			}
		}

		//将对象转成Map
		Map<String, Object> data = PropertyUtils.describe(bean);
		data.put("menuIds", menuIds);
		data.put("btnIds", btnIds);
		context.put(SUCCESS, true);
		context.put("data", data);
		HtmlUtil.writerJson(response, context);
	}
	
	@RequestMapping("/delete")
	public void delete(Object[] id,HttpServletResponse response) throws Exception{
		sysRoleService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}
	
	@RequestMapping("/loadRoleList")
	public void loadRoleList(HttpServletResponse response) throws Exception{
		List<SysRole>  roloList = sysRoleService.queryAllList();
		HtmlUtil.writerJson(response, roloList);
	}


	
	
}
