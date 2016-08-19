package com.hangtoo.html.decode.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hangtoo.html.decode.ITargetAttrDecoder;
public class TargetAttrDecoder implements ITargetAttrDecoder {

	@Override
	public List<String> getData(String html, String tableID, String targetTag, String targetAttr) throws IOException {
		Document doc = Jsoup.parse(html);
		Element reportTable=doc.getElementById(tableID);
		Elements tags=reportTable.getElementsByTag(targetTag);
		List<String> ret=new ArrayList<>();
		for(int i=0;i<tags.size();i++){
			Element e=tags.get(i);
			ret.add(e.attr(targetAttr));
		}
		return ret;
	}

	
	@Override
	public List<Element> getData(String html, String tableID, String targetTag) throws IOException {
		Document doc = Jsoup.parse(html);
		Element reportTable=doc.getElementById(tableID);
		Elements tags=reportTable.getElementsByTag(targetTag);
		List<Element> ret=new ArrayList<>();
		for(int i=0;i<tags.size();i++){
			Element e=tags.get(i);
			ret.add(e);
		}
		return ret;
	}
}
