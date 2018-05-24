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
		text-align:center;
	}
</style>

<script type="text/javascript">
	function myAddGoods() {
		var noempty = true;
		if (document.addGoods.name.value == "") {
			noempty = false;
		} else if (document.addGoods.amount.value == "") {
			noempty = false;
		} else if (document.addGoods.price.value == "") {
			noempty = false;
		}
		if (!noempty) {
			alert("请填写完整信息");
		} else {
			var amount = document.addGoods.amount.value;
			var price = document.addGoods.price.value;
			var regex = "^\+?[1-9][0-9]*$";
			if (!amount.match(regex)) {
				alert("请输入正确的数量");
				noempty = false;
			} else if (price.match(regex)) {
				alert("请输入正确的价格");
				noempty = false;
			}
		}
		return noempty;
	}
</script>

</head>

<body>
<%
	if (!(request.getAttribute("addGoodsError")==null)) {
		%>
		<script type="text/javascript">
			alert("<%= request.getAttribute("addGoodsError") %>");
		</script>
		<%
	}
%>
<form action="AddMyGoods" name="addGoods" onsubmit="return myAddGoods()" >
	<table align="center">
    <tr><td width="50px">名称:</td><td><input type="text" name="name" /></td></tr>
    <tr><td colspan="2" style="font-size:1px;">&nbsp;</td></tr>
    <tr><td>类别:</td><td style="text-align:left"><select name="category">
 							<option value="图书音像">图书音像</option>
                            <option value="衣服鞋子">衣服鞋子</option>
                            <option value="数码产品">数码产品</option>
                            <option value="实用家电">实用家电</option>
 						</select></td></tr>
    <tr><td colspan="2" style="font-size:1px;">&nbsp;</td></tr>
    <tr><td>数量:</td><td><input type="text" name="amount" /></td></tr>
    <tr><td colspan="2" style="font-size:1px;">&nbsp;</td></tr>
    <tr><td>价格:</td><td><input type="text" name="price" /></td></tr>
    <tr><td colspan="2" style="font-size:1px;">&nbsp;</td></tr>
    <tr><td colspan="2"><input type="reset" value="重置" />&nbsp;&nbsp;&nbsp;<input type="submit" value="添加" /></td></tr>
    </table>
</form>

</body>
</html>