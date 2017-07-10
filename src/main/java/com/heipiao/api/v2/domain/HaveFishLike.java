package com.heipiao.api.v2.domain;

import java.util.Date;

/**
 * 
 * @author duzh
 * @date 2017年7月8日
 * @version 1.0
 * 有鱼点赞
 */
public class HaveFishLike {
	/**
	 * 唯一主键id
	 */
	private Integer id;
	/**
	 * 被点赞有鱼id
	 */
	private Integer haveFishId;
	/**
	 * 点赞用户id
	 */
	private Integer uid;
	/**
	 * 点赞时间
	 */
	private Date likeTime;
	/**
	 * 点赞用户昵称
	 */
	private String nickName;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	public Integer getHaveFishId() {
		return haveFishId;
	}
	public void setHaveFishId(Integer haveFishId) {
		this.haveFishId = haveFishId;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Date getLikeTime() {
		return likeTime;
	}
	public void setLikeTime(Date likeTime) {
		this.likeTime = likeTime;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
}
