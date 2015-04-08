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

<script src="${base }/resources/js/index.js"></script>
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
					  登录
					</button>
	  				
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('getat')">获取访问令牌</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('getat1')">重新获取访问令牌</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('getinfo')">查询剩余流量</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('dgll')">订购套餐</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('getSmsCode')">下发随机码</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('contract')">合约查询</button>
	  			  </div>
	  			  
	  			  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('qryUserStatusInfo')">查询用户目前的状态</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('qryUserRegionInfo')">查询用户归属地信息</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('qryUserBrandInfo')">查询用户品牌信息</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('qryBalanceOfAccount')">查询当前用户账户余额信息</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('chkActivateQua')">对入网用户身份校验</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('sFreeMinResourceQry')">查询用户手机号码套餐资源情况</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('sFreeMinUseredQry')">查询用户套餐使用情况信息</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('kcQurNoAlias')">空中充值查询</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('kcDgNoAlias')">空中充值</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('sQryRandPassNoAlias')">下发随机码不需要token</button>
	  			  </div>
				  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('sShortAddModeNoAlias')">流量订购不需要token</button>
	  			  </div>
	  			  <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('sellTreatyPhoneNTK')">合约机销售不需要token</button>
	  			  </div>
	  			   <div class="col-lg-2"> 
	  					<button type="button" class="btn btn-primary" onclick="getatrest('sFreeMinQryNTK')">流量余额查询不需要token</button>
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
		        <form class="form-horizontal" role="form" id="lgform1" method="get" action="${base }/reurl">
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
				</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" onclick="tologin()" class="btn btn-primary">提交</button>
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