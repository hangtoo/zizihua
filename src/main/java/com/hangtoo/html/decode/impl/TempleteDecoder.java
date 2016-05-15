package com.hangtoo.html.decode.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	class Keyvalue{
        char csrc;
        char ctemp;
        int s=-1,e=-1,oldri=-1,oldtj=-1;
        int tags=-1,tage=-1;
        String key=null,value=null;
        boolean getkey=false,getvalue=false;
        
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
        	
            char cSrc;
            char cTemp;
            int s=-1,e=-1,oldISrc=-1,oldITemp=-1;
            int tags=-1,tage=-1;
            String key=null,value=null;
            boolean isGetKey=false,isGetValue=false;
            for (int iSrc=0,iTemp = 0; iSrc <= src.length() - 1&&iTemp<=template.length() - 1; ) {  
            	//logger.debug("ri="+ri+";tj="+tj+";");
            	
            	if(oldISrc==iSrc&&oldITemp==iTemp){
            		logger.debug(src.substring(0,iSrc));
            		logger.info("==============decoder error===============");
            		logger.debug(template.substring(0,iTemp));
            		break;
            	}else{
            		oldISrc=iSrc;
            		oldITemp=iTemp;
            	}
            	
            	cSrc = src.charAt(iSrc);
            	cTemp=template.charAt(iTemp);
            	
            	if(cSrc=='\n'||cSrc=='\t'||cSrc==' '||cSrc=='\r'){
            		iSrc++;
            		continue;
            	}
            	
            	if(cTemp=='\n'||cTemp=='\t'||cTemp==' '||cTemp=='\r'){
            		iTemp++;
            		continue;
            	}
            	
            	if(cTemp=='*'){
                    iSrc++;
            		iTemp++;
            		continue;
            	}
            	
            	if(cSrc==cTemp){//如果相同,则继续往前
            		
                    switch (cSrc) {
                    case '>':
                    	tags=iSrc;
                        break;  
                    case '<':
                    	tage=iSrc;
                    	break;
                    }
                    
                    iSrc++;
            		iTemp++;
                    
            	}else{
            		
            		switch(cTemp){
            		case '{':
            			s=iTemp;
            			break;
            		case '}':
            			e=iTemp;
            			key=template.substring(s+1,e);
            			e=-1;
            			isGetKey=true;
            			iTemp++;
            			break;
            		}
            		
        			if(!isGetKey){
        				iTemp++;
        			}
        			
    				if(!isGetValue&&cSrc!='<'){
    					iSrc++;
    				}
            	}
            	
            	
    			if(s!=-1&tags!=-1&&tage!=-1&&tage>tags){
    				value=src.substring(tags+1,tage);
    				isGetValue=true;
    				tags=-1;
    				tage=-1;
        			s=-1;
    			}
    			
    			logger.debug(">tags="+tags+";<tage="+tage+";getkey="+isGetKey+";getvalue="+isGetValue+";key="+key+";value="+value+";ri="+iSrc+";tj="+iTemp+";r="+cSrc+";t="+cTemp);
    			
            	if(isGetKey&&isGetValue){
            		result.put(key, value);
            		isGetKey=false;
            		isGetValue=false;
            	}
            	

            }
            
        }
		
		
		
		return result;
	}
	
}
