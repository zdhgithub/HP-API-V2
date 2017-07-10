package com.heipiao.api.v2.service.impl;

import javax.annotation.Resource;

import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.heipiao.api.v2.component.pay.PayConfig;
import com.heipiao.api.v2.service.TokenService;
import com.heipiao.api.v2.util.HttpUtils;


@Service
@Transactional
public class TokenServiceImp implements TokenService{

	@Resource
	private HttpUtils http;
	@Resource
	private PayConfig payConfig;
	
	@Override
	public String getWxaQrcode(String path) throws Exception {
		String result = getQrcodeImage(path);
		return result;
	}
	
	private String getQrcodeImage(String path) throws Exception{
		String ac = getAccessToken();
		String result = http.execute("https://api.weixin.qq.com/wxa/getwxacode?access_token=" + ac, "post", 	
				new StringEntity("{\"path\":\"" + path + "\",\"width\":430}", PayConfig.CHARSET));
		return result;
	}
	
	private String getAccessToken() throws Exception {
		String result = http.execute("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + 
				payConfig.wx_mini_appid + "&secret=" + payConfig.wx_mini_secret, "get", null);
		JSONObject data = JSONObject.parseObject(result);
		String token = data.getString("access_token");
		return token;
	}
}
