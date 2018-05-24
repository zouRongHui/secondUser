<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="myOrders" class="com.rone.secondUser.mybean.ShowMyOrders" scope="session"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>第二个</title>

<%@ include file="top.jsp" %>

<style type="text/css">
	td{
		text-align:center;
	}
</style>

</head>
<body>
<table width="40%" align="center">
	<tr>
		<th width="25%">商品名</th>
		<th width="25%">数量</th>
		<th width="25%">买家</th>
		<th width="25%">卖家</th>
	</tr>
	<%
	ArrayList<HashMap<String,String>> lists = myOrders.getLists();
	for (HashMap<String,String> hm : lists) {
	%>
    <tr><td colspan="4" style="font-size:1px;">&nbsp;</td></tr>
	<tr>
		<td><%=hm.get("goodsName") %></td>
		<td><%=hm.get("amount") %></td>
		<td><%=hm.get("purchaser") %></td>
		<td><%=hm.get("seller") %></td>
	</tr>
	<%
	}
	%>
</table>
</body>
</html>