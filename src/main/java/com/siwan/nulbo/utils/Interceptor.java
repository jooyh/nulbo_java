package com.siwan.nulbo.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter{

	protected static final Logger logger = LoggerFactory.getLogger(Interceptor.class);
	private static final String SESSION_USER_INFO_KEY = "userInfo";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		logger.debug("===================       START       ===================");
		logger.debug(" Request URI \t:  " + request.getRequestURI());

		HttpSession httpSession = request.getSession(true);
		Map session = (Map) httpSession.getAttribute(SESSION_USER_INFO_KEY);

		if("/".equals(request.getRequestURI())) {
//			session.remove(SESSION_USER_INFO_KEY);
		}else if("/login.do".equals(request.getRequestURI())){
			request.setAttribute("session", session);
		}else {
			if(!request.getRequestURI().contains("login")
				&& !request.getRequestURI().contains("join")
				&& !request.getRequestURI().contains("resources")
				&& session == null) {
				response.sendRedirect("/");
			}
		}
		logger.debug("===================       END       ===================");
		return super.preHandle(request, response, handler);
	}
}
