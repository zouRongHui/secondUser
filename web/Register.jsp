<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>第二个</title>

<%@ include file="top.jsp" %>

<style type="text/css">
	td{
		text-align:center
	}
	.first{
		width:170px;
		height:15px;
	}
	.second{
		font-size: 12px;
	}
</style>

<script type="text/javascript">
	function registerEmpty() {
		var noempty = true;
		if (document.register.id.value == "") {
			noempty = false;
		} else if (document.register.pass.value == "") {
			noempty = false;
		} else if (document.register.repass.value == "") {
			noempty = false;
		} else if (document.register.name.value == "") {
			noempty = false;
		} else if (document.register.tel.value == "") {
			noempty = false;
		}
		if (!noempty) {
			alert("请填写完整信息");
		} else {
			var id = document.register.id.value;
			var pass = document.register.pass.value;
			var repass = document.register.repass.value;
			var tel = document.register.tel.value;
			var regex = "^[a-zA-Z]\\w{5,8}$";
			if(!id.match(regex)) {
				alert("请输入以字母开头的6至9位账号");
				noempty = false;
			} else {
				regex = ".{5,15}";
				if (!(pass.match(regex) && repass.match(regex))) {
					alert("请输入6至16位密码");
					noempty = false;
				} else {
					regex = "^1[3|4|5|7|8][0-9]\\d{8}$";
					if(!tel.match(regex)) {
						alert("请输入正确的手机号码");
						noempty = false;
					} else if (pass != repass) {
						alert("两次输入的密码不同");
						noempty = false;
					}
				}
			}
		}
		
		return noempty;
	}
</script>
</head>
<body>
<%
	if (!(request.getAttribute("rigisterError")==null)) {
		%>
		<script type="text/javascript">
			alert("<%= request.getAttribute("rigisterError") %>");
		</script>
		<%
	}
%>
<form action="Register" name="register" onsubmit="return registerEmpty()" >
	<table width="260px" align="center">
    <tr><td width="80px">账号:</td><td width="180px"><input type="text" name="id" class="first" /></td></tr>
    <tr><td></td><td class="second">请输入以字母开头的6至9位账号</td></tr>
    <tr><td>密码:</td><td><input type="password" name="pass" class="first" /></td></tr>
    <tr><td></td><td class="second">请输入6至16位密码</td></tr>
    <tr><td>重复密码:</td><td><input type="password" name="repass" class="first" /></td></tr>
    <tr><td></td><td class="second">请重复密码</td></tr>
    <tr><td>昵称:</td><td><input type="text" name="name" class="first" /></td></tr>
    <tr><td></td><td class="second">&nbsp;</td></tr>
    <tr><td>联系电话:</td><td><input type="text" name="tel" class="first" /></td></tr>
    <tr><td></td><td class="second">&nbsp;</td></tr>
    <tr><td colspan="2"> <input type="reset" value="重置" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="注册" /></td></tr>
	</table>
</form>
</body>
</html>