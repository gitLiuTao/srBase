package com.sunrise.base.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SystemConstants {
	
	public static final String BASEGROUP = "基地用户";
	public static final String PROVINCEGROUP = "分省用户";
	public static final String BIGAREAGROUP = "大区用户";
	public static final String CITYGROUP = "地市用户";
	
	public static final String DATEFORMAT_DAY = "day";
	public static final String DATEFORMAT_MONTH = "month";
	public static final String DATEFORMAT_WEEK = "week";
	public static final String DATEFORMAT_YEAR = "year";
	
	public static final String CONTEXTPATH = "html\\portlet\\scjd\\resource\\";
	public static final String XLSX = ".xlsx";
	public static final String XLS = ".xls";
	public static final String APPLICATIONEXCEL = "application/vnd.ms-excel";
	public static final String UTF = "UTF-8";
	public static final String APPLICATIONEXCELUTF = "application/vnd.ms-excel;charset=UTF-8";
	
	
	public static final String MK_RPT = "mkAnalys";// 营销报表路径
	public static final String PROVINCE_RPT = "provinecAnalys";// 分省分析报表路径

	/**
	 * 业务分析
	 */
	public static final String businessAnalys = "businessAnalys";// 业务总览

	public static final String mainBusinessReceivedAnalysis = "mainBusinessReceivedAnalysis";// 主要业务量收分析

	public static final String CRBTBusinessAnalysis = "CRBTBusinessAnalysis";// 彩铃业务分析

	public static final String pureCRBTBusinessAnalysis = "pureCRBTBusinessAnalysis";// 纯彩铃业务分析

	public static final String ringBoxBusinessAnalysis = "ringBoxBusinessAnalysis";// 铃音盒业务分析

	public static final String ringingBusinessAnalysis = "ringingBusinessAnalysis";// 振铃业务分析

	/**
	 * 营销分析
	 */
	public static final String marktAnalysisView = "marktAnalysisView";// 营销分析总览

	public static final String marktAnalysisEdit = "marktAnalysisEdit";// 创建营销活动

	public static final String marktAnalysisList = "marktAnalysisList";// 营销活动查询

	public static final String marktAnalysisAnalyze = "marktAnalysisAnalyze";// 营销活动分析

	public static final String marktAnalysisReport = "marktAnalysisReport";// 新歌营销报表
	
	/**
	 * 后五个模块产品业务类型常量定义
	 */
	/**
	 * 彩振全
	 */
	public static final String productcza = "1";
	/**
	 * 彩铃全部
	 */
	public static final String productcring = "2";
	/**
	 * 纯彩铃
	 */
	public static final String productpcring = "3";
	/**
	 * 铃音盒
	 */
	public static final String productcringbox = "4";
	/**
	 * 振铃
	 */
	public static final String productzring = "5";
	/**
	 * 歌曲下载全部
	 */
	public static final String productdsall = "6";
	/**
	 * 歌曲下载按次
	 */
	public static final String productdbynum = "7";
	/**
	 * 歌曲下载包月
	 */
	public static final String productbympay = "8";
	/**
	 * 歌曲下载包月五元
	 */
	public static final String productbympayf = "9";
	/**
	 * 歌曲下载包月十元
	 */
	public static final String productbympayt = "10";
	
	/**
	 * cognos报表日期类型周成报表专用：日
	 */
	public static final String cognosDateTypeDay = "Day";
	/**
	 * cognos报表日期类型周成报表专用：月
	 */
	public static final String cognosDateTypeMonth = "Month";
	/**
	 * cognos报表日期类型周成报表专用：周
	 */
	public static final String cognosDateTypeWeek = "Week";
	/**
	 * cognos报表日期类型周成报表专用：年
	 */
	public static final String cognosDateTypeYear = "Year";
	/**
	 * 歌曲批量下载任务状态
	 */
	public static final String SONG_BATCH_TASK_STATUS = "700";
	/**
	 * 歌曲批量下载任务 - 待处理
	 */
	public static final String SONG_BATCH_SEARCH_TASK_STATE_NEW = "701";
	/**
	 * 歌曲批量下载任务 - 处理中
	 */
	public static final String SONG_BATCH_SEARCH_TASK_STATE_ING = "702";
	/**
	 * 歌曲批量下载任务 - 已处理
	 */
	public static final String SONG_BATCH_SEARCH_TASK_STATE_DONE = "703";
	/**
	 * 歌曲批量下载任务 - 处理失败
	 */
	public static final String SONG_BATCH_SEARCH_TASK_STATE_ERROR = "704";
	/**
	 * 歌曲批量下载任务 - 已取消
	 */
	public static final String SONG_BATCH_SEARCH_TASK_STATE_CANCEL = "705";
	/**
	 * 统计时间类型 - 按天
	 */
	public static final String SEARCH_DATETYPE_DAY = "day";
	/**
	 * 统计时间类型 - 按周
	 */
	public static final String SEARCH_DATETYPE_WEEK = "week";
	/**
	 * 统计时间类型 - 按月
	 */
	public static final String SEARCH_DATETYPE_MONTH = "month";
	/**
	 * 统计时间类型 - 按年
	 */
	public static final String SEARCH_DATETYPE_YEAR = "year";
	/**
	 * 统计时间类型 - 累计
	 */
	public static final String SEARCH_DATETYPE_LEIJI = "leiji";
	
	/**
	 * 榜单类型 - 歌手
	 */
	public static final String ORDER_TYPE_SINGER = "singer";
	
	/**
	 * 榜单类型 - 歌曲
	 */
	public static final String ORDER_TYPE_SONG = "song";

	/**
	 * 歌曲批量下载统计条件匹配类型 - 匹配版权ID
	 */
	public static final String SONG_BATCH_SEARCH_TASK_SEARCH_CONDITIONTYPE_C = "1";
	/**
	 * 歌曲批量下载统计条件匹配类型 - 匹配彩振全ID
	 */
	public static final String SONG_BATCH_SEARCH_TASK_SEARCH_conditionType_CZQ = "2";
	/**
	 * 歌曲批量下载统计条件匹配类型 - 匹配歌手名+歌曲名
	 */
	public static final String SONG_BATCH_SEARCH_TASK_SEARCH_conditionType_MM = "3";

	/**
	 * 记录状态 - 有效
	 */
	public static final Integer RECORD_STATE_1 = 1;
	/**
	 * 记录状态 - 无效
	 */
	public static final Integer RECORD_STATE_0 = 0;

	/**
	 * 合作类型
	 */
	public static final String COOPERATE_TYPE = "300";

	/**
	 * 渠道类型
	 */
	public static final String CHANNEL_TYPE = "1000";
	
	/**
	 * 渠道类型(方式1)
	 */
	public static final String CHANNEL_TYPE_1 = "2100";
	
	/**
	 * 渠道类型(方式2)
	 */
	public static final String CHANNEL_TYPE_2 = "2200";
	
	/**
	 * 渠道类型(方式3)
	 */
	public static final String CHANNEL_TYPE_3 = "2300";

	/**
	 * 计费类型
	 */
	public static final String FEE_TYPE = "400";
	/**
	 * 产品类型
	 */
	public static final String PRODECT_TYPE_NEW = "2400";
	/**
	 * 计费类型：全部
	 */
	public static final String FEE_TYPE_ALL ="0";
	/**
	 * 计费类型：收费
	 */
	public static final String FEE_TYPE_CHARGE = "2";
	/**
	 * 计费类型：免费
	 */
	public static final String FEE_TYPE_FREE ="1";
	/**
	 * 计费类型：其它
	 */
	public static final String FEE_TYPE_OTHER ="-1";
	/**
	 * 产品类型
	 */
	public static final String PRODECT_TYPE = "600";
	
	/**
	 * 销量榜产品类型
	 */
	public static final String ORDER_PRODECT_TYPE = "3400";
	
	/**
	 * 彩铃
	 */
	public static final String CRING_ID = "1";
	
	/**
	 * 振铃
	 */
	public static final String ZRING_ID = "2";
	
	
	/**
	 * 在线听 
	 */
	public static final String ONLINE_LISTEN_ID = "4";
	
	/**
	 * 纯彩铃产品ID
	 */
	public static final String P_CRING_ID = "1";
	/**
	 * 铃音盒
	 */
	public static final String P_CRING_BOX = "2";
	/**
	 * 铃音盒拆分
	 */
	public static final String P_CRING_BOX_SP = "3";
	/**
	 * 多媒体彩铃
	 */
	public static final String P_M_CRING = "4";
	
	/**
	 * 歌曲下载
	 */
	public static final String SONGDOWNLOAD_ID = "3";
	
	/**
	 * 歌曲下载按此
	 */
	public static final String SONGDOWNLOAD_CLICK = "6";
	/**
	 * 歌曲下载5元包月
	 */
	public static final String SONGDOWNLOAD_Monthly5 = "7";
	/**
	 * 歌曲下载10元包月
	 */
	public static final String SONGDOWNLOAD_Monthly10 = "8";
	/**
	 * 地域类型：全部(在数据库中处理时为空，此处的值为判别使用)
	 */
	public static final String REGIONTYPE_ALL = "all";
	/**
	 * 大区
	 */
	public static final String REGIONTYPE_BIGAREA = "bigArea";
	
	/**
	 * 省
	 */
	public static final String REGIONTYPE_PROVINCE = "province";
	
	/**
	 * 地市
	 */
	public static final String REGIONTYPE_AREA = "area";

	// add by yanmi begin
	/**
	 * 普通活动类型 101
	 */
	public static final String ACTIVE_TYPE_C = "101"; //
	/**
	 * 新歌活动类型 102
	 */
	public static final String ACTIVE_TYPE_N = "102"; //
	/**
	 * 维度因素 ALL
	 */
	public static final String DIM_ALL = "0"; //ALL
	/**
	 * 维度因素 N/A
	 */
	public static final String DIM_OTHER = "-1"; //N/A

	/**
	 * 版权是否过期 1 是
	 */
	public static final String IS_RESERVE_TYPE = "1500";

	/**
	 * 版权是否过期 1 是
	 */
	public static final String IS_RESERVE_1 = "1";
	/**
	 * 版权是否过期 0 否
	 */
	public static final String IS_RESERVE_0 = "0";
	/**
	 * 铃音盒类型 在sysdetailtype 中的编号
	 */
	public static final String RING_BOX_TYPE =  "1100";
	
	/**
	 * 用户类型 在sysdetailtype 中的编号
	 */
	public static final String USER_TYPE =  "500";
	
	/**
	 * 功能包月用户类型 在sysdetailtype 中的编号
	 */
	public static final String USER_TYPE_ORDER =  "505";
	
	/**
	 *版权状态 在sysdetailtype 中的编号
	 */
	public static final String COPY_RIGHT_STATUS =  "900";
	
	/**
	 * 地区excel 模版标识
	 */
	public static final String EXCEL_AREA_KPI = "地区kpi_template";
	
	/**
	 * 专辑excel 模版标识
	 */
	public static final String EXCEL_ALBUM_MUSIC = "产品ID(18位)albumMusic_template";
	
	/**
	 * WAP门户
	 */
	public static final String WAP_PORTAL = "2";
	
	/**
	 * PC客户端门户
	 */
	public static final String PC_PORTAL = "5";
	
	/**
	 * 歌曲下载业务分析——包月用户数
	 */
	public static final String PRODBUSI_ID_ALL="6";
	public static final String PRODBUSI_ID_PER="7";
	public static final String PRODBUSI_ID_MONTH_ALL="8";
	public static final String PRODBUSI_ID_MONTH_5="9";
	public static final String PRODBUSI_ID_MONTH_10="10";
	
	public static final String PRODBUSI_ID_ONLINE5="11";
	
	/**
	 * WWWW门户
	 */
	public static final String WWWW_TYPE = "www";
	
	/**
	 * WAP门户
	 */
	public static final String WAP_TYPE = "wap";
	
	/**
	 * SMS门户
	 */
	public static final String SMS_TYPE = "sms";
	
	/**
	 * SST门户
	 */
	public static final String SST_TYPE = "sst";
	
	/**
	 * PC门户
	 */
	public static final String PC_TYPE = "pc";
	
	/**
	 * IVR门户
	 */
	public static final String IVR_TYPE = "ivr";
	
	public static final String COGNOS_EXPORT_LABEL_DYXZBYYHLCQS = "当月新增包月用户留存趋势";
	public static final String COGNOS_EXPORT_LABEL_LJBYYHLCQS = "累计包月用户留存趋势";	 
	public static final String COGNOS_EXPORT_LABEL_QFZXTYH = "区分在线听用户";  
	public static final String COGNOS_EXPORT_LABEL_DLCSFC = "登录次数分层"; 
	public static final String COGNOS_EXPORT_LABEL_XZLXFC = "下载黏性分层";  
	public static final String COGNOS_EXPORT_LABEL_YHLXFC = "用户黏性分层"; 
	public static final String COGNOS_EXPORT_LABEL_FWLXFC = "访问黏性分层"; 
	public static final String COGNOS_EXPORT_LABEL_BDSCFC =  "拨打时长分层";  
	public static final String COGNOS_EXPORT_LABEL_SSCSFC =  "搜索次数分层"; 
	public static final String COGNOS_EXPORT_LABEL_STCSFC =  "试听次数分层";	
	public static final String COGNOS_EXPORT_LABEL_KTSCFC = "开通时长分层"; 
	public static final String COGNOS_EXPORT_LABEL_JZGXFC = "价值贡献分层"; 	
	public static final String COGNOS_EXPORT_LABEL_FWCSFC = "访问次数分层"; 
	public static final String COGNOS_EXPORT_LABEL_XZLFC = "下载量分层";
	public static final String COGNOS_EXPORT_LABEL_GMQLX = "歌迷群类型"; 
	public static final String COGNOS_EXPORT_LABEL_SJQS = "时间趋势";
	public static final String COGNOS_EXPORT_LABEL_YHLX = "用户类型";
	public static final String COGNOS_EXPORT_LABEL_FLYH = "分类用户";
	public static final String COGNOS_EXPORT_LABEL_YWLX = "业务类型";
	public static final String COGNOS_EXPORT_LABEL_JFLX = "计费类型";
	public static final String COGNOS_EXPORT_LABEL_CPLX = "产品类型";
	public static final String COGNOS_EXPORT_LABEL_DYFB = "地域分布";
	public static final String COGNOS_EXPORT_LABEL_QDFB = "渠道分布";
	public static final String COGNOS_EXPORT_LABEL_ZHLX = "账号类型";
	public static final String COGNOS_EXPORT_LABEL_DGFS =  "订购方式";	
	public static final String COGNOS_EXPORT_LABEL_CPMC =  "CP分析";  
	public static final String COGNOS_EXPORT_LABEL_SSNXFC =  "搜索黏性分层";  
	
	public static List<String> cognosLabel = new ArrayList<String>();
	static{		
		cognosLabel.add(COGNOS_EXPORT_LABEL_DYXZBYYHLCQS);	
		cognosLabel.add(COGNOS_EXPORT_LABEL_LJBYYHLCQS);
		cognosLabel.add(COGNOS_EXPORT_LABEL_QFZXTYH);
		cognosLabel.add(COGNOS_EXPORT_LABEL_FWCSFC);
		cognosLabel.add(COGNOS_EXPORT_LABEL_JZGXFC);
		cognosLabel.add(COGNOS_EXPORT_LABEL_KTSCFC);
		cognosLabel.add(COGNOS_EXPORT_LABEL_STCSFC);
		cognosLabel.add(COGNOS_EXPORT_LABEL_SSCSFC);
		cognosLabel.add(COGNOS_EXPORT_LABEL_BDSCFC);
		cognosLabel.add(COGNOS_EXPORT_LABEL_FWLXFC);
		cognosLabel.add(COGNOS_EXPORT_LABEL_YHLXFC);
		cognosLabel.add(COGNOS_EXPORT_LABEL_XZLXFC);
		cognosLabel.add(COGNOS_EXPORT_LABEL_DLCSFC);
		cognosLabel.add(COGNOS_EXPORT_LABEL_XZLFC);
		cognosLabel.add(COGNOS_EXPORT_LABEL_GMQLX);
		cognosLabel.add(COGNOS_EXPORT_LABEL_DGFS);
		cognosLabel.add(COGNOS_EXPORT_LABEL_ZHLX);
		cognosLabel.add(COGNOS_EXPORT_LABEL_QDFB);
		cognosLabel.add(COGNOS_EXPORT_LABEL_DYFB);
		cognosLabel.add(COGNOS_EXPORT_LABEL_CPLX);
		cognosLabel.add(COGNOS_EXPORT_LABEL_JFLX);
		cognosLabel.add(COGNOS_EXPORT_LABEL_YWLX);
		cognosLabel.add(COGNOS_EXPORT_LABEL_FLYH);
		cognosLabel.add(COGNOS_EXPORT_LABEL_YHLX);
		cognosLabel.add(COGNOS_EXPORT_LABEL_SJQS);
		cognosLabel.add(COGNOS_EXPORT_LABEL_CPMC);
		cognosLabel.add(COGNOS_EXPORT_LABEL_SSNXFC);
		
	}
	
	public static final String COGNOS_EXPORT_VALUELABEL_XZBYYHSYXZL = "新增包月用户使用下载量";
	public static final String COGNOS_EXPORT_VALUELABEL_YGYJZGXYHS = "1个月价值贡献用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_SGYJZGXYHS = "3个月价值贡献用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_LGYJZGXYHS = "6个月价值贡献用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_XZBYSYYHS = "新增包月使用用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_XZBYSYXZL = "新增包月使用下载量";
	public static final String COGNOS_EXPORT_VALUELABEL_YGYCMYHS = "1个月沉默用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_SGYCMYHS = "3个月沉默用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_LGYCMYHS = "6个月沉默用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_YGYDJYHS = "1个月冻结用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_SGYDJYHS = "3个月冻结用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_LGYDJYHS = "6个月冻结用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_YGYXZYHS = "1个月新增用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_SGYXZYHS = "3个月新增用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_LGYXZYHS = "6个月新增用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_YGYHYYHS = "1个月活跃用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_SGYHYYHS = "3个月活跃用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_LGYHYYHS = "6个月活跃用户数";	
	public static final String COGNOS_EXPORT_VALUELABEL_ZTXZYHS = "整体下载用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_SFXZYHS = "收费下载用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_HYBYYHS = "会员使用用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_BYSYYHS = "包月使用用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_BYSYXZL = "包月使用下载量";
	public static final String COGNOS_EXPORT_VALUELABEL_LJNYYHS = "累计包月用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_LJXZYHS = "累计下载用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_LJXXFSR = "累计信息费收入";
	public static final String COGNOS_EXPORT_VALUELABEL_XZBYYHS = "新增包月用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_FWXZZHL = "访问下载转化率";
	public static final String COGNOS_EXPORT_VALUELABEL_HYDDYHS = "会员(到达)用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_GMQYHS = "歌迷群用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_BYYHHX = "包月用户活性";
	public static final String COGNOS_EXPORT_VALUELABEL_ZXZYHS = "总下载用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_ZXZLSF = "总下载量收费";
	public static final String COGNOS_EXPORT_VALUELABEL_XZLZSR = "下载量总收入";	
	public static final String COGNOS_EXPORT_VALUELABEL_GMQXZL = "歌迷群下载量";
	public static final String COGNOS_EXPORT_VALUELABEL_XXFSR = "信息费收入";
	public static final String COGNOS_EXPORT_VALUELABEL_XZYHS = "下载用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_LXYHS = "黏性用户数";	
	public static final String COGNOS_EXPORT_VALUELABEL_BYYHS = "包月用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_SHYHS = "上行用户数";	
	public static final String COGNOS_EXPORT_VALUELABEL_HYYHS = "会员用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_HYHNF = "会员功能费";
	public static final String COGNOS_EXPORT_VALUELABEL_SZYHS = "新增用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_LSYHS = "流失用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_FWYHS = "访问用户数";	
	public static final String COGNOS_EXPORT_VALUELABEL_SSYHS = "搜索用户数";	
	public static final String COGNOS_EXPORT_VALUELABEL_YXSHL = "有效上行量";
	public static final String COGNOS_EXPORT_VALUELABEL_SFXZL = "收费下载量";
	public static final String COGNOS_EXPORT_VALUELABEL_LJXZL = "累计下载量";
	public static final String COGNOS_EXPORT_VALUELABEL_AZYHS = "安装用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_CCYHS = "操作用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_DLYHS = "登录用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_SYYHS = "使用用户数";
	public static final String COGNOS_EXPORT_VALUELABEL_GMQGM = "歌迷群规模";
	public static final String COGNOS_EXPORT_VALUELABEL_ZXZL = "总下载量";
	public static final String COGNOS_EXPORT_VALUELABEL_YWSR = "业务收入";
	public static final String COGNOS_EXPORT_VALUELABEL_DLCS = "登录次数";
	public static final String COGNOS_EXPORT_VALUELABEL_CCCS = "操作次数";
	public static final String COGNOS_EXPORT_VALUELABEL_BDSC = "拨打时长";
	public static final String COGNOS_EXPORT_VALUELABEL_BDL = "拨打量";
	public static final String COGNOS_EXPORT_VALUELABEL_SHL = "上行量";
	public static final String COGNOS_EXPORT_VALUELABEL_AZF = "安装量";
	public static final String COGNOS_EXPORT_VALUELABEL_XXF = "信息费";
	public static final String COGNOS_EXPORT_VALUELABEL_GNF = "功能费";
	public static final String COGNOS_EXPORT_VALUELABEL_FWL = "访问量";
	public static final String COGNOS_EXPORT_VALUELABEL_STL = "试听量";
	public static final String COGNOS_EXPORT_VALUELABEL_XZL = "下载量";	
	public static final String COGNOS_EXPORT_VALUELABEL_SSL = "搜索量";	
	public static final String COGNOS_EXPORT_VALUELABEL_ZSR = "总收入";
	public static final String COGNOS_EXPORT_LABEL_STYHS =  "试听用户数";  
	public static List<String> cognosValueLabel = new ArrayList<String>();
	static{
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_XZBYYHSYXZL);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_YGYJZGXYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_SGYJZGXYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_LGYJZGXYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_XZBYSYXZL);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_XZBYSYYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_YGYCMYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_SGYCMYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_LGYCMYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_YGYDJYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_SGYDJYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_LGYDJYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_YGYXZYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_SGYXZYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_LGYXZYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_YGYHYYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_SGYHYYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_LGYHYYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_ZTXZYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_FWXZZHL);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_XZBYYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_LJNYYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_BYSYXZL);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_BYSYYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_HYBYYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_SFXZYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_HYDDYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_LJXZYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_LJXXFSR);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_XZLZSR);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_ZXZLSF);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_ZXZYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_BYYHHX);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_GMQXZL);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_GMQYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_XXFSR);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_DLYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_LJXZL);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_CCYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_SYYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_AZYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_YXSHL);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_SSYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_FWYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_LSYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_SFXZL);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_SZYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_HYHNF);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_HYYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_SHYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_BYYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_LXYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_XZYHS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_GMQGM);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_ZXZL);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_BDSC);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_CCCS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_DLCS);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_YWSR);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_SSL);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_XZL);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_STL);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_FWL);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_GNF);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_XXF);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_AZF);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_SHL);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_BDL);
		cognosValueLabel.add(COGNOS_EXPORT_VALUELABEL_ZSR);
		cognosValueLabel.add(COGNOS_EXPORT_LABEL_STYHS);
	}
	
	/**
	 * 用户群提取
	 */
	public static int SEL_STATUS_PENDING = 1; //待处理
	public static int SEL_STATUS_MAKE = 2; //用户群生成
	public static int SEL_STATUS_LOCAL = 3; //导出到本地
	public static int SEL_STATUS_REMOTE = 4; //导出到远程
	public static int SEL_STATUS_MESSAGE = 5;//发送短信
	public static int SEL_STATUS_SUCCESS = 6;//流程成功
	public static int SEL_STATUS_FAIL = -1;//流程失败
	
	/**
	 * 用户群任务类型
	 */
	public static int SEL_TASKTYPE_TQ = 1; //提取
	public static int SEL_TASKTYPE_SC = 2; //上传
	public static int SEL_TASKTYPE_ZH = 3; //组合
	
	public static String ODS_DATATYPE_CPU = "cpu";
	public static String ODS_DATATYPE_MEM = "mem";
	public static String ODS_DATATYPE_DISK = "disk";
	public static String ODS_DATATYPE_IOSTAT = "iostat";
	public static String ODS_DATATYPE_NETWORK = "network";
	public static String ODS_DATATYPE_TOTAL = "Total_access number";
	public static String ODS_DATATYPE_OUT = "Total_access out of 60ms";
	public static String ODS_DATATYPE_ERROR = "Total_error access number";
	public static String ODS_DATATYPE_TOTALANDOUT = "totalAndout"; //报表合并
	
	
	public static Map<String, String> regionMap = new HashMap<String, String>();
	static{
		try{
////			List<DimProvince> allProvinces = DimProvinceLocalServiceUtil.getDimProvinces(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
//			for(DimProvince dp : allProvinces){
//				regionMap.put(String.valueOf(dp.getProvinceCode()), dp.getProvinceName());				
//			}
		}catch(Exception e){
			
		}
	}
	
	
	//时间维度
	
	

	       //下载渠道
	
	
	/**
	 * 活动excel sheet,
	 */
	public static  Map<Integer, String> sheetMapData = new HashMap<Integer, String>();
	static{
		sheetMapData.put(0, "彩铃列表");
		sheetMapData.put(1, "振铃列表");
		sheetMapData.put(2, "歌曲下载列表");
	}
	
	/**
	 * 活动excel frist row cell, 歌曲名称albumMusic_template	歌手信息	CP公司名称	内容ID(12位)	计费类型	音乐价格	接入CP分层比例	生效日期	失效日期

	 */
	public static Map<Integer, String> columnMapData = new HashMap<Integer, String>();
	static{
		columnMapData.put(0, "产品ID(18位)");
		columnMapData.put(1, "歌曲名称");
		columnMapData.put(2, "歌手信息");
		columnMapData.put(3, "CP公司名称");
		columnMapData.put(4, "内容ID(11位)");
		columnMapData.put(5, "计费类型");
		columnMapData.put(6, "音乐价格");
		columnMapData.put(7, "接入CP分层比例");
		columnMapData.put(8, "生效日期");
		columnMapData.put(9, "失效日期");
	}
	
	public static String createMKMenu  = "创建营销活动";
	public static String  newMKReportMenu = "新歌营销报表";
	
	
	public final static String ORG_BASE = "base";
	// add by yanmi end

	// cognos的session时间
	public static final long PERIODTIME = 30 * 60 * 1000;
	
	public static final long EXPORTTIME = 5 * 1000;

	public static final String COGNOSURLHEAD = "http://192.168.0.20:9300/p2pd/servlet/dispatch?b_action=xts.run&m=portal/report-viewer.xts&ui.tool=CognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27";
	
	public static final String COGNOSURLEND = "&ui.header=false&ui.toolbar=false";
	
	//add by chen
	public static final long CASHBOXPERIODTIME = 10;
	
	public static final String CASHBOX_AGREE = "JKY";
	
	public static final String CASHBOX_DECLINE = "JKN";
	
	

}
