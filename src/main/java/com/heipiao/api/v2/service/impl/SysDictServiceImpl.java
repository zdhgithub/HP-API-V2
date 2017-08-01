package com.heipiao.api.v2.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;
import com.heipiao.api.v2.domain.SysDict;
import com.heipiao.api.v2.mapper.SysDictMapper;
import com.heipiao.api.v2.service.SysDictService;

/**
 * 字典
 * @author Duzh
 *
 */
@Service
@Transactional(readOnly = true)
public class SysDictServiceImpl implements SysDictService {

	@Resource
	private SysDictMapper sysDictMapper;
	
	@Override
	public List<SysDict> getSysCodeByType(String type) {
		 List<SysDict> list = sysDictMapper.getDictByType(type);
		return list;
	}

	@Override
	@Transactional(readOnly = false,rollbackFor = {Exception.class})
	public void addSysDict(SysDict sysDict) {
		sysDictMapper.addDict(sysDict);
	}

}
