package com.hangtoo.common;

import java.util.List;

import org.apache.http.NameValuePair;

public interface IHttpGetPostData {
	public String getData(String url) throws Exception ;
	public String postData(String url,List<NameValuePair> formParams) throws Exception ;
}
