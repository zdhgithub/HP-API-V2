package com.heipiao.api.v2.domain;
/**
 * 
 * @author duzh
 * @date 2017年7月28日
 * @version 1.0
 * 字典
 */
public class SysDict {

	private Integer id;
	/**
	 * 备注说明
	 */
	private String remark;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 编号
	 */
	private Integer num;
	/**
	 * 值
	 */
	private String value;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
