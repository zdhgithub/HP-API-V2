package com.heipiao.api.v2.mapper;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.heipiao.api.v2.domain.LikeUser;
import com.heipiao.api.v2.domain.Marketing;
import com.heipiao.api.v2.domain.MarketingPicture;
import com.heipiao.api.v2.domain.Thumbs;

@Service
public interface MarketingMapper {

	/**
	 * 添加营销活动
	 * 
	 * @param marketing
	 */
	void addMarketing(Marketing marketing);

	/**
	 * 更新活动
	 * 
	 * @param marketing
	 */
	void updateMarketing(Marketing marketing);

	/**
	 * 根据活动状态获取活动
	 * 
	 * @param status
	 *            0 未发布，1 已发布，2已结束，10 删除
	 * @return
	 */
	List<Marketing> getMarketing(Map<String, Object> map);

	Integer getMarketingCount(Map<String, Object> map);

	/**
	 * 根据id获取活动
	 * 
	 * @param id
	 * @return
	 */
	Marketing getMarketingById(@Param("id") Integer id);

	/**
	 * 添加点赞用户
	 * 
	 * @param likeUser
	 */
	void addLikeUser(LikeUser likeUser);

	/**
	 * 获取点赞用户
	 * 
	 * @param map
	 * @return
	 */
	List<LikeUser> getLikeUser(Map<String, Object> map);

	/**
	 * 添加参与活动用户
	 * 
	 * @param marketingPicture
	 */
	void addMarketingPicture(MarketingPicture marketingPicture);

	/**
	 * 更新参与活动的用户内容
	 * 
	 * @param marketingPicture
	 */
	void updatePicture(Map<String, Object> map);

	/**
	 * 获取参与活动用户和内容
	 * 
	 * @param map
	 * @return
	 */
	List<MarketingPicture> getMarketingPicture(Map<String, Object> map);

	/**
	 * 获取单个参与活动用户和内容
	 * 
	 * @param map
	 * @return
	 */
	MarketingPicture getOneMarketingPicture(Map<String, Object> map);

	/**
	 * 查询单个点赞用户
	 * 
	 * @param map
	 * @return
	 */
	LikeUser getOneLikeUser(Map<String, Object> map);
	
	/**
	 * 查询指定用户有没有参加指定的活动
	 * @param uid 用户id
	 * @param mid 活动id
	 * @return
	 */
	Integer isJoin(@Param("uid") Long uid, @Param("mid") Integer mid);
	
	/**
	 * 获取营销活动列表
	 * @param start
	 * @param size 
	 * @return
	 */
	List<Marketing> getMarketingList(@Param("start") int start, @Param("size") int size);
	
	/**
	 * 修改审核状态
	 * 
	 * @param status 状态值
	 */
	public void updateStatus(Map<String,Object> map);

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
	List<Thumbs> getThumbsWithPage(@Param("mid") Integer mid, @Param("status") Integer status
			, @Param("start") Integer start, @Param("size") Integer size, @Param("begin") Date begin, @Param("end") Date end);

	/**
	 * 获取所有点赞活动发布图片的总数
	 * @param mid 点赞活动id
	 * @param status 审核状态
	 * @param begin 起始时间
	 * @param end 结束时间
	 * @return
	 */
	Integer getThumbsTotalCount(@Param("mid") Integer mid, @Param("status") Integer status
			, @Param("begin") Date begin, @Param("end") Date end);

	/**
	 * 审核点赞活动
	 * @param mid 点赞活动id
	 * @param uid 用户id
	 * @param status 审核状态
	 * @param reason 拒绝原因
	 * @param time 拒绝时间
	 * @result
	 */
	Integer audit(@Param("mid") Integer mid, @Param("uid") Integer uid, @Param("status") Integer status
			, @Param("reason") String reason, @Param("time") java.util.Date time);
	
}
