package com.heipiao.api.v2.domain;


/**
 * 
 * @author duzh
 * @date 2017年7月8日
 * @version 1.0
 * 有鱼评论
 */
public class HaveFishComment {

	/**
	 * 评论内容
	 */
	private String comment;
	/**
	 * 评论用户昵称
	 */
	private String nickName;
	
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
	
}
