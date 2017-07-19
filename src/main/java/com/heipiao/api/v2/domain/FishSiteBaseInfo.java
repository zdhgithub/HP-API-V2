package com.heipiao.api.v2.domain;


/**
 * 
 * @author duzh
 * @date 2017年7月18日
 * @version 1.0
 * 钓场信息
 */
public class FishSiteBaseInfo extends FishSiteBase{

	/**
	 * 钓场主uid
	 */
	private Integer fishSiteUid;
	/**
	 * 放鱼信息
	 */
	private String putFishInfo;
	/**
	 * 钓场规模
	 */
	private String siteSize;
	/**
	 * 收费情况
	 */
	private String siteCharge;
	/**
	 * 配套设施
	 */
	private String supportFacility;
	/**
	 * 距离
	 */
	private Integer duration;
	/**
	 * 钓场发布的有鱼数
	 */
	private Integer haveFishNum;
	/**
	 * 钓场默认图片
	 * @return
	 */
	private String mainPicture;
	/**
	 * 钓场图片
	 * @return
	 */
	private String image;
	
	
	public String getPutFishInfo() {
		return putFishInfo;
	}
	public void setPutFishInfo(String putFishInfo) {
		this.putFishInfo = putFishInfo;
	}
	public String getSiteSize() {
		return siteSize;
	}
	public void setSiteSize(String siteSize) {
		this.siteSize = siteSize;
	}
	public String getSiteCharge() {
		return siteCharge;
	}
	public void setSiteCharge(String siteCharge) {
		this.siteCharge = siteCharge;
	}
	public String getSupportFacility() {
		return supportFacility;
	}
	public void setSupportFacility(String supportFacility) {
		this.supportFacility = supportFacility;
	}
	public Integer getFishSiteUid() {
		return fishSiteUid;
	}
	public void setFishSiteUid(Integer fishSiteUid) {
		this.fishSiteUid = fishSiteUid;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Integer getHaveFishNum() {
		return haveFishNum;
	}
	public void setHaveFishNum(Integer haveFishNum) {
		this.haveFishNum = haveFishNum;
	}
	public String getMainPicture() {
		return mainPicture;
	}
	public void setMainPicture(String mainPicture) {
		this.mainPicture = mainPicture;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
