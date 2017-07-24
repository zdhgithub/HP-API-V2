package com.heipiao.api.v2.domain;

import java.util.Date;

/**
 * 
 * @author duzh
 * @date 2017年7月18日
 * @version 1.0
 * 钓场员工
 */
public class FishSiteEmployee {
	private Integer id;
	/**
	 * 员工id
	 */
	private Integer employeeUid;
	/**
	 * 钓场主id
	 */
	private Integer uid;
	/**
	 * 添加时间
	 */
	private Date addTime;
	/**
	 * 身份 职位（0-老板，1-员工）
	 * @return
	 */
	private Integer position;
	/**
	 * 昵称
	 * @return
	 */
	private String nickName;
	/**
	 * 头像
	 * @return
	 */
	private String portriat;
	/**
	 * 员工状态
	 * @return
	 */
	private Integer status;
	
	
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public Integer getEmployeeUid() {
		return employeeUid;
	}
	public void setEmployeeUid(Integer employeeUid) {
		this.employeeUid = employeeUid;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
	
}
