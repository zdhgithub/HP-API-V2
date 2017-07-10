package com.heipiao.api.v2.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.heipiao.api.v2.domain.ActivityArticle;

/**
 * @author wzw
 * @date 2017年3月21日
 */
@Service
public interface ActivityArticleMapper {

	ActivityArticle selectById(Long id);

	List<ActivityArticle> selectFilterByCid(Map<String, Object> map);

}
