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
		Element reportTable=getRealTable(doc.getElementById(tableID));
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
	
	/**
	 * 处理table里面嵌套table的情况
	 * 在原有table里面查找table标签，如果有，则以起元素相对较大的table作为真正的表格
	 */
	public Element getRealTable(Element reportTable){
		Elements childTables=reportTable.getElementsByTag(Constants.TABLE);
		if(childTables.size()<=1){//除自己以外，校验里面是否还有table
			return reportTable;
		}
		int maxelenum=0;
		int tmpnum;
		Element ret=null;
		Element ele=null;
		for(int i=1;i<childTables.size();i++){//除去自己
			ele=childTables.get(i);
			tmpnum=ele.getAllElements().size();
			if(tmpnum>maxelenum){
				maxelenum=tmpnum;
				ret=ele;
			}
		}
		return this.getRealTable(ret);
	}
	
}
