package com.heipiao.api.v2.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.heipiao.api.v2.domain.HaveFishDefault;

/**
 * 有鱼默认设置
 * 
 * @author Duh
 * @version 3.0
 * @date 2017.07.10
 *
 */
@Mapper
public interface HaveFishDefaultMapper {
	
	HaveFishDefault getHaveFishDefaultByUid(@Param("uid")Integer uid);
	
	void addHaveFishDefault(HaveFishDefault haveFishDefault);
	
	void updateHaveFishDefault(HaveFishDefault haveFishDefault);
}
