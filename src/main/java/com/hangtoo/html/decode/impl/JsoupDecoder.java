package com.hangtoo.html.decode.impl;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsoupDecoder {
	public String getData(String url) throws IOException{
		Document doc = Jsoup.connect(url).get();
		Elements newsHeadlines = doc.select("a");
		if(newsHeadlines.size()>0){
			return newsHeadlines.get(0).toString();
		}
		return null;
	}
}
