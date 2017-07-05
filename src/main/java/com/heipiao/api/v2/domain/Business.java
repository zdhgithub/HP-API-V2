package com.heipiao.api.v2.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "舜微商信息")
@Entity
@Table(name = "t_business")
public class Business {

	@ApiModelProperty(dataType = "Integer", example = "1", value = "舜微商流水号", notes = "系统自动生成对用户透明", position = 0, required = true)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id", nullable = false, insertable = false, updatable = false)
	private Integer id;

	@ApiModelProperty(dataType = "Integer", example = "3475", value = "用户id", notes = "", position = 1, required = true)
	@Column(name = "f_uid", unique = true, nullable = false, insertable = true, updatable = false)
	private Integer uid;

	@ApiModelProperty(dataType = "String", example = "13812345678", value = "手机号码", notes = "", position = 2, required = true)
	@Column(name = "f_phone_number", length = 15, nullable = false, insertable = true, updatable = true)
	private String phoneNumber;

	@ApiModelProperty(dataType = "String", example = "张三", value = "姓名", notes = "", position = 3, required = true)
	@Column(name = "f_name", length = 50, nullable = false, insertable = true, updatable = true)
	private String name;

	@ApiModelProperty(dataType = "String", example = "深圳市福田区车公庙泰然工贸园213栋3BV9", value = "店铺地址", notes = "", position = 4, required = true)
	@Column(name = "f_address", length = 100, nullable = false, insertable = true, updatable = true)
	private String address;

	@ApiModelProperty(dataType = "Double", example = "114.032428", value = "经度", notes = "", position = 5, required = true)
	@Column(name = "f_longitude", nullable = false, insertable = true, updatable = true)
	private Double longitude;

	@ApiModelProperty(dataType = "Double", example = "22.538205", value = "纬度", notes = "", position = 6, required = true)
	@Column(name = "f_latitude", nullable = false, insertable = true, updatable = true)
	private Double latitude;

	@ApiModelProperty(dataType = "Integer", example = "0", value = "状态", notes = "0-待审核；1-通过；2-不通过；3下架", position = 7, required = true)
	@Column(name = "f_status", nullable = false, insertable = true, updatable = true)
//	@Transient
	private Integer status;

	@ApiModelProperty(dataType = "String", example = "{'540':'1','630':'0','720':'1','810':'0','900':'1'}", value = "体验库存", notes = "key为竿长(mm)，value值1为有体验库存0为没有体验库存", position = 8, required = false)
	@Column(name = "f_stock", length = 1000, nullable = true, insertable = false, updatable = true)
	private String stock;

	@ApiModelProperty(dataType = "Datetime", example = "2017-05-22 15:32:44", value = "申请时间", notes = "", position = 9, required = false)
	@Column(name = "f_apply_time", nullable = false, insertable = true, updatable = true)
//	@Transient // javax.persistence.Transient
	private Date applyTime;

	@ApiModelProperty(dataType = "Datetime", example = "2017-05-22 15:32:53", value = "审核时间", notes = "", position = 10, required = false)
	@Column(name = "f_audit_time", nullable = true, insertable = false, updatable = true)
//	@Transient
	private Date auditTime;
	
	@ApiModelProperty(dataType = "Integer", example = "75", value = "距离", position = 11, required = false)
	@Column(name = "duration")
	@Transient
	private Integer duration;
	
	@ApiModelProperty(dataType = "Integer", example = "2", value = "送货上门（2、5、10 公里）", position = 12, required = false)
	@Column(name = "f_delivery", nullable = false, insertable = true, updatable = true)
	//@Transient
	private Integer delivery;
	
	@ApiModelProperty(dataType = "String", example = "silence", value = "昵称", position = 13, required = false)
	@Column(name = "nickname")
	@Transient
	private Integer nickname;
	
	@ApiModelProperty(dataType = "String", example = "3347/0.jpg", value = "头像", position = 14, required = false)
	@Column(name = "portriat")
	@Transient
	private Integer portriat;

	public Business() {
		super();
	}

	public Integer getDelivery() {
		return delivery;
	}

	public void setDelivery(Integer delivery) {
		this.delivery = delivery;
	}

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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getNickname() {
		return nickname;
	}

	public void setNickname(Integer nickname) {
		this.nickname = nickname;
	}

	public Integer getPortriat() {
		return portriat;
	}

	public void setPortriat(Integer portriat) {
		this.portriat = portriat;
	}

	@Override
	public String toString() {
		return "Alliance [id=" + id + ", uid=" + uid + ", phoneNumber=" + phoneNumber + ", shopName="
				+ ", name=" + name + ", address=" + address + ", longitude=" + longitude + ", latitude=" + latitude
				+ ", status=" + status + ", stock=" + stock + ", applyTime=" + applyTime + ", auditTime=" + auditTime
				+ ", duration=" + duration + ", nickname="+nickname+", portriat="+portriat+"]";
	}

}
