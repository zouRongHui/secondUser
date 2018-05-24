<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="myGoods" class="com.rone.secondUser.mybean.ShowMyGoods" scope="session"></jsp:useBean>
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

<script type="text/javascript">
	function myDelete() {
		var bool = confirm("删除该商品?");
		return bool;
	}
</script>
</head>
<body>
<table width="60%" align="center">
	<tr>
		<th width="18%">名称</th>
		<th width="18%">类别</th>
		<th width="18%">数量</th>
		<th width="18%">价格/元</th>
		<th colspan="2" width="28%">修改/删除</th>
	</tr>
	<%
	ArrayList<HashMap<String,String>> lists = myGoods.getLists();
	for (HashMap<String,String> hm : lists) {
		%>
        <tr><td colspan="5" style="font-size:1px;">&nbsp;</td></tr>
		<tr>
			<td><%=hm.get("name") %></td>
			<td><%=hm.get("category") %></td>
			<td><%=hm.get("amount") %></td>
			<td><%=hm.get("price") %>元</td>
			<td><form action="ChangeMyGoods.jsp" >
				<input type="hidden" name="number" value="<%= hm.get("number") %>" /><input type="hidden" name="amount" value="<%= hm.get("amount") %>" />
				<input type="hidden" name="name" value="<%= hm.get("name") %>" /><input type="hidden" name="price" value="<%= hm.get("price") %>" />
				<input type="hidden" name="category" value="<%= hm.get("category") %>" />
				<input type="submit" value="修改" /> </form></td>
			<td><form action="DeleteMyGoods" onsubmit="return myDelete()" ><input type="hidden" name="number" value="<%= hm.get("number") %>"/><input type="submit" value="删除">
				</form></td>
		</tr>
		<%
	}
	%>
	<tr>
		<td colspan="5"><a href="AddMyGoods.jsp" >上架商品</a></td>
	</tr>
</table>
</body>
</html>