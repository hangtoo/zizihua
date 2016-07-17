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

import hangtoo.entity.stock.TStock;
import hangtoo.page.stock.TStockPage;
import hangtoo.service.stock.TStockService;
 
/**
 * 
 * <br>
 * <b>功能：</b>TStockController<br>
 * <b>作者：</b>hlf<br>
 * <b>日期：</b> Sun Jul 17 20:21:28 CST 2016 <br>
 * <b>版权所有： 2014，hangtoo.com<br>
 */ 
@Controller
@RequestMapping("/tStock") 
public class TStockController extends BaseAction{
	
	private final static Logger log= Logger.getLogger(TStockController.class);
	
	@Autowired
	private TStockService tStockService; 
	
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(TStockPage page,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("hangtoo/stock/tStock",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  datalist(TStockPage page,HttpServletResponse response) throws Exception{
		List<TStock> dataList = tStockService.queryByList(page);
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
	public void save(TStock entity,Integer[] typeIds,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap<String,Object>();
		if(entity.getId()==null||StringUtils.isBlank(entity.getId().toString())){
			tStockService.add(entity);
		}else{
			tStockService.update(entity);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	
	@RequestMapping("/getId")
	public void getId(String id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap();
		TStock entity  = tStockService.queryById(id);
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
		tStockService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

}
