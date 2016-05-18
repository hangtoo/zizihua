package com.hangtoo.html.decode.impl;

import com.hangtoo.util.FileUtil;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

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

    @Getter
    @Setter
	static class Keyvalue{
        static int oldISrc=-1,oldITemp=-1;//上次解析的位置，包括待解析串及模板串
        static char cSrc,cTemp;//正在解析的字符及模板字符

        static int iKeyStart=-1,iKeyEnd=-1;//key的开始位置，key结束位置,比如{date1}
        static int iValueStart=-1,iValueEnd=-1;//标签的开始位置，标签的结束位置，比如>2016-01-02<
        static String key=null,value=null;//获得的key，获得的value
        static boolean isGetKey=false,isGetValue=false;//是否已经获得当前key和value

        @Override
        public String toString(){
        	JSONArray json = new JSONArray(this);
        	return json.toString();
        }
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
        	
        	src=new StringBuilder(">").append(src).append("<").toString();
        	logger.debug(src);

            for (int iSrc=0,iTemp = 0; iSrc <= src.length() - 1&&iTemp<=template.length() - 1; ) {  
            	//logger.debug("ri="+ri+";tj="+tj+";");
            	
            	if(Keyvalue.oldISrc==iSrc&&Keyvalue.oldITemp==iTemp){
            		logger.debug(src.substring(0,iSrc));
            		logger.info("==============decoder error===============");
            		logger.debug(template.substring(0,iTemp));
            		break;
            	}else{
            		Keyvalue.oldISrc=iSrc;
                    Keyvalue.oldITemp=iTemp;
            	}

                Keyvalue.cSrc = src.charAt(iSrc);
                Keyvalue.cTemp=template.charAt(iTemp);

                //跳过回车符，制表符，换行符，空格等
            	if(Keyvalue.cSrc=='\n'||Keyvalue.cSrc=='\t'||Keyvalue.cSrc==' '||Keyvalue.cSrc=='\r'){
            		iSrc++;
            		continue;
            	}
            	if(Keyvalue.cTemp=='\n'||Keyvalue.cTemp=='\t'||Keyvalue.cTemp==' '||Keyvalue.cTemp=='\r'){
            		iTemp++;
            		continue;
            	}
            	//模板为*表示可以匹配任何字符，同步往前
            	if(Keyvalue.cTemp=='*'){
                    iSrc++;
            		iTemp++;
            		continue;
            	}
                //如果相同,则同步往前
            	if(Keyvalue.cSrc==Keyvalue.cTemp){
                    switch (Keyvalue.cSrc) {
                    case '>':
                        Keyvalue.iValueStart=iSrc;
                        break;  
                    case '<':
                        Keyvalue.iValueEnd=iSrc;
                    	break;
                    }
                    iSrc++;
            		iTemp++;
            	}else{
            		switch(Keyvalue.cTemp){
            		case '{':
                        Keyvalue.iKeyStart=iTemp;
            			break;
            		case '}':
                        Keyvalue.iKeyEnd=iTemp;
                        Keyvalue.key=template.substring(Keyvalue.iKeyStart+1,Keyvalue.iKeyEnd);
                        Keyvalue.iKeyEnd=-1;
                        Keyvalue.isGetKey=true;
            			iTemp++;
            			break;
            		}
            		//先走模板，找key
        			if(!Keyvalue.isGetKey){
        				iTemp++;
        			}
        			//再走待解析字符串，找value
    				if(!Keyvalue.isGetValue&&Keyvalue.cSrc!='<'){
    					iSrc++;
    				}
            	}

                //在找到key及value的开始字符，并且找到了value的结束字符，且结束字符位置要晚于开始字符位置
    			if(Keyvalue.iKeyStart!=-1&Keyvalue.iValueStart!=-1&&Keyvalue.iValueEnd!=-1&&Keyvalue.iValueEnd>Keyvalue.iValueStart){
                    Keyvalue.value=src.substring(Keyvalue.iValueStart+1,Keyvalue.iValueEnd);
                    Keyvalue.isGetValue=true;
                    Keyvalue.iValueStart=-1;
                    Keyvalue.iValueEnd=-1;
                    Keyvalue.iKeyStart=-1;
    			}
    			logger.debug(">iValueStart="+Keyvalue.iValueStart+";<iValueEnd="+Keyvalue.iValueEnd+";getkey="+Keyvalue.isGetKey+";getvalue="+Keyvalue.isGetValue+";key="+Keyvalue.key+";value="+Keyvalue.value
                        +";iSrc="+iSrc+";iTemp="+iTemp+";r="+Keyvalue.cSrc+";t="+Keyvalue.cTemp);
            	if(Keyvalue.isGetKey&&Keyvalue.isGetValue){
            		result.put(Keyvalue.key, Keyvalue.value);
                    Keyvalue.isGetKey=false;
                    Keyvalue.isGetValue=false;
            	}
            }
        }

		return result;
	}
	
}
