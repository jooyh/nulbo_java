package com.siwan.nulbo.exception;

@SuppressWarnings("serial")
public class NulboCommonExcption extends Exception{

	private final String ERR_CODE;

	NulboCommonExcption(String msg,String code) {
		super(msg);
		ERR_CODE = code;
	}

	public NulboCommonExcption(String msg){
		this(msg,"E000");
	}

	public String getErrCode(){// 에러 코드를 얻을 수 있는 메서드도 추가한다.
		return ERR_CODE;// 이 메서드는 주로 getMessage()와 함께 사용될 것이다.
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}

}
