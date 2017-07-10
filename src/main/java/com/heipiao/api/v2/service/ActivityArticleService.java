package com.heipiao.api.v2.service;

import java.util.List;
import java.util.Map;

import com.heipiao.api.v2.domain.ActivityArticle;

/**
 * @author wzw
 * @date 2017年3月21日
 */
public interface ActivityArticleService {

	ActivityArticle getById(Long id);

	List<ActivityArticle> getListsByCid(Map<String, Object> map);

}
