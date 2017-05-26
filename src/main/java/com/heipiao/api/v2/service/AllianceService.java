package com.heipiao.api.v2.service;

import java.util.List;

import com.heipiao.api.v2.domain.Alliance;

/**
 * 网络体验中心加盟商
 * @author Chris
 *
 */
public interface AllianceService {
	
	/**
	 * 加盟
	 * @param alliance
	 */
	void addAlliance(Alliance alliance);
	
	/**
	 * 获取距离最近的?个加盟商
	 * @param count
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	List<Alliance> getTopAllianceList(int count, double longitude, double latitude);
	
	/**
	 * 审核加盟信息（CP）
	 * @param uid 加盟申请流水号
	 * @param status 状态
	 */
	void Auditing(int uid, int status);
	
	/**
	 * 修改加盟商信息
	 * @param uid 用户id
	 * @param alliance
	 */
	void updateAlliance(int uid, Alliance alliance);
	
	/**
	 * 修改加盟商信息(重新申请接口使用)
	 * @param uid 用户id
	 * @param alliance
	 */
	void updateAllianceForApply(int uid, Alliance alliance);
	
	/**
	 * 查用户状态
	 * @param uid 用户id
	 * @return 
	 */
	Integer getStatusByUid(int uid);
	
	/**
	 * 查所有加盟商（CP）
	 * @return
	 */
	List<Alliance> getAllianceList();
	
	/**
	 * 查询指定加盟商
	 * @param uid
	 * @return
	 */
	Alliance getAllianceByUid(int uid);
	
	/**
	 * 删除指定加盟商
	 * @param id 加盟商流水号id
	 */
	void deleteAllianceById(int id);
	
}
