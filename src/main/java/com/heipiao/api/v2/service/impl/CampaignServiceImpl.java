package com.heipiao.api.v2.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.heipiao.api.v2.domain.Campaign;
import com.heipiao.api.v2.domain.CampaignActor;
import com.heipiao.api.v2.domain.User;
import com.heipiao.api.v2.exception.ExpectationFailedException;
import com.heipiao.api.v2.exception.NotFoundException;
import com.heipiao.api.v2.exception.PreconditionException;
import com.heipiao.api.v2.exception.ServiceException;
import com.heipiao.api.v2.mapper.CampaignMapper;
import com.heipiao.api.v2.mapper.UserMapper;
import com.heipiao.api.v2.service.CampaignService;
import com.heipiao.api.v2.service.NotifyService;
import com.heipiao.api.v2.service.PayService;
import com.heipiao.api.v2.util.ExDateUtils;

/**
 * 活动相关
 * 
 * @author Chris
 * @version 3.0
 * @date 2017.03.06
 *
 */
@Service
public class CampaignServiceImpl implements CampaignService {

	@Resource
	private UserMapper userMapper;

	@Resource
	private CampaignMapper campaignMapper;

	@Resource
	private PayService payService;

	@Resource
	private NotifyService notifyService;

	@Resource(name = "PayService")
	private PayService pay;

	@Override
	public Campaign getCampaign(int id) { // ok
		// FIXME  try catch，下同
		return campaignMapper.getCampaign(id);
	}

	@Override
	public List<Campaign> getCampaignList(int start, int size) { // ok
		return campaignMapper.getCampaignList(start, size);
	}

	@Override
	public List<CampaignActor> getCampaignActorList(int id, int top) { // ok
		return campaignMapper.getCampaignActorList(id, top);
	}

	@Override
	public CampaignActor getCampaignActor(int cid, int uid) { // ok
		return campaignMapper.getCampaignActor(cid, uid);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public String enter(Long uid, int cid, String openId, int payType) { // ok
		CampaignActor ca = campaignMapper.getCampaignActor(cid, uid.intValue());
		if (ca != null && ca.getPayStatus() > 0 && ca.getPayStatus() < 3) {
			throw new PreconditionException("您已报名");
		}
		
		Campaign camp = campaignMapper.getCampaignAsLock(cid);
		if (camp == null || camp.getStatus() != 1) {
			throw new PreconditionException("活动已结束");
		}
		
		Timestamp entryTime = new Timestamp(ExDateUtils.getCalendar().getTimeInMillis());
		
		// 报名结束时间的判断
		if (camp.getEntryTerminalTime().getTime() <= entryTime.getTime()) {
			// FIXME 这是做什么？
			campaignMapper.setCampaignStatus(camp.getId(), 2);
			throw new PreconditionException("活动报名已结束");
		}
		
		if (camp.getQuota() <= camp.getCount()) {
			throw new PreconditionException("活动人数已满");
		}

		User user = userMapper.selectById(uid);
		if (user == null) {
			throw new NotFoundException("用户不存在");
		}
		
		float cost = 0.0f;
		if (payType == 1) {
			cost = camp.getCost();
		}

		if (ca != null) {
			ca = campaignMapper.getCampaignActorAsLock(ca.getOrderId()); // FIXME 这里待优化，上面已经查过了
			ca.setOrderId(ExDateUtils.getCurrentDayFormat("yyMMddHHmmss") + "04" + uid);
			ca.setEntryTime(entryTime);
			ca.setPayAmount(cost);
			ca.setPayStatus(payType == 1 ? 0 : 2);
			ca.setPayType(payType);
			ca.setRefundStatus(0);
			campaignMapper.updateCampaignActorByCidAndUid(ca); // 暂时不清楚为什么这里需要更新
		} else {
			ca = new CampaignActor();
			ca.setOrderId(ExDateUtils.getCurrentDayFormat("yyMMddHHmmss") + "04" + uid);
			ca.setEntryTime(entryTime);
			ca.setPayAmount(cost);
			ca.setPayStatus(payType == 1 ? 0 : 2);
			ca.setPayType(payType);
			ca.setRefundStatus(0);
			ca.setUid(uid.intValue());
			ca.setCid(cid);
			campaignMapper.addActor(ca);
		}

		camp.setCount(camp.getCount() + 1);

		if (camp.getCount().intValue() == camp.getQuota().intValue()) {
			camp.setStatus(2);
		}

		campaignMapper.updateCampaign(camp);

		String result = "";
		if (ca.getPayType() == 1) {
			try {
				result = payService.generatePayParam(uid, 1, ca.getOrderId(), InetAddress.getLocalHost().getHostAddress(),
						camp.getName(), Integer.valueOf(PayService.activity), openId);
			} catch (NumberFormatException e) {
				throw new ServiceException("支付类型错误", e);
			} catch (UnknownHostException e) {
				throw new ServiceException("生成支付数据时获取主机地址失败", e);
			}
		}
		
		return result;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void payActivityConfirm(Integer uid, Integer cid) { // ok
		CampaignActor ca = campaignMapper.getCampaignActor(cid, uid.intValue());
		if (ca == null) {
			throw new PreconditionException("报名失败");
		}
		
		CampaignActor caa = campaignMapper.getCampaignActorAsLock(ca.getOrderId());
		if (caa.getPayStatus() == 1) {
			return;
		}
		
		if (caa.getPayType() != 1) {
			throw new ExpectationFailedException("支付失败");
		}
		
		int i = notifyService.verifyOrders(1, caa.getOrderId(), PayService.activity, 2);
		if (i == 0) {
			cancelEnter(caa.getOrderId());
		}
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void cancelEnter(String orderId) {
		CampaignActor ca = campaignMapper.getCampaignActorAsLock(orderId);
		if (ca == null || ca.getPayStatus() != 0) {
			throw new PreconditionException("报名失败");
		}
		
		ca.setPayStatus(3);
		Campaign camp = campaignMapper.getCampaignAsLock(ca.getCid());
		if (camp != null) {
			camp.setCount(camp.getCount() - 1);
			if (camp.getEndTime().getTime() > ExDateUtils.getCalendar().getTimeInMillis()) {
				camp.setStatus(1);
			}
		}
		
		campaignMapper.updateCampaign(camp);
		campaignMapper.updateCampaignActor(ca);
	}

}
