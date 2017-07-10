package com.heipiao.api.v2.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * 点赞活动
 * @author Chris
 *
 */
@ApiModel(description = "点赞活动用户上传列表")
@Entity
public class ThumbsResult implements Serializable {
	
	private static final long serialVersionUID = 2226411076940063748L;

	@Id
	private Integer mid;
	
	@Id
	private Integer uid;
	
	@ApiModelProperty(dataType = "String", example = "Chris", value = "上传用户昵称")
	private String nickname;
	
	@ApiModelProperty(dataType = "String", example = "chris.jpg", value = "用户头像")
	private String portriat;
	
	@ApiModelProperty(dataType = "Date", example = "2017-06-13 20:21:41", value = "上传时间")
	private Date uploadTime;
	
	@ApiModelProperty(dataType = "Integer", example = "1011", value = "点赞数")
	private Integer likeCount;
	
	@ApiModelProperty(dataType = "Integer", example = "1", value = "审核状态", notes = "0待审核，1通过，2未通过")
	private Integer status;
	
	@ApiModelProperty(dataType = "String", example = "今天爆护了", value = "描述")
	private String pictureDesc;
	
	@ApiModelProperty(dataType = "String", example = "1.jpg,2.jpg,3.jpg", value = "上传的图片", notes = "分隔符英文逗号")
	private String picture;
	
	@ApiModelProperty(dataType = "String", example = "图片内容并非与钓鱼相关", value = "拒绝原因")
	private String refundReason;
	
	@ApiModelProperty(dataType = "Date", example = "2017-06-13 20:21:41", value = "拒绝时间")
	private String refundTime;
	
	@ApiModelProperty(dataType = "String", example = "Chris, 黑漂用户1", value = "点赞用户列表", notes = "分隔符英文逗号")
	private String likeAry;
	
	@ApiModelProperty(dataType = "Integer", example = "1", value = "排名")
	private int ranking;

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPortriat() {
		return portriat;
	}

	public void setPortriat(String portriat) {
		this.portriat = portriat;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPictureDesc() {
		return pictureDesc;
	}

	public void setPictureDesc(String pictureDesc) {
		this.pictureDesc = pictureDesc;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public String getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
	}

	public String getLikeAry() {
		return likeAry;
	}

	public void setLikeAry(String likeAry) {
		this.likeAry = likeAry;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	@Override
	public String toString() {
		return "ThumbsResult [mid=" + mid + ", uid=" + uid + ", nickname=" + nickname + ", portriat=" + portriat
				+ ", uploadTime=" + uploadTime + ", likeCount=" + likeCount + ", status=" + status + ", pictureDesc="
				+ pictureDesc + ", picture=" + picture + ", refundReason=" + refundReason + ", refundTime=" + refundTime
				+ ", likeAry=" + likeAry + ", ranking=" + ranking + "]";
	}

}
