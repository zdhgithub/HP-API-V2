package com.heipiao.api.v2.service;

import java.sql.Date;
import java.util.List;

import com.heipiao.api.v2.domain.PageInfo;
import com.heipiao.api.v2.domain.User;

public interface UserService {

	/**
	 * 调用微信接口，拉取微信用户信息
	 * @param userInfo
	 * @param code
	 * @return
	 */
	public String getWXUserinfo(String userInfo, String code);
	
	/**
	 * 根据unionId查询用户
	 * @param unionId，即OpenId
	 * @return
	 */
	public User queryUserByOpenId(String unionId);

	/**
	 * 用户注册 保存用户并返回带有id的User对象
	 * @param unionId openId
	 * @param gender 性别
	 * @param nickName 昵称
	 * @param avatarUrl 头像地址
	 * @return
	 */
	User save(String unionId, String gender, String nickName, String avatarUrl);

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
