package com.heipiao.api.v2.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.heipiao.api.v2.domain.PageInfo;
import com.heipiao.api.v2.domain.User;
import com.heipiao.api.v2.exception.NotFoundException;
import com.heipiao.api.v2.exception.ServiceException;
import com.heipiao.api.v2.mapper.UserMapper;
import com.heipiao.api.v2.pay.PayConfig;
import com.heipiao.api.v2.repository.UserRepository;
import com.heipiao.api.v2.service.UserService;
import com.heipiao.api.v2.util.ExAES128Utils;
import com.heipiao.api.v2.util.ExDateUtils;
import com.heipiao.api.v2.util.HttpUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;
	
	@Resource
	private UserRepository userRepository;

	@Resource
	private HttpUtils http;
	
	@Resource
	private PayConfig payConfig;
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public String getWXUserinfo(String userInfo, String code) {
		try {
			String rl =	http.execute("https://api.weixin.qq.com/sns/jscode2session?"
					+ "appid=" + payConfig.wx_mini_appid + "&secret=" + payConfig.wx_mini_secret + "&js_code=" + code + "&grant_type=authorization_code",
					"get", null);
			JSONObject rlJson = JSONObject.parseObject(rl);
			logger.info(rlJson.toString());
			
			JSONObject userInfoJson = JSONObject.parseObject(userInfo);
			return ExAES128Utils.decrypt(userInfoJson.getString("encryptedData"), rlJson.getString("session_key"), 
					ExAES128Utils.generateIV(userInfoJson.getString("iv")));
		} catch (Exception e) {
			throw new ServiceException("拉取微信接口异常，请稍后重试");
		}
	}

	@Override
	public User queryUserByOpenId(String unionId) {
		userMapper.queryUserByOpenId(unionId, null);
		return null;
	}

	@Override
	public User save(String unionId, String gender, String nickName, String avatarUrl) {
		User user = new User();
		user.setOpenId(unionId);
		user.setSex(gender);
		user.setNickname(nickName);
		user.setPortriat(avatarUrl);
		user.setRegisterTime(ExDateUtils.getDate());
		user.setLastLoginTime(ExDateUtils.getDate());
		
		userMapper.save(user);
		
		return user;
	}

	@Override
	public void updateLocation(long uid, double lng, double lat) {
		User user = userRepository.findOne(uid);
		if (user == null) {
			throw new NotFoundException("该用户不存在");
		}
		
//		user.setProvinceId(provinceId);
//		user.setProvince(province);
//		user.setCityId(cityId);
//		user.setCity(city);
		userRepository.save(user);
	}

	@Override
	public PageInfo<List<User>> getUserWithPage(Integer provinceId, Integer cityId, java.sql.Date regBegin,
			java.sql.Date regEnd, String orderBy, int start, int size) {
		List<User> list = userMapper.getUserWithPage(provinceId, cityId, regBegin, regEnd, orderBy, start, size);
		Integer totalCount = userMapper.getUserCountForPage(provinceId, cityId, regBegin, regEnd);
		
		PageInfo<List<User>> pageInfo = new PageInfo<List<User>>(totalCount, list);
		return pageInfo;
	}

}
