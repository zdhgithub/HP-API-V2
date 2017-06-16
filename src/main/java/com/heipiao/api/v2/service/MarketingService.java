package com.heipiao.api.v2.service;

import java.sql.Date;
import java.util.List;

import com.heipiao.api.v2.domain.Marketing;
import com.heipiao.api.v2.domain.PageInfo;
import com.heipiao.api.v2.domain.Thumbs;
import com.heipiao.api.v2.domain.ThumbsResult;

public interface MarketingService {

	
	/**
	 * 获取营销活动列表
	 * @param status
	 * @param start
	 * @param size
	 * @return
	 */
	PageInfo<List<Marketing>> getMarketingList(Integer status, Integer start, Integer size);

	/**
	 * 获取总数
	 * @param status
	 * @return
	 */
	Integer getMarketingCount(Integer status);

	/**
	 * 获取单个营销活动详情
	 * 
	 * @param id
	 * @return
	 */
	Marketing getMarketing(int id);

	/**
	 * 添加营销活动
	 * 
	 * @param marketing
	 */
	void addMarketing(Marketing marketing);

	/**
	 * 修改营销活动状态 status 0 未发布，1 已发布，2 已结束，3 删除
	 * 
	 * @param map
	 */
	void updateMarketing(Marketing marketing);

	/**
	 * 获取活动的所有发布的图片内容列表
	 * @param mid
	 * @param uid
	 * @param start
	 * @param size
	 * @return
	 */
	List<ThumbsResult> getThumbsList(int mid, long uid, int start, int size);

	/**
	 * 发布图片内容
	 * 
	 * @param thumbs
	 */
	void addThumbs(Thumbs thumbs);
	
	/**
	 * 修改发布图片内容
	 * @param mid
	 * @param uid
	 * @param thumbs
	 */
	void updateThumbs(int mid, long uid, Thumbs thumbs);

	/**
	 * 获取用户发布的内容
	 * @param mid
	 * @param uid
	 * @return Thumbs
	 */
	ThumbsResult getThumbs(int mid, long uid);

	/**
	 * 添加点赞用户
	 * @param mid
	 * @param uid
	 * @param likeUid
	 */
	void like(int mid, long uid, long likeUid);

	/**
	 * 查询用户是否点赞
	 * @param mid
	 * @param uid
	 * @param likeUid
	 * @return 返回true为已点赞，否则为未点赞
	 */
	boolean isLike(int mid, long uid, long likeUid);
	
	/**
	 * 查询用户有没有参加活动
	 * @param mid 活动id
	 * @param uid 用户id
	 * @return
	 */
	boolean isJoin(int mid, long uid);

	/**
	 * 获取所有点赞活动发布图片的列表
	 * @param mid 点赞活动id
	 * @param status 审核状态
	 * @param start 起始页
	 * @param size 页大小
	 * @param begin 起始时间
	 * @param end 结束时间
	 * @return
	 */
	PageInfo<List<ThumbsResult>> getThumbsWithPage(int mid, Integer status, int start, int size, Date begin, Date end);
	
	/**
	 * 审核点赞活动
	 * @param mid 点赞活动id
	 * @param uid 用户id
	 * @param status 状态
	 * @param reason 拒绝原因
	 * @return
	 */
	Integer audit(Integer mid, Integer uid, Integer status, String reason);

}
