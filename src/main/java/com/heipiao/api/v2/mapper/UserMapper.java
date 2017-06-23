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
	 */
	User selectById(@Param("id") Long id);
	
	List<User> getUserWithPage(@Param("provinceId") Integer provinceId, @Param("cityId") Integer cityId
			, @Param("regBegin") Date regBegin, @Param("regEnd") Date regEnd, @Param("orderBy") String orderBy
			, @Param("start") int start, @Param("size ") int size);
	
	Integer getUserCountForPage(@Param("provinceId") Integer provinceId, @Param("cityId") Integer cityId
			, @Param("regBegin") Date regBegin, @Param("regEnd") Date regEnd);
	
}
