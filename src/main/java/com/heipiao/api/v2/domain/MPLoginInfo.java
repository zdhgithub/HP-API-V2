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

	@Override
	public String toString() {
		return "MPLoginInfo [code=" + code + ", userInfo=" + userInfo + ", parentUid="+parentUid+"]";
	}

}
