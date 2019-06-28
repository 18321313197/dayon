package com.dayon.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpUtil {
	public static final String GET = "GET";
	public static final String POST = "POST";

	private HttpUtil() {
	}

	public String getString(String url, Map<String, String> paramMap, String method) {
		HttpURLConnection urlConnection = null;
		OutputStream os = null;
		InputStream is = null;

		try {

			URL httpUrl = new URL(url);// 创建对应的url对象
			urlConnection = (HttpURLConnection) httpUrl.openConnection();// HttpConnection对象，这一步只是创建了对象并没有连接远程服务器
			urlConnection.setDoInput(true);// 允许读
			urlConnection.setDoOutput(true);// 允许写
			urlConnection.setRequestMethod(method);// 请求方式
			urlConnection.setRequestProperty("Pragma:", "no-cache");
			urlConnection.setRequestProperty("Cache-Control", "no-cache");
			urlConnection.setRequestProperty("Content-Type", "text/xml");// 请求的消息格式
			urlConnection.setConnectTimeout(6000);// 很重要，设置超时时间
			os = urlConnection.getOutputStream();
			if (paramMap != null) {
				StringBuilder sb = new StringBuilder();
				for (String name : paramMap.keySet()) {
					if (sb.length() == 0) {
						sb.append("name=").append(paramMap.get(name));
					} else {
						sb.append("&").append("name=").append(paramMap.get(name));
					}
				}

				byte[] bytes = sb.toString().getBytes();// 这里的xml即为你想传递的值，因为项目中与三方交互要用GBK编码所以转换为GBK
				os.write(bytes);// 传递值
			}
			urlConnection.connect();// 真正的连接服务器
			is = urlConnection.getInputStream();
			StringBuilder sb = new StringBuilder();
			int c = 0;
			while ((c = is.read()) != -1) {
				sb.append((char) c);
			}
			return sb.toString();

		} catch (Exception e) {
			return null;
		} finally {

			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			urlConnection.disconnect();

		}
	}
}
