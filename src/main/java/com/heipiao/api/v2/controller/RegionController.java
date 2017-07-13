package com.heipiao.api.v2.controller;


import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.heipiao.api.v2.domain.Region;
import com.heipiao.api.v2.service.RegionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 用户模块
 * 
 * @author 作者 :duzh
 */
@Api(tags = "区域模块")
@RestController
@RequestMapping(value = "region", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegionController {
	
	private static final Logger logger = LoggerFactory.getLogger(RegionController.class);
	@Resource
	private RegionService regionService;
	
	@ApiOperation(value = "省份(城市)信息")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "name", value = "省份(城市)名称", dataType = "string", required = true)
	})
	@RequestMapping(value = "info", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Region> getProvince(
			@RequestParam(value = "name", required = true) String name
			){
		logger.debug("name:{}", name);
		List<Region> list = regionService.getProvince(name);
		return list;
	}
	
	@ApiOperation(value = "省份下面的所有城市")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "path", name = "num", value = "省份id", dataType = "integer", required = true)
	})
	@RequestMapping(value = "city/{num}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Region> getAllCity(
			@PathVariable(value = "num", required = true) Integer num
			){
		logger.debug("num:{}", num);
		List<Region> list = regionService.getAllCity(num);
		return list;
	}
}
