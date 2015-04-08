package com.sunrise.system.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sunrise.base.util.HttpClientTool;
import com.sunrise.base.util.HttpTool;


@Controller
public class IndexContrllor {

	
//	private String app_id = "10000017";//测试
//	private String app_key = "99adac7111f3f8ce6e0b5ec172fe7b1f";//md5
//	private String app_key = "08374jhecndh"; 原始
//	private String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKgIw7cSeHP+e6pdewxYqUrnXIE6adMNWLtjX/YcCcn6/G25o06XF9dh+OP8BYigteZKe04qIQn1Q8V58Y8t9G9f8Vh1+/XGActxBYsxkq2L4olV1pCCUAdumiZJ6kcVf1U6xq3SUzCw4SCn+ACR9sfd5VxlNulD1aB4QT+tbokhAgMBAAECgYBwbfzV32vs4JdJ9GZCaohLb+Y7KSRpbIK0TteESlpaZK7Fk/IqwXnYSNtJX/Ur56wNvEQolpOooAyKcv8nd+4WCPjjLtdltO+z0ble9An/4cv2SDv2zmZMcQxhb4bGBaqwc96A2c5pRZabwiwVeXmnXkzCVmE+e7sxBt5+eO5I4QJBAN5geXpzMkZDQo5THz/yVDVp1HKdQrnCVm/CkfPZYaaZORatCcDu1H/deuMSu7pDt8R47cxi/l9LNMmKK4LS9i0CQQDBcNqj/p6lpgigTJ/28fRFIiCZXuIjgR93r4cAp1KsQMNULVKc+SepDsFg8tC5xFC6KC5cFz+83fKQstIhH0tFAkEAs2AenSdO5l5a0XM7Qdm3E17Kf6laqXyZPI02LfyrSDdQVXo+4+HCh2GgPopd42G8Wj8xkixIA5Ymfmt1rZF+AQJBAKJT5eZAwTkYv5xlMMe1+toY7WpG6VYKATIaphuyTfomzsNQ1UbM/q8rprOIkAeT6nqNRXoaOG5xgiBTL2qELFECQDkYu337+uT+FKsAPetZCeiVQNbybgLftp+yZjnMWGEC/GhPqYjFbxE9r4dBcp84CSeir3Zv4ojB4wk0Y2vDMv4=";
	
	
	private String app_id = "10000135";//测试
	private String app_key = "69d6550a6b2471b54f179d707796c569";//md5
//	private String app_key = "%8rU30oW1*$&"; 原始
	private String privateKey="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIhRSEPMopBrBdB9iFjGKyL6B5MW5FlWSwvNa4aA3t29u8ru3VrrbsSg/4VTSFtJv4SzQC9wW1Ao/ZBKiyBLFbhZ4xj7azGlTOxDW7/SGcir+tjAZB+fzFPM0EPu3lYJ3M1hV3pMyYLfQTw5gIbeGgUDbDKLtAOMb0+xd329XRf5AgMBAAECgYBH6PkCKxUmsg5jKZWfnwpqbRNJ2K2lIwx7sWWjkp/+wXQrXU6QA9pvB69Dmed1oLXTPuwBGnhRFQ6N64TxCCrwS/AxGYxOhBtL+ZKhFglFc7bjbOZxKn8cZtf6fUY8PzNeW54/CgdrbBKz6Zz8IEZK58+n2trREmZbbfVW37NQAQJBAL/5Tm1qvSIZG7vygs8iM2d61qu5G6niVsjYdYItyGB6UVyeXeu5moK/QpmePgt9zzEA9F2VnmJXk4JYFgY3DIECQQC1yAwPTzZlznx2eqoYPvgwWzoubEvH0wMLGnsRAy4ZWULj5n9lA08GmIuOhTUlaHETHkPCdlXmxa0L1yC1zK95AkATw3vSgemkyAb163qSDohSP/A3z5/MdpPOq5I1a7c0T4Nu0JMEwJ/qk/wsSoFCt5oMBngh5lRe9XsnMSBbVXGBAkAAoeWI2Bm0WPeN4fddhjqO0IJvTukklNNZ4omzEXPDms/kwxSGYXCQ8U7q/AOnUamzC1PpBUhfOSjU9baja0bhAkAGKRz6EQrFBYqDYWJDZOiNyqNzA8RxJeWt1DWq3bMC9PZLBR3XmezhIg5wzyJoeUR9ptMDv+NBgUINEpbRYYxx";
	
	
	
//	private String app_id = "10000017";//上海测试
//	private String app_key = "99adac7111f3f8ce6e0b5ec172fe7b1f";//md5
//////	private String app_key = "08374jhecndh"; 原始
//	private String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKgIw7cSeHP+e6pdewxYqUrnXIE6adMNWLtjX/YcCcn6/G25o06XF9dh+OP8BYigteZKe04qIQn1Q8V58Y8t9G9f8Vh1+/XGActxBYsxkq2L4olV1pCCUAdumiZJ6kcVf1U6xq3SUzCw4SCn+ACR9sfd5VxlNulD1aB4QT+tbokhAgMBAAECgYBwbfzV32vs4JdJ9GZCaohLb+Y7KSRpbIK0TteESlpaZK7Fk/IqwXnYSNtJX/Ur56wNvEQolpOooAyKcv8nd+4WCPjjLtdltO+z0ble9An/4cv2SDv2zmZMcQxhb4bGBaqwc96A2c5pRZabwiwVeXmnXkzCVmE+e7sxBt5+eO5I4QJBAN5geXpzMkZDQo5THz/yVDVp1HKdQrnCVm/CkfPZYaaZORatCcDu1H/deuMSu7pDt8R47cxi/l9LNMmKK4LS9i0CQQDBcNqj/p6lpgigTJ/28fRFIiCZXuIjgR93r4cAp1KsQMNULVKc+SepDsFg8tC5xFC6KC5cFz+83fKQstIhH0tFAkEAs2AenSdO5l5a0XM7Qdm3E17Kf6laqXyZPI02LfyrSDdQVXo+4+HCh2GgPopd42G8Wj8xkixIA5Ymfmt1rZF+AQJBAKJT5eZAwTkYv5xlMMe1+toY7WpG6VYKATIaphuyTfomzsNQ1UbM/q8rprOIkAeT6nqNRXoaOG5xgiBTL2qELFECQDkYu337+uT+FKsAPetZCeiVQNbybgLftp+yZjnMWGEC/GhPqYjFbxE9r4dBcp84CSeir3Zv4ojB4wk0Y2vDMv4=";
	
//	private String app_id = "10000017";//本地测试
//	private String app_key = "99adac7111f3f8ce6e0b5ec172fe7b1f";//md5
//////	private String app_key = "08374jhecndh"; 原始
//	private String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKgIw7cSeHP+e6pdewxYqUrnXIE6adMNWLtjX/YcCcn6/G25o06XF9dh+OP8BYigteZKe04qIQn1Q8V58Y8t9G9f8Vh1+/XGActxBYsxkq2L4olV1pCCUAdumiZJ6kcVf1U6xq3SUzCw4SCn+ACR9sfd5VxlNulD1aB4QT+tbokhAgMBAAECgYBwbfzV32vs4JdJ9GZCaohLb+Y7KSRpbIK0TteESlpaZK7Fk/IqwXnYSNtJX/Ur56wNvEQolpOooAyKcv8nd+4WCPjjLtdltO+z0ble9An/4cv2SDv2zmZMcQxhb4bGBaqwc96A2c5pRZabwiwVeXmnXkzCVmE+e7sxBt5+eO5I4QJBAN5geXpzMkZDQo5THz/yVDVp1HKdQrnCVm/CkfPZYaaZORatCcDu1H/deuMSu7pDt8R47cxi/l9LNMmKK4LS9i0CQQDBcNqj/p6lpgigTJ/28fRFIiCZXuIjgR93r4cAp1KsQMNULVKc+SepDsFg8tC5xFC6KC5cFz+83fKQstIhH0tFAkEAs2AenSdO5l5a0XM7Qdm3E17Kf6laqXyZPI02LfyrSDdQVXo+4+HCh2GgPopd42G8Wj8xkixIA5Ymfmt1rZF+AQJBAKJT5eZAwTkYv5xlMMe1+toY7WpG6VYKATIaphuyTfomzsNQ1UbM/q8rprOIkAeT6nqNRXoaOG5xgiBTL2qELFECQDkYu337+uT+FKsAPetZCeiVQNbybgLftp+yZjnMWGEC/GhPqYjFbxE9r4dBcp84CSeir3Zv4ojB4wk0Y2vDMv4=";
//	private String app_id = "10000188";//空充正式环境测试
//	private String app_key = "2fdc349190f91eed30587084816af53b";//md5
//////	private String app_key = "s9&610h77065"; 原始
//	private String privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKubuG7w+AG0skVZ3JqQM8VfNlyL8q5UYUecdauAt6Wi6JM+czJxRYt8qZET6HWvTsDAyZF2dy6doCUfFwcdpwrfQkOdAfInJFOl6360Ejm8lQoWqqxqGZLJgWw2xm9z1vBWqe5hZ3BhcTSce/wOs8mmY67VTJhVyuvspjQ2YEK3AgMBAAECgYBm5XgxDUunujEqZDdidhSUxAJUe5WH8hBiX+4uZ3+2UXgi0c9A76pDePwzgTxhVDxkVzSFhALycJEVsQ6dtanyXViERyx92kUAYPJfUNKyWCeVeu5cLKt6lFELWgHDeYKCyYPHAfAuv165Q9phsCDPTFsBUXRnDN9u4HNuhbPIAQJBAO9ffzeU8SvUpLmf297v7t5D+UKpo+zxM8NJKYwzblEuoZv2y0Iaj5zFXkK9WjRXWSMIDGYVJor+sgus3V5trmkCQQC3hz0aDyj2tNNdyfdzPp+lBvhrvpbn2BA5bWwUZB2EmVJnRPU4ynnkRVGJ+0K4/WGgRJ0kPBRjxZUwYroemoQfAkEA6WxSfjz7wSYnS+wbWJbYdF4Mn3kVpWTAeVrK7TDqDrhOV+yd4ORNzy6X8LT9VbUn8wVSMenGIBymOsRjSkXioQJABVzbSTamMMdJ4dSz+VccHRteCO/xwuaKWtM6tEHUpvo/8SqPq9AmzafE4S5JXdRxlNhMazGiOuQcHuTToW9MawJAMrrNEw9pzuWjYeiho5yXk61Ok19dJX+zcDNn0J2LIWlLuv3lpSEtzZCpcK6YLS3s2cHdw7OmoBmSxokDA6S6lA==";
	
//	private String app_id = "1000017";//正式环境的测试
//	private String app_key = "99adac7111f3f8ce6e0b5ec172fe7b1f";//MD5
//////	PRIVATE STRING APP_KEY = "08374JHECNDH"; 原始
//	private String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKgIw7cSeHP+e6pdewxYqUrnXIE6adMNWLtjX/YcCcn6/G25o06XF9dh+OP8BYigteZKe04qIQn1Q8V58Y8t9G9f8Vh1+/XGActxBYsxkq2L4olV1pCCUAdumiZJ6kcVf1U6xq3SUzCw4SCn+ACR9sfd5VxlNulD1aB4QT+tbokhAgMBAAECgYBwbfzV32vs4JdJ9GZCaohLb+Y7KSRpbIK0TteESlpaZK7Fk/IqwXnYSNtJX/Ur56wNvEQolpOooAyKcv8nd+4WCPjjLtdltO+z0ble9An/4cv2SDv2zmZMcQxhb4bGBaqwc96A2c5pRZabwiwVeXmnXkzCVmE+e7sxBt5+eO5I4QJBAN5geXpzMkZDQo5THz/yVDVp1HKdQrnCVm/CkfPZYaaZORatCcDu1H/deuMSu7pDt8R47cxi/l9LNMmKK4LS9i0CQQDBcNqj/p6lpgigTJ/28fRFIiCZXuIjgR93r4cAp1KsQMNULVKc+SepDsFg8tC5xFC6KC5cFz+83fKQstIhH0tFAkEAs2AenSdO5l5a0XM7Qdm3E17Kf6laqXyZPI02LfyrSDdQVXo+4+HCh2GgPopd42G8Wj8xkixIA5Ymfmt1rZF+AQJBAKJT5eZAwTkYv5xlMMe1+toY7WpG6VYKATIaphuyTfomzsNQ1UbM/q8rprOIkAeT6nqNRXoaOG5xgiBTL2qELFECQDkYu337+uT+FKsAPetZCeiVQNbybgLftp+yZjnMWGEC/GhPqYjFbxE9r4dBcp84CSeir3Zv4ojB4wk0Y2vDMv4=";
//	
//	private String app_id = "10000184";//正式
//	private String app_key = "28139dca3a02984be59d2db0b963f71e";//md5
////	private String app_key = "3#wk574%2226"; 原始
//	private String privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAImwTxVshCJuLWRch/qUTKwAlCfvge1aRiplfRpUFJZ/hU9s1YiYpWe0/yxWiU9tYo+P9KHifw+LOJb1WvElLvCqn8vt/GNY0E8MAqCF3zVVAj95nNgtXr7Oxf9Sy1OYHHBb6uYlQ7kqNrl0/NSgWd7kixysLC4tBVzAIzpUZyZHAgMBAAECgYAiS6wnJfRzrwXXbcThAXdnkG1W1S2DtWysusLCgBbwtaq68dzuPTdV8Zttq9Bf3FskbzL777K+aGscDtksH5rlMdNRVYelJNWgammlYCpr3EwEdLalnpNBMs2oBpvItgWDMHa9FoytJQ6ue8Ojx45FhUhoMqbDlRTsEt/j+kLuoQJBALwhRl78IsIzGZQ9DzWaXAhZjemrUonAM8anvre8kLF1tJp7MiJFoGX1T3nXaXTpRUAdd4PJIa1+hiLhnMVRXqUCQQC7XIkO5DugCvddZJc+lRRpM/EYlTXD2UL8V8s79l7Biwz1qPN5Vfxitqm9IsqIDzoMl5aOduYsNrbgUKA4+Wl7AkA7hY+dN8rLhJ1gMF1iiT77CVKEDsdje+MlxCbsPQXOj74fbkDzZPIVsCKAGy5rZe6aFXidwoIcQT6xRI50Q4VdAkEAojZHY2EuTi2wZGdGmqQve7XAsuvcFKfxWtAX4N44w5BDtByGTzKmxEApTNrmRk4IkAZeDxHZzcXBJwwm66zkKQJAf6510qiWZ39I661aYBlgYnjIseUGg4PI7fUpRZm8PL3RicgjsHOUUEVxiCudPchw/kslfiRsnrXJl/NAtx0BhA==";
	
//	private String app_id = "10000185";//正式
//	private String app_key = "f0352c4f15c76a18dc9616a7f0e2866a";//md5
////	private String app_key = "#0k*F1P&YN#T"; 原始
	
	
//	private String app_id = "10000183";//正式
//	private String app_key = "b8d57374396be10aefcb8ed31a3a305d";//md5
////	private String app_key = "#0077A87%04$"; 原始
//	private String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAPMYE3oONB6zVZDVbd0KHf4cyUpDmHlqlrtn2SnVc9VGo4y9lF8QW5c+//Y+JUV5TTo3qhh4H6cQayKECfz3u+Z2k0Bf7c2UYGdbHAEpqJ8JXGfE1MsnVeW099F9SfzZ+gDZggnF/R7vlEOtZRMEaWSbk1RXMEtEL5bmzCkX8Yv3AgMBAAECgYA4avwKNcWu4lwXcxE+XZy8Xx3wWGPIug4ljGKALQPnGa/XeCA5N+ip33s0QRQRXaeovUaCy049TbgvmIlg5nX7APpQZ8R/iOB+/i5Pcu5Y703+tU0ffuAvPnAJifowul7qRvUqi5D7W1o28yeu16tJG4FHLchywEYqPTtpgTQcoQJBAPrOjH4p9cL2cIjKpvdq2ZOtID3gbK+Xy12K3thwSJPXxmo/Lpvryfox0kJkC2VcpAbluMf7ydZIzNf0rs3y+bkCQQD4IKTmoXxeY9+d84rZx+OX1gYzcRtyVBC8wq985eliHOYUYgkZyIFaaV9Rgt1JCoR7Dgm3rFVjHT7xXA9m0MsvAkEAr59gq5uwNvQgOvlEz22nq4wITqvPD2oIgpkSkJaXi8cFbjKN7aq/RQ4MAGdTv4eOaDIGIStD22Qg5Hu2yAoDKQJBALkoWwWDkfQ54R/UIlQ7dpnSa1Qt3kZldwv019GDG7JMUGD7e0ETP3vBUBDab9jWkV/mbNf0MM/CfjrL8O5Pdp8CQFjOUIer+p6k5Xa3Qq3FzvcNPoGPrHnu/cdsoPWGucnUT667kMN41npce9Pq+51thbbxwKJkq7eB6YZfIaeQ8bQ=";
	
	
//	private String httpUrl = "http://218.205.231.138:8051/openApi";
	private String httpUrl = "http://10.108.226.44:8080/openApi"; //本机
//	private String httpUrl = "http://10.109.63.228:8051/openApi"; //正式 
//	private String httpUrl = "http://218.205.231.138:8051/openApi"; //测试
//	private String httpUrl = "http://10.95.246.2:8080/openApi"; //测试环境
//	private String httpUrl = "http://10.108.226.102:8080/openApi"; //本机
	
	private Logger log = LoggerFactory.getLogger(IndexContrllor.class);
	public static void main(String[] args) {
//	 String appKeyMd5=	com.sunrise.base.signUtil.MD5.MD5Encode("%8rU30oW1*$&");
//	 System.out.println(appKeyMd5);
//		String url="http://218.205.252.26:18051/openApi/authLogin/random_code?phoneNum=15181244547&passMode=0&smsCode=&managerLogin=1";
//		String s=HttpClientTool.doGetUrl(url, "utf-8");
//		System.out.println(s);
//		System.out.println("LLUri="+new IndexContrllor().getLlinfoUrl());
//		System.out.println("dgUri="+new IndexContrllor().getDgUrl());
//		System.out.println("atURl="+new IndexContrllor().getARUrl());
//		System.out.println("getSmsURL="+new IndexContrllor().getSmsCodeUrl());
//		kcQurtest();
//		kcDgtest();
//		kcQurtest();
	}
//	实时话费查询  qryRealTimeFee   /auth/api/qryRealTimeFee
//	余额查询        sPFeeQuery
//	账单查询     sQryBillHome
//	已订购产品查询  qryOrdPudInfo
//	已办理营销活动查询  qryHandledAct
//	裸机销售  sellBarePhone
//	合约机销售  sellTreatyPhone
//	主资费变更  updateMainCharges
//	合约办理   sTreatyDeal
	
//	实时话费查询  qryRealTimeFee   /auth/api/qryRealTimeFee
	@RequestMapping("/qryRealTimeFee")
	@ResponseBody
	public Map<String,String> qryRealTimeFeeAlias(HttpServletRequest request) throws JSONException{
		Map<String,String> rest = new HashMap<String,String>();
		Map<String,String> pars = this.getSysPars(request, "qryRealTimeFee");
		pars.put("alias", alias);
//		采用post请求
		rest = this.doPostSign(null, pars, "/auth/api/qryRealTimeFee");
	  Map res=	com.sunrise.base.util.JsonHelper.toMap(rest.get("result"));
	  restJson("qryRealTimeFee",rest);
		return rest;
	}
private void restJson(String method, Map<String, String> rest) {
	System.out.println("访问方法："+method+";返回json数据："+rest);
}
//	余额查询        sPFeeQuery
	@RequestMapping("/sPFeeQuery")
	@ResponseBody
	public Map<String,String> sPFeeQueryAlias(HttpServletRequest request) throws JSONException{
		Map<String,String> rest = new HashMap<String,String>();
		Map<String,String> pars = this.getSysPars(request, "sPFeeQuery");
		pars.put("alias", alias);
		pars.put("msg_flag", "1");
//		采用post请求
		rest = this.doPostSign(null, pars, "/auth/api/sPFeeQuery");
		Map res=	com.sunrise.base.util.JsonHelper.toMap(rest.get("result"));
		restJson("sPFeeQuery",rest);
		return rest;
	}
//	账单查询     sQryBillHome
	@RequestMapping("/sQryBillHome")
	@ResponseBody
	public Map<String,String> sQryBillHomeAlias(HttpServletRequest request) throws JSONException{
		Map<String,String> rest = new HashMap<String,String>();
		Map<String,String> pars = this.getSysPars(request, "sQryBillHome");
		pars.put("alias", alias);
		pars.put("qry_start_date", "201412");
		pars.put("qry_type", "1");
		pars.put("serv_type", "1,2");
//		采用post请求
		rest = this.doPostSign(null, pars, "/auth/api/sQryBillHome");
		Map res=	com.sunrise.base.util.JsonHelper.toMap(rest.get("result"));
		restJson("sQryBillHome",rest);
		return rest;
	}
//	已订购产品查询  qryOrdPudInfo
	@RequestMapping("/qryOrdPudInfo")
	@ResponseBody
	public Map<String,String> qryOrdPudInfoAlias(HttpServletRequest request) throws JSONException{
		Map<String,String> rest = new HashMap<String,String>();
		Map<String,String> pars = this.getSysPars(request, "qryOrdPudInfo");
		pars.put("alias", alias);
		pars.put("qry_type", "0");
		pars.put("year_month", "201411");
//		采用post请求
		rest = this.doPostSign(null, pars, "/auth/api/qryOrdPudInfo");
		Map res=	com.sunrise.base.util.JsonHelper.toMap(rest.get("result"));
		restJson("qryOrdPudInfo",rest);
		return rest;
	}
//	已办理营销活动查询  qryHandledAct
	@RequestMapping("/qryHandledAct")
	@ResponseBody
	public Map<String,String> qryHandledActAlias(HttpServletRequest request) throws JSONException{
		Map<String,String> rest = new HashMap<String,String>();
		Map<String,String> pars = this.getSysPars(request, "qryHandledAct");
		pars.put("alias", alias);
//		采用post请求
		rest = this.doPostSign(null, pars, "/auth/api/qryHandledAct");
		Map res=	com.sunrise.base.util.JsonHelper.toMap(rest.get("result"));
		restJson("qryHandledAct",rest);
		return rest;
	}
//	裸机销售  sellBarePhone   
	@RequestMapping("/sellBarePhone")
	@ResponseBody
	public Map<String,String> sellBarePhoneAlias(HttpServletRequest request) throws JSONException{
		Map<String,String> rest = new HashMap<String,String>();
		Map<String,String> pars = this.getSysPars(request, "sellBarePhone");
		pars.put("alias", alias);
//		采用post请求
		rest = this.doPostSign(null, pars, "/auth/api/sellBarePhone");
		Map res=	com.sunrise.base.util.JsonHelper.toMap(rest.get("result"));
		restJson("sellBarePhone",rest);
		return rest;
	}
//	合约机销售  sellTreatyPhone
	@RequestMapping("/sellTreatyPhone")
	@ResponseBody
	public Map<String,String> sellTreatyPhoneAlias(HttpServletRequest request) throws JSONException{
		Map<String,String> rest = new HashMap<String,String>();
		Map<String,String> pars = this.getSysPars(request, "sellTreatyPhone");
		pars.put("alias", alias);
//		采用post请求
		rest = this.doPostSign(null, pars, "/auth/api/sellTreatyPhone");
		Map res=	com.sunrise.base.util.JsonHelper.toMap(rest.get("result"));
		restJson("sellTreatyPhone",rest);
		return rest;
	}
	////	主资费变更  updateMainCharges
	@RequestMapping("/updateMainCharges")
	@ResponseBody
	public Map<String,String> updateMainChargesAlias(HttpServletRequest request) throws JSONException{
		Map<String,String> rest = new HashMap<String,String>();
		Map<String,String> pars = this.getSysPars(request, "updateMainCharges");
		pars.put("alias", alias);
//		pars.put("operate_type", alias);
//		pars.put("prod_prcid", alias);
//		pars.put("limit_code", alias);
//		采用post请求
		rest = this.doPostSign(null, pars, "/auth/api/updateMainCharges");
		Map res=	com.sunrise.base.util.JsonHelper.toMap(rest.get("result"));
		restJson("updateMainCharges",rest);
		return rest;
	}
	/**
	 * //	合约办理   sTreatyDeal
	 * @date 2015年2月10日 上午10:30:49
	 * @auth wenb
	 */
	@RequestMapping("/sTreatyDeal")
	@ResponseBody
	public Map<String,String> sTreatyDealAlias(HttpServletRequest request) throws JSONException{
		Map<String,String> rest = new HashMap<String,String>();
		Map<String,String> pars = this.getSysPars(request, "sTreatyDeal");
		pars.put("alias", alias);
//		采用post请求
		rest = this.doPostSign(null, pars, "/auth/api/sTreatyDeal");
		Map res=	com.sunrise.base.util.JsonHelper.toMap(rest.get("result"));
		restJson("sTreatyDeal",rest);
		return rest;
	}
	
	/**
	 * 压力测试流量查询的url
	 * @return
	 */
	private String getLlinfoUrl(){
		Map<String,String> pars = new HashMap<String,String>();

		String at = "b284bca8402559bbe16d2089719e6511";
//		String ph = "15181244547";
		String ph = "15181244547";
		pars.put("app_id", app_id);
		pars.put("timestamp", "20150121095406");
		pars.put("access_token", at);
		pars.put("phone_no", ph);
		setStatus(pars);
		setEndSeller(pars);
		pars.put("alias", "75616");
		pars.put("version", "1.0");
		String method="sFreeMinQry";
		pars.put("method", method);
	  String returnpars=com.sunrise.base.signUtil.SignUtil.mapToSignForFX(privateKey, pars);
	  String url = httpUrl + "/auth/api/sFreeMinQry" +"?" + returnpars;
		return url;
	}
	
	private String getSmsCodeUrl(){
		Map<String,String> pars = new HashMap<String,String>();

		String at = "b284bca8402559bbe16d2089719e6511";
		String ph = "15181244547";
		pars.put("app_id", app_id);
		pars.put("timestamp", "20150121095406");
		pars.put("access_token", at);
		pars.put("phone_no", ph);
		setStatus(pars);
		setEndSeller(pars);
		pars.put("version", "1.0");
		String method="sQryRandPass";
		pars.put("method", method);
		
		pars.put("alias", "75616");
	  String returnpars=com.sunrise.base.signUtil.SignUtil.mapToSignForFX(privateKey, pars);
	  String url = httpUrl + "/auth/api/sQryRandPass" +"?" + returnpars;
		return url;
	}
	/**
	 * 订购流量的压力测试地址
	 * @return
	 */
	private String getDgUrl(){
		Map<String,String> pars = new HashMap<String,String>();

		String at = "b284bca8402559bbe16d2089719e6511";
		String ph = "15181244547";
		pars.put("app_id", app_id);
		pars.put("timestamp", "20150121095406");
		pars.put("access_token", at);
		pars.put("phone_no", ph);
		setStatus(pars);
		setEndSeller(pars);
		pars.put("version", "1.0");
		String method="sShortAddMode";
		pars.put("method", method);
		
		pars.put("prod_prcid", "300003");
		pars.put("sms_pwd", "454248");
		pars.put("alias", "75616");
		
	  String returnpars=com.sunrise.base.signUtil.SignUtil.mapToSignForFX(privateKey, pars);
	  String url = httpUrl + "/auth/api/sShortAddMode" +"?" + returnpars;
		return url;
	}
	private String getARUrl(){

		log.debug("获取at和rt方法");
		Map<String,String> rest = new HashMap<String,String>();
		Map<String,String> pars = new HashMap<String,String>();
		
//	request.getSession().setAttribute("phone_no", "15181244547");
//		request.getSession().setAttribute("auth_code", "15181244547100000172865484957547542014122417200600305");
		String ac = "11111111";
		String ph = "15181244547";
		
		pars.put("app_id", app_id);
		pars.put("app_key", app_key);
		pars.put("timestamp","20150121095406");
		setStatus(pars);
		setEndSeller(pars);
		pars.put("grant_type", "AC");
		pars.put("auth_code", ac);
		pars.put("phone_no", ph);
		pars.put("state", "1122333");
		pars.put("alias", "75616");
		
//		rest = this.doGetNoSign(pars, "/authLogin/access_token");
		
		Iterator<String> it = pars.keySet().iterator();
		List<String> list = new ArrayList<String>();
		StringBuffer sb=new StringBuffer();
		String key;
		while(it.hasNext()){
    		key = (String) it.next();
    		list.add(key+"="+pars.get(key)+"&");
    	}
		for(String s : list){
    		sb.append(s);
    	}
		sb.delete(sb.length()-1, sb.length());
		System.out.println("参数拼接后为："+sb.toString());
		String urlWithSign =httpUrl+"/authLogin/access_token"+"?"+sb.toString();
		return urlWithSign;
	
	}

	
//	private String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKgIw7cSeHP+e6pdewxYqUrnXIE6adMNWLtjX/YcCcn6/G25o06XF9dh+OP8BYigteZKe04qIQn1Q8V58Y8t9G9f8Vh1+/XGActxBYsxkq2L4olV1pCCUAdumiZJ6kcVf1U6xq3SUzCw4SCn+ACR9sfd5VxlNulD1aB4QT+tbokhAgMBAAECgYBwbfzV32vs4JdJ9GZCaohLb+Y7KSRpbIK0TteESlpaZK7Fk/IqwXnYSNtJX/Ur56wNvEQolpOooAyKcv8nd+4WCPjjLtdltO+z0ble9An/4cv2SDv2zmZMcQxhb4bGBaqwc96A2c5pRZabwiwVeXmnXkzCVmE+e7sxBt5+eO5I4QJBAN5geXpzMkZDQo5THz/yVDVp1HKdQrnCVm/CkfPZYaaZORatCcDu1H/deuMSu7pDt8R47cxi/l9LNMmKK4LS9i0CQQDBcNqj/p6lpgigTJ/28fRFIiCZXuIjgR93r4cAp1KsQMNULVKc+SepDsFg8tC5xFC6KC5cFz+83fKQstIhH0tFAkEAs2AenSdO5l5a0XM7Qdm3E17Kf6laqXyZPI02LfyrSDdQVXo+4+HCh2GgPopd42G8Wj8xkixIA5Ymfmt1rZF+AQJBAKJT5eZAwTkYv5xlMMe1+toY7WpG6VYKATIaphuyTfomzsNQ1UbM/q8rprOIkAeT6nqNRXoaOG5xgiBTL2qELFECQDkYu337+uT+FKsAPetZCeiVQNbybgLftp+yZjnMWGEC/GhPqYjFbxE9r4dBcp84CSeir3Zv4ojB4wk0Y2vDMv4=";

	//	private String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCoCMO3Enhz/nuqXXsMWKlK51yBOmnTDVi7Y1/2HAnJ+vxtuaNOlxfXYfjj/AWIoLXmSntOKiEJ9UPFefGPLfRvX/FYdfv1xgHLcQWLMZKti+KJVdaQglAHbpomSepHFX9VOsat0lMwsOEgp/gAkfbH3eVcZTbpQ9WgeEE/rW6JIQIDAQAB";
//	private String privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAImwTxVshCJuLWRch/qUTKwAlCfvge1aRiplfRpUFJZ/hU9s1YiYpWe0/yxWiU9tYo+P9KHifw+LOJb1WvElLvCqn8vt/GNY0E8MAqCF3zVVAj95nNgtXr7Oxf9Sy1OYHHBb6uYlQ7kqNrl0/NSgWd7kixysLC4tBVzAIzpUZyZHAgMBAAECgYAiS6wnJfRzrwXXbcThAXdnkG1W1S2DtWysusLCgBbwtaq68dzuPTdV8Zttq9Bf3FskbzL777K+aGscDtksH5rlMdNRVYelJNWgammlYCpr3EwEdLalnpNBMs2oBpvItgWDMHa9FoytJQ6ue8Ojx45FhUhoMqbDlRTsEt/j+kLuoQJBALwhRl78IsIzGZQ9DzWaXAhZjemrUonAM8anvre8kLF1tJp7MiJFoGX1T3nXaXTpRUAdd4PJIa1+hiLhnMVRXqUCQQC7XIkO5DugCvddZJc+lRRpM/EYlTXD2UL8V8s79l7Biwz1qPN5Vfxitqm9IsqIDzoMl5aOduYsNrbgUKA4+Wl7AkA7hY+dN8rLhJ1gMF1iiT77CVKEDsdje+MlxCbsPQXOj74fbkDzZPIVsCKAGy5rZe6aFXidwoIcQT6xRI50Q4VdAkEAojZHY2EuTi2wZGdGmqQve7XAsuvcFKfxWtAX4N44w5BDtByGTzKmxEApTNrmRk4IkAZeDxHZzcXBJwwm66zkKQJAf6510qiWZ39I661aYBlgYnjIseUGg4PI7fUpRZm8PL3RicgjsHOUUEVxiCudPchw/kslfiRsnrXJl/NAtx0BhA==";
//	private String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCoCMO3Enhz/nuqXXsMWKlK51yBOmnTDVi7Y1/2HAnJ+vxtuaNOlxfXYfjj/AWIoLXmSntOKiEJ9UPFefGPLfRvX/FYdfv1xgHLcQWLMZKti+KJVdaQglAHbpomSepHFX9VOsat0lMwsOEgp/gAkfbH3eVcZTbpQ9WgeEE/rW6JIQIDAQAB";
	
	private String alias="";
	@RequestMapping("/index") 
	public String index(){
		return "index";
	}
	@RequestMapping("/indexAlias") 
	public String indexAlias(){
		return "indexAlias";
	}
	/**rediristUrl
	 * rediristUrlAlias
	 * 设置沙箱或正式
	 * @date 2015年2月3日 下午3:39:57
	 * @auth wenb
	 */
	private void setStatus(RedirectAttributes attr){
//		attr.addAttribute("status", "2");
		attr.addAttribute("status", "1");
	}
	/**
	 * map设置
	 * @date 2015年2月3日 下午3:41:31
	 * @auth wenb
	 */
	private void setStatus(Map<String, String> pars) {
//		pars.put("status", "2");
		pars.put("status", "1");
	}
	/**
	 * 添加末梢渠道参数
	 * @date 2015年2月3日 下午4:45:13
	 * @auth wenb
	 */
	private void setEndSeller(Map<String, String> pars) {
		pars.put("end_seller", "22jj");
	}
	/**
	 * 跳转到登录页面
	 * @date 2015年2月10日 上午10:06:52
	 * @auth wenb
	 */
	private void setEndSellerAttr(RedirectAttributes attr) {
		attr.addAttribute("end_seller", "22jj");
	}
	/**
	 * 获取ac
	 * @date 2015年2月10日 上午10:17:31
	 * @auth wenb
	 */
	private void setEndSellerTakeAc(HttpServletRequest request) {
		// TODO Auto-generated method stub
		request.getSession().setAttribute("end_seller", "22jj");
	}
	
	private void setAlias(Map<String, String> pars,String alias) {
		// TODO Auto-generated method stub
		if(StringUtils.isNotBlank(alias)){
			pars.put("alias", alias);
		}else{
			pars.put("alias", "9876543");
		}
		
	}
	
	@RequestMapping("/reurl")
	public String rediristUrl(WebRequest request,RedirectAttributes attr){
		String url = request.getParameter("url");
		String type = request.getParameter("type");
		String state = request.getParameter("state");
		log.debug("登录跳转,参数为："+app_id+","+type+","+state);
		attr.addAttribute("app_id",app_id);
		attr.addAttribute("display", type);
		attr.addAttribute("state", state);
		attr.addAttribute("app_key", app_key);
		attr.addAttribute("response_type", "code");
		attr.addAttribute("redirect_uri", "/tellac");
		setStatus(attr);
		return "redirect:"+url;
	}
	
	@RequestMapping("/reurlAlias")
	public String rediristUrlAlias(WebRequest request,RedirectAttributes attr){
		String url = request.getParameter("url");
		String type = request.getParameter("type");
		String state = request.getParameter("state");
		String alias = request.getParameter("alias");
		this.alias=alias;
		log.debug("登录跳转,参数为："+app_id+","+type+","+state);
		attr.addAttribute("app_id",app_id);
//		attr.addAttribute("app_id","10000017");
		attr.addAttribute("display", type);
		attr.addAttribute("state", state);
		attr.addAttribute("alias", alias);
		attr.addAttribute("method", "authorize");
		attr.addAttribute("app_key", app_key);
		attr.addAttribute("response_type", "code");
		attr.addAttribute("redirect_uri", "/tellacAlias");
		setEndSellerAttr(attr);
		setStatus(attr);
		return "redirect:"+url;
	}
	
	/**跳转回来时，获取ac
	 * @param request
	 * @return
	 */
	@RequestMapping("/tellac")
	public String takeAc(HttpServletRequest request){
		String ac 		= request.getParameter("auth_code");
		String ph 		= request.getParameter("phone_no");
		String state 	= request.getParameter("state");
		log.debug("获取的AC验证码为："+ac+","+ph+","+state);
		request.getSession().setAttribute("auth_code", ac);
		request.getSession().setAttribute("phone_no", ph);
		return "index";
	}
	
	/**跳转回来时，获取ac
	 * @param request
	 * @return
	 */
	@RequestMapping("/tellacAlias")
	public String takeAcAlias(HttpServletRequest request){
		String ac 		= request.getParameter("auth_code");
		String ph 		= request.getParameter("phone_no");
		String state 	= request.getParameter("state");
		log.debug("获取的AC验证码为："+ac+","+ph+","+state);
		request.getSession().setAttribute("auth_code", ac);
		request.getSession().setAttribute("phone_no", ph);
		setEndSellerTakeAc(request);
//		request.getSession().setAttribute("end_seller", "abcd1111");
		return "indexAlias";
	}
	
	


	/**根据ac获取at和rt
	 * @param request
	 * @return
	 */
	@RequestMapping("/getat")
	@ResponseBody
	public Map<String,String> takeat(HttpServletRequest request){
		log.debug("获取at和rt方法");
		Map<String,String> rest = new HashMap<String,String>();
		Map<String,String> pars = new HashMap<String,String>();
		
//	request.getSession().setAttribute("phone_no", "15181244547");
//		request.getSession().setAttribute("auth_code", "15181244547100000172865484957547542014122417200600305");
		String ac = (String)request.getSession().getAttribute("auth_code");
		String ph = (String)request.getSession().getAttribute("phone_no");
		String qq = (String)request.getSession().getAttribute("qq");
		
		pars.put("app_id", app_id);
		pars.put("app_key", app_key);
		setStatus(pars);
		setEndSeller(pars);

		pars.put("timestamp", gettime());
		pars.put("grant_type", "AC");
		pars.put("auth_code", ac);
		pars.put("phone_no", ph);
		pars.put("state", "1122333");
		
		log.debug("AC="+ac+",ph="+ph+",qq="+qq); 
		
		rest=doPostSign(null, pars, "/authLogin/access_token");
//		rest = this.doGetNoSign(pars, "/authLogin/access_token");
		
		String at = (String)rest.get("access_token");
		String rt = (String)rest.get("refresh_token");
		log.debug("获取的访问令牌为："+at);
		log.debug("获取的刷新令牌为："+rt);

		request.getSession().setAttribute("access_token", at);
		request.getSession().setAttribute("refresh_token", rt);
		return rest;
	}
	
	/**根据ac获取at和rt
	 * @param request
	 * @return
	 */
	@RequestMapping("/getatAlias")
	@ResponseBody
	public Map<String,String> takeatAlias(HttpServletRequest request){
		log.debug("获取at和rt方法");
		Map<String,String> rest = new HashMap<String,String>();
		Map<String,String> pars = new HashMap<String,String>();
		
//	request.getSession().setAttribute("phone_no", "15181244547");
//		request.getSession().setAttribute("auth_code", "15181244547100000172865484957547542014122417200600305");
		String ac = (String)request.getSession().getAttribute("auth_code");
		String ph = (String)request.getSession().getAttribute("phone_no");
		if(StringUtils.isBlank(ph)){
//			ph="15181244547";
			ph="15828304779";
		}
		String qq = (String)request.getSession().getAttribute("qq");
		
		pars.put("app_id", app_id);
		pars.put("app_key", app_key);
		pars.put("method", "access_token");
		pars.put("timestamp", gettime());
		setStatus(pars);
		setEndSeller(pars);
		pars.put("grant_type", "AC");
		pars.put("auth_code", ac);
		pars.put("phone_no", ph);
		pars.put("state", "1122333");
		pars.put("alias", alias);
		
		log.debug("AC="+ac+",ph="+ph+",qq="+qq); 
		
//		rest = this.doGetNoSign(pars, "/authLogin/access_token");
		rest=doPostSign(null, pars, "/authLogin/access_token");
		
		String at = (String)rest.get("access_token");
		String rt = (String)rest.get("refresh_token");
		log.debug("获取的访问令牌为："+at);
		log.debug("获取的刷新令牌为："+rt);
		
		request.getSession().setAttribute("access_token", at);
		request.getSession().setAttribute("refresh_token", rt);
		return rest;
	}

	/**根据刷新令牌获取新的at
	 * @param request
	 * @return
	 */
	@RequestMapping("/getat1")
	@ResponseBody
	public Map<String,String> takeatOnly(HttpServletRequest request){
		Map<String,String> rest = new HashMap<String,String>();
		
		Map<String,String> pars = this.getSysPars(request, "accessToken_RefreshToken");
		String rt = (String)request.getSession().getAttribute("refresh_token");
		pars.put("refresh_token", rt);
		pars.put("app_key", app_key);
		System.out.println("获取atbyRt"+pars);
		//timestamp=20141230114626928, phone_no=15181244547, app_key=99adac7111f3f8ce6e0b5ec172fe7b1f, 
		//method=accessToken_RefreshToken, app_id=10000017, refresh_token=null, access_token=null, version=1.0
//		rest = this.doGetNoSign(pars, "/authLogin/accessToken_RefreshToken");
		rest=doPostSign(null, pars, "/authLogin/accessToken_RefreshToken");
		String at = (String)rest.get("access_token");
		log.debug("获取的访问令牌为："+at);

		request.getSession().setAttribute("access_token", at);
		
		return rest;
	}
	/**根据刷新令牌获取新的at
	 * @param request
	 * @return
	 */
	@RequestMapping("/getat1Alias")
	@ResponseBody
	public Map<String,String> takeatOnlyAlias(HttpServletRequest request){
		Map<String,String> rest = new HashMap<String,String>();
		
		Map<String,String> pars = this.getSysPars(request, "accessToken_RefreshToken");
		String rt = (String)request.getSession().getAttribute("refresh_token");
		pars.put("refresh_token", rt);
		pars.put("app_key", app_key);
		pars.put("alias", alias);
		System.out.println("获取atbyRt"+pars);
		//timestamp=20141230114626928, phone_no=15181244547, app_key=99adac7111f3f8ce6e0b5ec172fe7b1f, 
		//method=accessToken_RefreshToken, app_id=10000017, refresh_token=null, access_token=null, version=1.0
//		rest = this.doGetNoSign(pars, "/authLogin/accessToken_RefreshToken");
		rest=doPostSign(null, pars, "/authLogin/accessToken_RefreshToken");
		String at = (String)rest.get("access_token");
		log.debug("获取的访问令牌为："+at);
		
		request.getSession().setAttribute("access_token", at);
		
		return rest;
	}
	/**
	 * 解除绑定
	 * @param request
	 * @return
	 */
	@RequestMapping("/cancle_relation")
	@ResponseBody
	public Map<String,String> cancleRelation(HttpServletRequest request){
		Map<String,String> rest = new HashMap<String,String>();
		
		String phone_no = (String)request.getSession().getAttribute("phone_no");
		String alias = (String)request.getSession().getAttribute("alias");
		String state = (String)request.getSession().getAttribute("state");
		Map<String,String> pars = new HashMap<String,String> ();
		pars.put("app_id", app_id);
		pars.put("app_key", app_key);
		pars.put("timestamp", gettime());
		pars.put("grant_type", "AC");
		if(StringUtils.isNotBlank(state)){
			pars.put("state", state);
		}
		if(StringUtils.isBlank(phone_no)){
//			pars.put("phone_no", "15181244547");
			pars.put("phone_no", "15181244547");
		}else{
			pars.put("phone_no", phone_no);
		}
		pars.put("alias", "111111");
//		setAlias(pars,alias);
		setStatus(pars);
		setEndSeller(pars);
		pars.put("method", "cancle_relation");
		System.out.println("解绑参数"+pars);
		
//		rest = this.doGetNoSign(pars, "/authLogin/cancle_relation");
		rest=doPostSign(null, pars, "/authLogin/cancle_relation");
		
		return rest;
	}


	/**查询剩余流量
	 * @param request
	 * @return
	 */
	@RequestMapping("/getinfo")
	@ResponseBody
	public Map<String,String> getinfo(HttpServletRequest request){
		Map<String,String> rest = new HashMap<String,String>();
		Map<String,String> pars = this.getSysPars(request, "sFreeMinQry");
		
//		rest = this.doGetSign(null, pars, "/auth/api/sFreeMinQry"); 
//		采用post请求
		rest = this.doPostSign(null, pars, "/auth/api/sFreeMinQry"); 
		return rest;
	}
	/**查询剩余流量
	 * @param request
	 * @return
	 * @throws JSONException 
	 */
	@RequestMapping("/getinfoAlias")
	@ResponseBody
	public Map<String,String> getinfoAlias(HttpServletRequest request) throws JSONException{
		Map<String,String> rest = new HashMap<String,String>();
		Map<String,String> pars = this.getSysPars(request, "sFreeMinQry");
		pars.put("alias",alias);
		
//		rest = this.doGetSign(null, pars, "/auth/api/sFreeMinQry"); 
//		采用post请求
//		com.sunrise.base.util.JsonHelper.toMap(result);
		rest = this.doPostSign(null, pars, "/auth/api/sFreeMinQry"); 
		/*log.info("res_code="+rest.get("res_code"));
		log.info("res_msg="+rest.get("res_msg"));
		log.info("result="+rest.get("result"));
		Map res=com.sunrise.base.util.JsonHelper.toMap(rest.get("result"));
		String fee_list=(String) res.get("fee_list");
		JSONObject delMap = new JSONObject(fee_list);
		JSONArray arraList = delMap.getJSONArray("fee_info");
		for(int i = 0 ; i < arraList.length() ; i ++){
			String s = arraList.getString(i);
			res=com.sunrise.base.util.JsonHelper.toMap(s);
			log.info("total=="+i+"====="+res.get("total"));
			log.info("used=="+i+"====="+res.get("used"));
			log.info("remain=="+i+"====="+res.get("remain"));
			log.info("prod_prc_name=="+i+"====="+res.get("prod_prc_name"));
			log.info("stmp=="+i+"====="+res.get("stmp"));
			log.info("type=="+i+"====="+res.get("type"));
			log.info("type_name=="+i+"====="+res.get("type_name"));
		}
		log.info("返回map=="+rest);*/
		return rest;
	}
   

	/**订购流量
	 * @param request
	 * @return
	 */
	@RequestMapping("/dgll")
	@ResponseBody
	public Map<String,String> dgll(HttpServletRequest request){
		Map<String,String> rest = new HashMap<String,String>();
		//rest.put("state", "订购套餐成功");
		Map<String,String> pars = this.getSysPars(request, "sShortAddMode");
		pars.put("prod_prcid", "300021");
		pars.put("sms_pwd", "111111");
		pars.put("transNo", "1000013520150317112230");

//		rest = this.doGetSign(null, pars, "/auth/api/sShortAddMode"); 
//		采用post请求
		rest = this.doPostSign(null, pars, "/auth/api/sShortAddMode"); 
		return rest;
	}
	/**订购流量
	 * @param request
	 * @return
	 * @throws JSONException 
	 */
	@RequestMapping("/dgllAlias")
	@ResponseBody
	public Map<String,String> dgllAlias(HttpServletRequest request) throws JSONException{
		Map<String,String> rest = new HashMap<String,String>();
		//rest.put("state", "订购套餐成功");
		Map<String,String> pars = this.getSysPars(request, "sShortAddMode");
//		pars.put("prod_prcid", "300021");
//		pars.put("prod_prcid", "300037");
		
//		pars.put("prod_prcid", "300126");//正式环境测试
		pars.put("prod_prcid", "300004");//本地环境测试
//		pars.put("prod_prcid", "300001");
		pars.put("sms_pwd", "703485");
		pars.put("alias", alias);
		pars.put("order_id", "1000013520150317112230000001");
//		rest = this.doGetSign(null, pars, "/auth/api/sShortAddMode"); 
//		采用post请求
		rest = this.doPostSign(null, pars, "/auth/api/sShortAddMode");
		log.info( rest.get("res_code"));
		log.info( rest.get("res_msg"));
		log.info( rest.get("result"));
//		Map<String, String> res=	com.sunrise.base.util.JsonHelper.toMap(rest);
//		Map<String,String> res=new HashMap<String, String>();
//		res= com.sunrise.base.util.JsonHelper.toMap(rest);
//	Map res=	com.sunrise.base.util.JsonHelper.toMap(rest.get("result"));
//	     log.info("order_id=="+res.get("order_id").toString());
//	     log.info("eff_date=="+res.get("eff_date").toString());
//	     log.info("exp_date=="+res.get("exp_date").toString());
//	     log.info("effexp_mode=="+res.get("effexp_mode").toString());
//	     log.info("brand_id=="+res.get("brand_id").toString());
		
		return rest;
	}
	
	
	/**
	 * 查询用户目前的状态
	 * @param request
	 * @return
	 * @throws JSONException 
	 */
	@RequestMapping("/qryUserStatusInfo")
	@ResponseBody
	public Map<String,String> qryUserStatusInfoAlias(HttpServletRequest request) throws JSONException{
		Map<String,String> rest = new HashMap<String,String>();
		Map<String,String> pars = this.getSysPars(request, "qryUserStatusInfo");
		pars.put("alias",alias);
		
		rest = this.doPostSign(null, pars, "/auth/api/qryUserStatusInfo"); 
		return rest;
	}
	/**
	 * 查询用户归属地信息
	 * @param request
	 * @return
	 * @throws JSONException 
	 */
	@RequestMapping("/qryUserRegionInfo")
	@ResponseBody
	public Map<String,String> qryUserRegionInfoAlias(HttpServletRequest request) throws JSONException{
		Map<String,String> rest = new HashMap<String,String>();
		Map<String,String> pars = this.getSysPars(request, "qryUserRegionInfo");
//		pars.put("alias",alias);
		
		rest = this.doPostSign(null, pars, "/auth/api/qryUserRegionInfo"); 
		return rest;
	}
	/**
	 * 查询用户品牌信息
	 * @param request
	 * @return
	 * @throws JSONException 
	 */
	@RequestMapping("/qryUserBrandInfo")
	@ResponseBody
	public Map<String,String> qryUserBrandInfoAlias(HttpServletRequest request) throws JSONException{
		Map<String,String> rest = new HashMap<String,String>();
		Map<String,String> pars = this.getSysPars(request, "qryUserBrandInfo");
//		pars.put("alias",alias);
		
		rest = this.doPostSign(null, pars, "/auth/api/qryUserBrandInfo"); 
		return rest;
	}
	/**
	 * 查询当前用户账户余额信息
	 * @param request
	 * @return
	 * @throws JSONException 
	 */
	@RequestMapping("/qryBalanceOfAccount")
	@ResponseBody
	public Map<String,String> qryBalanceOfAccountAlias(HttpServletRequest request) throws JSONException{
		Map<String,String> rest = new HashMap<String,String>();
		Map<String,String> pars = this.getSysPars(request, "qryBalanceOfAccount");
//		pars.put("alias",alias);
		
		rest = this.doPostSign(null, pars, "/auth/api/qryBalanceOfAccount"); 
		return rest;
	}
	/**
	 * 对入网用户身份校验
	 * @param request
	 * @return
	 * @throws JSONException 
	 */
	@RequestMapping("/chkActivateQua")
	@ResponseBody
	public Map<String,String> chkActivateQuaAlias(HttpServletRequest request) throws JSONException{
		Map<String,String> rest = new HashMap<String,String>();
		
		Map<String,String> pars = new HashMap<String,String>();

		String at = (String)request.getSession().getAttribute("access_token");
		String ph = (String)request.getSession().getAttribute("phone_no");
		pars.put("app_id", app_id);
		pars.put("timestamp", gettime());
		pars.put("access_token", at);
		if(StringUtils.isBlank(ph))
//			ph="15181244547";
		ph="15181244547";
		pars.put("phone_no", ph);
		setStatus(pars);
		setEndSeller(pars);
		pars.put("version", "1.0");
		pars.put("method", "chkActivateQua");
		
		//业务参数
		pars.put("id_type", "1");
		pars.put("id_iccid", "123456789012345678");
		
//		pars.put("alias",alias);
		
		rest = this.doPostSign(null, pars, "/auth/api/chkActivateQua"); 
		return rest;
	}
	/**
	 * 查询用户手机号码套餐资源情况
	 * @param request
	 * @return
	 * @throws JSONException 
	 */
	@RequestMapping("/sFreeMinResourceQry")
	@ResponseBody
	public Map<String,String> sFreeMinResourceQryAlias(HttpServletRequest request) throws JSONException{
		Map<String,String> rest = new HashMap<String,String>();
		
		Map<String,String> pars = new HashMap<String,String>();

		String at = (String)request.getSession().getAttribute("access_token");
		String ph = (String)request.getSession().getAttribute("phone_no");
		pars.put("app_id", app_id);
		pars.put("timestamp", gettime());
		pars.put("access_token", at);
		if(StringUtils.isBlank(ph))
//			ph="15181244547";
		ph="15181244547";
		pars.put("phone_no", ph);
		setStatus(pars);
		setEndSeller(pars);
		pars.put("version", "1.0");
		pars.put("method", "sFreeMinResourceQry");
		pars.put("year_month", "201411");
		
//		pars.put("alias",alias);
		
		rest = this.doPostSign(null, pars, "/auth/api/sFreeMinResourceQry"); 
		return rest;
	}
	/**
	 * 查询用户套餐使用情况信息
	 * @param request
	 * @return
	 * @throws JSONException 
	 */
	@RequestMapping("/sFreeMinUseredQry")
	@ResponseBody
	public Map<String,String> sFreeMinUseredQryAlias(HttpServletRequest request) throws JSONException{
		Map<String,String> rest = new HashMap<String,String>();
		
		
		Map<String,String> pars = new HashMap<String,String>();

		String at = (String)request.getSession().getAttribute("access_token");
		String ph = (String)request.getSession().getAttribute("phone_no");
		pars.put("app_id", app_id);
		pars.put("timestamp", gettime());
		pars.put("access_token", at);
		if(StringUtils.isBlank(ph))
//			ph="15181244547";
		ph="15181244547";
		pars.put("phone_no", ph);
		setStatus(pars);
		setEndSeller(pars);
		pars.put("version", "1.0");
		pars.put("method", "sFreeMinUseredQry");
		
		pars.put("year_month", "201411");
		
//		pars.put("alias",alias);
		rest = this.doPostSign(null, pars, "/auth/api/sFreeMinUseredQry"); 
		return rest;
	}
	
	
	
	
	/**
	 * 随机码下发
	 * @param request
	 * @return
	 */
	@RequestMapping("/getSmsCode")
	@ResponseBody
	public Map<String,String> getSmsCode(HttpServletRequest request){
		Map<String,String> rest = new HashMap<String,String>();
		Map<String,String> pars = this.getSysPars(request, "sQryRandPass");
		
		rest = this.doPostSign(null, pars, "/auth/api/sQryRandPass"); 
//		rest = this.doGetSign(null, pars, "/auth/api/sQryRandPass"); 
		return rest;
	}

	/**
	 * 随机码下发
	 * @param request
	 * @return
	 */
	@RequestMapping("/getSmsCodeAlias")
	@ResponseBody
	public Map<String,String> getSmsCodeAlias(HttpServletRequest request){
		Map<String,String> rest = new HashMap<String,String>();
		Map<String,String> pars = this.getSysPars(request, "sQryRandPass");
		pars.put("alias",alias);
		
		rest = this.doPostSign(null, pars, "/auth/api/sQryRandPass"); 
//		rest = this.doGetSign(null, pars, "/auth/api/sQryRandPass"); 
		return rest;
	}
	
	
	/**生成合约接口参数
	 * @param request
	 * @param method
	 * @return
	 */
	private Map<String,String> getContractPars(HttpServletRequest request,String method){
		Map<String,String> pars = new HashMap<String,String>();

		String ph = (String)request.getSession().getAttribute("phone_no");

		pars.put("app_id", app_id);
		pars.put("timestamp", gettime());
		pars.put("app_key", app_key);
		if(StringUtils.isBlank(ph))
//			ph="15181244547";
		ph="15181244547";
		pars.put("phone_no", ph);
		pars.put("batch_no", "2A1410141000610588");
		pars.put("version", "1.0");
		setStatus(pars);
		setEndSeller(pars);
		pars.put("method", method);
		return pars;
	}
	
	/**
	 * 合约验证
	 * @param request
	 * @return
	 * @throws JSONException 
	 */
	@RequestMapping("/contract")
	@ResponseBody
	public Map<String,String> contract(HttpServletRequest request) throws JSONException{
		Map<String,String> rest = new HashMap<String,String>();
		Map<String,String> pars = this.getContractPars(request, "sMarketTermiChk");
		
		rest = this.doPostSign(null, pars, "/auth/contract/sMarketTermiChk"); 
         log.info("返回rest=="+rest);
         String res_code="";
         String res_msg="";
         String result="";
         res_code=(String) rest.get("res_code");
         res_msg=(String) rest.get("res_code");
         result=rest.get("result");
         Map res=com.sunrise.base.util.JsonHelper.toMap(result);
         log.info("返回业务数据resutl=="+res);
         log.info("返回业务数据check_flag=="+res.get("check_flag"));
         log.info("返回业务数据detail_msg=="+res.get("detail_msg"));
//		rest = this.doGetSign(null, pars, "/auth/contract/sMarketTermiChk"); 
		return rest;
	}
	/**
	 * 合约验证
	 * @param request
	 * @return
	 *//*
	 *合约验证是没有绑定alias的
	@RequestMapping("/contractAlias")
	@ResponseBody
	public Map<String,String> contractAlias(HttpServletRequest request){
		Map<String,String> rest = new HashMap<String,String>();
		Map<String,String> pars = this.getContractPars(request, "sMarketTermiChk");
		pars.put("alias", alias);
		rest = this.doPostSign(null, pars, "/auth/contract/sMarketTermiChk"); 
		return rest;
	}
	*/

	/**
	 * 对带有sign参数的接口访问请调用这个接口
	 * @param privateKey
	 * @param parameters
	 * @param method 接口方法 比如流量查询业务：/auth/api/sFreeMinQry
	 * @return
	 */
	private Map<String,String> doGetSign(String pk,Map<String, String> parameters,String method){
		pk=privateKey;
	  	String urlWithSign=getUrl(pk, parameters, method);
		
		String result=	HttpClientTool.doGetUrl(urlWithSign, "utf-8");
		log.info("result===="+result);
		//参数解析
		Map<String,String> rest=new HashMap<String, String>();
		 try {
			rest= com.sunrise.base.util.JsonHelper.toMap(result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("doGetSign请求出错："+e);
		}
		 return rest;
	}
	/**
	 * 空充查询接口
	 * @date 2015年2月6日 下午3:47:33
	 * @auth wenb
	 */
	public static void kcQurtest(){
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("app_id", "10000188");
		paraMap.put("phone_no", "13717500617");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time= sdf.format(new Date());
		paraMap.put("timestamp","20150210085451");
		paraMap.put("version", "1.0");
//		paraMap.put("method", "sAgtFeeQry");
		paraMap.put("method", "sQryRandPassWithoutToken");
//		  paraMap.put("end_seller", "sAgtFeeQry");
//		paraMap.put("command", "ZNMXCX");
		paraMap.put("app_key", "2fdc349190f91eed30587084816af53b");
		paraMap.put("status", "1");
		paraMap.put("end_seller", "1");
		
		String pk="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKubuG7w+AG0skVZ3JqQM8VfNlyL8q5UYUecdauAt6Wi6JM+czJxRYt8qZET6HWvTsDAyZF2dy6doCUfFwcdpwrfQkOdAfInJFOl6360Ejm8lQoWqqxqGZLJgWw2xm9z1vBWqe5hZ3BhcTSce/wOs8mmY67VTJhVyuvspjQ2YEK3AgMBAAECgYBm5XgxDUunujEqZDdidhSUxAJUe5WH8hBiX+4uZ3+2UXgi0c9A76pDePwzgTxhVDxkVzSFhALycJEVsQ6dtanyXViERyx92kUAYPJfUNKyWCeVeu5cLKt6lFELWgHDeYKCyYPHAfAuv165Q9phsCDPTFsBUXRnDN9u4HNuhbPIAQJBAO9ffzeU8SvUpLmf297v7t5D+UKpo+zxM8NJKYwzblEuoZv2y0Iaj5zFXkK9WjRXWSMIDGYVJor+sgus3V5trmkCQQC3hz0aDyj2tNNdyfdzPp+lBvhrvpbn2BA5bWwUZB2EmVJnRPU4ynnkRVGJ+0K4/WGgRJ0kPBRjxZUwYroemoQfAkEA6WxSfjz7wSYnS+wbWJbYdF4Mn3kVpWTAeVrK7TDqDrhOV+yd4ORNzy6X8LT9VbUn8wVSMenGIBymOsRjSkXioQJABVzbSTamMMdJ4dSz+VccHRteCO/xwuaKWtM6tEHUpvo/8SqPq9AmzafE4S5JXdRxlNhMazGiOuQcHuTToW9MawJAMrrNEw9pzuWjYeiho5yXk61Ok19dJX+zcDNn0J2LIWlLuv3lpSEtzZCpcK6YLS3s2cHdw7OmoBmSxokDA6S6lA==";
		String postParameter=com.sunrise.base.signUtil.SignUtil.mapToSignForFX(pk, paraMap);
		System.out.println(postParameter);
		//获取sign值
		String[] str=	postParameter.split("=");
		String sign=str[str.length-1];
		paraMap.put("sign", sign);
		System.out.println("sign="+sign);
	}
	/**
	 * 空充订购接口
	 * @date 2015年2月6日 下午3:48:10
	 * @auth wenb
	 */
	public static void kcDgtest(){
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("app_id", "10000188");
		paraMap.put("phone_no", "18382438242");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time= sdf.format(new Date());
		paraMap.put("timestamp",time);
		paraMap.put("version", "1.0");
		paraMap.put("method", "sAgtFeeAdd");
//		  paraMap.put("end_seller", "sAgtFeeQry");
		paraMap.put("app_key", "2fdc349190f91eed30587084816af53b");
		
		
//		paraMap.put("password", "9010100011");
		paraMap.put("password", "11111111");
		paraMap.put("feesum", "10");
		paraMap.put("mobile", "13488913949");
		//
		
		String pk="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKubuG7w+AG0skVZ3JqQM8VfNlyL8q5UYUecdauAt6Wi6JM+czJxRYt8qZET6HWvTsDAyZF2dy6doCUfFwcdpwrfQkOdAfInJFOl6360Ejm8lQoWqqxqGZLJgWw2xm9z1vBWqe5hZ3BhcTSce/wOs8mmY67VTJhVyuvspjQ2YEK3AgMBAAECgYBm5XgxDUunujEqZDdidhSUxAJUe5WH8hBiX+4uZ3+2UXgi0c9A76pDePwzgTxhVDxkVzSFhALycJEVsQ6dtanyXViERyx92kUAYPJfUNKyWCeVeu5cLKt6lFELWgHDeYKCyYPHAfAuv165Q9phsCDPTFsBUXRnDN9u4HNuhbPIAQJBAO9ffzeU8SvUpLmf297v7t5D+UKpo+zxM8NJKYwzblEuoZv2y0Iaj5zFXkK9WjRXWSMIDGYVJor+sgus3V5trmkCQQC3hz0aDyj2tNNdyfdzPp+lBvhrvpbn2BA5bWwUZB2EmVJnRPU4ynnkRVGJ+0K4/WGgRJ0kPBRjxZUwYroemoQfAkEA6WxSfjz7wSYnS+wbWJbYdF4Mn3kVpWTAeVrK7TDqDrhOV+yd4ORNzy6X8LT9VbUn8wVSMenGIBymOsRjSkXioQJABVzbSTamMMdJ4dSz+VccHRteCO/xwuaKWtM6tEHUpvo/8SqPq9AmzafE4S5JXdRxlNhMazGiOuQcHuTToW9MawJAMrrNEw9pzuWjYeiho5yXk61Ok19dJX+zcDNn0J2LIWlLuv3lpSEtzZCpcK6YLS3s2cHdw7OmoBmSxokDA6S6lA==";
		String postParameter=com.sunrise.base.signUtil.SignUtil.mapToSignForFX(pk, paraMap);
		//获取sign值
		String[] str=	postParameter.split("=");
		String sign=str[str.length-1];
		paraMap.put("sign", sign);
		
		
		String url="http://218.205.252.26:18051/openApi" + "/auth/contract/sAgtFeeAdd";
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
		
	}

	
	
	/**
	 * 没有token的流量订购
	 * @date 2015年2月6日 下午6:22:57
	 * @auth wenb
	 */
	@RequestMapping("/dgllNoToken")
	@ResponseBody
	public Map<String,String> dgllNoToken(HttpServletRequest request) throws JSONException{
		//rest.put("state", "订购套餐成功");
//		Map<String,String> pars = this.getSysPars(request, "sShortAddMode");
		Map<String,String> pars = new HashMap<String,String>();

		pars.put("app_id", app_id);
		pars.put("timestamp", gettime());
		String ph="15181244547";
		pars.put("phone_no", ph);
		setStatus(pars);
		setEndSeller(pars);
		pars.put("version", "1.0");
		pars.put("method", "sShortAddModeWithoutToken");
		
		pars.put("app_key", "2fdc349190f91eed30587084816af53b");
		
//		pars.put("prod_prcid", "300021");
//		pars.put("prod_prcid", "300037");
		
		pars.put("prod_prcid", "300153");//本地环境测试
//		pars.put("prod_prcid", "300112");
		pars.put("sms_pwd", "209397");
//		pars.put("alias", alias);meiyoubieming
		
		
		String pk="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKubuG7w+AG0skVZ3JqQM8VfNlyL8q5UYUecdauAt6Wi6JM+czJxRYt8qZET6HWvTsDAyZF2dy6doCUfFwcdpwrfQkOdAfInJFOl6360Ejm8lQoWqqxqGZLJgWw2xm9z1vBWqe5hZ3BhcTSce/wOs8mmY67VTJhVyuvspjQ2YEK3AgMBAAECgYBm5XgxDUunujEqZDdidhSUxAJUe5WH8hBiX+4uZ3+2UXgi0c9A76pDePwzgTxhVDxkVzSFhALycJEVsQ6dtanyXViERyx92kUAYPJfUNKyWCeVeu5cLKt6lFELWgHDeYKCyYPHAfAuv165Q9phsCDPTFsBUXRnDN9u4HNuhbPIAQJBAO9ffzeU8SvUpLmf297v7t5D+UKpo+zxM8NJKYwzblEuoZv2y0Iaj5zFXkK9WjRXWSMIDGYVJor+sgus3V5trmkCQQC3hz0aDyj2tNNdyfdzPp+lBvhrvpbn2BA5bWwUZB2EmVJnRPU4ynnkRVGJ+0K4/WGgRJ0kPBRjxZUwYroemoQfAkEA6WxSfjz7wSYnS+wbWJbYdF4Mn3kVpWTAeVrK7TDqDrhOV+yd4ORNzy6X8LT9VbUn8wVSMenGIBymOsRjSkXioQJABVzbSTamMMdJ4dSz+VccHRteCO/xwuaKWtM6tEHUpvo/8SqPq9AmzafE4S5JXdRxlNhMazGiOuQcHuTToW9MawJAMrrNEw9pzuWjYeiho5yXk61Ok19dJX+zcDNn0J2LIWlLuv3lpSEtzZCpcK6YLS3s2cHdw7OmoBmSxokDA6S6lA==";
		String postParameter=com.sunrise.base.signUtil.SignUtil.mapToSignForFX(pk, pars);
		//获取sign值
		String[] str=	postParameter.split("=");
		String sign=str[str.length-1];
		pars.put("sign", sign);
		
		
		String url="http://218.205.252.26:18051/openApi" + "/auth/contract/sShortAddModeWithoutToken";
		String result=HttpClientTool.doPost(url, pars);
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
	 * 下发随机码没有token
	 * @date 2015年2月6日 下午6:26:19
	 * @auth wenb
	 */
	@RequestMapping("/getSmsCodeNoToken")
	@ResponseBody
	public Map<String,String> getSmsCodeNoToken(HttpServletRequest request){
		Map<String,String> pars = new HashMap<String,String>();

		pars.put("app_id", app_id);
		pars.put("timestamp", gettime());
		String ph="15181244547";
		pars.put("phone_no", ph);
		setStatus(pars);
		setEndSeller(pars);
		pars.put("version", "1.0");
		pars.put("method", "sQryRandPassWithoutToken");
		pars.put("app_key", "2fdc349190f91eed30587084816af53b");
		
		String pk="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKubuG7w+AG0skVZ3JqQM8VfNlyL8q5UYUecdauAt6Wi6JM+czJxRYt8qZET6HWvTsDAyZF2dy6doCUfFwcdpwrfQkOdAfInJFOl6360Ejm8lQoWqqxqGZLJgWw2xm9z1vBWqe5hZ3BhcTSce/wOs8mmY67VTJhVyuvspjQ2YEK3AgMBAAECgYBm5XgxDUunujEqZDdidhSUxAJUe5WH8hBiX+4uZ3+2UXgi0c9A76pDePwzgTxhVDxkVzSFhALycJEVsQ6dtanyXViERyx92kUAYPJfUNKyWCeVeu5cLKt6lFELWgHDeYKCyYPHAfAuv165Q9phsCDPTFsBUXRnDN9u4HNuhbPIAQJBAO9ffzeU8SvUpLmf297v7t5D+UKpo+zxM8NJKYwzblEuoZv2y0Iaj5zFXkK9WjRXWSMIDGYVJor+sgus3V5trmkCQQC3hz0aDyj2tNNdyfdzPp+lBvhrvpbn2BA5bWwUZB2EmVJnRPU4ynnkRVGJ+0K4/WGgRJ0kPBRjxZUwYroemoQfAkEA6WxSfjz7wSYnS+wbWJbYdF4Mn3kVpWTAeVrK7TDqDrhOV+yd4ORNzy6X8LT9VbUn8wVSMenGIBymOsRjSkXioQJABVzbSTamMMdJ4dSz+VccHRteCO/xwuaKWtM6tEHUpvo/8SqPq9AmzafE4S5JXdRxlNhMazGiOuQcHuTToW9MawJAMrrNEw9pzuWjYeiho5yXk61Ok19dJX+zcDNn0J2LIWlLuv3lpSEtzZCpcK6YLS3s2cHdw7OmoBmSxokDA6S6lA==";
		String postParameter=com.sunrise.base.signUtil.SignUtil.mapToSignForFX(pk, pars);
		//获取sign值
		String[] str=	postParameter.split("=");
		String sign=str[str.length-1];
		pars.put("sign", sign);
		
		
		String url="http://218.205.252.26:18051/openApi" + "/auth/contract/sQryRandPassWithoutToken";
		String result=HttpClientTool.doPost(url, pars);
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
	 * post带sign的请求
	 * @param pk 分销商的私钥
	 * @param parameters 请求参数map
	 * @param method  请求的方法
	 * @return
	 */
	private Map<String,String> doPostSign(String pk,Map<String, String> parameters,String method){
		pk=privateKey;
	  	String postParameter=getPostUrl(pk, parameters, method);
	  	//获取sign值
	  	String[] str=	postParameter.split("=");
	  	System.out.println("3333333"+postParameter);
	  	String sign=str[str.length-1];
	  	System.out.println("4444"+sign);
	  	parameters.put("sign", sign);
	  	
	  	System.out.println(parameters);
	  	
		String url=httpUrl + method;
		log.info("请求url================="+url);
		log.info("请求parameters================="+parameters);
		String result=HttpClientTool.doPost(url, parameters);
		log.info("result==444444=="+result);
		//参数解析
		Map<String,String> rest=new HashMap<String, String>();
		 try {
			rest= com.sunrise.base.util.JsonHelper.toMap(result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("返回参数toMap解析出错："+e);
		}
		 return rest;
	}
	/**
	 * 
	 * @param mapPara 参数map
	 * @param method 调用接口方法 比如：/auth/api/sFreeMinQry
	 * @return
	 */
	private Map<String,String> doGetNoSign(Map<String,String> mapPara,String method){
		//组装参数
		Iterator<String> it = mapPara.keySet().iterator();
		List<String> list = new ArrayList<String>();
		StringBuffer sb=new StringBuffer();
		String key;
		while(it.hasNext()){
    		key = (String) it.next();
    		list.add(key+"="+mapPara.get(key)+"&");
    	}
		for(String s : list){
    		sb.append(s);
    	}
		sb.delete(sb.length()-1, sb.length());
		System.out.println("参数拼接后为："+sb.toString());
		String urlWithSign =httpUrl+method+"?"+sb.toString();
		System.out.println("urlat="+urlWithSign);
		String result=	HttpClientTool.doGetUrl(urlWithSign, "utf-8");
		//参数解析
		Map<String,String> rest=new HashMap<String, String>();
		 try {
			rest= com.sunrise.base.util.JsonHelper.toMap(result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("doGetNoSign请求出错："+e);
		}
		 return rest;
	}
	/**
	 * 需要sign参数的请调用这个方法
	 * @param privateKey
	 * @param parameters
	 * @param method 
	 * @return
	 */
	private String getUrl(String pk,Map<String, String> parameters,String method){
		log.debug("privateKey==="+pk);
		log.debug("parameters==="+parameters);
		log.debug("method===="+method);
		String returnpars=com.sunrise.base.signUtil.SignUtil.mapToSignForFX(pk, parameters);
		
		System.out.println();
		log.debug("returnpars===="+returnpars);
		
		
		String url = httpUrl + method +"?" + returnpars;
		
		log.debug("url===="+url);
		return url;
	}
	/**
	 * 
	 * post请求的参数拼接
	 * @param pk
	 * @param parameters
	 * @param method
	 * @return
	 */
	private String getPostUrl(String pk,Map<String, String> parameters,String method){
		log.debug("privateKey==="+pk);
		log.debug("parameters==="+parameters);
		log.debug("method===="+method);
		String returnpars=com.sunrise.base.signUtil.SignUtil.mapToSignForFX(pk, parameters);
		
		System.out.println();
		log.debug("returnpars===="+returnpars);
		
		return returnpars;
	}

	private String gettime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}

	
	/**生成系统参数
	 * @param request
	 * @param method
	 * @return
	 */
	private Map<String,String> getSysPars(HttpServletRequest request,String method){
		Map<String,String> pars = new HashMap<String,String>();

		String at = (String)request.getSession().getAttribute("access_token");
		String ph = (String)request.getSession().getAttribute("phone_no");
		pars.put("app_id", app_id);
		pars.put("timestamp", gettime());
		pars.put("access_token", at);
		if(StringUtils.isBlank(ph))
//			ph="15181244547";
		ph="15181244547";
		pars.put("phone_no", ph);
		setStatus(pars);
		setEndSeller(pars);
		pars.put("version", "1.0");
		pars.put("method", method);
		return pars;
	}
	
	
}
