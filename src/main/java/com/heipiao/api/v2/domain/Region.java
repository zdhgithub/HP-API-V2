package com.heipiao.api.v2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_sys_cfg_region")
public class Region {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id", nullable = false, insertable = false, updatable = false)
	private int id;

	@Column(name = "f_region_num", nullable = false, insertable = false, updatable = false)
	private int regionNum;
	
	@Column(name = "f_region_name", length = 20, nullable = false, insertable = false, updatable = false)
	private String regionName;
	
	@Column(name = "f_pid", nullable = false, insertable = false, updatable = false)
	private int pid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRegionNum() {
		return regionNum;
	}

	public void setRegionNum(int regionNum) {
		this.regionNum = regionNum;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "Region [id=" + id + ", regionNum=" + regionNum + ", regionName=" + regionName + ", pid=" + pid + "]";
	}

}
