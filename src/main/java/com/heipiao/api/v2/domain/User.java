package com.heipiao.api.v2.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author Chris
 *
 */
@ApiModel(description = "用户")
@Entity
public class User implements Serializable {

	private static final long serialVersionUID = -1632393744255837723L;

	@Id
	private Long id;
	
	@ApiModelProperty(dataType = "String", example = "13812345678", value = "用户名")
	private String username;
	
	@ApiModelProperty(dataType = "String", value = "密码")
	private String password;
	
	@ApiModelProperty(dataType = "String", example = "昵称", value = "昵称")
	private String nickname;
	
	@ApiModelProperty(dataType = "String", value = "第三方登录id")
	private String openId;
	
	@ApiModelProperty(dataType = "Date", example = "2017-06-23 16:27:21", value = "注册时间")
	private Date registerTime;
	
	@ApiModelProperty(dataType = "Integer", value = "省份id")
	private Integer provinceId;
	
	@ApiModelProperty(dataType = "String", example = "广东省", value = "省份")
	private String province;
	
	@ApiModelProperty(dataType = "String", value = "城市id")
	private Integer cityId;
	
	@ApiModelProperty(dataType = "String", example = "深圳市", value = "城市")
	private String city;
	
	@ApiModelProperty(dataType = "String", value = "用户头像地址")
	private String portriat;
	
	@ApiModelProperty(dataType = "Date", example = "2017-06-23 16:32:37", value = "最后登录时间")
	private Date lastLoginTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPortriat() {
		return portriat;
	}

	public void setPortriat(String portriat) {
		this.portriat = portriat;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", nickname=" + nickname
				+ ", openId=" + openId + ", registerTime=" + registerTime + ", provinceId=" + provinceId + ", province="
				+ province + ", cityId=" + cityId + ", city=" + city + ", portriat=" + portriat + ", lastLoginTime="
				+ lastLoginTime + "]";
	}

}
