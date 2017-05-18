package com.heipiao.api.v2.exception;

/**
 * 业务运行缺少前提条件
 * 
 * HTTP状态码：412
 * @author Chris
 *
 */
public class PreconditionException extends RuntimeException {

	private static final long serialVersionUID = 2783888662592835529L;

	private int code;
	
	public PreconditionException(int code) {
		super();
		this.code = code;
	}

	public PreconditionException(String msg) {
		super(msg);
	}
	
	public PreconditionException(int code, String msg) {
		super(msg);
		this.code = code;
	}

	public PreconditionException(String msg, Exception e) {
		super(msg, e);
	}

	public PreconditionException(int code, String msg, Exception e) {
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
