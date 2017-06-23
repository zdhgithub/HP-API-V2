package com.heipiao.api.v2.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heipiao.api.v2.domain.PageInfo;
import com.heipiao.api.v2.domain.User;
import com.heipiao.api.v2.mapper.UserMapper;
import com.heipiao.api.v2.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	@Override
	public PageInfo<List<User>> getUserWithPage(Integer provinceId, Integer cityId, java.sql.Date regBegin,
			java.sql.Date regEnd, String orderBy, int start, int size) {
		List<User> list = userMapper.getUserWithPage(provinceId, cityId, regBegin, regEnd, orderBy, start, size);
		Integer totalCount = userMapper.getUserCountForPage(provinceId, cityId, regBegin, regEnd);
		
		PageInfo<List<User>> pageInfo = new PageInfo<List<User>>(totalCount, list);
		return pageInfo;
	}

}
