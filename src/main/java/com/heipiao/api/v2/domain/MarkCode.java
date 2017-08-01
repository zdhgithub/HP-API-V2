package com.heipiao.api.v2.domain;
/**
 * 
 * @author duzh
 * @date 2017年7月28日
 * @version 1.0
 * 鱼标库
 */
public class MarkCode {

	private Integer id;
	/**
	 * 鱼标编码
	 */
	private String markNum;
	/**
	 * 鱼标状态（（0-未使用，1-正常，2-下架））
	 */
	private Integer status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMarkNum() {
		return markNum;
	}
	public void setMarkNum(String markNum) {
		this.markNum = markNum;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
