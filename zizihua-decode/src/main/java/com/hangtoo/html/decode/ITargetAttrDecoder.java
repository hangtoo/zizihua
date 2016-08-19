package com.hangtoo.html.decode;

import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Element;

/**
 * 
 * @author hlf
 * decode attr from the html by tableId and target tag/attr
 */
public interface ITargetAttrDecoder {
	List<String> getData(String html,String tableId,String targetTag,String targetAttr) throws IOException; 
	public List<Element> getData(String html, String tableID, String targetTag) throws IOException ;
}
