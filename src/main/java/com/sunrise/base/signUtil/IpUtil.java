package com.sunrise.base.signUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * ip地址处理的工具类
 * @author yudm
 *
 */
public class IpUtil {
	
	 /**获取请求客户端的Ip地址的方法
	 * @param request 
	 * @return 
	 */
	public static String getIpAddr(HttpServletRequest request) {  
         String ip = request.getHeader("X-Forwarded-For");  
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
             ip = request.getHeader("Proxy-Client-IP");  
         }  
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
             ip = request.getHeader("WL-Proxy-Client-IP");  
         }  
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
             ip = request.getHeader("HTTP_CLIENT_IP");  
         }  
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
             ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
         }  
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
             ip = request.getRemoteAddr();  
         }  
       //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
         if(ip!=null && ip.length()>15){ //"***.***.***.***".length() = 15
             if(ip.indexOf(",")>0){
            	 ip = ip.substring(0,ip.indexOf(","));
             }
         }
         
         return ip;  
     }

}
