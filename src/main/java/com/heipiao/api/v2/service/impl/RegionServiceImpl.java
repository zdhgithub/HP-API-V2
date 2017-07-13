package com.heipiao.api.v2.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heipiao.api.v2.domain.Region;
import com.heipiao.api.v2.mapper.RegionMapper;
import com.heipiao.api.v2.repository.RegionRepository;
import com.heipiao.api.v2.service.RegionService;

@Service
@Transactional
public class RegionServiceImpl implements RegionService {

	
	@Resource
	private RegionRepository regionRepository;
	
	@Resource
	private RegionMapper regionMapper;

	@Override
	public List<Region> getProvince(String name) {
		List<Region> list = regionMapper.getProvince(name);
		return list;
	}

	@Override
	public List<Region> getAllCity(Integer num) {
		List<Region> list = regionMapper.getAllCity(num);
		return list;
	}

	
}
