<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="com.rone.secondUser.mybean.User" scope="session" ></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>第二个</title>

<style type="text/css">
	td{
		text-align:center;
	}
</style>

<script type="text/javascript">
	function mySubmitOrder() {
		var bool = true;
		if (document.submitOrder.purchaser.value == "null") {
			alert("请先登陆");
			bool = false;
		} else {
			if (document.submitOrder.amount.value == "") {
				alert("请填写您需要购买的数量");
				bool = false;
			}
		}
		return bool;
	}
</script>
</head>

<body>
<h3 align="center"><%= request.getParameter("name") %>的详细信息</h3>
<table align="center">
<tr>
	<td>
    	<table>
        	<tr><td>名称:<%= request.getParameter("name") %></td></tr>
            <tr><td style="font-size:1px;">&nbsp;</td></tr>
            <tr><td>库存:<%= request.getParameter("amount") %>件</td></tr>
            <tr><td style="font-size:1px;">&nbsp;</td></tr>
            <tr><td>单价:<%= request.getParameter("price") %>元</td></tr>
            <tr><td style="font-size:1px;">&nbsp;</td></tr>
        </table>
    </td>
    <td>
    	<form action="SubmitOrder" name="submitOrder" onsubmit="return mySubmitOrder()" >
        	<input type="hidden" name="goodsName" value="<%= request.getParameter("name") %>" />
        	<input type="hidden" name="goodsID" value="<%= request.getParameter("number") %>" />
            <input type="hidden" name="purchaser" value='<jsp:getProperty property="id" name="user"/>' />
            <input type="hidden" name="seller" value="<%= request.getParameter("owner") %>" />
        <table>
        	<tr><td>类别:<%= request.getParameter("category") %></td></tr>
            <tr><td style="font-size:1px;">&nbsp;</td></tr>
            <tr><td>数量:<input type="text" name="amount" value="1" onclick="value=''" /></td></tr>
            <tr><td style="font-size:1px;">&nbsp;</td></tr>
            <tr><td><input type="submit" value="提交订单" /></td></tr>
            <tr><td style="font-size:1px;">&nbsp;</td></tr>
        </table>   
        </form>
    </td>
</tr>
</table>
</body>
</html>