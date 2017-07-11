package com.heipiao.api.v2.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.heipiao.api.v2.domain.HaveFishLike;

/**
 * 活动相关
 * 
 * @author Duh
 * @version 3.0
 * @date 2017.07.13
 *
 */
@Mapper
public interface HaveFishLikeMapper {

	void addHaveFishLike(HaveFishLike haveFishLike);
	
	void getLikeUser(@Param("uid")Integer uid,@Param("haveFishLike")Integer haveFishLike);
}
