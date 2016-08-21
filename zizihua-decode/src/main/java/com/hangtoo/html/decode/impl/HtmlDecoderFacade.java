package com.hangtoo.html.decode.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Element;

import com.hangtoo.common.impl.HttpClientGetPostData;
import com.hangtoo.html.EnumHeaderStyle;
import com.hangtoo.html.decode.IHtmlDecoder;
import com.hangtoo.html.decode.IHtmlDecoderFacade;
import com.hangtoo.html.decode.ITargetAttrDecoder;
import com.hangtoo.util.Constants;

public class HtmlDecoderFacade implements IHtmlDecoderFacade {
	IHtmlDecoder jsoupDecoder = new JsoupTableDecoder();
	ITargetAttrDecoder targetAttrDecoder=new TargetAttrDecoder();

	private Map<Integer, String> getTableData(String url, String tableID) throws IOException {
		String src = null;
		try {
			src = HttpClientGetPostData.getInstance().getData(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
/*		if (StringUtils.isEmpty(src)) {
			src = Jsoup.connect(url).get().html();
		}*/
		
		if(src==null){
			System.out.println("get url error:"+url);
			return null;
		}
		Map<Integer, String> data = jsoupDecoder.getData(src, tableID);

		return data;
	}
	private List<Element> getTableData(String url, String tableID,String targetTag) throws IOException {
		String src = null;
		try {
			src = HttpClientGetPostData.getInstance().getData(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
/*		if (StringUtils.isEmpty(src)) {
			src = Jsoup.connect(url).get().html();
		}*/
		
		if(src==null){
			System.out.println("get url error:"+url);
			return null;
		}
		
		List<Element> data = targetAttrDecoder.getData(src, tableID,targetTag);

		return data;
	}

	private List<String> getTableData(String url, String tableID,String targetTag,String targetAttr) throws IOException {
		String src = null;
		try {
			src = HttpClientGetPostData.getInstance().getData(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
/*		if (StringUtils.isEmpty(src)) {
			src = Jsoup.connect(url).get().html();
		}*/
		if(src==null){
			System.out.println("get url error:"+url);
			return null;
		}
		List<String> data = targetAttrDecoder.getData(src, tableID,targetTag,targetAttr);

		return data;
	}
	
	@Override
	public List<Element> getTargetAttr(String url,String tableID,EnumHeaderStyle enumheaderStyle,String targetTag) throws Exception{
		if (enumheaderStyle != EnumHeaderStyle.TOP) {
			throw new Exception("only support EnumHeaderStyle.TOP");
		}
		List<Element> datamap = this.getTableData(url, tableID,targetTag);
		
		return datamap;
	}
	
	@Override
	public List<String> getTargetAttr(String url,String tableID,EnumHeaderStyle enumheaderStyle,String targetTag,String targetAttr) throws Exception{
		if (enumheaderStyle != EnumHeaderStyle.TOP) {
			throw new Exception("only support EnumHeaderStyle.TOP");
		}
		List<String> datamap = this.getTableData(url, tableID,targetTag,targetAttr);
		
		return datamap;
	}
	
	@Override
	public List<Map<String, String>> getTableData(String url, String tableID, EnumHeaderStyle enumheaderStyle)
			throws Exception {
		if (enumheaderStyle != EnumHeaderStyle.TOP) {
			throw new Exception("only support EnumHeaderStyle.TOP");
		}
		Map<Integer, String> datamap = this.getTableData(url, tableID);
		if (datamap == null)
			return null;
		String strsize = datamap.get(Constants.TRSIZETAG);
		if (strsize == null)
			return null;
		String stdsize = datamap.get(Constants.TDSIZETAG);
		int trsize = Integer.valueOf(strsize);
		int tdsize = Integer.valueOf(stdsize);

		List<Map<String, String>> ret = new ArrayList<Map<String, String>>(trsize);
		// table header
		List<String> header = new ArrayList<String>(tdsize);
		for (int j = 0; j < tdsize; j++) {
			header.add(datamap.get(j));
		}

		Map<String, String> ele = new HashMap<String, String>();
		for (int i = 0; i < trsize - 1; i++) {
			for (int j = 0; j < tdsize; j++) {
				ele.put(header.get(j), datamap.get((i + 1) * Constants.TDMAX + j));
			}
			ret.add(ele);
			ele = new HashMap<String, String>();
		}

		return ret;
	}
}
