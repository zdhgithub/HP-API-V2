package com.heipiao.api.v2.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.heipiao.api.v2.domain.User;

/**
 * 
 * @author Chris
 *
 */
@Service
public interface UserMapper {

	/**
	 * 根据 uid查找用户
	 * @param id
	 * @return
	 */
	User selectById(@Param("id") Long id);
	
	/**
	 * 根据unionId和Source查找用户
	 * @param unionId uniondId
	 * @param source 来源
	 * @return
	 */
	User queryUserByOpenId(@Param("unionId") String unionId, @Param("source") String source);
	
	/**
	 * 添加用户
	 * @param user
	 */
	void save(User user);
	
	/**
	 * 查询用户信息（分页）
	 * @param provinceId 省份id
	 * @param cityId 城市id
	 * @param regBegin 注册开始时间
	 * @param regEnd 注册结束时间
	 * @param orderBy 排序依据
	 * @param start 起始记录
	 * @param size 页大小
	 * @return
	 */
	List<User> getUserWithPage(@Param("provinceId") Integer provinceId, @Param("cityId") Integer cityId
			, @Param("regBegin") Date regBegin, @Param("regEnd") Date regEnd, @Param("orderBy") String orderBy
			, @Param("start") int start, @Param("size ") int size);
	
	/**
	 * 查询指定条件的用户数量（用于分页）
	 * @param provinceId 省份id
	 * @param cityId 城市id
	 * @param regBegin 注册开始时间
	 * @param regEnd 注册结束时间
	 * @return
	 */
	Integer getUserCountForPage(@Param("provinceId") Integer provinceId, @Param("cityId") Integer cityId
			, @Param("regBegin") Date regBegin, @Param("regEnd") Date regEnd);
	
}
