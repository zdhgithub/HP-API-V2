package com.heipiao.api.v2.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heipiao.api.v2.domain.LikeUser;
import com.heipiao.api.v2.domain.Marketing;
import com.heipiao.api.v2.domain.MarketingPicture;
import com.heipiao.api.v2.domain.User;
import com.heipiao.api.v2.exception.BadRequestException;
import com.heipiao.api.v2.mapper.MarketingMapper;
import com.heipiao.api.v2.mapper.UserMapper;
import com.heipiao.api.v2.service.MarketingService;
import com.heipiao.api.v2.util.ExDateUtils;

@Service
public class MarketingServiceImpl implements MarketingService {

	@Resource
	private MarketingMapper marketingMapper;
	
	@Resource UserMapper userMapper;

	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void addMarketing(Marketing marketing) {
		marketingMapper.addMarketing(marketing);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void updateMarketing(Marketing marketing) {
		marketingMapper.updateMarketing(marketing);

	}

	@Override
	public List<Marketing> getList(Map<String, Object> map) {
		return marketingMapper.getMarketing(map);
	}
	
	public List<Marketing> getList(int start, int size) {
		return marketingMapper.getMarketingList(start, size);
	}

	@Override
	public Integer getMarketingCount(Map<String, Object> map) {
		return marketingMapper.getMarketingCount(map);
	}

	@Override
	public Marketing getOneMarketing(Integer id) {
		return marketingMapper.getMarketingById(id);
	}

	@Override
	public List<MarketingPicture> getPictureList(Map<String, Object> map) {
		List<MarketingPicture> list = marketingMapper.getMarketingPicture(map);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map1 = new HashMap<>();
			map1.put("marketingId", list.get(i).getMarketingId());
			map1.put("marketUid", list.get(i).getUid());
			List<LikeUser> list1 = marketingMapper.getLikeUser(map1);
			list.get(i).setLikeUsuer(list1);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void addPictures(MarketingPicture marketingPicture) {
		marketingMapper.addMarketingPicture(marketingPicture);
	}

	@Override
	public MarketingPicture getOneMaretingPicture(Map<String, Object> map) {
		Map<String, Object> map1 = new HashMap<>();
		map1.put("marketingId", map.get("marketingId"));
		map1.put("marketUid", map.get("uid"));
		List<LikeUser> liekUser = marketingMapper.getLikeUser(map1);
		MarketingPicture marketingPicture = marketingMapper.getOneMarketingPicture(map);
		marketingPicture.setLikeUsuer(liekUser);
		return marketingPicture;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void addLikeUser(LikeUser likeUser) {
		Marketing marketing = getOneMarketing(likeUser.getMarketingId());
		if (marketing == null || marketing.getStatus() == 2) {
			throw new BadRequestException("活动已结束");
		}

		if (marketing.getEndTime().getTime() < ExDateUtils.getDate().getTime()) {
			throw new BadRequestException("点赞活动已结束");
		}
		
		User user = userMapper.selectById(likeUser.getLikeUid());
		if (user == null) {
			throw new BadRequestException("没有这个用户：" + likeUser.getLikeUid());
		}
		
		Long uid = likeUser.getLikeUid();
		Map<String, Object> _map = new HashMap<String, Object>();
		_map.put("marketingId", likeUser.getMarketingId());
		_map.put("marketUid", likeUser.getMarketUid());
		_map.put("likeUid", uid);
		if (marketingMapper.getOneLikeUser(_map) != null) {
			throw new BadRequestException("已点赞");
		}

		// 这个根本不应当出现在数据表里
		likeUser.setNickName(user.getNickname() == null ? null : user.getNickname());
		likeUser.setPortrait(user.getPortriat() == null ? null : user.getPortriat());
		
		Map<String, Object> map = new HashMap<>();
		map.put("marketingId", likeUser.getMarketingId());
		map.put("uid", likeUser.getMarketUid());
		MarketingPicture picture = marketingMapper.getOneMarketingPicture(map);
		map.put("likeCount", picture.getLikeCount() + 1);
		marketingMapper.updatePicture(map);
		marketingMapper.addLikeUser(likeUser);
	}

	@Override
	public LikeUser getOneLikeUser(Map<String, Object> map) {
		return marketingMapper.getOneLikeUser(map);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void updateMarketingPicture(Map<String, Object> map) {
		marketingMapper.updatePicture(map);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer isJoin(Long uid, Integer mid) {
		return marketingMapper.isJoin(uid, mid);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	public void updatePictures(Map<String, Object> map) {
		marketingMapper.updatePicture(map);
		marketingMapper.updateStatus(map);
	}

}
