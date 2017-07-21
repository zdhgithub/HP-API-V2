package com.heipiao.api.v2.domain;

/**
 * 微信用户参数
 * @author Chris
 *
 */
public class MPLoginInfo {
	
	private String code;
	
	private String userInfo;
	
	private Integer parentUid;
	
	private Double lat;
	
	private Double lon;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public Integer getParentUid() {
		return parentUid;
	}

	public void setParentUid(Integer parentUid) {
		this.parentUid = parentUid;
	}

	
	
	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	@Override
	public String toString() {
		return "MPLoginInfo [code=" + code + ", userInfo=" + userInfo + ", parentUid="+parentUid+"]";
	}

}
