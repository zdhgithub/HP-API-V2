package com.heipiao.api.v2.service;

import java.util.List;

import com.heipiao.api.v2.domain.FishSiteBaseInfo;
import com.heipiao.api.v2.domain.FishSiteEmployee;
import com.heipiao.api.v2.domain.HaveFish;

/**
 * 钓点
 * @author Duzh
 *
 */
public interface FishSizeService {
	
	/**
	 * 钓点列表
	 */
	List<FishSiteBaseInfo> getFishsiteList(Integer start,Integer size,Double longitude,Double latitude);

	/**
	 *查看钓点详情
	 */
	FishSiteBaseInfo getfishSiteDetial(Integer uid,Double longitude,Double latitude);
	/**
	 * 钓场发布的所有有鱼列表
	 */
	List<HaveFish>  getHaveFishAllList(Integer uid,Integer start,Double lon,Double lat); 
	/**
	 * 修改钓场信息
	 */
	void addFishSiteBaseInfo(FishSiteBaseInfo fishSiteBaseInfo);
	/**
	 * 钓场员工列表
	 */
	List<FishSiteEmployee> getEmployee(Integer uid);
	
	/**
	 * 删除员工
	 */
	void deleteFishSiteEmployee(Integer id);
	/**
	 *添加员工
	 */
	void addFishSiteEmployee(Integer uid,Integer employeeUid);
	/**
	 * 搜索可添加员工
	 */
	FishSiteEmployee getUsableEmployee(String phone);
}
