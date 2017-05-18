package com.heipiao.api.v2.exception.msg;

/**
 * 异常的时候，返回给客户端参考的错误号和错误信息
 * @author Chris
 *
 */
public class UniversalErrorMessage {
	
	private int code;
	
	private String msg;
	
	public UniversalErrorMessage(int code) {
		super();
		this.code = code;
	}

	public UniversalErrorMessage(String msg) {
		super();
		this.msg = msg;
	}

	public UniversalErrorMessage(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
