package com.hangtoo.base.util;

import java.util.ResourceBundle;

/**
 * 项目参数工具类
 * 
 * @author zhangdaihao
 * 
 */
public class ResourceUtil {

	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("config");

	/**
	 * 获得sessionInfo名字
	 * 
	 * @return
	 */
	public static final String getSessionInfoName() {
		return bundle.getString("sessionInfoName");
	}

	/**
	 * 获得上传表单域的名称
	 * 
	 * @return
	 */
	public static final String getUploadFieldName() {
		return bundle.getString("uploadFieldName");
	}

	/**
	 * 获得上传文件的最大大小限制
	 * 
	 * @return
	 */
	public static final long getUploadFileMaxSize() {
		return Long.valueOf(bundle.getString("uploadFileMaxSize"));
	}

	/**
	 * 获得允许上传文件的扩展名
	 * 
	 * @return
	 */
	public static final String getUploadFileExts() {
		return bundle.getString("uploadFileExts");
	}

	/**
	 * 获得上传文件要放到那个目录
	 * 
	 * @return
	 */
	public static final String getUploadDirectory() {
		return bundle.getString("uploadDirectory");
	}
	
	
	/**
	 * 获取数据库类型
	 * 
	 * @return
	 */
	public static final String getJdbcUrl() {
		return bundle.getString("jdbc_url").toLowerCase();
	}
	
	public static final String getRuleFileName(){
		return bundle.getString("ruleFileName");
	}
	
	public static final String getRuleDestDir(){
		return bundle.getString("ruleDestDir");
	}

	public static final String getApiFileName(int apitype){
		return bundle.getString("apiFileName").split(",")[apitype];
	}
	
	public static final String[] getApiFileNames(){
		return bundle.getString("apiFileName").split(",");
	}
	
	@Deprecated
	public static final String getMdnFileName(){
		return getApiFileName(0);
	}
	@Deprecated
	public static final String getPosFileName(){
		return getApiFileName(1);
	}
	@Deprecated
	public static final String getCfnFileName(){
		return getApiFileName(2);
	}
	@Deprecated
	public static final String getStatusFileName(){
		return getApiFileName(3);
	}
	
	public static final String getSuffix(){
		return bundle.getString("suffix");
	}
	public static final String getDestDir(){
		return bundle.getString("DestDir");
	}
	public static final String getNotifyurl(){
		return bundle.getString("notifyurl");
	}

}
