package com.sunrise.base.signUtil;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

    

/**  
*  url配置请求校验工具类   
*/
public class SignUtil {
    
    private static String SECRET = "secret"; 
    
    private static String ENCODING = "UTF-8";
    
    /** 
     * 验证签名 
     *  
     * @param sign  加密签名(第三方私钥加密)
     * @param appid  分销商身份
     * @param token  登录验证信息(MD5加密)
     * @param timestamp  时间戳
     * @param publicKey  分销商公钥（每个分销商唯一，需到配置文件配置，生成方式为平台统一生成RSAUtils.genKeyPair()）
     * @param params     参数
     * @return boolean
     */  
    public static boolean checkSignature(String sign, String appid, String token, String timestamp, String publicKey, Map<String, String> params) throws Exception {
        
        // 将加密后的字符串与signature对比
    	String tmpStr = getSignature(appid, token, timestamp, params);
    	//第三方公钥解密
    	String newsign = new String(RSAUtils.decryptByPublicKey(BASE64.decode(URLDecoder.decode(sign, ENCODING).getBytes(ENCODING)), publicKey));
        return tmpStr != null ? tmpStr.equals(newsign) : false;  
    } 
    
    /**
     * 生成私钥加密字符串
     * @param appid  分销商身份
     * @param token  登录验证信息(MD5加密)
     * @param timestamp  时间戳
     *  @param privateKey  分销商私钥
     * @param params  参数
     * @return String 返回根据私钥加密后的字符串
     */
    public static String getPrivateSignature(String appid, String token, String timestamp, String privateKey, Map<String, String> params) throws Exception {
    	String sign = getSignature(appid, token, timestamp, params);
    	//私钥加密 
    	String key = new String(BASE64.encode(RSAUtils.encryptByPrivateKey(sign.getBytes(ENCODING), privateKey)));
    	return URLEncoder.encode(key, ENCODING);
    }
    
    /**
     * 生成加密参数字符串
     * @param appid  分销商身份
     * @param token  登录验证信息(MD5加密)
     * @param timestamp  时间戳
     * @param params     参数
     * @return String 返回加密后的参数字符串
     */
    private static String getSignature(String appid, String token, String timestamp, Map<String, String> params) throws Exception {
    	StringBuffer sb = new StringBuffer();
//    	sb.append(SECRET).append(appid).append(token).append(timestamp);
    	sb.append(SECRET);
    	if(params != null && !params.isEmpty()){
    		//对参数进行字典序排序
    		List<String> list = new ArrayList<String>();
    		Iterator it = params.keySet().iterator();
    		String key;
        	while(it.hasNext()){
        		key = (String) it.next();
        		list.add(key+params.get(key));
        	}
        	Collections.sort(list);        	
        	for(String s : list){
        		sb.append(s);
        	}
    	}
    	sb.append(SECRET);
    	return MD5.MD5Encode(URLEncoder.encode(sb.toString(), ENCODING));
    }
    
    /**
     * 分销商安全令牌生成
     * @param appid 分销商身份
     * @return String
     */
    public static String getToken(String appid){    	
    	return MD5.MD5Encode(appid+ DateUtil.getFullDateTime() + UUID.randomUUID());
    }
    
    /**
     * 普通用户安全令牌生成
     * @param appid 分销商身份
     * @return String
     */
    public static String getUserToken(String userId){
    	return MD5.MD5Encode("USER"+userId+ DateUtil.getFullDateTime() + UUID.randomUUID());
    }
  
    /**
     * 用户刷新令牌生成
     * 2014年12月15日 15:17:23 添加 xizf
     * TODO(这里用一句话描述这个方法的作用).
     * @param userId
     * @return
     */
    public static String getRefreshToken(String userId){
    	return MD5.MD5Encode("ReFresh"+userId+ DateUtil.getFullDateTime() + UUID.randomUUID());
    }
    
    
    /** 
     * 将字节数组转换为十六进制字符串 
     *  
     * @param byteArray 
     * @return 
     */  
    private static String byteToStr(byte[] byteArray) {  
        String strDigest = "";  
        for (int i = 0; i < byteArray.length; i++) {  
            strDigest += byteToHexStr(byteArray[i]);  
        }  
        return strDigest;  
    }  
  
    /** 
     * 将字节转换为十六进制字符串 
     *  
     * @param mByte 
     * @return 
     */  
    private static String byteToHexStr(byte mByte) {  
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
        char[] tempArr = new char[2];  
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];  
        tempArr[1] = Digit[mByte & 0X0F];    
        String s = new String(tempArr);  
        return s;  
    }  
    
    public static void main(String[] a)throws Exception {
    	
    	Map<String, Object> keyMap = initKey();
		String publicKey = getPublicKey(keyMap);
		System.out.println(publicKey);
		String privateKey = getPrivateKey(keyMap);
		System.out.println(privateKey);

//        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCec9xPxqhpj5xFNsSZO3yw2XSkFwcHVaTOIiXnl28jJvGm6V7Ns8kdzzKbLRZNC6FFwLUbHyfyHJAuU/bgL5yXlJZCnbarxSqSJozikLR3mH2t48sDFSLhuvOdIUfR+cXg0sa0HUjIvWO9rtwEXmOXrZmW2MrEjFlflmwpIPAeTQIDAQAB"; 
//        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAL2xnZWZh0T+F42p1hgE8NFzln0B+Qir3caZjxaMvYmPdkVwlQeNHu6DFe+3MufnPwd2rd7LIPsTZ69ylTWpBVFhF06Q0tEpir1vodDilMmsA39++XUOseZNpb76ThFy717RIZm9g/7uorDS2+2WFHdjR/PPTz1VQL3DWkVg7QBLAgMBAAECgYEAlojlLVrw5edeeaVVqSoCkbTQr9No9WCuVnR2AMcfgpK+oMx+98cdzNlqBxmy5CW29w1PXYaFXbpAhhLAJh1DgbgygxMSfCs4i/iwkn7jWBnEjyChS17LQqOJZdoGpjfv0h0+nrIwqt0pMD81tp9WGdx0BBKMCE1gnHtsPxwWW8ECQQD8fvL/T4744usVCvhfhYxt+H9fBBUG+bPkdGna5F9qT2mdeBJpyz8KT+9MTxY4xfU8Sn3ItD8RbVBePbiGIam/AkEAwFOMGD0hC2trQTUDhBgQpAKv7K9/dCzAGdM1/y3WvdCyyN0shB79pe18sJ3S9/ZfkLthmGx5Hn2SqqaFLJiUdQJBAJNOFMHyTjzRVciO/9vqc8TshpKONR4+qz6K0/7J4QiKj9k4ZbWmx35ip/7i3Nn+U1X7N2rSDUFDfvGcHHzUQs0CQGAS3C7XioRL2r4uH12DL/zeIseXO5HrMOM1sQmR+m3DrSvN4Ij3ejnDEsCfdl3Lwx1nnW8o8LAchoGcwXXvmmkCQFCcXBz9Q4zgncf0NqhqYEfc8Fyoa5Hg3OCQ6xm8v7nHoHm+R4q2IcWR87hzUbJgqE7m2+AACyYiw6eY7tXK8Q0="; 

//    	 String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKgIw7cSeHP+e6pdewxYqUrnXIE6adMNWLtjX/YcCcn6/G25o06XF9dh+OP8BYigteZKe04qIQn1Q8V58Y8t9G9f8Vh1+/XGActxBYsxkq2L4olV1pCCUAdumiZJ6kcVf1U6xq3SUzCw4SCn+ACR9sfd5VxlNulD1aB4QT+tbokhAgMBAAECgYBwbfzV32vs4JdJ9GZCaohLb+Y7KSRpbIK0TteESlpaZK7Fk/IqwXnYSNtJX/Ur56wNvEQolpOooAyKcv8nd+4WCPjjLtdltO+z0ble9An/4cv2SDv2zmZMcQxhb4bGBaqwc96A2c5pRZabwiwVeXmnXkzCVmE+e7sxBt5+eO5I4QJBAN5geXpzMkZDQo5THz/yVDVp1HKdQrnCVm/CkfPZYaaZORatCcDu1H/deuMSu7pDt8R47cxi/l9LNMmKK4LS9i0CQQDBcNqj/p6lpgigTJ/28fRFIiCZXuIjgR93r4cAp1KsQMNULVKc+SepDsFg8tC5xFC6KC5cFz+83fKQstIhH0tFAkEAs2AenSdO5l5a0XM7Qdm3E17Kf6laqXyZPI02LfyrSDdQVXo+4+HCh2GgPopd42G8Wj8xkixIA5Ymfmt1rZF+AQJBAKJT5eZAwTkYv5xlMMe1+toY7WpG6VYKATIaphuyTfomzsNQ1UbM/q8rprOIkAeT6nqNRXoaOG5xgiBTL2qELFECQDkYu337+uT+FKsAPetZCeiVQNbybgLftp+yZjnMWGEC/GhPqYjFbxE9r4dBcp84CSeir3Zv4ojB4wk0Y2vDMv4=";
//    	 String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCoCMO3Enhz/nuqXXsMWKlK51yBOmnTDVi7Y1/2HAnJ+vxtuaNOlxfXYfjj/AWIoLXmSntOKiEJ9UPFefGPLfRvX/FYdfv1xgHLcQWLMZKti+KJVdaQglAHbpomSepHFX9VOsat0lMwsOEgp/gAkfbH3eVcZTbpQ9WgeEE/rW6JIQIDAQAB";
    	 /*String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIgie1QcsHg/U4KHA+qPHu0o3WVa"+
"B4zcpRvifJ0dbqNC8Y+EOx0CL43zj+FRwAqH3b3T/hrrKCDcu7x/4FD51Wi61OmiY95FzpOa8wDZ"+
"4YpbuS4vQWXlKrfEIi7pyBvf/sAHV6zgR4zOQ+63S5X8EnJRhV88/8pfsMTEv4dhjol7AgMBAAEC"+
"gYAx6EJCUco4Ky8YWRsMcK+rziFsOJcwAuvczylUCCg5Xh9kt9tcBSMd7T7AIdI/ihO2tfMgNzmc"+
"4o9IybCYvx6HG5OeW0616zjVC5/w/24ixmBGrp8oU4Jp/LZVylrKz0MZ2kWpVPMMbVx71WDWm33r"+
"eIEWE0x3OYQoMQ5Fwr818QJBAO4eg+9VLlEry1Z+ZVEtST2lUICiak8p6qLsW0o0FJR6nEgWWbii"+
"siOtVLQz4pdiVrTFswTi4pOlsOxkp7NG/68CQQCSW3eXFkK9xnlfO2IGOw+Q0nQaAUxSyGCKwai9"+
"YpOJgjW34xLl4GFdM3mUUJP1CbUKo8V9MelqtGP65NlRyFn1AkEA4i/waW6Dga1VCIMl4LdgxAL9"+
"dkNRMoOBk2Nq0BCqn+PcAgnhDibVilhChq5mhiBudmb5RyHx0ghpCBQQzJYblwJAJS9eHCIIxQTH"+
"U53X0vNwigAehXjs9l94v+hiQfPovlg548WqyIMQ2cg5TKiIXwqfV9uyUpO61WSNvnIkkPXRfQJBAKoqh18gzBC5lJOsqFzZlYj/IqBo/NikX13ZSeRDbc2YcVqvt5VYZi0/uthv5OfNAXpeJnVn4/Kp"+
"y5e0CukhY3o=";

    	 String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCIIntUHLB4P1OChwPqjx7tKN1lWgeM3KUb4nyd"+
"HW6jQvGPhDsdAi+N84/hUcAKh9290/4a6ygg3Lu8f+BQ+dVoutTpomPeRc6TmvMA2eGKW7kuL0Fl"+
"5Sq3xCIu6cgb3/7AB1es4EeMzkPut0uV/BJyUYVfPP/KX7DExL+HYY6JewIDAQAB";*/

    	
        //appKey=30000002&passLength=null&passMode=0&phone_no=13985555555&smsPwd=null&timeStamp=20141125143116&userName=fenxiao
        Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("appKey", "11000021");
		parameters.put("timeStamp", "20141208151536");
		parameters.put("userName", "sunrise");
		parameters.put("phone_no", "13981199000");
		parameters.put("passMode", "0");
		parameters.put("passLength", "6");
//		parameters.put("sign", "FMM2FmMgMnsVnsPNbq1RkZT%2BIVAUkRLKEop%2FJEcba61uQ4vcdv2VBxnLZw8kfrAkU970SupqRF1HZfLXavFH9KvvRjIXK4jLYG2ZtnDdMD36CuPWJx7p8DOazXhIpC2%2F9aTygsCUyAaYHcls5N34dkuxXuWN1%2BO6nbfRAgO%2F3Vk%3D");
//		parameters.put("sign", "ELgYQCLUtRJOtVMQaBcLSbg%2FFRxqlFXYsWKP4ewK4IEM2oI%2FyowkYX82Se7gJ08nDmLM89YsIjFFyw9iaihtlNy9V8Bo0brpDbqnwldba0wpxzQFC56aH3FrBQRyiL5pRK5FQahimhoa%2FOas997QH4u74adxr7h9TI9I5jLknQg%3D");
		
//		appKey=11000021&passLength=6&passMode=0&phone_no=13981199000&timeStamp=20141208151536&userName=sunrise
//				&sign=ELgYQCLUtRJOtVMQaBcLSbg%2FFRxqlFXYsWKP4ewK4IEM2oI%2FyowkYX82Se7gJ08nDmLM89YsIjFFyw9iaihtlNy9V8Bo0brpDbqnwldba0wpxzQFC56aH3FrBQRyiL5pRK5FQahimhoa%2FOas997QH4u74adxr7h9TI9I5jLknQg%3D
		
//		String s1 = mapToSign(privateKey,parameters);
		String s1=	mapToSignForFX(privateKey, parameters);
		System.out.println("加密结果:"+s1);
		String sign=s1.split("&")[s1.split("&").length-1];
		System.out.println(sign);
		//==加密结束==
		//==解密：====
//		String sign="ELgYQCLUtRJOtVMQaBcLSbg%2FFRxqlFXYsWKP4ewK4IEM2oI%2FyowkYX82Se7gJ08nDmLM89YsIjFFyw9iaihtlNy9V8Bo0brpDbqnwldba0wpxzQFC56aH3FrBQRyiL5pRK5FQahimhoa%2FOas997QH4u74adxr7h9TI9I5jLknQg%3D";
//		String sign="FMM2FmMgMnsVnsPNbq1RkZT%2BIVAUkRLKEop%2FJEcba61uQ4vcdv2VBxnLZw8kfrAkU970SupqRF1HZfLXavFH9KvvRjIXK4jLYG2ZtnDdMD36CuPWJx7p8DOazXhIpC2%2F9aTygsCUyAaYHcls5N34dkuxXuWN1%2BO6nbfRAgO%2F3Vk%3D";
//		String sign="FMM2FmMgMnsVnsPNbq1RkZT%2BIVAUkRLKEop%2FJEcba61uQ4vcdv2VBxnLZw8kfrAkU970SupqRF1HZfLXavFH9KvvRjIXK4jLYG2ZtnDdMD36CuPWJx7p8DOazXhIpC2%2F9aTygsCUyAaYHcls5N34dkuxXuWN1%2BO6nbfRAgO%2F3Vk%3D&timeStamp=20141208151536&userName=sunrise&sign=UQrftc%2BWt8yA%2BJlMVDfoG3z5BgcgLC9ipQL7h%2BCdv34HHSErgT8pH%2FYG9LqVOVBLIB6sRcnxwXo1iKFCyQHJYdhLgsN6dAVUjuR7bFbx%2FbYhqULdqWzYJnDFlYovLkffmKBTmJszIuhPZtJ%2F1jJUiv09FTU7qhQfhbNSVcgdQZ4%3D";
//		checkSignatureNoToken(sign, "", "", publicKey, parameters);
		StringBuffer sb = new StringBuffer();
    	sb.append(SECRET);
    	if(parameters != null && !parameters.isEmpty()){
    		//对参数进行字典序排序
    		List<String> list = new ArrayList<String>();
    		Iterator<String> it = parameters.keySet().iterator();
    		String key;
        	while(it.hasNext()){
        		key = (String) it.next();
        		if("sign".equalsIgnoreCase(key) || null==parameters.get(key)||"".equals(parameters.get(key)))
        		{
        			continue;
        		}
        		list.add(key+parameters.get(key));
        	}
        	Collections.sort(list);        	
        	for(String s : list){
        		sb.append(s);
        	}
    	}
    	sb.append(SECRET);
    	System.out.println("排序以后为:"+sb.toString());
    	System.out.println("ENCODE以后为:"+URLEncoder.encode(sb.toString(), ENCODING));
        String md5= MD5.MD5Encode(URLEncoder.encode(sb.toString(), ENCODING));
        System.out.println(md5);
        //解密的时候需要将sign  decode
        sign = URLDecoder.decode(sign, ENCODING);
      boolean re=  RSAUtils.verify(md5.getBytes(), publicKey, sign);
    	
      System.out.println(re);
    }

    /** 
     * 验证签名 (无token)
     *  
     * @param sign  加密签名(第三方私钥加密)
     * @param appid  分销商身份
     * @param timestamp  时间戳
     * @param publicKey  分销商公钥（每个分销商唯一，需到配置文件配置，生成方式为平台统一生成RSAUtils.genKeyPair()）
     * @param params     参数
     * @return boolean
     */  
	public static boolean checkSignatureNoToken(String sign, String appid,
			String timestamp, String publicKey, Map<String, String[]> params) throws Exception {
		 // 将加密后的字符串与signature对比
    	String tmpStr = getSignatureNoToken(appid, timestamp, params);
    	System.out.println("MD5加密以后为:"+tmpStr);
    	//第三方公钥解密
    	System.out.println("比对sign:"+sign);
    	
    	return RSAUtils.verify(tmpStr.getBytes(), publicKey, sign);
    	
//    	byte[] data = sign.getBytes(ENCODING);
////    	byte[] data = URLDecoder.decode(sign.replaceAll(",", "+"),"utf-8").getBytes(ENCODING);
//    	
//    	byte[] data2  = BASE64.decode(data);
//    	String newsign = new String(RSAUtils.decryptByPublicKey(data2, publicKey));
//    	System.out.println("SIGN解密以后为:"+newsign);
//        return tmpStr != null ? tmpStr.equals(newsign) : false;  
	}
	
	 /**
     * 生成加密参数字符串, 无TOKEN 
     * @param appid  分销商身份
     * @param timestamp  时间戳
     * @param params     参数
     * @return String 返回加密后的参数字符串
     */
    private static String getSignatureNoToken(String appid, String timestamp, Map<String, String[]> params) throws Exception {
    	StringBuffer sb = new StringBuffer();
//    	sb.append(SECRET).append(appid).append(timestamp);
    	sb.append(SECRET);
    	if(params != null && !params.isEmpty()){
    		//对参数进行字典序排序
    		List<String> list = new ArrayList<String>();
    		Iterator<String> it = params.keySet().iterator();
    		String key;
        	while(it.hasNext()){
        		key = (String) it.next();
        		if("sign".equalsIgnoreCase(key) || null==params.get(key)[0]||"".equals(params.get(key)[0]))
        		{
        			continue;
        		}
        		list.add(key+params.get(key)[0]);
        	}
        	Collections.sort(list);        	
        	for(String s : list){
        		sb.append(s);
        	}
    	}
    	sb.append(SECRET);
    	System.out.println("排序以后为:"+sb.toString());
    	System.out.println("ENCODE以后为:"+URLEncoder.encode(sb.toString(), ENCODING));
    	return MD5.MD5Encode(URLEncoder.encode(sb.toString(), ENCODING));
    }
    
    /**
     * 根据MAP组装相应数据，并自动排序，encode，MD5,RSA加密。最终反馈得到的最后参数
     * TODO(这里用一句话描述这个方法的作用).
     * @param privateKey
     * @param parameters
     * @return
     */
    public static String mapToSign(String privateKey,Map<String,String> parameters)
    {
    	
    	/**
    	 * 操作顺序
    	 * 1、map排序
    	 * 2、组装参数
    	 * 3、encodeUTF-8
    	 * 4、MD5 加密
    	 * 5、rsa加密
    	 * 6、encode utf-8
    	 * 7、组装返回参数
    	 */
    	String returnParameters = null;
    	StringBuffer sb = null;
    	String parameter = null;
    	try
    	{
    		sb = new StringBuffer();
    		if(parameters != null && !parameters.isEmpty()){
        		//对参数进行字典序排序
        		List<String> list = new ArrayList<String>();
        		Iterator<String> it = parameters.keySet().iterator();
        		String key;
            	while(it.hasNext()){
            		key = (String) it.next();
            		list.add(key+"="+parameters.get(key)+"&");
            	}
            	Collections.sort(list);        	
            	for(String s : list){
            		sb.append(s);
            	}
        	}
    		sb.delete(sb.length()-1, sb.length());
    		System.out.println("排序以后为:"+sb.toString());
    		parameter = URLEncoder.encode(sb.toString(),ENCODING);
    		System.out.println("URL ENCODE以后为:"+parameter);
    		parameter = MD5.MD5Encode(parameter);
    		System.out.println("MD5以后为:"+parameter);
    		
//    		parameter = new String(BASE64.encode(RSAUtils.encryptByPrivateKey(parameter.getBytes(ENCODING),privateKey)));
    		
    		parameter = RSAUtils.sign(parameter.getBytes(), privateKey);
    		
    		System.out.println("RSA以后为:"+parameter);
    		parameter = URLEncoder.encode(parameter, ENCODING);
    		System.out.println("URL ENCODE以后为:"+parameter);
    		returnParameters = sb.toString()+"&sign="+parameter;
    		System.out.println("返回参数为:"+sb.toString()+"&sign="+parameter);
    		
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return returnParameters;
    }
    /**
     * 此方法供    登陆成功短信提示发送接口 单独使用
     * 根据MAP组装相应数据，并自动排序，encode，MD5,RSA加密。最终反馈得到的最后参数
     * TODO(这里用一句话描述这个方法的作用).
     * @param privateKey
     * @param parameters
     * @param param
     * @return
     */
    public static String mapToSign(String privateKey,Map<String,String> parameters,String param)
    {
    	
    	/**
    	 * 操作顺序
    	 * 1、map排序
    	 * 2、组装参数
    	 * 3、encodeUTF-8
    	 * 4、MD5 加密
    	 * 5、rsa加密
    	 * 6、encode utf-8
    	 * 7、组装返回参数
    	 */
    	String returnParameters = null;
    	StringBuffer sb = null;
    	String parameter = null;
    	try
    	{
    		sb = new StringBuffer();
    		if(parameters != null && !parameters.isEmpty()){
        		//对参数进行字典序排序
        		List<String> list = new ArrayList<String>();
        		Iterator<String> it = parameters.keySet().iterator();
        		String key;
            	while(it.hasNext()){
            		key = (String) it.next();
            		list.add(key+"="+parameters.get(key)+"&");
            	}
            	Collections.sort(list);        	
            	for(String s : list){
            		sb.append(s);
            	}
        	}
    		sb.delete(sb.length()-1, sb.length());
    		System.out.println("排序以后为:"+sb.toString());
    		parameter = URLEncoder.encode(sb.toString(),ENCODING);
    		System.out.println("URL ENCODE以后为:"+parameter);
    		parameter = MD5.MD5Encode(parameter);
    		System.out.println("MD5以后为:"+parameter);
    		
//    		parameter = new String(BASE64.encode(RSAUtils.encryptByPrivateKey(parameter.getBytes(ENCODING),privateKey)));
    		
    		parameter = RSAUtils.sign(parameter.getBytes(), privateKey);
    		
    		System.out.println("RSA以后为:"+parameter);
    		parameter = URLEncoder.encode(parameter, ENCODING);
    		System.out.println("URL ENCODE以后为:"+parameter);
    		returnParameters = param+"&sign="+parameter;
    		System.out.println("返回参数为:"+param+"&sign="+parameter);
    		
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return returnParameters;
    }
    
    /**
     * 根据MAP组装相应数据，并自动排序，encode，MD5,RSA加密。最终反馈得到的最后参数
     * TODO(这里用一句话描述这个方法的作用).
     * @param privateKey
     * @param parameters
     * @return
     */
    public static String mapToSignForFX(String privateKey,Map<String,String> parameters)
    {
    	
    	/**
    	 * 
    	 * 操作顺序
    	 * 1、map排序
    	 * 2、组装参数
    	 * 3、encodeUTF-8
    	 * 4、MD5 加密
    	 * 5、rsa加密
    	 * 6、encode utf-8
    	 * 7、组装返回参数
    	 * 
    	 * 其实sign的算法是这样的：
    	 * 不是对参数=vaule&参数=value这样进行加密，而是不要=和&符号，比如：不是：app_id=123&app_key=22 ==>(A)，而是：app_id123app_key22==>(B)
    	 * 1、将map参数都拼接成B格式的list===》(C)
    	 * 2、将C进行字典排序：Collections.sort(C);==》此时的c就是字典排序的list了
    	 * 3、将C的值顺序拼接成字符串==》D
    	 * 4 、将D进行encodeUTF-8 编码：parameter = URLEncoder.encode(sb.toString(),ENCODING);
    	 * 5、MD5加密：parameter = MD5.MD5Encode(parameter);
    	 * 6、rsa私钥加密：parameter = RSAUtils.sign(parameter.getBytes(), privateKey);
    	 * 7、encodeUTF-8 编码 parameter = URLEncoder.encode(parameter, ENCODING);
    	 * 经过这个7个步骤就得到了sign
    	 */
    	String returnParameters = null;
    	StringBuffer sb = null;
    	String parameter = null;
    	StringBuffer visitData = null;
    	try
    	{
    		sb = new StringBuffer();
    		visitData = new StringBuffer();
    		sb.append(SECRET);
    		if(parameters != null && !parameters.isEmpty()){
        		//对参数进行字典序排序
    			//list装map的key和对应的value的值，不需要=和&符号，最后用于生成sign的
 //比如：parameters=map={timeStamp=20141208151536, passLength=6, passMode=0, phone_no=13981199000, userName=sunrise, appKey=11000021}
        		//while 后得到：list=[timeStamp20141208151536, passLength6, passMode0, phone_no13981199000, userNamesunrise, appKey11000021]
    			//visitList=[timeStamp=20141208151536&, passLength=6&, passMode=0&, phone_no=13981199000&, userName=sunrise&, appKey=11000021&]
    			List<String> list = new ArrayList<String>();
        		List<String> visitList = new ArrayList<String>();
        		Iterator<String> it = parameters.keySet().iterator();
        		String key;
            	while(it.hasNext()){
            		key = (String) it.next();
            		list.add(key+parameters.get(key));
            		visitList.add(key+"="+parameters.get(key)+"&");
            	}
            	//对list进行字段排序，
            	//得到：list=[appKey11000021, passLength6, passMode0, phone_no13981199000, timeStamp20141208151536, userNamesunrise]
            	Collections.sort(list);        	
            	//得到sb
            	//sb=secretappKey11000021passLength6passMode0phone_no13981199000timeStamp20141208151536userNamesunrise
            	for(String s : list){
            		sb.append(s);
            	}
            	
            	//visitList字典排序：
            	//得到：visitList=[appKey=11000021&, passLength=6&, passMode=0&, phone_no=13981199000&, timeStamp=20141208151536&, userName=sunrise&]
            	Collections.sort(visitList); 
            	//visitData=appKey=11000021&passLength=6&passMode=0&phone_no=13981199000&timeStamp=20141208151536&userName=sunrise
            	for(String s : visitList){
            		visitData.append(s);
            	}
        	}
    		sb.append(SECRET);
    		//删除visitData的最后一个&
    		visitData.delete(visitData.length()-1, visitData.length());
    		System.out.println("排序以后为:"+sb.toString());
    		parameter = URLEncoder.encode(sb.toString(),ENCODING);
    		System.out.println("URL ENCODE以后为:"+parameter);
    		parameter = MD5.MD5Encode(parameter);
    		System.out.println("MD5以后为:"+parameter);
    		
//    		parameter = new String(BASE64.encode(RSAUtils.encryptByPrivateKey(parameter.getBytes(ENCODING),privateKey)));
    		
    		parameter = RSAUtils.sign(parameter.getBytes(), privateKey);
    		
    		System.out.println("RSA以后为:"+parameter);
    		parameter = URLEncoder.encode(parameter, ENCODING);
    		System.out.println("URL ENCODE以后为:"+parameter);
    		returnParameters = visitData.toString()+"&sign="+parameter;
    		System.out.println("返回参数为:"+returnParameters);
    		
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return returnParameters;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

	public static final String KEY_ALGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
	private static final String PUBLIC_KEY = "RSAPublicKey";
	private static final String PRIVATE_KEY = "RSAPrivateKey";

	public static String getPublicKey(Map<String, Object> keyMap)
			throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		byte[] publicKey = key.getEncoded();
		return encryptBASE64(key.getEncoded());
	}

	public static String getPrivateKey(Map<String, Object> keyMap)
			throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		byte[] privateKey = key.getEncoded();
		return encryptBASE64(key.getEncoded());
	}

	public static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

	public static String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}

	public static Map<String, Object> initKey() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator
				.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}

}

