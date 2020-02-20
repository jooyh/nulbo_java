package com.siwan.nulbo.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	SqlSession sqlSession;

	@Autowired
	ShaPasswordEncoder encoder;

	@Value("${encode.salt}")
	String pwSalt;

	private static final String NAME_SPACE = "mappers.userService.";

	/**
	 * 사용자 정보조회
	 * @param param
	 * @return userInfo
	 */
	public Map selectUserInfo(Map param) {
		String pw = (String) param.get("userPw");
		if(pw != null) {
			String shaPw = encoder.encodePassword(pw,null);
			param.put("userPw",shaPw);
		}
		Map userInfo = sqlSession.selectOne(NAME_SPACE+"selectUserInfo",param);
		return userInfo;
	}

	/**
	 * 사용자 등록
	 * @param param
	 * @return rsltCnt
	 */
	public int insertUserInfo(Map param) {
		String pw = (String) param.get("userPw");
		String shaPw = encoder.encodePassword(pw,null);
		param.put("userPw",shaPw);
		int rsltCnt = sqlSession.insert(NAME_SPACE+"insertUserInfo",param);
		return rsltCnt;
	}
}
