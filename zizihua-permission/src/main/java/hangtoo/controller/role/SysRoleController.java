package hangtoo.controller.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hangtoo.base.util.HtmlUtil;
import com.hangtoo.base.web.BaseAction;

import hangtoo.entity.role.SysRole;
import hangtoo.page.role.SysRolePage;
import hangtoo.service.role.SysRoleService;

/**
 *
 * <br>
 * <b>功能：</b>SysRoleController<br>
 * <b>作者：</b>hlf<br>
 * <b>日期：</b> Wed Jan 18 16:54:57 CST 2017 <br>
 * <b>版权所有： 2014，hangtoo.com<br>
 */
@Controller
@RequestMapping("/sysRole")
public class SysRoleController extends BaseAction{

	private final static Logger log= Logger.getLogger(SysRoleController.class);

	@Autowired
	private SysRoleService sysRoleService;


	/**
	 *
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public ModelAndView  list(SysRolePage page,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("hangtoo/role/list",context);
	}


	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/dataList")
	public void  datalist(SysRolePage page,HttpServletResponse response) throws Exception{
		List<SysRole> dataList = sysRoleService.queryByList(page);
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
		SysRole entity=(SysRole) super.json2Obj(request,SysRole.class);
		if(entity.getId()==null||StringUtils.isBlank(entity.getId().toString())){
			sysRoleService.add(entity);
		}else{
			sysRoleService.update(entity);
		}
		sendSuccessStatus(response, "保存成功~");
	}


	@RequestMapping("/getId")
	public void getId(String id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap();
		SysRole entity  = sysRoleService.queryById(id);
		if(entity  == null){
			sendFailureStatus(response, "没有找到对应的记录!");
			return;
		}
		context.put(SUCCESS, true);
		context.put("data", entity);
		HtmlUtil.writerJson(response, context);
	}



	@RequestMapping("/delete")
	public void delete(HttpServletRequest request,HttpServletResponse response) throws Exception{
    SysRole entity=(SysRole) super.json2Obj(request,SysRole.class);
		sysRoleService.delete(entity.getId());
		sendSuccessStatus(response, "删除成功");
	}

}
