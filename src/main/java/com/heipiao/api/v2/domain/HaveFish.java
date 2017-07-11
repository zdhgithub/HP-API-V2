package com.heipiao.api.v2.domain;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author duzh
 * @date 2017年7月8日
 * @version 1.0
 * 有鱼
 */
public class HaveFish {

	/**
	 * 唯一标识
	 */
	private Integer id;
	/**
	 *发布有鱼用户 
	 */
	private Integer uid;
	/**
	 *用户昵称 
	 */
	private String nickName;
	/**
	 *用户头像
	 */
	private String portriat;
	/**
	 * 有鱼标题
	 */
	private String title;
	/**
	 * 内容类型
	 */
	private Integer type;
	/**
	 * 发布钓点
	 */
	private String fishSiteName;
	/**
	 *联系方式
	 */
	private String phone;
	/**
	 * 有鱼内容地址
	 */
	private String url;
	/**
	 * 发布时间
	 */
	private Date publishTime;
	/**
	 * 发布经度
	 */
	private Double lon;
	/**
	 * 发布纬度
	 */
	private Double lat;
	
	/**
	 * 发布省份编号
	 */
	private Integer provinceId;
	/**
	 * 省份名称
	 */
	private String provinceName;
	/**
	 * 发布城市编号
	 */
	private Integer cityId;
	/**
	 * 发布城市名称
	 */
	private String cityName;
	/**
	 * 是否是默认配置
	 */
	private Integer isDefault;
	/**
	 * 是否显示
	 */
	private Integer isDisplay;
	/**
	 * 点赞用户
	 */
	private String haveFishLikes;
	/**
	 * 评论用户
	 */
	private List<HaveFishComment> haveFishComments;
	/**
	 * 距离
	 * @return
	 */
	private Integer duration;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getFishSiteName() {
		return fishSiteName;
	}
	public void setFishSiteName(String fishSiteName) {
		this.fishSiteName = fishSiteName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	public Integer getIsDisplay() {
		return isDisplay;
	}
	public void setIsDisplay(Integer isDisplay) {
		this.isDisplay = isDisplay;
	}
	
	public String getHaveFishLikes() {
		return haveFishLikes;
	}
	public void setHaveFishLikes(String haveFishLikes) {
		this.haveFishLikes = haveFishLikes;
	}
	public List<HaveFishComment> getHaveFishComments() {
		return haveFishComments;
	}
	public void setHaveFishComments(List<HaveFishComment> haveFishComments) {
		this.haveFishComments = haveFishComments;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
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
	
}
