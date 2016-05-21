package com.hangtoo.html.decode.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hangtoo.html.decode.impl.bean.KeyValue;
import com.hangtoo.util.FileUtil;

/**
 * 
 * @author hlf
 * 确保每个数据均包含在  ><之间
 * 如数据为：>thevalue<
 * 模板 为 ：>{key}<
 * 
 * 返回值为 key=thevalue
 */
public class TempleteDecoder {
    private static final Logger logger = LoggerFactory.getLogger(TempleteDecoder.class);

    private static String template;
	
	public static String getTemplate() {
		return template;
	}
	
	public static void setTemplate(String template) {
		TempleteDecoder.template =new StringBuilder(">").append(template).append("<").toString();
	}
	
	public static void setFileTemplate(String filename){
		String filedata=FileUtil.readFile(filename);
		TempleteDecoder.template =new StringBuilder(">").append(filedata).append("<").toString();
	}

	
	
	public static Map<String,String> getData(String src,String template){
		setTemplate(template);
		
		return getData(src);
	}
	
	public static Map<String,String> getData(String src){
		
		if(StringUtils.isEmpty(template)){
			return null;
		}
		
		Map<String,String> result=new HashMap<String,String>();
		
        if ((src != null) && (src.length() > 0)) {
        	
        	KeyValue keyValue=new KeyValue();
        	
        	src=new StringBuilder(">").append(src).append("<").toString();
        	logger.debug(src);

            for (int iSrc=0,iTemp = 0; iSrc <= src.length() - 1&&iTemp<=template.length() - 1; ) {  
            	//logger.debug("ri="+ri+";tj="+tj+";");
            	
            	if(keyValue.getOldISrc()==iSrc&&keyValue.getOldITemp()==iTemp){
            		logger.debug(src.substring(0,iSrc));
            		logger.info("==============decoder error===============");
            		logger.debug(template.substring(0,iTemp));
            		break;
            	}else{
            		keyValue.setOldISrc(iSrc);
                    keyValue.setOldITemp(iTemp);
            	}

                keyValue.setcSrc(src.charAt(iSrc));
                keyValue.setcTemp(template.charAt(iTemp));

                //跳过回车符，制表符，换行符，空格等
            	if(keyValue.getcSrc()=='\n'||keyValue.getcSrc()=='\t'||keyValue.getcSrc()==' '||keyValue.getcSrc()=='\r'){
            		iSrc++;
            		continue;
            	}
            	if(keyValue.getcTemp()=='\n'||keyValue.getcTemp()=='\t'||keyValue.getcTemp()==' '||keyValue.getcTemp()=='\r'){
            		iTemp++;
            		continue;
            	}
            	//模板为*表示可以匹配任何字符，同步往前
            	if(keyValue.getcTemp()=='*'){
                    iSrc++;
            		iTemp++;
            		continue;
            	}
                //如果相同,则同步往前
            	if(keyValue.getcSrc()==keyValue.getcTemp()){
                    switch (keyValue.getcSrc()) {
                    case '>':
                        keyValue.setiValueStart(iSrc);
                        break;  
                    case '<':
                        keyValue.setiValueEnd(iSrc);
                    	break;
                    }
                    iSrc++;
            		iTemp++;
            	}else{
            		switch(keyValue.getcTemp()){
            		case '{':
                        keyValue.setiKeyStart(iTemp);
            			break;
            		case '}':
                        keyValue.setiKeyEnd(iTemp);
                        keyValue.setKey(template.substring(keyValue.getiKeyStart()+1,keyValue.getiKeyEnd()));
                        keyValue.setiKeyEnd(-1);
                        keyValue.setGetKey(true);
            			iTemp++;
            			break;
            		}
            		//先走模板，找key
        			if(!keyValue.isGetKey()){
        				iTemp++;
        			}
        			//再走待解析字符串，找value
    				if(!keyValue.isGetValue()&&keyValue.getcSrc()!='<'){
    					iSrc++;
    				}
            	}

                //在找到key及value的开始字符，并且找到了value的结束字符，且结束字符位置要晚于开始字符位置
    			if(keyValue.getiKeyStart()!=-1&keyValue.getiValueStart()!=-1&&keyValue.getiValueEnd()!=-1&&keyValue.getiValueEnd()>keyValue.getiValueStart()){
                    keyValue.setValue(src.substring(keyValue.getiValueStart()+1,keyValue.getiValueEnd()));
                    keyValue.setGetValue(true);
                    keyValue.setiValueStart(-1);
                    keyValue.setiValueEnd(-1);
                    keyValue.setiKeyStart(-1);
    			}
    			logger.debug(keyValue.toString());
            	if(keyValue.isGetKey()&&keyValue.isGetValue()){
            		result.put(keyValue.getKey(), keyValue.getValue());
                    keyValue.setGetKey(false);
                    keyValue.setGetValue(false);
            	}
            }
        }

		return result;
	}
	
}
