package com.heipiao.api.v2.mapper;


import java.util.Date;
import java.util.List;

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
	
	List<FishSiteBase> getAllFishSiteBase(@Param("start") Integer start,@Param("size") Integer size,@Param("provinceId") Integer provinceId,@Param("cityId") Integer cityId,
			@Param("regBegin") Date regBegin,@Param("regEnd") Date regEnd);
	
	Integer getFishSiteBaseCountForPage(@Param("provinceId") Integer provinceId,@Param("cityId") Integer cityId,@Param("regBegin") Date regBegin,@Param("regEnd") Date regEnd);
	
	
	void updateFishSiteBase(@Param("uid") Integer uid,@Param("status")Integer status);
}
