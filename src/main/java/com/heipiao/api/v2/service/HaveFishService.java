package com.heipiao.api.v2.service;

import java.util.List;

import com.heipiao.api.v2.domain.FishSiteBase;
import com.heipiao.api.v2.domain.HaveFish;

/**
 * 有鱼
 * @author Duzh
 *
 */
public interface HaveFishService {
	
	/**
	 * 获取有鱼详情列表
	 */
	List<HaveFish> getHaveFishList(Integer uid,Integer start,Double longitude,Double latitude);
	
	/**
	 * 获取有鱼默认列表
	 */
	List<HaveFish> getHaveFishAllList(Integer uid,Integer start,Integer size,Double longitude,Double latitude);
	/**
	 * 发布有鱼
	 */
	void addHaveFish(HaveFish haveFish);
	/**
	 * 根据uid获取用户的钓场默认设置
	 */
	FishSiteBase getDefaultSet(Integer uid);
	/**
	 * 添加钓场基本设置
	 */
	void addFishSiteBase(FishSiteBase fishSiteBase);
}
