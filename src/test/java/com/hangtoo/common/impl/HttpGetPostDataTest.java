package com.hangtoo.common.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.junit.Test;

import com.hangtoo.common.IHttpGetPostData;

public class HttpGetPostDataTest {

	@Test
	public void testGetData() {
		
		IHttpGetPostData httpGetPostData=HttpGetPostData.getInstance();
		try {
			String url="http://www.baidu.com";
			String ret=httpGetPostData.getData(url);
			System.out.println(ret);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testPostData() {
		IHttpGetPostData httpGetPostData=HttpGetPostData.getInstance();
		try {
			String url="http://www.baidu.com";
			List<NameValuePair> formParams=new ArrayList<NameValuePair>();
			String ret=httpGetPostData.postData(url,formParams);
			System.out.println(ret);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
