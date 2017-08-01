package com.heipiao.api.v2.domain;
/**
 * 
 * @author duzh
 * @date 2017年7月28日
 * @version 1.0
 * 钓场鱼标配置
 */
public class MarkFish {

	private Integer id;
	/**
	 * 钓场主uid
	 */
	private Integer siteUid;
	/**
	 * 鱼标编码
	 */
	private String markNum;
	/**
	 * 鱼标对应的奖品内容
	 */
	private String markContent;
	/**
	 * 领取奖品的条件
	 */
	private String condition;
	/**
	 * 送货方式
	 */
	private String deliveryType;
	/**
	 * 状态（0-正常，1-下架）
	 */
	private Integer status;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSiteUid() {
		return siteUid;
	}
	public void setSiteUid(Integer siteUid) {
		this.siteUid = siteUid;
	}
	public String getMarkNum() {
		return markNum;
	}
	public void setMarkNum(String markNum) {
		this.markNum = markNum;
	}
	public String getMarkContent() {
		return markContent;
	}
	public void setMarkContent(String markContent) {
		this.markContent = markContent;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
