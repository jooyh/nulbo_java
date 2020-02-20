package com.siwan.nulbo.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter{

	protected static final Logger logger = LoggerFactory.getLogger(Interceptor.class);
	private static final String SESSION_USER_INFO_KEY = "userInfo";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("===================       START       ===================");
		logger.debug(" Request URI \t:  " + request.getRequestURI());

		HttpSession httpSession = request.getSession(true);
		Map session = (Map) httpSession.getAttribute(SESSION_USER_INFO_KEY);

		if("/".equals(request.getRequestURI())) {
			if(session != null) httpSession.removeAttribute(SESSION_USER_INFO_KEY);
		}else {
			if(!request.getRequestURI().contains("login")
				&& !request.getRequestURI().contains("join")
				&& !request.getRequestURI().contains("emailCheck")
				&& !request.getRequestURI().contains("resources")
				&& session == null) {
				response.sendRedirect("/");
			}
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
		logger.debug("===================       END       ===================");
	}
}
