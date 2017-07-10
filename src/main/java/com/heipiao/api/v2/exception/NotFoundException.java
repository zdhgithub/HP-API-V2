package com.heipiao.api.v2.exception;

/**
 * 资源找不到
 * 
 * HTTP状态码：404
 * @author Chris
 *
 */
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2783888662592835529L;

	private int code;
	
	public NotFoundException() {
		super();
	}

	public NotFoundException(int code) {
		super();
		this.code = code;
	}

	public NotFoundException(String msg) {
		super(msg);
	}
	
	public NotFoundException(int code, String msg) {
		super(msg);
		this.code = code;
	}

	public NotFoundException(String msg, Exception e) {
		super(msg, e);
	}

	public NotFoundException(int code, String msg, Exception e) {
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
