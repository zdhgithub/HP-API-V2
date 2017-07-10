package com.heipiao.api.v2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.heipiao.api.v2.domain.Region;

/**
 * 活动相关
 * 
 * @author Duh
 * @version 3.0
 * @date 2017.07.13
 *
 */
@Mapper
public interface RegionMapper {

	
	/**
	 * 获取省份
	 * @return
	 */
	public List<Region> getProvince(@Param("name") String name);
	/**
	 * 获取城市
	 * @return
	 */
	public List<Region> getAllCity(@Param("num") Integer num);

}
