package com.heipiao.api.v2.controller;


import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.heipiao.api.v2.constant.RespMsg;
import com.heipiao.api.v2.constant.Status;
import com.heipiao.api.v2.domain.SysDict;
import com.heipiao.api.v2.exception.BadRequestException;
import com.heipiao.api.v2.exception.NotFoundException;
import com.heipiao.api.v2.service.SysDictService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 用户模块
 * 
 * @author 作者 :duzh
 */
@Api(tags = "系统字典模块")
@RestController
@RequestMapping(value = "sysdict", produces = MediaType.APPLICATION_JSON_VALUE)
public class SysDictController {
	
	private static final Logger logger = LoggerFactory.getLogger(SysDictController.class);
	@Resource
	private SysDictService sysDictService;
	
	@ApiOperation(value = "（OCC和小程序公用）配置参数（鱼大小（fishsize）、配送方式（delivery）、奖品内容（reward）、奖励条件（condition））", response = SysDict.class) 	
	@ApiImplicitParam(paramType = "query", name = "type", value = "类型（鱼大小（fishsize）、配送方式（delivery）、奖品内容（reward）、奖励条件（condition））",dataType = "String",required = true)
	@RequestMapping(value = "sysdict", method = RequestMethod.GET)
	public RespMsg<List<SysDict>> getSysDiceList(
			@RequestParam(value = "type", required = true) String type) {
		logger.debug("type:{}",type);
		if(type == null){
			throw new NotFoundException("参数不能为空");
		}
		List<SysDict> list = sysDictService.getSysCodeByType(type);
		return new RespMsg<List<SysDict>>(list);
	}
	
	@ApiOperation(value = "OCC字典添加", notes = "参数说明：<br />"
			+ "type：类型（dataType:string  [鱼大小（fishsize）、配送方式（delivery）、奖品内容（reward）、奖励条件（condition）]）<br/>"
			+ "remark：备注说明<br/>"
			+ "num：类型编号<br/>"
			+ "value：值<br/>"
			)
	@RequestMapping(value = "dict",method = RequestMethod.POST)
	public String haveFishLike(@RequestBody SysDict sysDict) {
		
		logger.debug("sysDict:{}", sysDict);
		
		if (	sysDict.getType()== null 
				|| sysDict.getNum() == null 
				||sysDict.getValue() == null){
			throw new BadRequestException("必要参数不能为空");
		}
		sysDictService.addSysDict(sysDict);
		return JSONObject.toJSONString(Status.success);
	}	
}
