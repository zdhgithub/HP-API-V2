package com.heipiao.api.v2.exception;

/**
 * 错误的请求
 * 一般应用于参数检查
 * 
 * HTTP状态码：400
 * @author Chris
 *
 */
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 2783888662592835529L;

	private int code;
	
	public BadRequestException() {
		super();
	}

	public BadRequestException(int code) {
		super();
		this.code = code;
	}

	public BadRequestException(String msg) {
		super(msg);
	}
	
	public BadRequestException(int code, String msg) {
		super(msg);
		this.code = code;
	}
	
	public BadRequestException(String msg, Exception e) {
		super(msg, e);
	}
	
	public BadRequestException(int code, String msg, Exception e) {
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
