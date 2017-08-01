package com.heipiao.api.v2.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.heipiao.api.v2.domain.MarkHaveFish333;

/**
 * 有鱼默认设置
 * 
 * @author Duh
 * @version 3.0
 * @date 2017.07.10
 *
 */
@Mapper
public interface MarkHaveFishMapper {
	
	List<MarkHaveFish333> getMarkHaveFishByMarkNum(@Param("markNum")String markNum,@Param("start")Integer start);
	
	List<MarkHaveFish333> getMarkHaveFishByUid(@Param("rewardUid")Integer rewardUid,@Param("start")Integer start);
	
	void addMarkHaveFish(MarkHaveFish333 markHaveFish);
	
	void updateMarkHaveFish(MarkHaveFish333 markHaveFish);
}
