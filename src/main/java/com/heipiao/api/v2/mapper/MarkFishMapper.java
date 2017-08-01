package com.heipiao.api.v2.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.heipiao.api.v2.domain.MarkFish;

/**
 * 有鱼默认设置
 * 
 * @author Duh
 * @version 3.0
 * @date 2017.07.10
 *
 */
@Mapper
public interface MarkFishMapper {
	
	List<MarkFish> getMarkFishByUid(@Param("siteUid")Integer siteUid,@Param("status")Integer status);
	
	List<MarkFish> getAllList(@Param("status")Integer status);
	
	void addMarkFish(MarkFish markFish);
	
	void updateMarkFish(MarkFish markFish);
	
	void deleteDeliveryAddress(@Param("id")Integer id);
	
	void updateMarkFishByUid(@Param("siteUid")Integer siteUid,@Param("markNum")String marknum,@Param("status")Integer status);
}
