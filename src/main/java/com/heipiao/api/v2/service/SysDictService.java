package com.heipiao.api.v2.service;

import java.util.List;

import com.heipiao.api.v2.domain.SysDict;

/**
 *系统字典
 * @author Duzh
 *
 */
public interface SysDictService {
	
	List<SysDict> getSysCodeByType(String type);
	
	void addSysDict(SysDict sysDict);
}
