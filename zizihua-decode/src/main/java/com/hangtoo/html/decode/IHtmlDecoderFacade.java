package com.hangtoo.html.decode;

import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Element;

import com.hangtoo.html.EnumHeaderStyle;

/**
 * 
 * @author hlf
 * get data from the url by tableId 
 */
public interface IHtmlDecoderFacade {

	//public Map<Integer, String> getTableData(String url,String tableId) throws Exception ;

	/**
	 * 根据url获取指定tableID下的数据对
	 * @param url
	 * @param tableID
	 * @param enumheaderStyle
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> getTableData(String url, String tableID,EnumHeaderStyle enumheaderStyle) throws Exception;
	
	/**
	 * 根据url获取指定tableID下的Element列表
	 * @param url
	 * @param tableID
	 * @param enumheaderStyle
	 * @param targetTag
	 * @return
	 * @throws Exception
	 */
	public List<Element> getTargetAttr(String url,String tableID,EnumHeaderStyle enumheaderStyle,String targetTag) throws Exception ;
	
	/**
	 * 根据url获取指定tableID下的targetTag的targetAttr属性
	 * @param url
	 * @param tableID
	 * @param enumheaderStyle
	 * @param targetTag
	 * @param targetAttr
	 * @return
	 * @throws Exception
	 */
	public List<String> getTargetAttr(String url,String tableID,EnumHeaderStyle enumheaderStyle,String targetTag,String targetAttr) throws Exception ;
	
}
