package com.sunrise.base.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartUtil {
	/**
	 * 时间报表chart折线图
	 * @param valueMap  X轴上的坐标对应的值
	 * @param titleTime chart的标题
	 * @return
	 */
	public static Map<String,Object> lineChart(List<Map<String,Object>> labelList,String titleTime){
		Map<String,Object> jsonObj = new HashMap<String,Object>();
		
		Map<String,String> chart = new HashMap<String,String>();  //拼chart
		chart.put("unescapeLinks", "0");
		chart.put("animation", "1");
		chart.put("bgColor", "FFFFFF");  //图表背景色
		chart.put("divLineThickness", "1");  //网格线粗细
		chart.put("showBorder", "0");		//是否显示图表边框
		chart.put("canvasborderthickness", "0");  //画板边框
		chart.put("canvasbordercolor", "C6C6C6"); //画板边框颜色
		chart.put("linecolor", "3370B4");   //网格线颜色
		chart.put("anchorRadius", "4");  //折线节点半径
		chart.put("showAlternateHGridColor", "1");  //是否在纵向网格带交替的颜色，默认为0(False)
		chart.put("anchorBgColor", "3370B4");   //折线节点填充颜色，6位16进制颜色值
		chart.put("anchorBorderColor", "3370B4");  //折线节点边框颜色，6位16进制颜色值
		chart.put("showLabels", "1");   //是否显示x轴的坐标值
		chart.put("formatnumberscale", "0");  //代表y轴单位
		chart.put("showvalues", "0");
		
		if(labelList.size() < 10){
			chart.put("slantLabels", "1");  //是否旋转x轴的坐标值
			chart.put("labelDisplay", "NONE");  //'WRAP/STAGGER/ROTATE/NONE'(x轴坐标值的具体展现形式)
		}else{
			chart.put("slantLabels", "1"); //将x轴坐标值旋转为倾斜的还是完全垂直
			chart.put("rotateLabels", "1");
			chart.put("labelDisplay", "ROTATE");
		}
		chart.put("caption", titleTime);
		try {
			jsonObj.put("chart", chart);
			jsonObj.put("data", labelList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObj;
	}
	
	/**创建多线条的折线图数据
	 * @param dataList 报表需要展示的原始数据
	 * @param lines 折线的集合，每条折线为一个map，每个map中至少包含lineName（线条名称），dataKey（线条对应的原数据中的key）
	 * @param labelKey 横坐标
	 * @param ChartTitle 图表的标题
	 */
	public  static Map<String,Object>  CreateMultiLineChart(List<Map<String,Object>> dataList,List<Map<String,String>> lines,String labelKey,String ChartTitle){
		
		if(dataList == null || dataList.size() <1||dataList.get(0)==null){
			return null;
		}
		
		Map<String,Object> jsonObj = new HashMap<String,Object>();
		
		Map<String,String> chart = new HashMap<String,String>();  //拼chart
		chart.put("unescapeLinks", "0");
		chart.put("animation", "1");
		chart.put("bgColor", "FFFFFF");  //图表背景色
		chart.put("divLineThickness", "1");  //网格线粗细
		chart.put("showBorder", "0");		//是否显示图表边框
		chart.put("canvasborderthickness", "0");  //画板边框
		chart.put("canvasbordercolor", "C6C6C6"); //画板边框颜色
//		chart.put("linecolor", "3370B4");   //网格线颜色
		chart.put("anchorRadius", "4");  //折线节点半径
		chart.put("showAlternateHGridColor", "1");  //是否在纵向网格带交替的颜色，默认为0(False)
//		chart.put("anchorBgColor", "3370B4");   //折线节点填充颜色，6位16进制颜色值
//		chart.put("anchorBorderColor", "3370B4");  //折线节点边框颜色，6位16进制颜色值
		chart.put("showLabels", "1");   //是否显示x轴的坐标值
		chart.put("formatnumberscale", "0");  //代表y轴单位
		chart.put("showvalues", "0");
		
		List<Map<String,Object>> categoriesList = new ArrayList<Map<String,Object>>();//横坐标的集合
		List<Map<String,Object>> categoryList = new ArrayList<Map<String,Object>>();//横坐标对象列表
		Map<String,Object> category = new HashMap<String,Object>();
		category.put("category", categoryList);
		categoriesList.add(category);//添加到集合中
		
		List<Map<String,Object>> datasetList = new ArrayList<Map<String,Object>>();//线条数据的集合
		
		for(int i = (dataList.size()-1);i>=0;i--){//处理数据列表
			Map<String,Object> data = dataList.get(i);
			  //首先根据传入的labelkey将横坐标取出放入list中
			Object label = data.get(labelKey);
			Map<String,Object> labelMap = new HashMap<String,Object>();
			labelMap.put("label", label);
			categoryList.add(labelMap);
		}
		//将每条线的数据取出来
		for(Map<String,String> line:lines){
			String lineName = line.get("lineName");
			String dataKey = line.get("dataKey");
			
			Map<String,Object> lineRest = new HashMap<String,Object>();//线条的数据结果
			List<Map<String,Object>> lineData = new ArrayList<Map<String,Object>>();//线条的数据列表
			
			for(int i = (dataList.size()-1);i>=0;i--){//处理数据列表，因为数据库是按照日期倒序，所有这里要倒序取
				Map<String,Object> data = dataList.get(i);
				  //首先根据传入的labelkey将横坐标取出放入list中
				Object value = data.get(dataKey);
				Map<String,Object> valueMap = new HashMap<String,Object>();
				valueMap.put("value", value);
				lineData.add(valueMap);
			}
			lineRest.put("seriesname", lineName);
			lineRest.put("data", lineData);
			
			//将每条线的数据组装好之后，放到数据结果集中
			datasetList.add(lineRest);
		}	
		
		if(categoryList.size() < 10){
			chart.put("slantLabels", "1");  //是否旋转x轴的坐标值
			chart.put("labelDisplay", "NONE");  //'WRAP/STAGGER/ROTATE/NONE'(x轴坐标值的具体展现形式)
		}else{
			chart.put("slantLabels", "1"); //将x轴坐标值旋转为倾斜的还是完全垂直
			chart.put("rotateLabels", "1");
			chart.put("labelDisplay", "ROTATE");
		}
		chart.put("caption", ChartTitle);//设置报表title
		
		//组装最终结构
		jsonObj.put("chart", chart);
		jsonObj.put("categories", categoriesList);
		jsonObj.put("dataset", datasetList);
		return jsonObj;
	}
	
	/**
	 * @param dataset seriesname,data
	 * @param labels
	 * @param title
	 * @param xname
	 * @param yname
	 * @return
	 */
	public static Map<String,Object> mutiLineChart(List<Map<String,Object>> dataset,List<Map<String,String>> labels,String title){
		
		Map<String,Object> jsonObj = new HashMap<String,Object>();
		
		Map<String,String> chart = new HashMap<String,String>();  //拼chart
		chart.put("unescapeLinks", "0");
		chart.put("animation", "1");
		chart.put("bgColor", "FFFFFF");  //图表背景色
		chart.put("divLineThickness", "1");  //网格线粗细
		chart.put("showBorder", "0");		//是否显示图表边框
		chart.put("canvasborderthickness", "0");  //画板边框
		chart.put("canvasbordercolor", "C6C6C6"); //画板边框颜色
//		chart.put("linecolor", "3370B4");   //网格线颜色
		chart.put("anchorRadius", "4");  //折线节点半径
		chart.put("showAlternateHGridColor", "1");  //是否在纵向网格带交替的颜色，默认为0(False)
//		chart.put("anchorBgColor", "3370B4");   //折线节点填充颜色，6位16进制颜色值
//		chart.put("anchorBorderColor", "3370B4");  //折线节点边框颜色，6位16进制颜色值
		chart.put("showLabels", "1");   //是否显示x轴的坐标值
		chart.put("formatnumberscale", "0");  //代表y轴单位
		chart.put("showvalues", "0");
		
		if(labels.size() < 10){
			chart.put("slantLabels", "1");  //是否旋转x轴的坐标值
			chart.put("labelDisplay", "NONE");  //'WRAP/STAGGER/ROTATE/NONE'(x轴坐标值的具体展现形式)
		}else{
			chart.put("slantLabels", "1"); //将x轴坐标值旋转为倾斜的还是完全垂直
			chart.put("rotateLabels", "1");
			chart.put("labelDisplay", "ROTATE");
		}
		chart.put("caption", title);
//		chart.put("xaxisname", xname);
//		chart.put("yaxisname", yname);
		
		List cate = new ArrayList();
		Map c1 = new HashMap();
		
		c1.put("category", labels);
		cate.add(c1);
		
		jsonObj.put("chart", chart);
		jsonObj.put("categories", cate);
		jsonObj.put("dataset", dataset);
		
		return jsonObj;
	}
	
	/**
	 * 地域chart(渠道同此)柱状图
	 * @param valueList
	 * @param titleTime
	 * @return
	 */
	public static Map<String,Object> columnChart(List<Map<String,Object>> labelList,String titleTime){
		Map<String,Object> jsonObj = new HashMap<String,Object>();
		
		Map<String,String> chart = new HashMap<String,String>();  //拼chart
		chart.put("unescapeLinks", "0");
		chart.put("animation", "1");
		chart.put("bgColor", "FFFFFF");  
		chart.put("divLineThickness", "1");  
		chart.put("showBorder", "0");		
		chart.put("canvasborderthickness", "0");  
		chart.put("canvasbordercolor", "C6C6C6"); 
		chart.put("linecolor", "3370B4");   
		chart.put("plotgradientcolor", "FFFFFF");  //数据的有坡度颜色方案
		chart.put("showAlternateHGridColor", "1"); 
		chart.put("showshadow", "0");   //是否显示阴影
		chart.put("showLabels", "1");   //是否显示x轴的坐标值
		chart.put("formatnumberscale", "0");  //代表y轴单位
		chart.put("showvalues", "0");
		chart.put("anchorBgColor", "3370B4");   //折线节点填充颜色，6位16进制颜色值
		chart.put("anchorBorderColor", "3370B4");  //折线节点边框颜色，6位16进制颜色值
		chart.put("showPlotBorder","1");
		if(labelList.size() < 10){
			chart.put("slantLabels", "1");  //是否旋转x轴的坐标值
			chart.put("labelDisplay", "NONE");  //'WRAP/STAGGER/ROTATE/NONE'(x轴坐标值的具体展现形式)
		}else{
			chart.put("slantLabels", "1"); //将x轴坐标值旋转为倾斜的还是完全垂直
			chart.put("rotateLabels", "1");
			chart.put("labelDisplay", "ROTATE");
		}
		
		chart.put("caption", titleTime);
		try {
			jsonObj.put("chart", chart);
			jsonObj.put("data", labelList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObj;
	}
	
	/**
	 * 条状图chart
	 * @param valueList
	 * @param titleTime
	 * @return
	 */
	public static Map<String,Object> barChart(List<Map<String,Object>> labelList,String titleTime){
		Map<String,Object> jsonObj = new HashMap<String,Object>();
		
		Map<String,String> chart = new HashMap<String,String>();  //拼chart
//		chart.put("unescapeLinks", "0");
//		chart.put("animation", "1");
//		chart.put("bgColor", "FFFFFF");  
//		//chart.put("divLineThickness", "1");  
//		chart.put("showBorder", "0");		
//		chart.put("canvasborderthickness", "0");  
//		chart.put("canvasBgAlpha", "0");
//		chart.put("canvasbordercolor", "FFFFFF"); 
//		chart.put("plotgradientcolor", "");  //数据的有坡度颜色方案
//		chart.put("showAlternateHGridColor", "0");  
//		chart.put("numDivLines", "0");
//		chart.put("showLimits", "0");
//		chart.put("showplotborder", "0");   //(是否显示柱子的边框
//		chart.put("showshadow", "0");   //是否显示阴影
//		chart.put("showvalues", "0");
//		chart.put("formatnumberscale", "0");  //代表y轴单位
		
		chart.put("unescapeLinks", "0");
		chart.put("animation", "1");
		chart.put("bgColor", "FFFFFF");  
		chart.put("divLineThickness", "1");  
		chart.put("showBorder", "0");		
		chart.put("canvasborderthickness", "0");  
		chart.put("canvasbordercolor", "C6C6C6"); 
		chart.put("linecolor", "3370B4");   
		chart.put("plotgradientcolor", "FFFFFF");  //数据的有坡度颜色方案
		chart.put("showAlternateHGridColor", "1");  
		chart.put("showplotborder", "1");   //(是否显示柱子的边框
		chart.put("showshadow", "0");   //是否显示阴影
		chart.put("showLabels", "1");   //是否显示x轴的坐标值
		chart.put("formatnumberscale", "0");  //代表y轴单位
		chart.put("showvalues", "0");
		chart.put("caption", titleTime);
		try {
			jsonObj.put("chart", chart);
			jsonObj.put("data", labelList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObj;
	}
}
