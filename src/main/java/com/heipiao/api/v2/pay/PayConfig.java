package com.heipiao.api.v2.pay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 支付参数配置类
 * @author wzw
 * @date 2016年7月26日
 * @version 1.0
 */
@Component
public class PayConfig {
	
	// TODO
	public static final String utf_8 = "UTF-8";
	
	@Value("${pay.wx.appid_c}")
	public String pay_wx_appid_c;
	
	@Value("${pay.wx.appSecret_c}")
	public String pay_wx_appSecret_c;
	
	@Value("${pay.wx.mch_id_c}")
	public String pay_wx_mch_id_c;
	
	@Value("${pay.wx.https.certPath_c}")
	public String pay_wx_https_certPath_c;
	
	@Value("${pay.wx.key_c}")
	public String pay_wx_key_c;
	
	@Value("${pay.wx.appid_b}")
	public String pay_wx_appid_b;
	
	@Value("${pay.wx.mch_id_b}")
	public String pay_wx_mch_id_b;
	
	@Value("${pay.wx.https.certPath_b}")
	public String pay_wx_https_certPath_b;
	
	@Value("${pay.wx.key_b}")
	public String pay_wx_key_b;
	
	@Value("${pay.wx.notify_url}")
	public String pay_wx_notify_url;
	
	
//	============微信小程序参数============================
	@Value("${wx.mini.appid}")
	public String wx_mini_appid ;
	
	@Value("${wx.mini.secret}")
	public String wx_mini_secret;
	
}
