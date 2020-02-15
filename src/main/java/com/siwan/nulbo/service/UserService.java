package com.siwan.nulbo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	SqlSession sqlSession;
	 
	private static final String NAME_SPACE = "mappers.userService";
	
	public Map selectUser() {
		List<Map> userList = sqlSession.selectList("selectUserInfo");
		return (Map) new HashMap().put("userList", userList);
	}
	
}
