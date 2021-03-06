package com.heipiao.api.v2.domain;

import java.util.Date;

/**
 * 
 * @author duzh
 * @date 2017年7月8日
 * @version 1.0
 * 钓场基本信息
 */
public class FishSiteBase {

	/**
	 * 钓场主id
	 */
	private Integer fishSiteUid;
	/**
	 * 钓场主姓名
	 */
	private String userName;
	/**
	 *用户昵称 
	 */
	private String nickName;
	/**
	 *用户头像
	 */
	private String portriat;
	/**
	 * 钓场名称
	 */
	private String fishSiteName;
	/**
	 * 联系方式
	 */
	private String phone;
	/**
	 * 审核状态
	 */
	private Integer status;
	/**
	 * 钓场经度
	 */
	private Double lon;
	/**
	 * 钓场纬度
	 */
	private Double lat;
	/**
	 * 钓场省份编号
	 */
	private Integer provinceId;
	/**
	 * 钓场省份名称
	 */
	private String provinceName;
	/**
	 * 钓场城市编号
	 */
	private Integer cityId;
	/**
	 * 钓场城市名称
	 */
	private String cityName;
	/**
	 * 注册时间
	 */
	private Date setTime;
	/**
	 * 详细地址
	 */
	private String address;
	/**
	 * 钓场是否申请了标鱼（0-未申请，1-申请）
	 */
	private Integer applyMark;

	public Integer getFishSiteUid() {
		return fishSiteUid;
	}
	public void setFishSiteUid(Integer fishSiteUid) {
		this.fishSiteUid = fishSiteUid;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
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
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPortriat() {
		return portriat;
	}
	public void setPortriat(String portriat) {
		this.portriat = portriat;
	}
	public Integer getApplyMark() {
		return applyMark;
	}
	public void setApplyMark(Integer applyMark) {
		this.applyMark = applyMark;
	}

	
}
