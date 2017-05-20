package com.heipiao.api.v2.mapper;

import org.springframework.stereotype.Service;

import com.heipiao.api.v2.domain.User;

/**
 * @author z
 * @version 1.0
 * @description userMapper
 * @date 2016年6月1日
 */
@Service
public interface UserMapper {

	/**
	 * 根据 uid查找用户
	 */
	User selectById(Long id);
	
}
