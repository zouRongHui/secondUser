<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
<style type="text/css">
	body{background: url(image/login.JPG)}
	label{
		color:red;
		font-size:80%;
	}
	td{
		font-size:90%;
		opacity:0.8;
	}
	.bb{
		width: 97px; 
		height: 30px;
		background: url(image/button.png);
	}
</style>
<script type="text/javascript">
var ranNum = "";
for (var i = 0; i < 4; i++) {
	ranNum += parseInt(Math.random()*10, 10);
}

function login() {
	  var $userName = $("#userName");
	  var $userPassword = $("#userPassword");
	  var $authCode = $("#authCode");
	  if ($userName.val() == "") {
		  $("#userNameTip").html("* 请输入账号");
		  $userName.focus();
		  return false;
	  }
	  if ($userPassword.val() == "") {
		  $("#userPasswordTip").html("* 请输入密码");
		  $userPassword.focus();
		  return false;
	  }
	  if ($authCode.val() == "") {
		  $("#authCodeTip").html("* 请输入验证码");
		  $authCode.focus();
		  return false;
	  }
	  var regex = "^[a-zA-Z]\\w{5,8}$";
	  if (!$userName.val().match(regex)) {
		  $("#userNameTip").html("* 请输入以字母开头的6至9位账号");
		  $userName.focus();
		  return false;
	  }
	  regex = "^.{5,15}$";
	  if (!$userPassword.val().match(regex)) {
		  $("#userPasswordTip").html("* 请输入6至16位密码");
		  $userPassword.focus();
		  return false;
	  }
	  regex = "^\\d{4}$";
	  if (!$authCode.val().match(regex)) {
		  $("#authCodeTip").html("* 请输入4位数字验证码");
		  $authCode.focus();
		  return false;
	  }
	  if (!($authCode.val() == ranNum)) {
		  $("#authCodeTip").html("* 验证码输入错误");
		  $authCode.focus();
		  return false;
	 }
}
$(document).ready(function(){
	var img = document.getElementById("authCodeImg");
	img.src = "AuthCode?authCode=" + ranNum;
  $("#userName").blur(function(){
	  $("#userNameTip").html("");
  });
  $("#userPassword").blur(function(){
	  $("#userPasswordTip").html("");
  });
  $("#authCode").blur(function(){
	  $("#authCodeTip").html("");
  });
});
function nextAuthCode(){
	$("#authCode").val("");
	var img = document.getElementById("authCodeImg");
	ranNum = "";
	for (var i = 0; i < 4; i++) {
		ranNum += parseInt(Math.random()*10, 10);
	}
	img.src = "AuthCode?authCode=" + ranNum;
}
</script>
</head>
<body>
<table width="1284" height="422">
  <tr>
    <td width="777" height="160">&nbsp;</td>
    <td width="495">&nbsp;</td>
  </tr>
  <tr>
    <td height="300">&nbsp;</td>
    <td>
	<form action="Login" method="post" onsubmit="return login()">
	<table>
		<tr><td>用户名: <input type="text" name="id" id="userName"/></td><td><label id="userNameTip"></label></td></tr>
		<tr><td></td><td></td></tr>
		<tr><td></td><td></td></tr>
		<tr><td>密　码: <input type="password" name="pass" id="userPassword"/></td><td><label id="userPasswordTip"></label></td></tr>
		<tr><td></td><td></td></tr>
		<tr><td></td><td></td></tr>
		<tr><td>验证码: <input type="text" name="authCode" id="authCode"/></td><td><label id="authCodeTip"></label></td></tr>
		<tr><td></td><td></td></tr>
		<tr><td></td><td></td></tr>
		<tr><td align="center"><img alt="验证码" id="authCodeImg"/><a href="javascript:nextAuthCode()">看不清</a></td><td></td></tr>
		<tr><td></td><td></td></tr>
		<tr><td></td><td></td></tr>
		<tr><td align="center"><input type="submit" class="bb" value=" " /></td><td><a href="Register.jsp" target="_top">立即注册</a></td></tr>
	</table>
	</form>
	</td>
  </tr>
</table>
  <%
  	if (!(request.getAttribute("loginError")==null)) {
  		%>
  		<script type="text/javascript">
  			alert("<%= request.getAttribute("loginError") %>");
  		</script>
  		<%
  	}
  %>
</body>
</html>