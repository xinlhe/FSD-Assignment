package com.ibm.springframework.assignment.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.springframework.assignment.entity.User;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
			throws Exception {

		/*
		 * HttpSession session = httpServletRequest.getSession(true); User userInfo =
		 * (User) session.getAttribute("userInfo");
		 * 
		 * if (userInfo == null) {
		 * 
		 * if (httpServletRequest.getRequestURI().indexOf("Login") >= 0) {
		 * 
		 * return true; } else if
		 * (httpServletRequest.getRequestURI().indexOf("register") >= 0) {
		 * 
		 * return true; } else {
		 * 
		 * httpServletResponse.sendRedirect("/user/tologin"); return false; } }
		 */
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {

	}
}
