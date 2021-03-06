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

@ApiModel(description = "加盟商信息")
@Entity
@Table(name = "t_mp_alliance")
public class Alliance {

	@ApiModelProperty(dataType = "Integer", example = "1", value = "加盟商流水号", notes = "系统自动生成对用户透明", position = 0, required = true)
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

	@ApiModelProperty(dataType = "String", example = "深圳市黑漂渔具店", value = "渔具店名称", notes = "", position = 3, required = true)
	@Column(name = "f_shop_name", length = 100, nullable = false, insertable = true, updatable = true)
	private String shopName;

	@ApiModelProperty(dataType = "String", example = "张三", value = "姓名", notes = "", position = 4, required = true)
	@Column(name = "f_name", length = 50, nullable = false, insertable = true, updatable = true)
	private String name;

	@ApiModelProperty(dataType = "String", example = "深圳市福田区车公庙泰然工贸园213栋3BV9", value = "店铺地址", notes = "", position = 5, required = true)
	@Column(name = "f_address", length = 100, nullable = false, insertable = true, updatable = true)
	private String address;

	@ApiModelProperty(dataType = "Double", example = "114.032428", value = "经度", notes = "", position = 6, required = true)
	@Column(name = "f_longitude", nullable = false, insertable = true, updatable = true)
	private Double longitude;

	@ApiModelProperty(dataType = "Double", example = "22.538205", value = "纬度", notes = "", position = 7, required = true)
	@Column(name = "f_latitude", nullable = false, insertable = true, updatable = true)
	private Double latitude;

	@ApiModelProperty(dataType = "Integer", example = "0", value = "状态", notes = "0待审核，1审核已通过，-1审核未通过", position = 8, required = true)
	@Column(name = "f_status", nullable = false, insertable = true, updatable = true)
//	@Transient
	private Integer status;

	@ApiModelProperty(dataType = "String", example = "{'540':'1','630':'0','720':'1','810':'0','900':'1'}", value = "体验库存", notes = "key为竿长(mm)，value值1为有体验库存0为没有体验库存", position = 9, required = false)
	@Column(name = "f_stock", length = 1000, nullable = true, insertable = false, updatable = true)
	private String stock;

	@ApiModelProperty(dataType = "Datetime", example = "2017-05-22 15:32:44", value = "申请加盟时间", notes = "", position = 10, required = false)
	@Column(name = "f_apply_time", nullable = false, insertable = true, updatable = true)
//	@Transient // javax.persistence.Transient
	private Date applyTime;

	@ApiModelProperty(dataType = "Datetime", example = "2017-05-22 15:32:53", value = "审核时间", notes = "", position = 11, required = false)
	@Column(name = "f_audit_time", nullable = true, insertable = false, updatable = true)
//	@Transient
	private Date auditTime;
	
	@ApiModelProperty(dataType = "Integer", example = "75", value = "距离", position = 12, required = false)
	@Column(name = "duration")
	@Transient
	private Integer duration;
	
	@ApiModelProperty(dataType = "Integer", example = "1", value = "类型（0:代理商，1:舜微商）", position = 13, required = false)
	@Column(name = "type")
	@Transient
	private Integer type;
	
	@ApiModelProperty(dataType = "Integer", example = "2", value = "送货上门（2、5、10 公里）", position = 14, required = false)
	@Column(name = "delivery")
	@Transient
	private Integer delivery;

	public Alliance() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
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

	public Integer getDelivery() {
		return delivery;
	}

	public void setDelivery(Integer delivery) {
		this.delivery = delivery;
	}

	@Override
	public String toString() {
		return "Alliance [id=" + id + ",type="+ type +",uid=" + uid + ", phoneNumber=" + phoneNumber + ", shopName=" + shopName
				+ ", name=" + name + ", address=" + address + ", longitude=" + longitude + ", latitude=" + latitude
				+ ", status=" + status + ", stock=" + stock + ", applyTime=" + applyTime + ", auditTime=" + auditTime
				+ ", duration=" + duration + "]";
	}

}
