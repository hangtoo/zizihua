package com.hangtoo.html.decode;

import java.io.IOException;
import java.util.Map;

public interface HtmlDecoder {
	Map<Integer,String> getData(String html,String tableID) throws IOException; 
}
