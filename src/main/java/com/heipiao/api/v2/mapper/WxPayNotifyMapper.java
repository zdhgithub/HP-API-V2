package com.heipiao.api.v2.mapper;

import org.springframework.stereotype.Service;

import com.heipiao.api.v2.domain.WxPayNotify;

/**
 * @author wzw
 * @date 2016年7月21日
 * @version 1.0
 */
@Service
public interface WxPayNotifyMapper {

	WxPayNotify selectWxPayNotifyByOutTradeNo(String out_trade_no);
	
	WxPayNotify selectWxPayNotifyAsLockByOutTradeNo(String out_trade_no);
	
	void insertPojo(WxPayNotify pojo);
	
	void updatePojo(WxPayNotify pojo);
	
}
