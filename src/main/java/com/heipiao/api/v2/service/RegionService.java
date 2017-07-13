package com.heipiao.api.v2.service;


import java.util.List;

import com.heipiao.api.v2.domain.Region;

public interface RegionService {
	/**
	 * 获取省份信息
	 * @param name 省份名称
	 */
	List<Region> getProvince(String name);
	
	/**
	 * 获取城市信息
	 * @param num 省份id
	 */
	List<Region> getAllCity(Integer num);
}
