package com.xl.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xl.cookieutils.CookieUtils;

public class ClearServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	清空浏览记录
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		取得指定cookie
		Cookie c = CookieUtils.getCookieByName("ids", request.getCookies());
//		设置路径
		c.setPath(request.getContextPath()+"/");
//		设置存活时间
		c.setMaxAge(0);
//		写回浏览器,coolkie名称和路径必须一样才会认为是同一个cookie
		response.addCookie(c);
//		跳转
		response.sendRedirect(request.getContextPath()+"/product_list.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
