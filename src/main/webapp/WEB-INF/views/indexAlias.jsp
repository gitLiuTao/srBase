<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="content-type" content="text/html; charset=UTF-8">
<title>分销平台接口测试</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="${base }/resources/css/bootstrap.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${base }/resources/js/jquery-1.11.0.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${base }/resources/js/bootstrap.min.js"></script>

<script src="${base }/resources/js/indexAlias.js"></script>
</head>
<body>
	<div style="height: 80px;"></div>
	<div class="row">
	  <div class="col-lg-2"> </div>
	  <div class="col-lg-8">
	  		<div class="row">
				  <div class="col-lg-2">
	  				<!-- Button trigger modal -->
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#dlmtc">
					  绑定Alias
					</button>
	  				
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('getatAlias')">获取访问令牌Alias</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('getat1Alias')">重新获取访问令牌Alias</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('getinfoAlias')">查询剩余流量Alias</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('dgllAlias')">订购套餐Alias</button>
	  			  </div>
	  			  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('sShortAddModeNMSAlias')">订购套餐无需二次短信验证Alias</button>
	  			  </div>
	  			  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('getSmsCodeAlias')">下发随机码Alias</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('cancle_relation')">解绑Alias</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('contract')">合约查询</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('qryUserStatusInfo')">查询用户目前的状态Alias</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('qryUserRegionInfo')">查询用户归属地信息Alias</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('qryUserBrandInfo')">查询用户品牌信息Alias</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('qryBalanceOfAccount')">查询当前用户账户余额信息Alias</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('chkActivateQua')">对入网用户身份校验Alias</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('sFreeMinResourceQry')">查询用户手机号码套餐资源情况Alias</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('sFreeMinUseredQry')">查询用户套餐使用情况信息Alias</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('qryRealTimeFee')">实时话费查询Alias</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('sPFeeQuery')">余额查询Alias</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('sQryBillHome')">账单查询Alias</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('qryOrdPudInfo')">已订购产品查询Alias</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('qryHandledAct')">已办理营销活动查询Alias</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('sellBarePhone')">裸机销售Alias</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('sellTreatyPhone')">合约机销售Alias</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('updateMainCharges')">主资费变更Alias</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('sTreatyDeal')">合约办理Alias</button>
	  			  </div>
	  		</div>
	  </div>
	  <div class="col-lg-2"> </div>
	</div>
	
	<!-- ----------登录的模态窗口----------- -->
<div class="modal fade"  id="dlmtc" tabindex="-1" role="dialog" aria-labelledby="dlcs-labe" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="dlcs-labe">输入登录的参数</h4>
      </div>
      <div class="modal-body">
		        <form class="form-horizontal" role="form" id="lgform1" method="get" action="${base }/reurlAlias">
				  <div class="form-group">
				    <label for="app_id" class="col-sm-2 control-label">跳转路径</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="url" id="url" placeholder="app_id">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="type" class="col-sm-2 control-label">展示类型</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="type" id="type" placeholder="展示类型">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="state" class="col-sm-2 control-label">state</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="state" id="state" placeholder="一致性比较的状态字段">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="alias" class="col-sm-2 control-label">alias</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="alias" id="alias" placeholder="用户别名">
				    </div>
				  </div>
				 
				</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" onclick="tologinAlias()" class="btn btn-primary">提交</button>
      </div>
    </div> 
  </div> 
</div> 
	<!-- ----------登录的模态窗口----------- -->
	
	
	
	<!-- ----------展示结果的模态窗口----------- -->
<div class="modal fade"  id="showinfo" tabindex="-1" role="dialog" aria-labelledby="info_title" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="info_title">结果</h4>
      </div>
      <div class="modal-body" id="mt_infobody">
		        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div> 
  </div> 
</div> 
	<!-- ----------展示结果的模态窗口----------- -->
	
	
</body>
</html>