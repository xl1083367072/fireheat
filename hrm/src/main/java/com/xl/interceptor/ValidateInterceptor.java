package com.xl.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xl.domain.User;

public class ValidateInterceptor implements HandlerInterceptor {

	public ValidateInterceptor() {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean flag = false;
		String path = request.getServletPath();
		if(path.contains("login")) {
			flag = true;
		}else {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			if(user==null) {
				flag = false;
				request.setAttribute("message", "请先登录");
				System.out.println("拦截器===============================");
				request.getRequestDispatcher(request.getContextPath()+"/loginForm.jsp").forward(request, response);
			}else {
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
