package com.hangtoo.common.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import com.hangtoo.common.IHttpGetPostData;
import com.hangtoo.util.DateUtils;

public class HttpGetPostDataTest {

	@Test
	public void testGetData() {
		
		IHttpGetPostData httpGetPostData=HttpClientGetPostData.getInstance();
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
		IHttpGetPostData httpGetPostData=HttpClientGetPostData.getInstance();
		try {
			String url="http://www.baidu.com";
			List<NameValuePair> formParams=new ArrayList<NameValuePair>();
			String ret=httpGetPostData.postData(url,formParams);
			
			
			url="http://www.sge.com.cn/sgeclient/sgeData/public/json/delaydata.json";
			formParams.add(new BasicNameValuePair("timestamp",""+DateUtils.addDay(DateUtils.now(),0,-1).getTime()));
			ret=httpGetPostData.postData(url,formParams);
			
			url="http://www.sge.com.cn/sgeclient/TraderMarket/getCentralizedData?date=2016-08-01";
			formParams.add(new BasicNameValuePair("timestamp",""+DateUtils.now().getTime()));
			//[{"TRADEDATE":"20160801","PRODCODE":"SHAU","PRODNAME":"上海金","AMPRICE":"288.65","PMPRICE":"287.95"}]

			System.out.println(ret);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
