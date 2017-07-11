package com.heipiao.api.v2.domain;

import java.util.Date;

/**
 * 
 * @author duzh
 * @date 2017年7月8日
 * @version 1.0
 * 有鱼评论
 */
public class HaveFishComment {
	/**
	 * 有鱼id
	 */
	private Integer haveFishId;
	/**
	 * 评论内容
	 */
	private String comment;
	/**
	 * 评论用户昵称
	 */
	private String nickName;
	/**
	 * 评论时间
	 * @return
	 */
	private Date commentTime;
	/**
	 * 评论用户
	 * @return
	 */
	private Integer uid;
	
	
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getHaveFishId() {
		return haveFishId;
	}
	public void setHaveFishId(Integer haveFishId) {
		this.haveFishId = haveFishId;
	}
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
}
