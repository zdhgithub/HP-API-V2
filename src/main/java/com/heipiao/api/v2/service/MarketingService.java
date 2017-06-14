package com.heipiao.api.v2.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.heipiao.api.v2.domain.LikeUser;
import com.heipiao.api.v2.domain.Marketing;
import com.heipiao.api.v2.domain.MarketingPicture;
import com.heipiao.api.v2.domain.PageInfo;
import com.heipiao.api.v2.domain.Thumbs;
import com.heipiao.api.v2.exception.ServiceException;

public interface MarketingService {

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
	 * 获取营销活动列表
	 * 
	 * @param status
	 *            0 未发布，1 已发布，2 已结束，3 删除
	 * @return
	 */
	List<Marketing> getList(Map<String, Object> map);
	
	/**
	 * 获取营销活动列表
	 * @param start
	 * @param size
	 * @return
	 */
	List<Marketing> getList(int start, int size);

	/**
	 * 获取总数
	 * 
	 * @param map
	 * @return
	 */
	Integer getMarketingCount(Map<String, Object> map);

	/**
	 * 获取单个营销活动详情
	 * 
	 * @param id
	 * @return
	 */
	Marketing getOneMarketing(Integer id);

	/**
	 * 获取活动的所有发布的图片内容列表
	 * 
	 * @param map
	 * @return
	 */
	List<MarketingPicture> getPictureList(Map<String, Object> map);

	/**
	 * 发布图片内容
	 * 
	 * @param marketingPicture
	 */
	void addPictures(MarketingPicture marketingPicture);

	/**
	 * 获取用户发布的内容
	 * 
	 * @param map
	 * @return MarketingPicture
	 */
	MarketingPicture getOneMaretingPicture(Map<String, Object> map);

	/**
	 * 添加点赞用户
	 * 
	 * @param likeUser
	 */
	void addLikeUser(LikeUser likeUser) throws ServiceException;

	/**
	 * 查询点赞用户
	 * 
	 * @param map
	 * @return
	 */
	LikeUser getOneLikeUser(Map<String, Object> map);

	/**
	 * 更新上传图片内容
	 * 
	 * @param map
	 */
	void updateMarketingPicture(Map<String, Object> map);
	
	/**
	 * 查询用户有没有参加活动
	 * @param uid 用户id
	 * @param mid 活动id
	 * @return
	 */
	Integer isJoin(Long uid, Integer mid);
	
	/**
	 * 修改发布图片内容
	 * @param marketingPicture
	 */
	void updatePictures(Map<String, Object> map);

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
	PageInfo<List<Thumbs>> getThumbsWithPage(Integer mid, Integer status, Integer start, Integer size, Date begin, Date end);
	
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
