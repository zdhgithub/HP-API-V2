package com.heipiao.api.v2.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.heipiao.api.v2.domain.HaveFish;

/**
 * 活动相关
 * 
 * @author Duh
 * @version 3.0
 * @date 2017.07.13
 *
 */
@Mapper
public interface HaveFishMapper {

	List<HaveFish> getHaveFishList(@Param("uid") Integer uid,@Param("start")Integer start,@Param("lon") Double lon,@Param("lat")Double lat);
	
	List<HaveFish> getHaveFishAllList(@Param("uid") Integer uid,@Param("start")Integer start,@Param("size")Integer size,
			@Param("lon") Double lon,@Param("lat")Double lat);
	
	void addHaveFish(HaveFish haveFish);
	
	List<HaveFish> getHaveFishOccList(@Param("start") Integer start,@Param("size") Integer size,@Param("provinceId") Integer provinceId,@Param("cityId") Integer cityId,
			@Param("regBegin") Date regBegin,@Param("regEnd") Date regEnd,@Param("type") Integer type,@Param("nickName") String nickName);

	Integer getHaveFishOccListCountForPage(@Param("provinceId") Integer provinceId,@Param("cityId") Integer cityId,
			@Param("regBegin") Date regBegin,@Param("regEnd") Date regEnd,@Param("type") Integer type,@Param("nickName") String nickName);
	
	void updateHaveFish(@Param("id")Integer id,@Param("isDisplay")Integer isDisplay);
	
	HaveFish getOneHaveFish(@Param("id")Integer id);
	
	List<HaveFish> getSiteHaveFishList(@Param("uid") Integer uid,@Param("start")Integer start,@Param("lon") Double lon,@Param("lat")Double lat);
	
	List<HaveFish> getHaveFishOccDetialList(@Param("uid") Integer uid,@Param("start")Integer start);
	
	Integer getPageCount(Integer uid);
}

