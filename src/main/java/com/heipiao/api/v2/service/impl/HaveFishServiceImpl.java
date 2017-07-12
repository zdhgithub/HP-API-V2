package com.heipiao.api.v2.service.impl;

import java.util.Date;
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
import com.heipiao.api.v2.domain.PageInfo;
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
		Integer uid = fishSiteBase.getFishSiteUid();
		FishSiteBase base = fishSiteBaseMapper.getFishSiteBaseByUid(uid);
		if(base != null){
			fishSiteBaseMapper.updateFishSite(fishSiteBase);
		}else{
			fishSiteBaseMapper.addFishSiteBase(fishSiteBase);
		}
	}

	@Override
	@Transactional(readOnly = false,rollbackFor = {Exception.class})
	public boolean addLikeUser(HaveFishLike fishHaveLike) {
		boolean result = false;
		Integer haveFishId = fishHaveLike.getHaveFishId();
		Integer uid = fishHaveLike.getUid();
		HaveFishLike likeuser = haveFishLikeMapper.getLikeUser(haveFishId,uid);
		if(likeuser != null){
			result = true;
		}else{
			fishHaveLike.setLikeTime(ExDateUtils.getDate());
			haveFishLikeMapper.addHaveFishLike(fishHaveLike);
			result = false;
		}
		return result;
	}

	@Override
	@Transactional(readOnly = false,rollbackFor = {Exception.class})
	public void addCommentUser(HaveFishComment fishHaveComment) {
		fishHaveComment.setCommentTime(ExDateUtils.getDate());
		haveFishCommentMapper.addHaveFishComment(fishHaveComment);
	}

	@Override
	public PageInfo<List<FishSiteBase>> getAllFishSiteSet(Integer start, Integer size, Integer provinceId, Integer cityId,
			Date regBegin, Date regEnd) {
		List<FishSiteBase> list = fishSiteBaseMapper.getAllFishSiteBase(start,size,provinceId,cityId,regBegin,regEnd);
		Integer totalCount =fishSiteBaseMapper.getFishSiteBaseCountForPage(provinceId,cityId,regBegin,regEnd);
		
		PageInfo<List<FishSiteBase>> pageInfo = new PageInfo<List<FishSiteBase>>(totalCount, list);
		return pageInfo;
	}

	@Override
	public void updateFishSiteBase(Integer uid, Integer status) {
		fishSiteBaseMapper.updateFishSiteBase(uid, status);
	}

	@Override
	public PageInfo<List<HaveFish>> getAllHaveFishByPage(Integer start, Integer size, Integer provinceId,
			Integer cityId, Date regBegin, Date regEnd, Integer type,String nickname) {
		
		List<HaveFish> list = haveFishMapper.getHaveFishOccList(start,size,provinceId,cityId,regBegin,regEnd,type,nickname);
		Integer totalCount =haveFishMapper.getHaveFishOccListCountForPage(provinceId,cityId,regBegin,regEnd,type,nickname);
		PageInfo<List<HaveFish>> pageInfo = new PageInfo<List<HaveFish>>(totalCount, list);
		return pageInfo;
	}

	@Override
	@Transactional(readOnly = false,rollbackFor = {Exception.class})
	public void updateHaveFish(Integer id, Integer isDisplay) {
		haveFishMapper.updateHaveFish(id,isDisplay);
	}

	@Override
	@Transactional(readOnly = true)
	public HaveFish getOneHaveFish(Integer id) {
		return haveFishMapper.getOneHaveFish(id);
	}
}
