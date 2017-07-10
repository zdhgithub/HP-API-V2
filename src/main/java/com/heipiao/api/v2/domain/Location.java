package com.heipiao.api.v2.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("定位信息")
public class Location {
	
	@ApiModelProperty(value = "省份id")
	private int provinceId;
	
	@ApiModelProperty(value = "省份")
	private String province;

	@ApiModelProperty(value = "城市id")
	private int cityId;
	
	@ApiModelProperty(value = "城市")
	private String city;

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Location [provinceId=" + provinceId + ", province=" + province + ", cityId=" + cityId + ", city=" + city
				+ "]";
	}

}
