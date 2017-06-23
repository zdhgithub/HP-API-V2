package com.heipiao.api.v2.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heipiao.api.v2.domain.Marketing;
import com.heipiao.api.v2.domain.PageInfo;
import com.heipiao.api.v2.domain.Thumbs;
import com.heipiao.api.v2.domain.ThumbsResult;
import com.heipiao.api.v2.domain.User;
import com.heipiao.api.v2.exception.BadRequestException;
import com.heipiao.api.v2.exception.ExpectationFailedException;
import com.heipiao.api.v2.exception.NotFoundException;
import com.heipiao.api.v2.exception.PreconditionException;
import com.heipiao.api.v2.mapper.MarketingMapper;
import com.heipiao.api.v2.mapper.UserMapper;
import com.heipiao.api.v2.service.MarketingService;
import com.heipiao.api.v2.util.ExDateUtils;

@Service
@Transactional
public class MarketingServiceImpl implements MarketingService {

	@Resource
	private MarketingMapper marketingMapper;
	
	@Resource
	private UserMapper userMapper;
	
	/**
	 * 取点赞活动时要截取的点赞数量
	 */
	private static final int CAT_THUMBS_COUNT = 10;
	
	public PageInfo<List<Marketing>> getMarketingList(Integer status, Integer start, Integer size) {
		List<Marketing> list = marketingMapper.getMarketingList(status, start, size);
		int count = getMarketingCount(status);
		
		PageInfo<List<Marketing>> result = new PageInfo<List<Marketing>>(count, list);
		return result;
	}

	@Override
	public Integer getMarketingCount(Integer status) {
		return marketingMapper.getMarketingCount(status);
	}

	@Override
	public Marketing getMarketing(int mid) {
		Marketing marketing = marketingMapper.getMarketingById(mid);
		if (marketing == null) {
			throw new NotFoundException("活动不存在：" + mid);
		}
		
		return marketing;
	}

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
	public List<ThumbsResult> getThumbsList(int mid, long uid, int start, int size) {
		return marketingMapper.getThumbsList(mid, uid, CAT_THUMBS_COUNT, start, size);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void addThumbs(Thumbs thumbs) {
		thumbs.setUploadTime(ExDateUtils.getDate());
		marketingMapper.addThumbs(thumbs);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void updateThumbs(int mid, long uid, Thumbs thumbs) {
		thumbs.setLikeCount(0); // 重置为0
		thumbs.setStatus(0); // 重置为0
		thumbs.setUploadTime(ExDateUtils.getDate()); // 重置上传时间
		thumbs.setRefundReason(null); // 清空拒绝原因
		thumbs.setRefundTime(null); // 清空拒绝时间
		
		marketingMapper.updateThumbs(mid, uid, thumbs);
	}

	@Override
	public ThumbsResult getThumbs(int mid, long uid) {
		return marketingMapper.getThumbs(mid, uid);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public String like(int mid, long uid, long likeUid) {
		Marketing marketing = getMarketing(mid);
		if (marketing.getStatus() == 2 || marketing.getEndTime().getTime() < ExDateUtils.getDate().getTime()) {
			throw new PreconditionException("点赞活动已结束");
		}
		
		User user = userMapper.selectById(likeUid);
		if (user == null) {
			throw new BadRequestException("没有这个用户：" + likeUid);
		}
		
		boolean falg = isLike(mid, uid, likeUid);
		if (falg) {
			throw new ExpectationFailedException("已点赞");
		}
		
		boolean flag = isJoin(mid, uid);
		if (!flag) {
			throw new ExpectationFailedException("该用户未参加活动");
		}
		
		Date likeTime = ExDateUtils.getDate();
		marketingMapper.addLike(mid, uid, likeUid, likeTime);
		marketingMapper.updateThumbsLikeCount(mid, uid);
		return user.getNickname();
	}

	@Override
	public boolean isLike(int mid, long uid, long likeUid) {
		Integer count = marketingMapper.isLike(mid, uid, likeUid);
		return count != null && count.intValue() > 0 ? true : false;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isJoin(int mid, long uid) {
		Integer count = marketingMapper.isJoin(mid, uid);
		return count != null && count.intValue() > 0 ? true : false;
	}

	@Override
	public PageInfo<List<ThumbsResult>> getThumbsWithPage(int mid, Integer status, int start, int size, java.sql.Date begin, java.sql.Date end, String orderField, String orderBy) {
		List<ThumbsResult> list = marketingMapper.getThumbsWithPage(mid, status, CAT_THUMBS_COUNT, start, size, begin, end, orderField, orderBy);
		Integer totalCount = marketingMapper.getThumbsCountForPage(mid, status, begin, end);
		
		PageInfo<List<ThumbsResult>> pageInfo = new PageInfo<List<ThumbsResult>>(totalCount, list);
		return pageInfo;
	}

	@Override
	public Integer audit(int mid, int uid, int status, String reason) {
		Date time = null;
		if (status == 2) {
			time = ExDateUtils.getDate();
		}
		
		return marketingMapper.audit(mid, uid, status, reason, time);
	}

	@Override
	public String getAllLike(int mid, long uid) {
		return marketingMapper.getAllLike(mid, uid);
	}

}
