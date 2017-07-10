package com.heipiao.api.v2.mapper;

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
	
	List<HaveFish> getHaveFishAllList(@Param("uid") Integer uid,@Param("start")Integer start,@Param("size")Integer size,@Param("lon") Double lon,@Param("lat")Double lat);
	
	void addHaveFish(HaveFish haveFish);
}
