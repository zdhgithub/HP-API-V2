package com.heipiao.api.v2.exception;

/**
 * TODO 添加内容
 * 
 * HTTP状态码：417
 * @author Chris
 *
 */
public class ExpectationFailedException extends RuntimeException {

	private static final long serialVersionUID = 2783888662592835529L;

	private int code;
	
	public ExpectationFailedException(int code) {
		super();
		this.code = code;
	}

	public ExpectationFailedException(String msg) {
		super(msg);
	}
	
	public ExpectationFailedException(int code, String msg) {
		super(msg);
		this.code = code;
	}
	
	public ExpectationFailedException(String msg, Exception e) {
		super(msg, e);
	}
	
	public ExpectationFailedException(int code, String msg, Exception e) {
		super(msg, e);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
