<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="allGoods" class="com.rone.secondUser.mybean.ShowAllGoods" scope="session"></jsp:useBean>
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

</head>
<body>

<%
	if (!(request.getAttribute("feedback")==null)) {
		%>
		<script type="text/javascript">
			alert("<%= request.getAttribute("feedback") %>");
		</script>
		<%
	}
%>

<% 
	//System.out.println(allGoods.isHasLists()+"");
	if (!allGoods.isHasLists()) {
%>
	<table width="100%">
		<tr>
			<td style="text-align:left"><img alt="图片消失啦" src="image/Java.PNG"></td>
            <td>&nbsp;</td>
			<td style="text-align:right"><img alt="图片消失啦" src="image/JavaScript.PNG"></td>
		</tr>
	</table>
<%
	} else {
		//System.out.println("index_1");
		ArrayList<HashMap<String,String>> lists = allGoods.getLists();
%> 
		<table width="60%" align="center">
			<tr >
				<th width="20%">名称</th>
				<th width="20%">类别</th>
				<th width="20%">数量</th>
				<th width="20%">价格/元</th>
				<th width="20%">详细信息</th>
			</tr>
			<% 
			for (HashMap<String,String> hm : lists) {
			%>
			<tr><td colspan="5" style="font-size:1px;">&nbsp;</td></tr>
			<tr>
				<td><%=hm.get("name") %></td>
				<td><%=hm.get("category") %></td>
				<td><%=hm.get("amount") %></td>
				<td><%=hm.get("price") %>元</td>
				<td><form action="ShowGoods.jsp">
					<input type="hidden" name="number" value="<%= hm.get("number") %>" /><input type="hidden" name="amount" value="<%= hm.get("amount") %>" />
					<input type="hidden" name="name" value="<%= hm.get("name") %>" /><input type="hidden" name="price" value="<%= hm.get("price") %>" />
					<input type="hidden" name="category" value="<%= hm.get("category") %>" /><input type="hidden" name="owner" value="<%= hm.get("owner") %>" />
					<input type="submit" value="详细信息" ></form></td>
			</tr>
			<%
			}
			%>
		</table>
<%
	}
%>
</body>
</html>