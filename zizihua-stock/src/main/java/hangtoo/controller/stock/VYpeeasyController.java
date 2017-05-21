package hangtoo.controller.stock;

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

import hangtoo.entity.stock.VYpeeasy;
import hangtoo.page.stock.VYpeeasyPage;
import hangtoo.service.stock.VYpeeasyService;
 
/**
 * 
 * <br>
 * <b>功能：</b>VYpeeasyController<br>
 * <b>作者：</b>hlf<br>
 * <b>日期：</b> Sun Jul 24 10:00:21 CST 2016 <br>
 * <b>版权所有： 2014，hangtoo.com<br>
 */ 
@Controller
@RequestMapping("/vYpeeasy") 
public class VYpeeasyController extends BaseAction{
	
	private final static Logger log= Logger.getLogger(VYpeeasyController.class);
	
	@Autowired
	private VYpeeasyService vYpeeasyService; 
	
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(VYpeeasyPage page,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("hangtoo/stock/vYpeeasy",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  datalist(VYpeeasyPage page,HttpServletResponse response) throws Exception{
		List<VYpeeasy> dataList = vYpeeasyService.queryByList(page);
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
	public void save(VYpeeasy entity,Integer[] typeIds,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap<String,Object>();
		if(entity.getId()==null||StringUtils.isBlank(entity.getId().toString())){
			vYpeeasyService.add(entity);
		}else{
			vYpeeasyService.update(entity);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	
	@RequestMapping("/getId")
	public void getId(String id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap();
		VYpeeasy entity  = vYpeeasyService.queryById(id);
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
		vYpeeasyService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

}
