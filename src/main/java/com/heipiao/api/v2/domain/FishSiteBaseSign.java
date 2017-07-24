package com.heipiao.api.v2.domain;

import java.util.Date;

/**
 * 
 * @author duzh
 * @date 2017年7月18日
 * @version 1.0
 * 钓场员工
 */
public class FishSiteBaseSign {
	/**
	 * 钓场主id
	 */
	private Integer uid;
	/**
	 * 签到时间
	 */
	private Date signTime;
	/**
	 * 昵称
	 * @return
	 */
	private String nickName;
	/**
	 * 头像
	 * @return
	 */
	private String portriat;
	/**
	 * 签到uid
	 * @return
	 */
	private Integer signUid;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Date getSignTime() {
		return signTime;
	}
	public void setSignTime(Date signTime) {
		this.signTime = signTime;
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
	public Integer getSignUid() {
		return signUid;
	}
	public void setSignUid(Integer signUid) {
		this.signUid = signUid;
	}
	
	
	
}
