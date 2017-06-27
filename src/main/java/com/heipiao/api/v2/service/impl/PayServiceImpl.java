package com.heipiao.api.v2.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heipiao.api.v2.domain.CampaignActor;
import com.heipiao.api.v2.domain.WxPayNotify;
import com.heipiao.api.v2.exception.BadRequestException;
import com.heipiao.api.v2.exception.PreconditionException;
import com.heipiao.api.v2.mapper.CampaignMapper;
import com.heipiao.api.v2.mapper.WxPayNotifyMapper;
import com.heipiao.api.v2.pay.PayConfig;
import com.heipiao.api.v2.service.NotifyService;
import com.heipiao.api.v2.service.PayService;
import com.heipiao.api.v2.util.ArithUtil;

/**
 * @author wzw
 * @date 2016年7月18日
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class PayServiceImpl implements PayService{
	
	@Resource
	private PayConfig payConfig;
	
	@Resource
	private WxPayNotifyMapper wxPayNotifyMapper;
	
	@Resource(name="PayService")
	private com.heipiao.api.v2.pay.PayService pay;
	
	@Resource
	private NotifyService notifyService;
	@Resource
	private CampaignMapper campaignMapper;
	
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	public String generatePayParam(Long uid, int platform, String orderId,String app_ip,String body,
			Integer hpService,String openid) {
		String rs = null;
		if(hpService == 4) {
			rs = generateActivity(uid,platform,orderId,app_ip,body,openid);
			return rs;
		} else {
			throw new BadRequestException("仅支付活动支付");
		}
	}

	/**
	 * 
	 * @param uid
	 * @param platform
	 * @param orderId
	 * @param app_ip
	 * @param body
	 * @param openid
	 * @return
	 * @throws Exception 
	 */
	private String generateActivity(Long uid, int platform, String orderId, String app_ip, String body, String openid) {
		CampaignActor ca = campaignMapper.getCampaignActorAsLock(orderId);
		if (ca == null) {
			throw new PreconditionException("未生成支付订单");
		}
		
		if (ca.getPayStatus() != 0) {
			throw new PreconditionException("支付状态异常");
		}
		
		String ps;
		switch (platform) {
		case 1 :
			ps = wx(ca.getOrderId(), activity, ca.getPayAmount().doubleValue(), body, app_ip,openid, 2);
			 break;
		 default :
			 throw new PreconditionException("仅支持微信支付");
		}
		
		return ps;
	}

	/**
	 * 
	 * @param orderId
	 * @param hpService
	 * @param ordersMoney : 单位：元
	 * @param body
	 * @param app_ip
	 * @return
	 * @throws Exception
	 */
	private String wx(String orderId,String hpService,Double ordersMoney,String body,String app_ip,String openid,Integer whereIsApp) {
		 WxPayNotify wx = wxPayNotifyMapper.selectWxPayNotifyByOutTradeNo(orderId);
		 if(wx == null){
			 wx = new WxPayNotify();
			 wx.setOut_trade_no(orderId);
			 wx.setAttach(hpService);
			 wxPayNotifyMapper.insertPojo(wx);
		 }else{
			 //处理查询支付
			 int result = notifyService.verifyOrders(1, orderId,hpService,whereIsApp);
			 if(result != 0) {
				throw new PreconditionException("支付状态异常");
			 }
		 }
		 String payStr;
		try {
			payStr = pay.wechatUnifiedorder(whereIsApp != null && whereIsApp == 2 ? payConfig.wx_mini_appid : payConfig.pay_wx_appid_c, body, orderId, app_ip
					 , new Double(ArithUtil.mul(ordersMoney, 100, 2)).intValue(),wx.getAttach(),openid,whereIsApp);
			return payStr;
		} catch (Exception e) {
			throw new PreconditionException("调用微信支付接口失败，请稍后重试");
		}
	}

}
