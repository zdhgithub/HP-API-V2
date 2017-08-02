package com.heipiao.api.v2.domain;

/**
 * 
 * @author duzh
 * @date 2017年7月8日
 * @version 1.0
 * 有鱼
 */
public class DeliveryAddress {

	/**
	 * 唯一标识
	 */
	private Integer id;
	/**
	 *用户 
	 */
	private Integer uid;
	/**
	 *用户名 
	 */
	private String name;
	/**
	 *联系方式
	 */
	private String phone;
	/**
	 * 发布省份编号
	 */
	private Integer provinceId;
	/**
	 * 省份名称
	 */
	private String provinceName;
	/**
	 * 发布城市编号
	 */
	private Integer cityId;
	/**
	 * 发布城市名称
	 */
	private String cityName;
	/**
	 * 区域编号
	 */
	private Integer regionId;
	/**
	 * 区域名称
	 */
	private String regionName;
	/**
	 * 详细地址
	 */
	private String address;
	/**
	 * 是否是默认设置
	 */
	private Integer isDefault;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public Integer getRegionId() {
		return regionId;
	}
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	
}
