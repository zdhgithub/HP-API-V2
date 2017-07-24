package com.heipiao.api.v2.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.heipiao.api.v2.domain.FishSiteBaseSign;


/**
 * 钓场签到
 * @author duzh
 * @date 2017年7月24日
 * @version 1.0
 */
@Mapper
public interface FishSiteBaseSignMapper {
	
	List<FishSiteBaseSign> getSignList(@Param("uid")Integer uid);
	
	void addSignUid(FishSiteBaseSign fishSiteBaseSign);
	
	FishSiteBaseSign getIsSign(@Param("uid")Integer uid,@Param("signUid")Integer signUid);
	
}

