package com.heipiao.api.v2.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.heipiao.api.v2.domain.FishSiteBaseInfo;

/**
 * 钓场基本设置
 * 
 * @author Duh
 * @version 3.0
 * @date 2017.07.10
 *
 */
@Mapper
public interface FishSiteBaseInfoMapper {
	
	List<FishSiteBaseInfo> getFishSite(@Param("start")Integer start,@Param("size")Integer size,
			@Param("lon") Double lon,@Param("lat")Double lat);
	
	void addFishSiteBaseInfo(FishSiteBaseInfo fishSiteBaseInfo);
	
	FishSiteBaseInfo getFishSiteByuid(@Param("fishSiteUid") Integer uid,@Param("lon") Double lon,@Param("lat")Double lat);
	
	void updateFishSiteInfo(FishSiteBaseInfo fishSiteBaseInfo);
}
