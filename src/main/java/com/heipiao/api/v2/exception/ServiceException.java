package com.heipiao.api.v2.exception;

/**
 * 服务通用异常
 * 通常应用于服务层片逻辑时产生的异常，如SqlException等
 * 
 * HTTP状态码：500
 * @author Chris
 *
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 6890849143085620167L;
	
	private int code;
	
	public ServiceException(int code) {
		super();
		this.code = code;
	}
	
	public ServiceException(String msg) {
		super(msg);
	}
	
	public ServiceException(int code, String msg) {
		super(msg);
		this.code = code;
	}
	
	public ServiceException(String msg, Exception e) {
		super(msg, e);
	}
	
	public ServiceException(int code, String msg, Exception e) {
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
