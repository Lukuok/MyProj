package com.ksw.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.Security;

public class TestCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url = "https://www.naver.com/";

		String responseMessage = "";

		InputStream is = null;

		InputStreamReader isr = null;
		BufferedReader br = null;

		StringBuffer sb = new StringBuffer();

		// 이 부분이 https부분입니다.***************************
		System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");

		com.sun.net.ssl.internal.ssl.Provider provider = new com.sun.net.ssl.internal.ssl.Provider();

		Security.addProvider(provider);
		// *****************************************************

		try {

			URL httpsUrl = new URL(url);

			URLConnection conn = httpsUrl.openConnection();

			conn.setUseCaches(false);

			conn.setConnectTimeout(40000);

			conn.setDoOutput(true);// post 방식 설정

			conn.connect();

			responseMessage = conn.getHeaderField(0);

			System.out.println(responseMessage);
			// HTTP/1.1 200 OK 형식의 http 헤더 결과 코드가 출력됩니다.

			is = conn.getInputStream();

			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);

			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			System.out.println(sb.toString());
			// html 부분 출력

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
