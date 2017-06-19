package com.heipiao.api.v2.domain;

import java.util.Date;

public class Thumbs {

	/**
	 * 营销活动id
	 */
	private Integer mid;

	/**
	 * 上传图片用户id
	 */
	private Long uid;

	/**
	 * 上传图片 必须是3张图片
	 */
	private String picture;

	/**
	 * 图片描述
	 */
	private String pictureDesc;

	/**
	 * 点赞数
	 */
	private Integer likeCount;

	/**
	 * 图片审核状态 0 待审核，1 通过，2 未通过
	 */
	private Integer status;

	/**
	 * 上传时间
	 */
	private Date uploadTime;

	/**
	 * 审核不通过原因 
	 */
	private String refundReason;

	/**
	 * 审核不通过时间
	 */
	private Date refundTime;

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getPictureDesc() {
		return pictureDesc;
	}

	public void setPictureDesc(String pictureDesc) {
		this.pictureDesc = pictureDesc;
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

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public Date getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}

	@Override
	public String toString() {
		return "Thumbs [mid=" + mid + ", uid=" + uid + ", picture=" + picture + ", pictureDesc=" + pictureDesc
				+ ", likeCount=" + likeCount + ", status=" + status + ", uploadTime=" + uploadTime + ", refundReason="
				+ refundReason + ", refundTime=" + refundTime + "]";
	}

}
