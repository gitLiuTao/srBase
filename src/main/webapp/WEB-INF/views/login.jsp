<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>业务监测三期</title>
<link type="text/css" rel="stylesheet" href="${base }/resources/css/wrap1280.css" />
<link type="text/css" rel="stylesheet" href="${base }/resources/css/base.css" />
<link type="text/css" rel="stylesheet" href="${base }/resources/css/login.css" />
<script type="text/javascript" src="${base }/resources/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${base }/resources/js/jquery.tmpl.min.js"></script>
<script type="text/javascript" src="${base }/resources/system/js/login.js"></script>
<script type="text/javascript">
	var base = "${pageContext.request.contextPath}";
	var isOpenSms = <c:out value="${isOpenSms}" />;
	var codeTimeOut = <c:out value="${codeTimeOut}" />;
</script>
</head>
<body>
	<div class="head">
		<div class="logo">
			<img src="${base }/resources/images/logo.png" />
		</div>
		<div class="headname"></div>
	</div>
	<form name="loginForm" action="${base }/login" method="post" onsubmit="return sub();">
		<div class="loginbg">
			<div class="login">
				<div class="group">
					<label>账号：</label><input name="username" type="text" class="logininput" />
				</div>
				<div class="group">
					<label>密码：</label><input name="password" type="password" class="logininput" />
				</div>
				<div class="control">
					<input type="submit" class="loginbtn" value="" /><input type="reset" class="resetbtn" value="" />
				</div>
			</div>
		</div>
	</form>
	<div class="foot">
		<div class="wrap">
			<p>
				<span>Copyright &copy; 2013-2014 ChinaMobile All Right Reserved</span><span>中国移动无线音乐基地 版权所有</span>
			</p>
			<p>
				<span>联系人：王云霄</span><span>服务热线：15184368704</span><span>联系邮箱：15184368704@139.com</span>
			</p>
		</div>
	</div>
	<script type="text/x-jquery-tmpl" id="errorMsgTmpl">
      <div id="errorMsg" class="logincode">
        <div class="group4">
          <label><img src="${base}/resources/images/failure.png"/></label>${'${'}msg}
        </div>
      </div>
    </script>
	<script type="text/x-jquery-tmpl" id="codeMsgTmpl">
      <div id="codeMsg" class="logincode">
        <div class="group2">
           <label><img src="${base}/resources/images/success.png" /></label>${'${'}msg}
         </div>
         <div class="group3">
          <label>验证码</label><input type="text" name="code" class="codeinput" /><input type="button" class="codebtn"/>
         </div>
      </div>
    </script>
</body>
</html>