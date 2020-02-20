package com.siwan.nulbo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseController{
	protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	private static final String JSON_STRING_KEY = "jsonStr";
	private static final String USER_SESSION_KEY = "userInfo";

	protected Map getParamMap(HttpServletRequest request) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		String jsonStr = (String) request.getParameter(JSON_STRING_KEY);
		ObjectMapper mapper = new ObjectMapper();
		Map sessionMap = (Map) request.getSession().getAttribute(USER_SESSION_KEY);
		if(sessionMap != null) paramMap.put(USER_SESSION_KEY,sessionMap);
		if(jsonStr == null || "".equals(jsonStr)) return paramMap;
		try {
			paramMap = mapper.readValue(jsonStr, new TypeReference<Map<String, Object>>(){});
			for( String key : paramMap.keySet() ){
				Object obj = paramMap.get(key);
				if(obj != null) {
					String clsName = paramMap.get(key).getClass().getName();
					if(clsName.contains("List")) {
						List<Map> cnvtList = mapper.readValue(paramMap.get(key).toString(), new TypeReference<List<Map>>(){});
						paramMap.put(key,cnvtList);
					}
				}
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		logger.debug(" [ ::: PARAMETER ::: ] "+paramMap);
		return paramMap;
	}

	protected Map setRsultnMapForRead(Object result) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		String code , msg;

		if(result != null) {
			code = "SUCC";
			msg  = "성공적으로 조회되었습니다.";
		}else{
			code = "ERR";
			msg  = "조회 결과가 없습니다.";
		}

		resultMap.put("code",code);
		resultMap.put("msg",msg);
		resultMap.put("data",(Map) result);
		return resultMap;
	}

	protected Map setRsultnMapForWrite(int result) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		String code , msg;

		if(result > 0) {
			code = "SUCC";
			msg  = "성공적으로 등록되었습니다.";
		}else {
			code = "ERR";
			msg  = "등록에 실패하였습니다.";
		}

		resultMap.put("code", code);
		resultMap.put("msg" , msg);
		resultMap.put("data", result);
		return resultMap;
	}
}
