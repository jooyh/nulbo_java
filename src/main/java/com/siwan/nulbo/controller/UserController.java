package com.siwan.nulbo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siwan.nulbo.exception.NulboCommonExcption;
import com.siwan.nulbo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

	@Autowired
	private UserService userService;

	/**
	 * 로그인
	 * @param request
	 * @return userInfo
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public Map login(HttpServletRequest request) {
		Map userInfo = userService.selectUserInfo(getParamMap(request));
		if(userInfo != null ) {
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", userInfo);
		}
		return userInfo;
	}

	@RequestMapping("/join.do")
	@ResponseBody
	public void join(HttpServletRequest request) throws NulboCommonExcption {
		int rsltCnt = userService.insertUserInfo(getParamMap(request));
		if(rsltCnt != 1) throw new NulboCommonExcption("등록중 오류 발생");
	}

	@RequestMapping("/emailCheck.do")
	@ResponseBody
	public void emailCheck(HttpServletRequest request) throws NulboCommonExcption {
		Map userInfo = userService.selectUserInfo(getParamMap(request));
		if(userInfo != null) throw new NulboCommonExcption("중복된 이메일입니다.");
	}
}
