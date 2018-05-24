<%@page import="com.rone.secondUser.mybean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="com.rone.secondUser.mybean.User" scope="session"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>第二个</title>

<style type="text/css" >
	.login{
		width:100px;
	}
	.userName{
		color:#F03;
	}
	td{
		text-align:center;
	}
</style>

<script type="text/javascript">
	function loginEmpty() {
		var noempty = true;
		if (document.login.id.value == "") {
			noempty = false;
		} else if (document.login.pass.value == ""){
			noempty = false;
		}
		if (!noempty) {
			alert("账号密码存在空值");
		} else {
			var id = document.login.id.value;
			var pass = document.login.pass.value;
			var regex = "^[a-zA-Z]\\w{5,8}$";
			if(!id.match(regex)) {
				alert("请输入以字母开头的6至9位账号");
				noempty = false;
			} else {
				regex = ".{5,15}";
				if (!pass.match(regex)) {
					alert("请输入6至16位密码");
					noempty = false;
				}
			}
		}
		return noempty;
	}
	
	function getPTime(){
		 var weekArray = new Array("星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
		 var week = weekArray[new Date().getDay()]; 
		 document.getElementById("ptime").innerHTML=new Date().toLocaleString()+" &nbsp;"+week;
		 setTimeout(getPTime,1000);
    }
	
</script>

</head>

<body style="background: url(image/bg.JPG)">
<table width="100%" >
    <tr>
        <td width="" height="30px" style="text-align:left" >
        <% if (user.getId() == null) { %>
			<a href="Register.jsp" style="font-size:14px" target="_top">会员注册</a>
		<% } else { %>
			&nbsp;
		<% } %>
        </td>
        <td width="31%" align="center" ><a href="index.jsp" target="_top"><img src="image/logo.png" /></a></td>
        <td width="39%" style="text-align:right;font-size:13px" >
        <% if (user.getId() == null) { %>
        	<a href="Welcome.jsp" style="font-size:14px" target="_parent" >登录</a>&nbsp;
            <!-- 
            <form action="Login" name="login" onsubmit="return loginEmpty()" target="_top">
                账号: <input type="text" name="id" value="" class="login" />
                密码: <input type="password" name="pass" class="login" />
                <input type="submit" value="登陆" />
            </form>
             -->
        <% } else { %>
        hello&nbsp;,&nbsp;<span class="userName" ><jsp:getProperty property="name" name="user"/></span>&nbsp;&nbsp;
        <a href="Logout" style="font-size:14px" target="_parent">退出</a>&nbsp;
        <% } %>
        </td>
    </tr>
    <tr>
		<td>&nbsp;</td> 
	    <td>
	    <form action="SearchGoods" target="_parent" method="get">
	    <input type="text" name="name" value="" />&nbsp;<input type="submit" value="搜索" />
	    </form> 
	    </td>
    <td>&nbsp;</td> 
        
    </tr>
    <tr>
    	<td style="text-align:left;" >&nbsp;<span id="ptime" style="display: inline;font-size: 13px"></span>
        	<script language="javascript">getPTime(); </script>
        </td> 
        <td>&nbsp;</td> 
    	<td style="text-align:right" >
        <% if (user.getId() != null) { %>
        <a href="SearchMyGoods" target="_top" >我的商品</a>&nbsp;<a href="MyOrders" target="_top" >我的订单</a>
        <% } %>
        </td>
    </tr>
    
</table>
<hr/>
</body>
</html>