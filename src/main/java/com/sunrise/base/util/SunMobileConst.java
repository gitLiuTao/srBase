package com.sunrise.base.util;

/**
 * 
 */

/**
 * @author Guocf
 * 日出移动项目系统常量
 */
public final class SunMobileConst {

	/**
	 * 成功或失败标记
	 */
	public static final String BOOLEAN_SUCCESS="1";  //成功
	public static final String BOOLEAN_FAIL="0";     //失败
	
	
	/**
	 * 是否涉密
	 */
	public static final String SECRET_YES="1";       //涉密
	public static final String SECRET_NOT="0";       //不涉密
	
	/*
	 * 下划线分割符号
	 */
	public static final String SPLITER_LOWER_LINE="_";
	/**
	 * 中划线分割符号
	 */
	public static final String SPLITER_MIDDLE_LINE="-";
	
	/**
	 * 英文逗号
	 */
	public static final String SPLITER_COMMA_EN=",";
	/**
	 * 中文逗号
	 */
	public static final String SPLITER_COMMA_CN=",";
	
	
//	/**
//	 * 部署状态
//	 */
//	public static final String DEPLOY_STATE=SysParamBean.getParamValue("publish.state");
//	
//	//注册码位数
//	public static final int RIGIST_CODE_DIGIT=Integer.parseInt(SysParamBean.getParamValue("regist.checkcode.digit"));
	
	//调试状态
	public static final String DEPLOY_IS_DEBUG="1";
	
	/**
	 * 系统当前的字符集
	 */
	public static final String CUR_CHARSET="utf-8";     
	
	/**
	 * Classes的根路径
	 * 以‘/’结尾
	 */
	//public static final String ROOT_CLASSES=OSChecker.CLASS_PATH;
	
	/**
	 * 安全登录Key和Token
	 */
//	public static final String SECURE_KEY=SysParamBean.getParamValue("security.key");
//	public static final String SECURE_TOKEN=SysParamBean.getParamValue("security.token");
	
	/**
	 * MapABC的参数－LicenseKey
	 */
//	public static final String MAP_ABC_KEY=SysParamBean.getParamValue("mapadb.key");
//	//http://search1.mapabc.com/sisserver?
//	public static final String MAP_ABC_WWW=SysParamBean.getParamValue("mapadb.www");
	
	
	/**
	 * SOA的API项目的网址
	 */
//	public static final String SOA_OPEN_API_IP=SysParamBean.getParamValue("soa.open.api.ip");
//	
//	
//	/**
//	 * 文章地理名称提取方式
//	 */
//	public static final String GEONAME_FETCH_METHOD=SysParamBean.getParamValue("geoname.fetch.method");
//	
//	/**
//	 * 发布状态
//	 */
//	public static final String PUBLISH_STATE=SysParamBean.getParamValue("PetApp.publish.state");
	
	/**
	 * 直接调用我们自己的API
	 */
	public static final String GEONAME_FETCH_OUR_API="1";
	/**
	 * 调用上海交通大学的WebService接口
	 */
	public static final String GEONAME_FETCH_WEB_SERVICE="2";
	
	
	/**
	 * EJB对象访问类型
	 * 
	 */
	//public static final String EJB_OBJECT_TYPE=SysParamBean.getParamValue("ejb.object.type");
	//远程方式访问
	public static final String EJB_REMOTE="0";
	//本地方式访问
	public static final String EJB_LOCAL="1";
	
	
	//Object.class.getResource("").getPath();
	/**
	 * 上海交通大学相关参数
	 * CRFPP动态链接库名称
	 */
//	public static final String JTTU_CRFPP_DLL_WINDOWS=SysParamBean.getParamValue("sjtu.crfpp.dll.windows"); 
//	public static final String JTTU_CRFPP_DLL_LINUX=SysParamBean.getParamValue("sjtu.crfpp.dll.linux"); 
//	public static final String JTTU_CRFPP_MODEL_NAME=SysParamBean.getParamValue("sjtu.crfpp.model.name"); 
	
	public static final String JTTU_MODEL_PATH="JTTU_MODEL_PATH"; 
	
	
	/**
	 * 词典后缀名称
	 * 该文件必须放在cfg/sjtu/Dictionary目录下
	 */
	//public static final String JTTU_DICT_SUFFIX=SysParamBean.getParamValue("sjtu.dictionary.suffix");
	
	 public static final String  BOOLEAN_FALSE = "0";          							//字符串false
	 public static final String  BOOLEAN_TRUE = "1";           							//字符串true
	   
	    
	public static final String  SPLITE_CHAR = "/";            							//字符分割符
    public static final String  SPLITE_STRING = "=>";         							//字符串分割符
    public static final String  SPLITE_STRING2 = "<===>";         						//字符串分割符
  
    /**
     * 数据类型标识
     */
    public static final String  DATA_TYPE_VARCHAR = "1";                                //字符型
    public static final String  DATA_TYPE_INT = "2";                                    //整数型
    public static final String  DATA_TYPE_WITH_FLOAT = "3";                             //带精度的数字型
    public static final String  DATA_TYPE_DATE_TIME = "4";                              //日期时间型 可以精确到时分秒
    public static final String  DATA_TYPE_CLOB = "5";                                   //大文本型
    public static final String  DATA_TYPE_DATE = "6";                              		//日期型
    
    
    /**
	  * 009dog应用程序标识
	  */ 
	 public static final String APP_009_DOG="1";
	 /**
	  * 009Lite应用程序标识，009的精简版
	  */
	 public static final String APP_009_LITE="2";
	 
	 /**
	  * 赛SIRI应用
	  */
	 public static final String APP_PK_SIRI="3";
	 
	 /**
	  * 009动漫
	  */
	 public static final String APP_009_FLASH="4";
	 
	 /**
	  * 009的3D应用
	  */
	 public static final String APP_009_3D="5";
	 
	 
     public static void initData(){
//    	System.out.println("ROOT_CLASSES==>"+SunChinaConst.ROOT_CLASSES);
//    	System.out.println("JTTU_CRFPP_DLL==>"+SunChinaConst.JTTU_CRFPP_DLL_WINDOWS);
//    	System.out.println("JTTU_DICT_SUFFIX==>"+SunChinaConst.JTTU_DICT_SUFFIX);
    }
     
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

	}
	
	

}
