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
 * 需要权限验证的接口 进入此controller
 * @author 向仕波
 *
 */
@Controller(value="IndexNewController")
public class IndexNewController {
	
	private String app_id = "10000135";
	
	private String http_url = "http://10.108.226.199:8080/openApi";
	
	private String app_key = "69d6550a6b2471b54f179d707796c569";
	
	private String pri_key = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIhRSEPMopBrBdB9iFjGKyL6B5MW5FlWSwvNa4aA3t29u8ru3VrrbsSg/4VTSFtJv4SzQC9wW1Ao/ZBKiyBLFbhZ4xj7azGlTOxDW7/SGcir+tjAZB+fzFPM0EPu3lYJ3M1hV3pMyYLfQTw5gIbeGgUDbDKLtAOMb0+xd329XRf5AgMBAAECgYBH6PkCKxUmsg5jKZWfnwpqbRNJ2K2lIwx7sWWjkp/+wXQrXU6QA9pvB69Dmed1oLXTPuwBGnhRFQ6N64TxCCrwS/AxGYxOhBtL+ZKhFglFc7bjbOZxKn8cZtf6fUY8PzNeW54/CgdrbBKz6Zz8IEZK58+n2trREmZbbfVW37NQAQJBAL/5Tm1qvSIZG7vygs8iM2d61qu5G6niVsjYdYItyGB6UVyeXeu5moK/QpmePgt9zzEA9F2VnmJXk4JYFgY3DIECQQC1yAwPTzZlznx2eqoYPvgwWzoubEvH0wMLGnsRAy4ZWULj5n9lA08GmIuOhTUlaHETHkPCdlXmxa0L1yC1zK95AkATw3vSgemkyAb163qSDohSP/A3z5/MdpPOq5I1a7c0T4Nu0JMEwJ/qk/wsSoFCt5oMBngh5lRe9XsnMSBbVXGBAkAAoeWI2Bm0WPeN4fddhjqO0IJvTukklNNZ4omzEXPDms/kwxSGYXCQ8U7q/AOnUamzC1PpBUhfOSjU9baja0bhAkAGKRz6EQrFBYqDYWJDZOiNyqNzA8RxJeWt1DWq3bMC9PZLBR3XmezhIg5wzyJoeUR9ptMDv+NBgUINEpbRYYxx";
	
	/**
	 * 提供给页面测试，页面什么参数都不用传，都在contrller方法中拼接好了
	 * 流量订购沙箱测试
	 * @date 2015年2月6日 下午4:41:48
	 * @auth wenb
	 */
	@RequestMapping("/sShortAddModeNMSAlias")
	@ResponseBody
	public  Map<String,String> sShortAddModeNMSAlias(HttpServletRequest request){
		Map<String, String> paraMap = new HashMap<String, String>();
		String at = (String)request.getSession().getAttribute("access_token");
		String ph = (String)request.getSession().getAttribute("phone_no");
		paraMap.put("app_id", app_id);
		if(StringUtils.isBlank(ph)){
			paraMap.put("phone_no", "15982413818");
		}else{
			paraMap.put("phone_no", ph);
		}
		paraMap.put("access_token", at);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time= sdf.format(new Date());
		paraMap.put("timestamp",time);
		paraMap.put("version", "1.0");
//		paraMap.put("prod_prcid", "300004");
		paraMap.put("prod_prcid", "300001");
		paraMap.put("alias", "111111");
		paraMap.put("order_id", "1000013520150317112230000002");
		paraMap.put("method", "sShortAddModeNMS");
		paraMap.put("app_key", app_key);
		setEndSeller(paraMap);
		setStatus(paraMap);
		
		String pk= pri_key;
		String postParameter=com.sunrise.base.signUtil.SignUtil.mapToSignForFX(pk, paraMap);
		//获取sign值
		String[] str=	postParameter.split("=");
		String sign=str[str.length-1];
		paraMap.put("sign", sign);
		
		
		String url= http_url + "/auth/api/sShortAddModeNMS";//沙箱测试地址
		String result=HttpClientTool.doPost(url, paraMap);
		//参数解析
		Map<String,String> rest=new HashMap<String, String>();
		try {
			rest= com.sunrise.base.util.JsonHelper.toMap(result);
		} catch (JSONException e) {
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
//		pars.put("status", "2");//沙箱
		pars.put("status", "1");//正式
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
