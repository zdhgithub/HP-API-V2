package com.heipiao.api.v2.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.heipiao.api.v2.domain.Business;

/**
 * 活动相关
 * 
 * @author Duh
 * @version 3.0
 * @date 2017.07.13
 *
 */
@Mapper
public interface BusinessMapper {

	
	/**
	 * 查询全部舜微商
	 * @return
	 */
	public List<Business> getAllBusinessList(@Param("regBegin") Date regBegin, @Param("regEnd") Date regEnd, @Param("address") String address
			, @Param("start") int start, @Param("size") int size);


	/**
	 * 查询全部舜微商
	 * @return
	 */
	Integer getBusinessCountForPage(@Param("regBegin") Date regBegin, @Param("regEnd") Date regEnd, @Param("address") String address);
}
