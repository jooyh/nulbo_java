package com.siwan.nulbo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	public Map getUserInfo(HttpServletRequest request) {
		Map rtnMap = new HashMap();
		Map paramMap = getParamMap(request);
		return rtnMap;
	}
}
