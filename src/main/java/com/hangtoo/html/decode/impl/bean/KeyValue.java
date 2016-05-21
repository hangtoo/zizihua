package com.hangtoo.html.decode.impl.bean;

import org.json.JSONArray;

public class KeyValue{
    int oldISrc=-1,oldITemp=-1;//上次解析的位置，包括待解析串及模板串
	char cSrc,cTemp;//正在解析的字符及模板字符

    int iKeyStart=-1,iKeyEnd=-1;//key的开始位置，key结束位置,比如{date1}
    int iValueStart=-1,iValueEnd=-1;//标签的开始位置，标签的结束位置，比如>2016-01-02<
    String key=null,value=null;//获得的key，获得的value
    boolean isGetKey=false,isGetValue=false;//是否已经获得当前key和value
    public int getOldISrc() {
		return oldISrc;
	}

	public void setOldISrc(int oldISrc) {
		this.oldISrc = oldISrc;
	}

	public int getOldITemp() {
		return oldITemp;
	}

	public void setOldITemp(int oldITemp) {
		this.oldITemp = oldITemp;
	}

	public char getcSrc() {
		return cSrc;
	}

	public void setcSrc(char cSrc) {
		this.cSrc = cSrc;
	}

	public char getcTemp() {
		return cTemp;
	}

	public void setcTemp(char cTemp) {
		this.cTemp = cTemp;
	}

	public int getiKeyStart() {
		return iKeyStart;
	}

	public void setiKeyStart(int iKeyStart) {
		this.iKeyStart = iKeyStart;
	}

	public int getiKeyEnd() {
		return iKeyEnd;
	}

	public void setiKeyEnd(int iKeyEnd) {
		this.iKeyEnd = iKeyEnd;
	}

	public int getiValueStart() {
		return iValueStart;
	}

	public void setiValueStart(int iValueStart) {
		this.iValueStart = iValueStart;
	}

	public int getiValueEnd() {
		return iValueEnd;
	}

	public void setiValueEnd(int iValueEnd) {
		this.iValueEnd = iValueEnd;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isGetKey() {
		return isGetKey;
	}

	public void setGetKey(boolean isGetKey) {
		this.isGetKey = isGetKey;
	}

	public boolean isGetValue() {
		return isGetValue;
	}

	public void setGetValue(boolean isGetValue) {
		this.isGetValue = isGetValue;
	}

    @Override
    public String toString(){
    	JSONArray json = new JSONArray(this);
    	return json.toString();
    }
}
