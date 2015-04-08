package com.sunrise.system.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunrise.base.util.HttpClientTool;

/**
 * 不需要权限验证的接口 进入此controller
 * @author 向仕波
 *
 */
@Controller(value="IndexNewControllerNTK")
public class IndexNewControllerNTK {
	
	private String app_id = "10000135";
	
	private String http_url = "http://10.108.226.234:8080/openApi";
	
	private String app_key = "69d6550a6b2471b54f179d707796c569";
	
	private String pri_key = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIhRSEPMopBrBdB9iFjGKyL6B5MW5FlWSwvNa4aA3t29u8ru3VrrbsSg/4VTSFtJv4SzQC9wW1Ao/ZBKiyBLFbhZ4xj7azGlTOxDW7/SGcir+tjAZB+fzFPM0EPu3lYJ3M1hV3pMyYLfQTw5gIbeGgUDbDKLtAOMb0+xd329XRf5AgMBAAECgYBH6PkCKxUmsg5jKZWfnwpqbRNJ2K2lIwx7sWWjkp/+wXQrXU6QA9pvB69Dmed1oLXTPuwBGnhRFQ6N64TxCCrwS/AxGYxOhBtL+ZKhFglFc7bjbOZxKn8cZtf6fUY8PzNeW54/CgdrbBKz6Zz8IEZK58+n2trREmZbbfVW37NQAQJBAL/5Tm1qvSIZG7vygs8iM2d61qu5G6niVsjYdYItyGB6UVyeXeu5moK/QpmePgt9zzEA9F2VnmJXk4JYFgY3DIECQQC1yAwPTzZlznx2eqoYPvgwWzoubEvH0wMLGnsRAy4ZWULj5n9lA08GmIuOhTUlaHETHkPCdlXmxa0L1yC1zK95AkATw3vSgemkyAb163qSDohSP/A3z5/MdpPOq5I1a7c0T4Nu0JMEwJ/qk/wsSoFCt5oMBngh5lRe9XsnMSBbVXGBAkAAoeWI2Bm0WPeN4fddhjqO0IJvTukklNNZ4omzEXPDms/kwxSGYXCQ8U7q/AOnUamzC1PpBUhfOSjU9baja0bhAkAGKRz6EQrFBYqDYWJDZOiNyqNzA8RxJeWt1DWq3bMC9PZLBR3XmezhIg5wzyJoeUR9ptMDv+NBgUINEpbRYYxx";

	/**
	 * 空充订购接口
	 * 提供给页面测试，页面什么参数都不用传，都在contrller方法中拼接好了
	 * @date 2015年2月6日 下午3:48:10
	 * @auth wenb
	 */
	@RequestMapping("/kcDgNoAlias")
	@ResponseBody
	public  Map<String,String> kcDgNoAlias(){
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("app_id", app_id);
		paraMap.put("phone_no", "18382438242");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time= sdf.format(new Date());
		paraMap.put("timestamp",time);
		paraMap.put("version", "1.0");
		paraMap.put("method", "sAgtFeeAddNTK");
		setOrderId(paraMap);
//		  paraMap.put("end_seller", "sAgtFeeQry");
		paraMap.put("app_key", app_key);
		
		
//		paraMap.put("password", "9010100011");
		paraMap.put("password", "11111111");
		paraMap.put("feesum", "10");
		paraMap.put("mobile", "13488913949");
		setStatus(paraMap);
		setEndSeller(paraMap);
		//
		
		String pk= pri_key;
		String postParameter=com.sunrise.base.signUtil.SignUtil.mapToSignForFX(pk, paraMap);
		//获取sign值
		String[] str=	postParameter.split("=");
		String sign=str[str.length-1];
		paraMap.put("sign", sign);
		
		
//		String url="http://218.205.252.26:18051/openApi" + "/auth/contract/sAgtFeeAdd";
		String url=http_url + "/auth/contract/sAgtFeeAddNTK";//沙箱测试地址
		String result=HttpClientTool.doPost(url, paraMap);
		//参数解析
		Map<String,String> rest=new HashMap<String, String>();
		try {
			rest= com.sunrise.base.util.JsonHelper.toMap(result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rest);
		return rest;
	}
	
	
	/**
	 * 提供给页面测试，页面什么参数都不用传，都在contrller方法中拼接好了
	 * 空充查询
	 * @date 2015年2月6日 下午4:41:48
	 * @auth wenb
	 */
	@RequestMapping("/kcQurNoAlias")
	@ResponseBody
	public  Map<String,String> kcQurNoAlias(){
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("app_id", app_id);
		paraMap.put("phone_no", "15982413818");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time= sdf.format(new Date());
		paraMap.put("timestamp",time);
		paraMap.put("version", "1.0");
		paraMap.put("method", "sAgtFeeQry");
//		paraMap.put("end_seller", "sAgtFeeQry");
		paraMap.put("command", "ZNMXCX");
		paraMap.put("app_key", app_key);
		setEndSeller(paraMap);
		setStatus(paraMap);
		
//		String pk="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKubuG7w+AG0skVZ3JqQM8VfNlyL8q5UYUecdauAt6Wi6JM+czJxRYt8qZET6HWvTsDAyZF2dy6doCUfFwcdpwrfQkOdAfInJFOl6360Ejm8lQoWqqxqGZLJgWw2xm9z1vBWqe5hZ3BhcTSce/wOs8mmY67VTJhVyuvspjQ2YEK3AgMBAAECgYBm5XgxDUunujEqZDdidhSUxAJUe5WH8hBiX+4uZ3+2UXgi0c9A76pDePwzgTxhVDxkVzSFhALycJEVsQ6dtanyXViERyx92kUAYPJfUNKyWCeVeu5cLKt6lFELWgHDeYKCyYPHAfAuv165Q9phsCDPTFsBUXRnDN9u4HNuhbPIAQJBAO9ffzeU8SvUpLmf297v7t5D+UKpo+zxM8NJKYwzblEuoZv2y0Iaj5zFXkK9WjRXWSMIDGYVJor+sgus3V5trmkCQQC3hz0aDyj2tNNdyfdzPp+lBvhrvpbn2BA5bWwUZB2EmVJnRPU4ynnkRVGJ+0K4/WGgRJ0kPBRjxZUwYroemoQfAkEA6WxSfjz7wSYnS+wbWJbYdF4Mn3kVpWTAeVrK7TDqDrhOV+yd4ORNzy6X8LT9VbUn8wVSMenGIBymOsRjSkXioQJABVzbSTamMMdJ4dSz+VccHRteCO/xwuaKWtM6tEHUpvo/8SqPq9AmzafE4S5JXdRxlNhMazGiOuQcHuTToW9MawJAMrrNEw9pzuWjYeiho5yXk61Ok19dJX+zcDNn0J2LIWlLuv3lpSEtzZCpcK6YLS3s2cHdw7OmoBmSxokDA6S6lA==";
		String pk= pri_key;
		String postParameter=com.sunrise.base.signUtil.SignUtil.mapToSignForFX(pk, paraMap);
		//获取sign值
		String[] str=	postParameter.split("=");
		String sign=str[str.length-1];
		paraMap.put("sign", sign);
		
		
//		String url="http://218.205.252.26:18051/openApi" + "/auth/contract/sAgtFeeQry";
		String url = http_url + "/auth/contract/sAgtFeeQry";//沙箱测试地址
		String result=HttpClientTool.doPost(url, paraMap);
		//参数解析
		Map<String,String> rest=new HashMap<String, String>();
		try {
			rest= com.sunrise.base.util.JsonHelper.toMap(result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rest);
		return rest;
		
	}
	
	/**
	 * 提供给页面测试，页面什么参数都不用传，都在contrller方法中拼接好了
	 * 发送短信验证码
	 * @date 2015年2月6日 下午4:41:48
	 * @auth wenb
	 */
	@RequestMapping("/sQryRandPassNoAlias")
	@ResponseBody
	public  Map<String,String> sQryRandPassNoAlias(){
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("app_id", app_id);
		paraMap.put("phone_no", "15982413818");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time= sdf.format(new Date());
		paraMap.put("timestamp",time);
		paraMap.put("version", "1.0");
		paraMap.put("method", "sQryRandPassNTK");
		paraMap.put("app_key", app_key);
		setEndSeller(paraMap);
		setStatus(paraMap);
		
		String pk= pri_key;
		String postParameter=com.sunrise.base.signUtil.SignUtil.mapToSignForFX(pk, paraMap);
		//获取sign值
		String[] str=	postParameter.split("=");
		String sign=str[str.length-1];
		paraMap.put("sign", sign);
		
		
		String url= http_url + "/auth/contract/sQryRandPassNTK";//沙箱测试地址
		String result=HttpClientTool.doPost(url, paraMap);
		//参数解析
		Map<String,String> rest=new HashMap<String, String>();
		try {
			rest= com.sunrise.base.util.JsonHelper.toMap(result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rest);
		return rest;
		
	}
	
	/**
	 * 提供给页面测试，页面什么参数都不用传，都在contrller方法中拼接好了
	 * 流量订购沙箱测试
	 * @date 2015年2月6日 下午4:41:48
	 * @auth wenb
	 */
	@RequestMapping("/sShortAddModeNoAlias")
	@ResponseBody
	public  Map<String,String> sShortAddModeNoAlias(){
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("app_id", app_id);
		paraMap.put("phone_no", "15982413818");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time= sdf.format(new Date());
		paraMap.put("timestamp",time);
		paraMap.put("version", "1.0");
		
		paraMap.put("prod_prcid", "300001");
		paraMap.put("sms_pwd", "111111");
		paraMap.put("transNo", "1000013520150317112230000003");
		paraMap.put("method", "sShortAddModeNTK");
		paraMap.put("app_key", app_key);
		setEndSeller(paraMap);
		setStatus(paraMap);
		
		String pk= pri_key;
		String postParameter=com.sunrise.base.signUtil.SignUtil.mapToSignForFX(pk, paraMap);
		//获取sign值
		String[] str=	postParameter.split("=");
		String sign=str[str.length-1];
		paraMap.put("sign", sign);
		
		
		String url = http_url+"/auth/contract/sShortAddModeNTK";//沙箱测试地址
		String result=HttpClientTool.doPost(url, paraMap);
		//参数解析
		Map<String,String> rest=new HashMap<String, String>();
		try {
			rest= com.sunrise.base.util.JsonHelper.toMap(result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rest);
		return rest;
	}
	
//	合约机销售  sellTreatyPhone
	@RequestMapping("/sellTreatyPhoneNTK")
	@ResponseBody
	public Map<String,String> sellTreatyPhoneNTKAlias(HttpServletRequest request) throws JSONException{
		Map<String, String> paraMap = new HashMap<String, String>();

//		String at = (String)request.getSession().getAttribute("access_token");
		String ph = (String)request.getSession().getAttribute("phone_no");
		paraMap.put("app_id", app_id);
		paraMap.put("app_key", app_key);
		paraMap.put("timestamp", gettime());
//		paraMap.put("access_token", at);
		
		
		setOrderId(paraMap);
		paraMap.put("activeId", app_key);
		paraMap.put("activeName", app_key);
		paraMap.put("channelId", app_key);
		
		paraMap.put("custname", app_key);
		paraMap.put("isAgent", app_key);
		paraMap.put("merchantId", "1");
		paraMap.put("phoneno", app_key);
		
		paraMap.put("totalPrice", app_key);
		paraMap.put("type", app_key);
		paraMap.put("chPayRealId", "8");
		paraMap.put("payment", app_key);
		
		paraMap.put("payId", app_key);
		paraMap.put("orderSum", app_key);
		paraMap.put("prephcode", "300001");
		paraMap.put("price", app_key);
		paraMap.put("productId", "300001");
		paraMap.put("productName", app_key);
		
		paraMap.put("terminalColor", app_key);
		paraMap.put("serialId", app_key);
		paraMap.put("systemId", app_key);
		paraMap.put("payment", app_key);
		
		
		if(StringUtils.isBlank(ph))
		ph="15181244547";
		paraMap.put("phone_no", ph);
		setStatus(paraMap);
		setEndSeller(paraMap);
		paraMap.put("version", "1.0");
		paraMap.put("method", "sellTreatyPhoneNTK");
//		pars.put("alias", alias);
//		采用post请求
		String pk= pri_key;
		String postParameter=com.sunrise.base.signUtil.SignUtil.mapToSignForFX(pk, paraMap);
		//获取sign值
		String[] str=	postParameter.split("=");
		String sign=str[str.length-1];
		paraMap.put("sign", sign);
		
		
		String url = http_url+"/auth/contract/sellTreatyPhoneNTK";//沙箱测试地址
		String result=HttpClientTool.doPost(url, paraMap);
		//参数解析
		Map<String,String> rest=new HashMap<String, String>();
		try {
			rest= com.sunrise.base.util.JsonHelper.toMap(result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rest);
		return rest;
	}
	
	
	/**查询剩余流量
	 * @param request
	 * @return
	 * @throws JSONException 
	 */
	@RequestMapping("/sFreeMinQryNTK")
	@ResponseBody
	public Map<String,String> sFreeMinQryNTK(HttpServletRequest request){
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("app_id", app_id);
		paraMap.put("app_key", app_key);
		paraMap.put("timestamp", gettime());
		paraMap.put("phone_no", "15181244547");
		setStatus(paraMap);
		setEndSeller(paraMap);
		paraMap.put("version", "1.0");
		paraMap.put("method", "sFreeMinQryNTK");
		
		String pk= pri_key;
		String postParameter=com.sunrise.base.signUtil.SignUtil.mapToSignForFX(pk, paraMap);
		//获取sign值
		String[] str=	postParameter.split("=");
		String sign=str[str.length-1];
		paraMap.put("sign", sign);

		String url = http_url+"/auth/contract/sFreeMinQryNTK";//沙箱测试地址
		String result=HttpClientTool.doPost(url, paraMap);
		//参数解析
		Map<String,String> rest=new HashMap<String, String>();
		try {
			rest= com.sunrise.base.util.JsonHelper.toMap(result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rest);
		return rest;
	}
	
	/**
	 * map设置
	 * @date 2015年2月3日 下午3:41:31
	 * @auth wenb
	 */
	private void setStatus(Map<String, String> pars) {
		pars.put("status", "2");//沙箱
//		pars.put("status", "1");//正式
	}
	
	/**
	 * 设置订单号
	 */
	private void setOrderId(Map<String, String> pars){
		pars.put("order_id", "1000013520150312194406000000");
	}
	
	/**
	 * 添加末梢渠道参数
	 * @date 2015年2月3日 下午4:45:13
	 * @auth wenb
	 */
	private void setEndSeller(Map<String, String> pars) {
		pars.put("end_seller", "abcd1234");
	}
	
	private String gettime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}
}
