package com.hangtoo.html.decode.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hangtoo.html.decode.IHtmlDecoder;
import com.hangtoo.util.Constants;

public class JsoupTableDecoder implements IHtmlDecoder{
	public Map<Integer,String> getData(String html,String tableID) throws IOException{
		Map<Integer,String> ret=new HashMap<Integer,String>();
		Document doc = Jsoup.parse(html);
		Element reportTable=doc.getElementById(tableID);
		Elements trs=reportTable.getElementsByTag(Constants.TR);
		int maxtdsize=0;
		for(int i=0;i<trs.size();i++){
			Elements tds=trs.get(i).getElementsByTag(Constants.TD);
			if(tds.size()>maxtdsize){
				maxtdsize=tds.size();
			}
			for(int j=0;j<tds.size();j++){
				Element td=tds.get(j);
				ret.put(i*Constants.TDMAX+j, td.text());
			}
		}
		ret.put(Constants.TRSIZETAG, ""+trs.size());
		ret.put(Constants.TDSIZETAG, ""+maxtdsize);
		
		return ret;
	}
}
