package com.heipiao.api.v2.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heipiao.api.v2.component.map.AMapService;
import com.heipiao.api.v2.domain.FishSiteBase;
import com.heipiao.api.v2.domain.HaveFish;
import com.heipiao.api.v2.domain.HaveFishComment;
import com.heipiao.api.v2.domain.HaveFishLike;
import com.heipiao.api.v2.domain.Location;
import com.heipiao.api.v2.mapper.FishSiteBaseMapper;
import com.heipiao.api.v2.mapper.HaveFishCommentMapper;
import com.heipiao.api.v2.mapper.HaveFishLikeMapper;
import com.heipiao.api.v2.mapper.HaveFishMapper;
import com.heipiao.api.v2.service.HaveFishService;
import com.heipiao.api.v2.util.ExDateUtils;




@Service
@Transactional(readOnly = true)
public class HaveFishServiceImpl implements HaveFishService{
	
	@Resource
	private HaveFishMapper haveFishMapper;
	@Resource
	private FishSiteBaseMapper fishSiteBaseMapper;
	@Resource
	private AMapService amapService;
	@Resource
	private HaveFishLikeMapper haveFishLikeMapper;
	@Resource
	private HaveFishCommentMapper haveFishCommentMapper;
	
	
	@Override
	public List<HaveFish> getHaveFishList(Integer uid,Integer start,Double longitude,Double latitude) {
		List<HaveFish> list = haveFishMapper.getHaveFishList(uid,start,longitude,latitude);
		return list;
	}

	@Override
	public List<HaveFish> getHaveFishAllList(Integer uid, Integer start, Integer size, Double longitude,
			Double latitude) {
		List<HaveFish> list = haveFishMapper.getHaveFishAllList(uid, start, size, longitude, latitude);
		return list;
	}

	@Override
	@Transactional(readOnly = false,rollbackFor = {Exception.class})
	public void addHaveFish(HaveFish haveFish) {
		Location location = amapService.geocode_regeo(haveFish.getLon(), haveFish.getLat());
		haveFish.setPublishTime(ExDateUtils.getDate());
		haveFish.setCityId(location.getCityId());
		haveFish.setCityName(location.getCity());
		haveFish.setProvinceId(location.getProvinceId());
		haveFish.setProvinceName(location.getProvince());
		haveFishMapper.addHaveFish(haveFish);
	}

	@Override
	public FishSiteBase getDefaultSet(Integer uid) {
		FishSiteBase baseSite = fishSiteBaseMapper.getFishSiteBaseByUid(uid);
		return baseSite;
	}

	@Override
	@Transactional(readOnly = false,rollbackFor = {Exception.class})
	public void addFishSiteBase(FishSiteBase fishSiteBase) {
		Location location = amapService.geocode_regeo(fishSiteBase.getLon(), fishSiteBase.getLat());
		fishSiteBase.setCityId(location.getCityId());
		fishSiteBase.setCityName(location.getCity());
		fishSiteBase.setProvinceId(location.getProvinceId());
		fishSiteBase.setProvinceName(location.getProvince());
		fishSiteBase.setSetTime(ExDateUtils.getDate());
		fishSiteBaseMapper.addFishSiteBase(fishSiteBase);
	}

	@Override
	@Transactional(readOnly = false,rollbackFor = {Exception.class})
	public void addLikeUser(HaveFishLike fishHaveLike) {
		Integer haveFishId = fishHaveLike.getHaveFishId();
		Integer uid = fishHaveLike.getUid();
		haveFishLikeMapper.getLikeUser(haveFishId,uid);
		fishHaveLike.setLikeTime(ExDateUtils.getDate());
		haveFishLikeMapper.addHaveFishLike(fishHaveLike);
	}

	@Override
	@Transactional(readOnly = false,rollbackFor = {Exception.class})
	public void addCommentUser(HaveFishComment fishHaveComment) {
		fishHaveComment.setCommentTime(ExDateUtils.getDate());
		haveFishCommentMapper.addHaveFishComment(fishHaveComment);
	}

}
