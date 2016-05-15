package com.hangtoo.html.decode.impl;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

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
