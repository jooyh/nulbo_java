package com.siwan.nulbo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController extends BaseController{

	@RequestMapping("/")
	public String main() {
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
