package com.heipiao.api.v2.service;

import com.heipiao.api.v2.domain.WxPayNotify;

/**
 * @author zf
 * @version 1.0
 * @description 处理回调通知
 * @date 2016年7月4日
 */
public interface NotifyService {
	
	/**
	 * 验证平台并查询订单结果信息处理
	 * 
	 * @param tradePlatform
	 * @param orderId
	 */
	int verifyOrders(int tradePlatform, String orderId, String attach, Integer whereIsApp);

	/**
	 * @param orderId
	 * @param m 
	 * 
	 *  0:表示订单未支付
	 * 1：表示订单已支付
	 * -1：表示订单取消或者其它
	 * 
	 */
	public int wxTradeQueryResult(String orderId, WxPayNotify wx, Integer whereIsApp);

	/**
	 * @param wx
	 * @return
	 * @throws Exception 
	 */
	boolean wxOrdersSuccess(WxPayNotify wx);

	/**
	 * @param orderId
	 * @param attach
	 * @return
	 * @throws Exception
	 */
	boolean orderFail(String orderId, String attach);

	/**
	 * @param wxPayNotify
	 * @return
	 * @throws Exception 
	 */
	String wxNotify(WxPayNotify wxPayNotify);
	
	/**
	 * 微信
	 * 通过订单号查询通知
	 * @param orderId
	 * @return
	 */
	public WxPayNotify getWxPayNotify(String orderId);

}
