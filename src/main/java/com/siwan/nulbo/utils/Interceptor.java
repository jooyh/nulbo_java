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
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		logger.debug("===================       START       ===================");
		logger.debug(" Request URI \t:  " + request.getRequestURI());
        
		HttpSession httpSession = request.getSession(true);
		Map session = (Map) httpSession.getAttribute("userInfo");
		
		if("/".equals(request.getRequestURI())) {
			
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
		return super.preHandle(request, response, handler);
	}
}
