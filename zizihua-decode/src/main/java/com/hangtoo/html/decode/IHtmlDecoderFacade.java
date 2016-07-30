package com.hangtoo.html.decode;

import java.util.List;
import java.util.Map;

import com.hangtoo.html.EnumHeaderStyle;

/**
 * 
 * @author hlf
 * get data from the url by tableId 
 */
public interface IHtmlDecoderFacade {
	public Map<Integer, String> getTableData(String url,String tableId) throws Exception ;
	public List<Map<String,String>> getTableData(String url, String tableID,EnumHeaderStyle enumheaderStyle) throws Exception;
}
