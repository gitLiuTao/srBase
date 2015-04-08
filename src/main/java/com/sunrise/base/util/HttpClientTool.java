package com.sunrise.base.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;

public class HttpClientTool {
	/**
	 * 
	 * @param uri
	 *            完整请求地址
	 * @param charset
	 *            字符编码
	 * @return
	 * @author wenb
	 * @date 2014年12月11日 下午12:26:19
	 */
	public static String doGetUrl(String uri, String charset) {
		org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
		// 创建get请求
		HttpMethod getMethod = new GetMethod(uri);
		try {
			// 执行请求
			httpClient.executeMethod(getMethod);
			// 以流形式回去返回结果
			InputStream responseStream = getMethod.getResponseBodyAsStream();
			// 创建一个流
			BufferedReader br = new BufferedReader(new InputStreamReader(
					responseStream, charset));
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
			br.close();
			return outContent;
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			System.out.println("发起GET请求错误");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("发起GET请求IO错误");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 释放连接
			getMethod.releaseConnection();
		}
		return null;
	}

	/**
	 * 
	 * @param uri url
	 * @param param 参数
	 * @return
	 */
	public static String doPost(String uri,Map<String,String> param) {
		String result="";
		System.out.println("11111====="+param);
		try {
			HttpClient client = new HttpClient();
			// 使用POST方法
			PostMethod method = new PostMethod( uri);
			if(param != null && !param.isEmpty()){
				Iterator<String> it = param.keySet().iterator();
				String key;
	        	while(it.hasNext()){
	        		key = (String) it.next();
	        		method.addParameter(key, param.get(key));
	        		if(key.equals("sign"))
	        			System.out.println("postSign="+ param.get(key));
	        	}
				
			}
			System.out.println("请求methos==="+method);
			client.executeMethod(method);
			// 打印服务器返回的状态
			System.out.println(method.getStatusLine());
			// 打印返回的信息
			System.out.println();
			InputStream stream = method.getResponseBodyAsStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(stream,
					"UTF-8"));
			StringBuffer buf = new StringBuffer();
			String line;
			while (null != (line = br.readLine())) {
//				buf.append(line).append("\n");
				buf.append(line);
			}
			System.out.println(buf.toString());
			result=buf.toString();
			// 释放连接
			method.releaseConnection();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
