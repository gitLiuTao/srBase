/**
 * 
 */
package com.sunrise.base.util;


import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;



/**
 * @author Ken.Guo
 * WebApp工具类
 */
public final class WebUtil {

	/**
	 * Constructor
	 */
	public WebUtil() {
		
	}

	/**
     * 查看form提交过来的参数是否正确
     * @param request HttpServletRequest
     */
    public static Map getReqParamMap(HttpServletRequest request){
      Map map=new HashMap();
      
      Enumeration enumT=request.getParameterNames();
      while(enumT.hasMoreElements()){
          String key=(String)enumT.nextElement();
          String value=request.getParameter(key);
          //System.out.println(key+"======>"+value);
          //map.put(arg0, arg1)
          map.put(key, value);
      }
      return map;
    }
    
   
    
    /**
     * 获取返回时的表单信息
     * @param request HttpServletRequest
     */
    public static String getBackParams(HttpServletRequest request){
      StringBuffer buffer=new StringBuffer();
      Enumeration enumT=request.getParameterNames();
      while(enumT.hasMoreElements()){
          String key=(String)enumT.nextElement();
          String value=request.getParameter(key);
          value=ListUtil.toNull(value);
          //if(!ListUtil.isNull(value)){
        	  buffer.append("<input type='hidden' name='"+key+"' value='"+value+"' > \n");
          //}
      }
      return buffer.toString();
    }
    

    /**
     * 查看form提交过来的参数是否正确
     * @param request HttpServletRequest
     * @param outParams List 排除在外的参数列表
     * 说明当前页面可能已经有了该参数
     */
    public static String getBackParams(HttpServletRequest request,List outParams){
      StringBuffer buffer=new StringBuffer();
      Enumeration enumT=request.getParameterNames();
      while(enumT.hasMoreElements()){
          String key=(String)enumT.nextElement();
          String value=request.getParameter(key);
          value=ListUtil.toNull(value);
          if(!ListUtil.isNull(outParams)){
        	  if(!outParams.contains(key)){
        		  buffer.append("<input type='hidden' name='"+key+"' value='"+value+"' > \n");
        	  }
          }
      }
      return buffer.toString();
    }
    
    
    
    
  
  
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

	}

}
