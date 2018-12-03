<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" align="center">
		<tr>
			<td>商品名称</td>
			<td>数量</td>
		</tr>
		<%
			Map<String,Integer> map = (Map<String,Integer>)request.getSession().getAttribute("cart");	
			if(map==null)
			{
				out.print("<tr><td colspan='2'>购物车空空如也</td></tr>");
			}
			else
			{
				for(String key:map.keySet())
				{
		%>
					<tr>
						<td><%=key %></td>
						<td><%=map.get(key) %></td>
					</tr>
		<%
				}
			}
		%>
	</table>
	<center>
	<a href="/history/product_list.jsp">继续购物</a>&bnsp;&bnsp;&bnsp;
	<a href="/history/clearCart">清空购物车</a>
	</center>
</body>
</html>