package com.heipiao.api.v2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.heipiao.api.v2.domain.SysDict;
@Service
public interface SysDictMapper {
	
	
	List<SysDict> getDictByType(@Param("type") String type);

	
	void addDict(SysDict sysDict);

	void deleteDict(@Param("id")Integer id);
	
}
