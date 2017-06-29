package com.heipiao.api.v2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.heipiao.api.v2.domain.Campaign;
import com.heipiao.api.v2.domain.CampaignActor;

/**
 * 活动相关
 * 
 * @author Chris
 * @version 3.0
 * @date 2017.03.06
 *
 */
@Service
public interface CampaignMapper {

	/**
	 * 获取活动
	 * 
	 * @param id
	 *            活动id
	 * @return
	 */
	public Campaign getCampaign(@Param("id") int id);

	/**
	 * 获取活动列表（除了未发布的）
	 * 
	 * @return
	 */
	public List<Campaign> getCampaignList(@Param("start") Integer start, @Param("size") Integer size);

	/**
	 * 获取所有活动参与人
	 * 
	 * @param id
	 *            活动id
	 * @return
	 */
	public List<CampaignActor> getCampaignActorList(@Param("id") Integer id, @Param("top") Integer top);

	/**
	 * 获取指定活动参与人
	 * 
	 * @param cid
	 *            活动id
	 * @param uid
	 *            参与人id
	 * @return
	 */
	public CampaignActor getCampaignActor(@Param("cid") Integer cid, @Param("uid") Long uid);

	/**
	 * 设置活动状态
	 * 
	 * @param id
	 *            活动id
	 * @param status
	 *            状态值
	 * @return
	 */
	public int setCampaignStatus(@Param("id") Integer id, @Param("status") Integer status);

	/**
	 * 添加行级索
	 * 
	 * @param cid
	 * @return
	 */
	public Campaign getCampaignAsLock(Integer cid);
	
	/**
	 * 更新活动id和用户id更新用户参加报名信息 // TODO 暂时不不清楚为什么这里一定要更新
	 * @param caa
	 */
	public void updateCampaignActorByCidAndUid(CampaignActor caa);

	/**
	 * 报名
	 * 
	 * @param ca
	 * @return
	 */
	public int addActor(CampaignActor ca);

	/**
	 * 更新活动
	 * 
	 * @param campaign
	 */
	public int updateCampaign(Campaign campaign);

	public CampaignActor getCampaignActorAsLock(String orderId);

	public void updateCampaignActor(CampaignActor ca);

}
