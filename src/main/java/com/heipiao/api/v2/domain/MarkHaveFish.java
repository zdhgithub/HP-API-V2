package com.heipiao.api.v2.domain;

import java.sql.Date;

/**
 * 标鱼表
 * @author duzh
 * @date 2017年7月28日
 * @version 1.0
 */
public class MarkHaveFish {

	private Integer id;
	/**
	 * 发布人（钓场主或管理员用户id）
	 */
	private Integer publishUid;
	/**
	 * 发布用户昵称
	 */
	private String pubNickName;
	/**
	 * 发布人头像
	 */
	private String pubPortrait;
	/**
	 * 中标钓友用户id
	 */
	private Integer rewardUid;
	/**
	 * 标鱼视频地址
	 */
	private String url;
	/**
	 * 钓场名称
	 */
	private String siteName;
	/**
	 * 鱼大小
	 */
	private String fishSize;
	/**
	 * 鱼标编号
	 */
	private String markNum;
	/**
	 * 审核时间
	 */
	private Date auditTime;
	/**
	 * 审核状态（0-待审核，1-审核通过，2-审核不通过）
	 */
	private Integer auditStatus;
	/**
	 * 审核结果
	 */
	private String auditResult;
	/**
	 * 发放结果
	 */
	private String grantResult;
	/**
	 * 视频上传时间
	 */
	private Date uploadTime;
	/**
	 *奖品内容
	 */
	private String content;
	/**
	 * 奖励条件
	 * @return
	 */
	private String condition;
	/**
	 * 送货方式
	 * @return
	 */
	private String delivery;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPublishUid() {
		return publishUid;
	}
	public void setPublishUid(Integer publishUid) {
		this.publishUid = publishUid;
	}
	public Integer getRewardUid() {
		return rewardUid;
	}
	public void setRewardUid(Integer rewardUid) {
		this.rewardUid = rewardUid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getFishSize() {
		return fishSize;
	}
	public void setFishSize(String fishSize) {
		this.fishSize = fishSize;
	}
	public String getMarkNum() {
		return markNum;
	}
	public void setMarkNum(String markNum) {
		this.markNum = markNum;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getAuditResult() {
		return auditResult;
	}
	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}
	public String getGrantResult() {
		return grantResult;
	}
	public void setGrantResult(String grantResult) {
		this.grantResult = grantResult;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getPubNickName() {
		return pubNickName;
	}
	public void setPubNickName(String pubNickName) {
		this.pubNickName = pubNickName;
	}
	public String getPubPortrait() {
		return pubPortrait;
	}
	public void setPubPortrait(String pubPortrait) {
		this.pubPortrait = pubPortrait;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	
	
	
}
