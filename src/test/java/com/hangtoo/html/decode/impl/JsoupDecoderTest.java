package com.hangtoo.html.decode.impl;

import org.junit.Test;

import java.io.IOException;

public class JsoupDecoderTest {

	@Test
	public void testGetData() {
		JsoupDecoder jsoupDecoder=new JsoupDecoder();
		try {
			String data=jsoupDecoder.getData("http://www.baidu.com");
			System.out.println(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
