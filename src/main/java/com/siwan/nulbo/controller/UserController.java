package com.siwan.nulbo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.siwan.nulbo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

	@Autowired
	private UserService userService;

	@RequestMapping("/selectUserInfo.do")
	public Map getUserInfo(HttpServletRequest request) {
		Map rtnMap = new HashMap();
		Map paramMap = getParamMap(request);
		userService.selectUser(rtnMap);
		return rtnMap;
	}
}
