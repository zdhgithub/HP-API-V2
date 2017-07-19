package com.heipiao.api.v2.domain;

import java.util.Date;

/**
 * 
 * @author duzh
 * @date 2017年7月8日
 * @version 1.0
 * 钓场基本信息
 */
public class HaveFishDefault {

	/**
	 * 钓场主id
	 */
	private Integer uid;
	/**
	 * 钓场主姓名
	 */
	private String userName;
	/**
	 * 钓场名称
	 */
	private String fishSiteName;
	/**
	 * 联系方式
	 */
	private String phone;
	/**
	 * 钓场经度
	 */
	private Double lon;
	/**
	 * 钓场纬度
	 */
	private Double lat;
	/**
	 * 注册时间
	 */
	private Date setTime;
	/**
	 * 详细地址
	 */
	private String address;

	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getFishSiteName() {
		return fishSiteName;
	}
	public void setFishSiteName(String fishSiteName) {
		this.fishSiteName = fishSiteName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Date getSetTime() {
		return setTime;
	}
	public void setSetTime(Date setTime) {
		this.setTime = setTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setName(String userName) {
		this.userName = userName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
