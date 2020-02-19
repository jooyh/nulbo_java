package com.siwan.nulbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.siwan.nulbo.service.UserService;

@Controller
public class indexController extends BaseController{

	@Autowired
	UserService userService;

	@RequestMapping("/")
	public String main() {
//		userService.selectUser();
		return "login";
	}

	@RequestMapping("/login.do")
	public String login() {
		return "login";
	}

	@RequestMapping("/userPage.do")
	public String userPage() {
		return "userPage";
	}

	@RequestMapping("/timeline.do")
	public String timeline() {
		return "timeline";
	}

}
