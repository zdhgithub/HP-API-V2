package com.heipiao.api.v2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.heipiao.api.v2.domain.FishSiteEmployee;

/**
 * 活动相关
 * 
 * @author Duh
 * @version 3.0
 * @date 2017.07.13
 *
 */
@Mapper
public interface FishSiteEmployeeMapper {

	List<FishSiteEmployee> getEmployeeList(@Param("uid") Integer uid);
	
	void addEmployee(FishSiteEmployee fishSiteEmployee);
	
	FishSiteEmployee getEmployeeByPhone(@Param("phone")String phone);
	
	void deleteEmployee(@Param("id") Integer id);
	
	FishSiteEmployee selectEmployee(@Param("uid")Integer uid,@Param("employeeUid")Integer employeeUid);
}

