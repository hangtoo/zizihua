package com.hangtoo.html.decode;

import java.io.IOException;
import java.util.Map;

/**
 * 
 * @author hlf
 * decode data from the html by tableId
 */
public interface IHtmlDecoder {
	Map<Integer,String> getData(String html,String tableId) throws IOException; 
}
