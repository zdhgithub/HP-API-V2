package com.heipiao.api.v2.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.heipiao.api.v2.domain.ActivityArticle;
import com.heipiao.api.v2.mapper.ActivityArticleMapper;
import com.heipiao.api.v2.service.ActivityArticleService;

/**
 * @author wzw
 * @date 2017年3月21日
 */
@Service
public class ActivityArticleServiceImpl implements ActivityArticleService {

	@Resource
	private ActivityArticleMapper activityArticleMapper;

	@Override
	public ActivityArticle getById(Long id) {
		return activityArticleMapper.selectById(id);
	}

	@Override
	public List<ActivityArticle> getListsByCid(Map<String, Object> map) {
		return activityArticleMapper.selectFilterByCid(map);
	}

}
