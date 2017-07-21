package com.heipiao.api.v2.service.impl;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.heipiao.api.v2.component.map.AMapService;
import com.heipiao.api.v2.component.pay.PayConfig;
import com.heipiao.api.v2.domain.Location;
import com.heipiao.api.v2.domain.PageInfo;
import com.heipiao.api.v2.domain.Region;
import com.heipiao.api.v2.domain.User;
import com.heipiao.api.v2.exception.BadRequestException;
import com.heipiao.api.v2.exception.NotFoundException;
import com.heipiao.api.v2.exception.ServiceException;
import com.heipiao.api.v2.mapper.RegionMapper;
import com.heipiao.api.v2.mapper.UserMapper;
import com.heipiao.api.v2.repository.RegionRepository;
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
	private RegionRepository regionRepository;

	@Resource
	private HttpUtils http;
	
	@Resource
	private PayConfig payConfig;
	
	@Resource
	private AMapService amapService;
	
	@Resource
	private RegionMapper regionMapper;
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public String getWXUserinfo(String userInfo, String code) {
		try {
			String result =	http.execute("https://api.weixin.qq.com/sns/jscode2session?"
					+ "appid=" + payConfig.wx_mini_appid + "&secret=" + payConfig.wx_mini_secret + "&js_code=" + code + "&grant_type=authorization_code",
					"get", null);
			logger.debug(result);
			JSONObject resultJson = JSONObject.parseObject(result);
			
			JSONObject userInfoJson = JSONObject.parseObject(userInfo);
			return ExAES128Utils.decrypt(userInfoJson.getString("encryptedData"), resultJson.getString("session_key"), 
					ExAES128Utils.generateIV(userInfoJson.getString("iv")));
		} catch (Exception e) {
			logger.error("调用微信接口异常", e);
			throw new ServiceException("调用微信接口异常，请稍后重试");
		}
	}

	@Override
	public User queryUserByOpenId(String unionId,JSONObject resultJson) {
		User user = userMapper.queryUserByOpenId(unionId, null);
		if(!user.getNickname().equals(resultJson.getString("nickName")) || !user.getPortriat().equals(resultJson.getString("avatarUrl"))){
			user.setNickname(resultJson.getString("nickName"));
			user.setPortriat(resultJson.getString("avatarUrl"));
			userMapper.updateUser(user);
		}
		return user;
	}

	@Override
	public User save(String unionId, String gender, String nickName, String avatarUrl,Integer parentUid,Double lat,Double lon) {
		User user = new User();
		user.setOpenId(unionId);
		user.setSex(gender);
		user.setNickname(nickName);
		user.setPortriat(avatarUrl);
		user.setRegisterTime(ExDateUtils.getDate());
		user.setLastLoginTime(ExDateUtils.getDate());
		user.setParentUid(parentUid);
		Location location = amapService.geocode_regeo(lon, lat);
		
		Region region;
		region = regionRepository.getRegionByRegionName(location.getProvince());
		if (region == null) {
			throw new BadRequestException("找不到指定省份信息:" + location.getProvince());
		}
		
		int provinceId = region.getRegionNum();
		
		region = regionRepository.getRegionByRegionName(location.getCity());
		if (region == null) {
			throw new BadRequestException("找不到指定城市信息:" + location.getCity());
		}
		
		int cityId = region.getRegionNum();
		
		user.setProvinceId(provinceId);
		user.setProvince(location.getProvince());
		user.setCityId(cityId);
		user.setCity(location.getCity());
		userMapper.save(user);
		
		return user;
	}

	@Override
	public Location updateLocation(long uid, double lng, double lat) {
		User user = userRepository.findOne(uid);
		if (user == null) {
			throw new NotFoundException("该用户不存在");
		}
		
		// 对于已经保存过省份和城市信息的用户，直接返回
		if (user.getProvinceId() != null && StringUtils.isNotBlank(user.getProvince())
				&& user.getCityId() != null && StringUtils.isNotBlank(user.getCity())) {
			Location location = new Location();
			location.setProvinceId(user.getProvinceId());
			location.setProvince(user.getProvince());
			location.setCityId(user.getCityId());
			location.setCity(user.getCity());
			return location;
		}
		
		Location location = amapService.geocode_regeo(lng, lat);
		
		Region region;
		region = regionRepository.getRegionByRegionName(location.getProvince());
		if (region == null) {
			throw new BadRequestException("找不到指定省份信息:" + location.getProvince());
		}
		
		int provinceId = region.getRegionNum();
		
		region = regionRepository.getRegionByRegionName(location.getCity());
		if (region == null) {
			throw new BadRequestException("找不到指定城市信息:" + location.getCity());
		}
		
		int cityId = region.getRegionNum();
		
		user.setProvinceId(provinceId);
		user.setProvince(location.getProvince());
		user.setCityId(cityId);
		user.setCity(location.getCity());
		userRepository.save(user);
		
		location.setProvinceId(provinceId);
		location.setCityId(cityId);
		
		return location;
	}

	@Override
	public PageInfo<List<User>> getUserWithPage(Integer provinceId, Integer cityId, java.sql.Date regBegin,
			java.sql.Date regEnd, String orderBy, int start, int size) {
		List<User> list = userMapper.getUserWithPage(provinceId, cityId, regBegin, regEnd, orderBy, start, size);
		Integer totalCount = userMapper.getUserCountForPage(provinceId, cityId, regBegin, regEnd);
		
		PageInfo<List<User>> pageInfo = new PageInfo<List<User>>(totalCount, list);
		return pageInfo;
	}

	@Override
	public PageInfo<List<User>> getChildUserWithPage(Date regBegin, Date regEnd,
			String orderBy, int start, int size, int parentUid) {
		List<User> list = userMapper.getChildUserWithPage(regBegin, regEnd, orderBy, start, size,parentUid);
		Integer totalCount = userMapper.getChildUserCountForPage(regBegin, regEnd,parentUid);
		
		PageInfo<List<User>> pageInfo = new PageInfo<List<User>>(totalCount, list);
		return pageInfo;
	}

	@Override
	public List<User> getChildUserPage(int page, int size, int parentUid) {
		List<User> list = userMapper.getChildUserPage(page, size,parentUid);
		return list;
	}

	
}
