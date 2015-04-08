package com.sunrise.base.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.UUID;

/**
 * @author USER
 * 
 */
public class HttpTool {

	public static final String CHARSET_UTF8 = "utf-8";

	/**
	 * Get方式提交数据
	 * 
	 * @param httpUrl
	 *            http请求地址
	 * @throws Exception
	 */
	public static String doGetUrl(String httpUrl, String charSet)
			throws Exception {
		// 创建一个链接
		URL url = new URL(httpUrl);
		// 打开该链接
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 设置该请求方式为GET方式
		conn.setRequestMethod("GET");
		// 设置服务端有数据返回
		conn.setDoInput(true);

		// 创建数据流缓冲
		BufferedReader br = new BufferedReader(new InputStreamReader(
				conn.getInputStream(), charSet));
		String line;
		// 缓存服务端返回的数据
		StringBuffer buffer = new StringBuffer();
		while ((line = br.readLine()) != null) {
			buffer.append(line + "\n");
		}
		// 服务端返回的内容
		String outContent = buffer.toString();
		if (outContent != null) {
			outContent = outContent.trim();
		}
		// 将服务端返回的数据打印到Java的控制台
		// System.out.println("服务端返回的内容如下:"+outContent);
		// 关闭数据流
		br.close();
		return outContent;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String doPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println("post的result++++" + result);
		return result;
	}


	public static void main(String[] args) {
		StringBuilder httpUrl = new StringBuilder();
		httpUrl.append("http://api.t.163.com/oauth/request_token?oauth_consumer_key=2MuMPNhJzTlwF8Z8&oauth_signature=oOwSbUvcEOd1vrRTsN0zGcJly3egD6A1&oauth_timestamp=");
		Date date = new Date();
		httpUrl.append(date.getTime());
		httpUrl.append("&oauth_nonce=");
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		httpUrl.append(uuid);
		httpUrl.append("&oauth_signature_method=HMAC-SHA1");

		System.out.println(httpUrl.toString());

		/*
		 * try { String rtvBody=HttpTool.doGetUrl(httpUrl.toString(),
		 * CHARSET_UTF8); System.out.println(rtvBody); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
	}
}
