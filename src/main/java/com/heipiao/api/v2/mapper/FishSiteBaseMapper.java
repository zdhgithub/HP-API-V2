package com.heipiao.api.v2.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.heipiao.api.v2.domain.FishSiteBase;

/**
 * 钓场基本设置
 * 
 * @author Duh
 * @version 3.0
 * @date 2017.07.10
 *
 */
@Mapper
public interface FishSiteBaseMapper {
	
	FishSiteBase getFishSiteBaseByUid(@Param("uid")Integer uid);
	
	void addFishSiteBase(FishSiteBase fishSiteBase);
}
