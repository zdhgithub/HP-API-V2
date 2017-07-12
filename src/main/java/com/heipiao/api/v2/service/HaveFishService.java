package com.heipiao.api.v2.service;

import java.util.Date;
import java.util.List;

import com.heipiao.api.v2.domain.FishSiteBase;
import com.heipiao.api.v2.domain.HaveFish;
import com.heipiao.api.v2.domain.HaveFishComment;
import com.heipiao.api.v2.domain.HaveFishLike;
import com.heipiao.api.v2.domain.PageInfo;

/**
 * 有鱼
 * @author Duzh
 *
 */
public interface HaveFishService {
	
	/**
	 * 获取有鱼详情列表
	 */
	List<HaveFish> getHaveFishList(Integer uid,Integer start,Double longitude,Double latitude,Integer isSelf);

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
	
	/**
	 * 有鱼点赞
	 */
	boolean addLikeUser(HaveFishLike fishHaveLike);
	/**
	 * 有鱼评论
	 */
	void addCommentUser(HaveFishComment fishHaveComment);
	/**
	 * 获取钓场基本配置信息
	 */
	PageInfo<List<FishSiteBase>> getAllFishSiteSet(Integer start,Integer size,Integer provinceId,Integer cityId,Date regBegin,Date regEnd);

	/**
	 * 审核钓场信息
	 */
	void updateFishSiteBase(Integer uid,Integer status);
	
	/**
	 * 获取有鱼列表
	 */
	PageInfo<List<HaveFish>> getAllHaveFishByPage(Integer start,Integer size,Integer provinceId,Integer cityId,Date regBegin,Date regEnd,Integer type,String nickname);
	
	/**
	 * 审核有鱼显示与否
	 */
	void updateHaveFish(Integer id,Integer isDisplay);
	
	/**
	 *
	 */
	HaveFish getOneHaveFish(Integer id);
}
