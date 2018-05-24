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
	function myChangeGoods() {
		var noempty = true;
		if (document.changeGoods.name.value == "") {
			noempty = false;
		} else if (document.changeGoods.amount.value == "") {
			noempty = false;
		} else if (document.changeGoods.price.value == "") {
			noempty = false;
		} else if (document.changeGoods.category.value == "") {
			noempty = false;
		} 
		if (!noempty) {
			alert("请填写完整信息");
		} else {
			var amount = document.changeGoods.amount.value;
			var price = document.changeGoods.price.value;
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
<form action="ChangeMyGoods" name="changeGoods" onsubmit="return myChangeGoods()" >
	<input type="hidden" name="number" value="<%= request.getParameter("number") %>" />
	<table align="center">
    <tr><td width="50px">名称:</td><td><input type="text" name="name" value="<%= request.getParameter("name") %>" onclick="value=''" /></td></tr>
    <tr><td colspan="2" style="font-size:1px;">&nbsp;</td></tr>
    <tr><td>类别:</td><td style="text-align:left;"><select name="category">
                            <option value="图书音像" <%= request.getParameter("category").equals("图书音像") ? "selected='selected'" : "" %> >图书音像</option>
                            <option value="衣服鞋子" <%= request.getParameter("category").equals("衣服鞋子") ? "selected='selected'" : "" %> >衣服鞋子</option>
                            <option value="数码产品" <%= request.getParameter("category").equals("数码产品") ? "selected='selected'" : "" %> >数码产品</option>
                            <option value="实用家电" <%= request.getParameter("category").equals("实用家电") ? "selected='selected'" : "" %> >实用家电</option>
                        </select></td></tr>
    <tr><td colspan="2" style="font-size:1px;">&nbsp;</td></tr>
    <tr><td>数量:</td><td><input type="text" name="amount" value="<%= request.getParameter("amount") %>" onclick="value=''" /></td></tr>
    <tr><td colspan="2" style="font-size:1px;">&nbsp;</td></tr>
    <tr><td>价格:</td><td><input type="text" name="price" value="<%= request.getParameter("price") %>" onclick="value=''" /></td></tr>
    <tr><td colspan="2" style="font-size:1px;">&nbsp;</td></tr>
    <tr><td colspan="2"><input type="submit" value="修改" /></td></tr>
    </table>
</form>
</body>
</html>