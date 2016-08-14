package com.hangtoo.html.decode.impl;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Jsoup;
import org.junit.Test;

import com.hangtoo.html.decode.IHtmlDecoder;

public class JsoupTableDecoderTest {

	@Test
	public void testGetData() {
		IHtmlDecoder jsoupDecoder=new JsoupTableDecoder();
		try {
			String url="http://www.szse.cn/szseWeb/FrontController.szse?ACTIONID=7&AJAX=AJAX-TRUE&CATALOGID=1803&TABKEY=tab1&txtQueryDate=2016-05-20&REPORT_ACTION=search";
			String src=Jsoup.connect(url).get().html();
			String tableID="REPORTID_tab1";
			
			Map<Integer,String> data=jsoupDecoder.getData(src,tableID);
			for(Map.Entry<Integer,String> entry:data.entrySet()){
				System.out.println(entry.getKey()+":"+entry.getValue());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetData1() {
		IHtmlDecoder jsoupDecoder=new JsoupTableDecoder();
		try {
			String url="http://www.sge.com.cn/xqzx/mrxq/539598.shtml";
			String src=Jsoup.connect(url).get().html();
			String tableID="page_con";
			
			Map<Integer,String> data=jsoupDecoder.getData(src,tableID);
			for(Map.Entry<Integer,String> entry:data.entrySet()){
				System.out.println(entry.getKey()+":"+entry.getValue());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
