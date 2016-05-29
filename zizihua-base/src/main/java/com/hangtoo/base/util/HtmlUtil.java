package com.hangtoo.base.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import com.hangtoo.base.util.json.JSONUtil;

/**
 * <br>
 * <b>功能：</b>详细的功能描述<br>
 * <b>作者：</b>www.jeecg.org<br>
 * <b>日期：</b> Dec 14, 2013 <br>
 * <b>更新者：</b><br>
 * <b>日期：</b> <br>
 * <b>更新内容：</b><br>
 */
public class HtmlUtil {
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>输出json格式<br>
	 * <b>作者：</b>www.jeecg.org<br>
	 * <b>日期：</b> Dec 14, 2013 <br>
	 * @param response
	 * @param jsonStr
	 * @throws Exception
	 */
	public static void writerJson(HttpServletResponse response,String jsonStr) {
			writer(response,jsonStr);
	}
	
	public static void writerJson(HttpServletResponse response,Object object){
			try {
				response.setContentType("application/json");
				writer(response,JSONUtil.toJSONString(object));
			} catch (JSONException e) {
				e.printStackTrace();
			}
	}

	public static void writerJson(HttpServletResponse response,Object object,String callback){
		try {
			response.setContentType("application/json");
			String ret=JSONUtil.toJSONString(object);
			if(callback!=null){
				ret=callback+"("+ret+")";
			}
			writer(response,ret);
		} catch (JSONException e) {
			e.printStackTrace();
		}
}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>输出HTML代码<br>
	 * <b>作者：</b>www.jeecg.org<br>
	 * <b>日期：</b> Dec 14, 2013 <br>
	 * @param response
	 * @param htmlStr
	 * @throws Exception
	 */
	public static void writerHtml(HttpServletResponse response,String htmlStr) {
		writer(response,htmlStr);
	}
	public static void writerText(HttpServletResponse response,String path,String filename) {
		
		response.setContentType("application/txt;charset=utf-8");

		/* 如果想出来让IE提示你是打开还是保存的对话框，加上下面这句就可以了 */
		response.setHeader("Content-disposition", "attachment;filename="+filename);
		response.setHeader("Cache-Control",
				"must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		response.setDateHeader("Expires",
				(System.currentTimeMillis() + 1000));
		
		ServletOutputStream out=null;  
		FileInputStream in=null;
		try {
			out = response.getOutputStream();  
			in = new FileInputStream(path);
			
            int b = 0;  
            byte[] buffer = new byte[512];  
            b = in.read(buffer);  
            while (b != -1){  
            	out.write(buffer,0,b);  
                b = in.read(buffer);  
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(in!=null){
					in.close();	
				}
	            out.close();  
	            out.flush();  
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	private static void writer(HttpServletResponse response,String str){
		try {
			//设置页面不缓存
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out= null;
			out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
}
