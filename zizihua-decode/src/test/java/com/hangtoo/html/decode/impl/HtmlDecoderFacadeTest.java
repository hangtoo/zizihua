package com.hangtoo.html.decode.impl;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.hangtoo.base.util.json.JSONUtil;
import com.hangtoo.html.EnumHeaderStyle;
import com.hangtoo.html.decode.IHtmlDecoderFacade;

public class HtmlDecoderFacadeTest {

	@Test
	public void testGetTableData() {
		IHtmlDecoderFacade htmlDecoderFacade=new HtmlDecoderFacade();
		try {
			String url="http://www.szse.cn/szseWeb/FrontController.szse?ACTIONID=7&AJAX=AJAX-TRUE&CATALOGID=1803&TABKEY=tab1&txtQueryDate=2016-03-30&REPORT_ACTION=search";
			String tableID="REPORTID_tab1";
			List<Map<String,String>> data=htmlDecoderFacade.getTableData(url,tableID,EnumHeaderStyle.TOP);
			System.out.println(JSONUtil.toJSONString(data,true));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
