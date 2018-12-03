package com.xl.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xl.cookieutils.CookieUtils;

public class GetProductById extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		取得id值
		String id = request.getParameter("id");
//		存放最终cookie值
		String newids = "";
//		取得指定cookie
		Cookie c = CookieUtils.getCookieByName("ids",request.getCookies());
//		若cookie为空,则是第一次访问,创建cookie把此id加入
		if(c==null)
		{
			newids = id;
		}
//		若不为空
		else {
//			截取cookie值，转换成集合
			String ids = c.getValue();
			String[] arr = ids.split("-");
			List<String> asList = Arrays.asList(arr);
			LinkedList<String> list = new LinkedList<>(asList);
//			若cookie值里面包含这个id，移动到第一个位置
			if(list.contains(id))
			{
				list.remove(id);
				list.addFirst(id);
			}
//			若不包含这个id
			else
			{
//			若已经有了3条浏览记录,则删除掉最后一个，把新的id放到首位
				if(list.size()>2)
				{
					list.removeLast();
					list.addFirst(id);
				}
//				若不到3条记录，则直接放到首位
				else
				{
					list.addFirst(id);
				}
			}
//			再拼接成字符串
			for (String s : list) {
				newids += (s+"-");
			}
//			去掉最后一个-
			newids.substring(0, newids.length()-1);
		}
//		创建cookie
		c = new Cookie("ids", newids);
//		设置路径
		c.setPath(request.getContextPath()+"/");
//		设置存活时间
		c.setMaxAge(3600);
//		写回浏览器
		response.addCookie(c);
//		跳转到商品详情页
		response.sendRedirect(request.getContextPath()+"/product_info"+id+".htm");
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
