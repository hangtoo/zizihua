package com.hangtoo.common.impl;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hangtoo.common.IHttpGetPostData;

public class HttpClientGetPostData implements IHttpGetPostData {
	private static final Logger logger = LoggerFactory.getLogger(HttpClientGetPostData.class);

	private static IHttpGetPostData instance = null;
	
	private HttpClientGetPostData(){
	}
	public static IHttpGetPostData getInstance() {
		if(instance==null){
			instance=new HttpClientGetPostData();
		}
		return instance;
	}

	@Override
	public String getData(String url) throws IOException {
		CloseableHttpClient httpclient =HttpClients.createSystem();

		HttpGet httpget = new HttpGet(url);
		httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
		httpget.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 120*1000);//连接超时
		httpget.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 120*1000);//读取超时
		
		CloseableHttpResponse response = null;
		
		try {
			response = httpclient.execute(httpget);

			HttpEntity entity = response.getEntity();
			int statusCode=response.getStatusLine().getStatusCode();
			if(statusCode!=200){
				logger.warn("getData from '{}' return {}",url,statusCode);
				return null;
			}
			if (entity != null) {
	            // 打印响应内容长度    
	            logger.debug("Response content length:  {}",entity.getContentLength());
	            String ret=EntityUtils.toString(entity);
	            // 打印响应内容    
	            logger.debug("Response content:  {}",ret);
	            return ret;
	        }
		} catch (Exception e) {
			httpget.abort();
			logger.error(e.toString());
			e.printStackTrace();
		} finally{
			if(response!=null){
				response.close();
			}
			if(httpclient!=null){
				httpclient.close();	
			}
		}
		return null;
	}

	@Override
	public String postData(String url,List<NameValuePair> formParams) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            UrlEncodedFormEntity uefEntity=new UrlEncodedFormEntity(formParams, "UTF-8");  
            httppost.setEntity(uefEntity);
            response = httpclient.execute(httppost);
            
            HttpEntity entity = response.getEntity();
			int statusCode=response.getStatusLine().getStatusCode();
			if(statusCode!=200){
				logger.warn("post to '{}',return {},Data is {}",url,statusCode,formParams);
				return null;
			}
			if (entity != null) {
	            // 打印响应内容长度    
	            logger.debug("Response content length:  {}",entity.getContentLength());
	            String ret=EntityUtils.toString(entity);
	            // 打印响应内容    
	            logger.debug("Response content:  {}",ret);
	            return ret;
	        }
            
        } catch (Exception e) {
        	httppost.abort();
			logger.error(e.toString());
			e.printStackTrace();
		}finally {
			if(response!=null){
				response.close();
			}
			if(httpclient!=null){
				httpclient.close();	
			}
        }
		return url;
	}

}
