package com.heipiao.api.v2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.heipiao.api.v2.domain.Alliance;

/**
 * 活动相关
 * 
 * @author Chris
 * @version 3.0
 * @date 2017.03.06
 *
 */
@Mapper
public interface AllianceMapper {

	/**
	 * 根据距离参数经纬度查找距离最近的3家加盟商
	 * @param count
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	public List<Alliance> getTopAllianceList(@Param("count") int count, @Param("longitude") double longitude, @Param("latitude") double latitude);
	
	/**
	 * 查询全部加盟商
	 * @return
	 */
	public List<Alliance> getAllianceList();

}
