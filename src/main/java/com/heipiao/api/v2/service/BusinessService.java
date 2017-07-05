package com.heipiao.api.v2.service;

import java.util.Date;
import java.util.List;

import com.heipiao.api.v2.domain.Business;
import com.heipiao.api.v2.domain.PageInfo;

/**
 * 网络体验中心舜微商
 * @author Duzh
 *
 */
public interface BusinessService {
	
	/**
	 * 微商
	 * @param alliance
	 */
	void addBusiness(Business business);
	
	/**
	 * 审核舜微商信息（CP）
	 * @param uid 加盟申请流水号
	 * @param status 状态
	 */
	void Auditing(int uid, int status);
	
	/**
	 * 修改舜微商信息
	 * @param uid 用户id
	 * @param alliance
	 */
	void updateBusiness(int uid, Business business);
	
	/**
	 * 修改加盟商信息(重新申请接口使用)
	 * @param uid 用户id
	 * @param alliance
	 */
	void updateBusinessForApply(int uid, Business alliance);
	
	/**
	 * 查用户状态
	 * @param uid 用户id
	 * @return 
	 */
	Integer getStatusByUid(int uid);
	
	/**
	 * 查所有舜微商（CP）
	 * @return
	 */
	PageInfo<List<Business>> getBusinessList(Date regBegin,Date regEnd,String address,int start,int size);
	
	/**
	 * 查询指定舜微商
	 * @param uid
	 * @return
	 */
	Business getBusinessByUid(int uid);
	
	/**
	 * 删除指定舜微商
	 * @param id 舜微商流水号id
	 */
	void deleteBusinessById(int id);
	
}
