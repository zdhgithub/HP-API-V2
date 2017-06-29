package com.heipiao.api.v2.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.heipiao.api.v2.component.pay.PayParams;
import com.heipiao.api.v2.component.pay.PayService;
import com.heipiao.api.v2.domain.CampaignActor;
import com.heipiao.api.v2.domain.WxPayNotify;
import com.heipiao.api.v2.exception.PreconditionException;
import com.heipiao.api.v2.exception.ServiceException;
import com.heipiao.api.v2.mapper.CampaignMapper;
import com.heipiao.api.v2.mapper.WxPayNotifyMapper;
import com.heipiao.api.v2.service.AccountBillService;
import com.heipiao.api.v2.service.CampaignService;
import com.heipiao.api.v2.service.NotifyService;
import com.heipiao.api.v2.util.ArithUtil;

/**
 * @author zf
 * @version 1.0
 * @description 处理支付回调业务
 * @date 2016年7月4日
 */
@Service
@Transactional(readOnly = true)
public class NotifyServiceImpl implements NotifyService {
//	private static final String FAIL = "fail";
//	private static final String SUCCESS = "success";
//
//	private static final Logger log = LoggerFactory.getLogger(NotifyServiceImpl.class);

	@Resource
	private WxPayNotifyMapper wxPayNotifyMapper;

	@Resource
	private PayParams payParams;
	
	@Resource(name="PayService")
	private PayService pay;
	
	@Resource
	private AccountBillService accountBillService;
	
	@Resource
	private CampaignMapper campaignMapper;
	
	@Resource
	private CampaignService campaignService;

	@Override
	public int verifyOrders(int tradePlatform, String orderId,String attach,Integer whereIsApp) {
		if(tradePlatform == 1){
			WxPayNotify wx = wxPayNotifyMapper.selectWxPayNotifyByOutTradeNo(orderId);
			int result = wxTradeQueryResult(orderId,wx, whereIsApp);
			if(result == 1){
				return wxOrdersSuccess(wx) ? 0 : 1;
			 }
			if(result == -1){
				return orderFail(orderId,attach) ? 0 : 1;
			}
		}
		
		throw new PreconditionException("仅支持微信支付");
	}

	/** 
	 * 查询订单的结果做处理
	 * 
	 * 0:表示订单未支付
	 * 1：表示订单已支付
	 * -1：表示订单取消或者其它
	 */
	@Override
	public int wxTradeQueryResult(String orderId, WxPayNotify wx,Integer whereIsApp) {
		if(wx == null)
			return -1;
		
		try {
			Map<String, String> m = pay.wechatQueryTrade(orderId,whereIsApp);
			JSONObject json = JSONObject.parseObject(JSONObject.toJSONString(m));
			WxPayNotify wx0 = JSONObject.toJavaObject(json, WxPayNotify.class);
			wx0.setAttach(wx.getAttach());
			wx0.toCopy(wx);
			if(wx.getReturn_code().equalsIgnoreCase(PayParams.success) && wx.getResult_code().equalsIgnoreCase(PayParams.success)){
				String trade_state = m.get("trade_state");
				if(trade_state.equals(PayParams.success)){
					//处理成功后的逻辑
	//				if(wxPayNotify(wx).equals(payParams.responseXml(PayParams.success))){
	//					return Status.orders_paid;
	//				}else {
	//					return Status.orders_error;
	//				}
					return 1;
				//如果是非支付状态取消订单
				}else if (trade_state.equals("REFUND") || trade_state.equals("CLOSED") 
						|| trade_state.equals("PAYERROR") || trade_state.equals("REVOKED") ){
	//				return cancelOrder(orderId);
					return -1;
				}
			}
		} catch (Exception e) {
			throw new ServiceException("微信接口调用失败", e);
		}
		
		return 0;
		
	}

	/**
	 * @param wx
	 * @return
	 * @throws Exception 
	 */
	@Override
	public boolean wxOrdersSuccess(WxPayNotify wx) {
		String respStatus = PayParams.responseXml(PayParams.success);
		String hpStatus = wxNotify(wx);
		return hpStatus.equals(respStatus);
	}

	/**
	 * @param orderId
	 * @param attach 
	 * @return
	 * @throws Exception 
	 */
	@Override
	public boolean orderFail(String orderId, String attach) {
		switch (attach) {
		case com.heipiao.api.v2.service.PayService.activity:
			campaignService.cancelEnter(orderId);
			return true;
		default:
			return false;
		}
	}
	
	@Override
	@Transactional(readOnly = false,rollbackFor = {Exception.class})
	public String wxNotify(WxPayNotify wxPayNotify) {
		WxPayNotify pojo = wxPayNotifyMapper.selectWxPayNotifyAsLockByOutTradeNo(wxPayNotify.getOut_trade_no());
		if(pojo == null)
			return PayParams.responseXml(PayParams.success);
		
		//并发处理
		if(pojo.getResult_code() != null && pojo.getResult_code().equalsIgnoreCase(PayParams.success)){
			return PayParams.responseXml(PayParams.success);
		}
		
		switch (pojo.getAttach()) {
//		case cn.heipiao.api.service.PayService.buyGoodOrders:
//			return wxPayNotify(wxPayNotify);
//		case cn.heipiao.api.service.PayService.payGoldCoin:
//			return wxPayGoldCoinNofify(wxPayNotify); 
//		case cn.heipiao.api.service.PayService.payShop:
//			return wxPayShopNotify(wxPayNotify); 
		case com.heipiao.api.v2.service.PayService.activity:
			return wxActivityNotify(wxPayNotify); 
		default:
			return PayParams.success;
		}
	}
	
	private String wxActivityNotify(WxPayNotify wxPayNotify) {
		// 判断参数
		CampaignActor ca = campaignMapper.getCampaignActorAsLock(wxPayNotify.getOut_trade_no());
		if (ca == null || ca.getPayStatus() != 0) {
			return PayParams.responseXml(PayParams.fail);
		}
		
		if(wxPayNotify.getTotal_fee().intValue() != (int)ArithUtil.mul(ca.getPayAmount(), 100, 2)){
			return PayParams.responseXml(PayParams.fail);
		}
		
		// 更新支付通知数据
		wxPayNotifyMapper.updatePojo(wxPayNotify);
		
		ca.setPayStatus(1);
		campaignMapper.updateCampaignActor(ca);

		accountBillService.addPojo(ca.getUid().longValue(), ca.getOrderId(), 2, 20, 1, ca.getPayAmount().doubleValue(), "活动报名-微信");
		return PayParams.responseXml(PayParams.success);
	}

	@Override
	public WxPayNotify getWxPayNotify(String orderId) {
		return wxPayNotifyMapper.selectWxPayNotifyByOutTradeNo(orderId);
	}
	
}
