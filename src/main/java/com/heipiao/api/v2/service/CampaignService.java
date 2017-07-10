package com.heipiao.api.v2.service;

import java.util.List;

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
public interface CampaignService {

	/**
	 * 获取活动
	 * 
	 * @param id
	 *            活动id
	 * @return
	 */
	public Campaign getCampaign(int id);

	/**
	 * 获取活动列表
	 * 
	 * @return
	 */
	public List<Campaign> getCampaignList(int start, int size);

	/**
	 * 获取所有活动参与人
	 * 
	 * @param id
	 *            活动id
	 * @param top
	 *            取前几位
	 * @return
	 */
	public List<CampaignActor> getCampaignActorList(int id, int top);

	/**
	 * 获取所有活动参与人
	 * 
	 * @param cid
	 *            活动id
	 * @param uid
	 *            参与人id
	 * @return
	 */
	public CampaignActor getCampaignActor(int cid, long uid);

	/**
	 * 报名
	 * 
	 * @param uid
	 *            用户id
	 * @param cid
	 *            活动id
	 * @param openid
	 *            报名时间
	 * @param payType
	 *            支持类型
	 */
	public String enter(Long uid, int cid, String openid, int payType);

	/**
	 * 报名确认
	 * 
	 * @param uid
	 * @param cid
	 * @throws Exception
	 */
	public void payActivityConfirm(Long uid, Integer cid);

	public void cancelEnter(String orderId);
	
}
