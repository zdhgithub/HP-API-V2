package com.heipiao.api.v2.service;

import java.sql.Date;
import java.util.List;

import com.heipiao.api.v2.domain.PageInfo;
import com.heipiao.api.v2.domain.User;

public interface UserService {

	/**
	 * 获取所有点赞活动发布图片的列表
	 * @param provinceId 省份id
	 * @param cityId 城市
	 * @param regBegin 注册起始时间
	 * @param regEnd 注册结束时间
	 * @param orderBy 排序依据
	 * @param start 起始页
	 * @param size 页大小
	 * @return
	 */
	PageInfo<List<User>> getUserWithPage(Integer provinceId, Integer cityId, Date regBegin, Date regEnd, String orderBy, int start, int size);
	
}
