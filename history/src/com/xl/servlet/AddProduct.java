package com.xl.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//添加商品到购物车
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		设置编码
		response.setContentType("text/html;charset=utf-8");
//		获得输出流
		PrintWriter writer = response.getWriter();
//		获得商品名称
		String name = request.getParameter("name");
//		重新编码
		name = new String(name.getBytes("iso8859-1"), "utf-8");
//		获得session
		HttpSession session = request.getSession();
//		获得session域中的购物车对象
		Map<String, Integer> map = (Map<String, Integer>) session.getAttribute("cart");
//		商品数量
		Integer count = null;
//		判断购物车是否为空
		if(map==null)
		{
//			若为空，则创建购物车
			map = new HashMap<String,Integer>();
//			将购物车放入session中
			session.setAttribute("cart", map);
//			商品数量为1
			count = 1;
		}
		else
		{
//			若不为空,判断购物车中有没有此商品
//			若有此商品，则数量加1
			if(map.containsKey(name))
			{
//				取得原来数量
				Integer c = map.get(name);
				count = c + 1;
			}
//			若没有，数量为1
			else
			{
				count = 1;
			}
		}
//		将商品放入购物车
		map.put(name, count);
//		提示信息
		writer.print("<a href='"+request.getContextPath()+"/product_list.jsp"+"'>继续购物</a>&nbsp;&nbsp;&nbsp;");
		writer.print("<a href='"+request.getContextPath()+"/cart.jsp"+"'>查看购物车</a>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
