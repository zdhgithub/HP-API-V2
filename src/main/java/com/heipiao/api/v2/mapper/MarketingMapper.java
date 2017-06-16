package com.heipiao.api.v2.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.heipiao.api.v2.domain.Marketing;
import com.heipiao.api.v2.domain.Thumbs;
import com.heipiao.api.v2.domain.ThumbsResult;

@Service
public interface MarketingMapper {
	
	/**
	 * 获取营销活动列表
	 * @param status
	 * @param start
	 * @param size 
	 * @return
	 */
	List<Marketing> getMarketingList(@Param("status") Integer status, @Param("start") Integer start, @Param("size") Integer size);

	/**
	 * 获取营销活动数量
	 * @param status
	 * @return
	 */
	Integer getMarketingCount(@Param("status") Integer status);

	/**
	 * 根据id获取活动
	 * 
	 * @param id
	 * @return
	 */
	Marketing getMarketingById(@Param("id") int id);

	/**
	 * 添加营销活动
	 * 
	 * @param marketing
	 */
	void addMarketing(Marketing marketing);

	/**
	 * 修改营销活动
	 * 
	 * @param marketing
	 */
	void updateMarketing(Marketing marketing);

	/**
	 * 添加点赞用户
	 * @param mid
	 * @param uid
	 * @param likeUid
	 * @param likeTime
	 */
	void addLike(int mid, long uid, long likeUid, Date likeTime);

	/**
	 * 添加参与活动用户
	 * 
	 * @param thumbs
	 */
	void addThumbs(Thumbs thumbs);

	/**
	 * 更新参与活动的用户内容
	 * @param mid
	 * @param uid
	 * @param thumbs
	 */
	void updateThumbs(int mid, long uid, Thumbs thumbs);
	
	/**
	 * 更新参与活动的用户点赞次数（+1）
	 * @param mid
	 * @param uid
	 */
	void updateThumbsLikeCount(int mid, long uid);

	/**
	 * 获取参与点赞活动用户内容
	 * @param mid
	 * @param uid
	 * @param start
	 * @param size
	 * @return
	 */
	List<ThumbsResult> getThumbsList(int mid, long uid, int start, int size);

	/**
	 * 获取指定用户参与点赞活动内容
	 * @param mid
	 * @param uid
	 * @return
	 */
	ThumbsResult getThumbs(@Param("mid") int mid, @Param("uid") long uid);

	/**
	 * 查询单个点赞用户
	 * @param mid
	 * @param uid
	 * @param likeUid
	 * @return
	 */
	Integer isLike(int mid, long uid, long likeUid);
	
	/**
	 * 查询指定用户有没有参加指定的活动
	 * @param mid 活动id
	 * @param uid 用户id
	 * @return
	 */
	Integer isJoin(@Param("mid") Integer mid, @Param("uid") Long uid);

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
	List<ThumbsResult> getThumbsWithPage(@Param("mid") int mid, @Param("status") Integer status
			, @Param("start") int start, @Param("size") int size, @Param("begin") java.sql.Date begin, @Param("end") java.sql.Date end);

	/**
	 * 获取所有点赞活动发布图片的总数
	 * @param mid 点赞活动id
	 * @param status 审核状态
	 * @param begin 起始时间
	 * @param end 结束时间
	 * @return
	 */
	Integer getThumbsTotalCount(@Param("mid") Integer mid, @Param("status") Integer status
			, @Param("begin") java.sql.Date begin, @Param("end") java.sql.Date end);

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
			, @Param("reason") String reason, @Param("time") Date time);
	
}
