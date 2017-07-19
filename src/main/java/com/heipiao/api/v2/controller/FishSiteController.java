package com.heipiao.api.v2.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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
import com.heipiao.api.v2.domain.FishSiteBaseInfo;
import com.heipiao.api.v2.domain.FishSiteEmployee;
import com.heipiao.api.v2.domain.HaveFish;
import com.heipiao.api.v2.exception.BadRequestException;
import com.heipiao.api.v2.exception.NotFoundException;
import com.heipiao.api.v2.service.FishSizeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "钓点模块")
@RestController
@RequestMapping(value = "fishsite", produces = MediaType.APPLICATION_JSON_VALUE)
public class FishSiteController {
	
	private static final Logger logger = LoggerFactory.getLogger(FishSiteController.class);
	@Resource
	private FishSizeService fishSizeService;
	
	@ApiOperation(value = "钓点列表", response = FishSiteBaseInfo.class)
	@ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "start", value = "查询页码，首页传1", dataType = "int", required = true),
		@ApiImplicitParam(paramType = "query", name = "size", value = "页面大小", dataType = "int", required = true),
		@ApiImplicitParam(paramType = "query", name = "longitude", value = "经度", dataType = "double", defaultValue = "114.032428", required = true),
		@ApiImplicitParam(paramType = "query", name = "latitude", value = "纬度", dataType = "double", defaultValue = "22.538205", required = true)
		})
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public RespMsg<List<FishSiteBaseInfo>> getFishSiteList(
			@RequestParam(value = "start", required = true) Integer start,
			@RequestParam(value = "size", required = true) Integer size,
			@RequestParam(value = "longitude", required = true) Double longitude,
			@RequestParam(value = "latitude", required = true) Double latitude
			) {
		logger.debug("start:{},size:{},longitude:{},latitude:{}",start,size,longitude,latitude);
		start = start - 1 <= 0 ? 0 : (start - 1); 
		List<FishSiteBaseInfo> list = fishSizeService.getFishsiteList(start,size,longitude,latitude);
		return new RespMsg<List<FishSiteBaseInfo>>(list);
	}
	
	@ApiOperation(value = "查看钓点详情",response = FishSiteBaseInfo.class)
	@ApiImplicitParams({@ApiImplicitParam(paramType = "path", name = "uid", value = "用户id", dataType = "int",required = true),
		@ApiImplicitParam(paramType = "query", name = "longitude", value = "经度", dataType = "double", defaultValue = "114.032428", required = true),
		@ApiImplicitParam(paramType = "query", name = "latitude", value = "纬度", dataType = "double", defaultValue = "22.538205", required = true)})
	@RequestMapping(value = "detail/{uid}", method = RequestMethod.GET)
	public RespMsg<FishSiteBaseInfo> getHaveFishList(
			@PathVariable(value = "uid", required = true) Integer uid,
			@RequestParam(value = "longitude", required = true) Double longitude,
			@RequestParam(value = "latitude", required = true) Double latitude
			){
		logger.debug("uid:{},start:{},size:{},longitude:{},latitude:{}",uid,longitude,latitude);
		
		if(uid == null || longitude==null || latitude==null){
			throw new NotFoundException("参数不能为空");
		}
		FishSiteBaseInfo list = fishSizeService.getfishSiteDetial(uid,longitude,latitude);
		return new RespMsg<FishSiteBaseInfo>(list);
	}
	
	@ApiOperation(value = "查看钓场发布的所有有鱼列表",response = HaveFish.class)
	@ApiImplicitParams({@ApiImplicitParam(paramType = "path", name = "uid", value = "用户id", dataType = "int",required = true),
		@ApiImplicitParam(paramType = "query", name = "start", value = "查询页码，首页传1", dataType = "int", required = true),
		@ApiImplicitParam(paramType = "query", name = "longitude", value = "经度", dataType = "double", defaultValue = "114.032428", required = true),
		@ApiImplicitParam(paramType = "query", name = "latitude", value = "纬度", dataType = "double", defaultValue = "22.538205", required = true)})
	@RequestMapping(value = "havefish/{uid}", method = RequestMethod.GET)
	public RespMsg<List<HaveFish>> getHaveFishList(
			@PathVariable(value = "uid", required = true) Integer uid,
			@RequestParam(value = "longitude", required = true) Double longitude,
			@RequestParam(value = "latitude", required = true) Double latitude,
			@RequestParam(value = "start", required = true) Integer start
			){
		logger.debug("uid:{},start:{}",uid);
		
		if(uid == null){
			throw new NotFoundException("参数不能为空");
		}
		List<HaveFish> list = fishSizeService.getHaveFishAllList(uid, start, longitude, latitude);
		return new RespMsg<List<HaveFish>>(list);
	}
	
	@ApiOperation(value = "钓场信息修改", notes = "参数说明：<br />"
			+ "fishSiteUid：用户id<br/>"
			+ "userName：钓场主姓名<br/>"
			+ "fishSiteName: 钓场名称<br/>"
			+ "phone: 联系方式<br/>"
			+ "lon：经度<br/>"
			+ "lat：纬度<br/>"
			+ "address：详细地址<br/>"
			+ "putFishInfo:放鱼信息<br/>"
			+ "siteSize:规模大小<br/>"
			+ "siteCharge:收费情况<br/>"
			+ "supportFacility:配套设施<br/>"
			)
	@RequestMapping(value = "baseinfo",method = RequestMethod.PUT)
	public String applyHaveFish(@RequestBody FishSiteBaseInfo fishSiteBaseInfo) {
		logger.debug("FishSiteBaseInfo:{}", fishSiteBaseInfo);
		
		if (fishSiteBaseInfo.getFishSiteUid() == null || StringUtils.isBlank(fishSiteBaseInfo.getFishSiteName())
				||  fishSiteBaseInfo.getLon() == null || fishSiteBaseInfo.getLat() == null
				|| fishSiteBaseInfo.getPhone()==null) {
			throw new BadRequestException("必要参数不能为空");
		}
		fishSizeService.addFishSiteBaseInfo(fishSiteBaseInfo);
		return JSONObject.toJSONString(Status.success);
	}
	
	@ApiOperation(value = "钓场员工列表", response = FishSiteEmployee.class) 	
	@ApiImplicitParam(paramType = "path", name = "uid", value = "钓场主id", dataType = "int",required = true)
	@RequestMapping(value = "employee/{uid}", method = RequestMethod.GET)
	public RespMsg<List<FishSiteEmployee>> getFishSiteEmployeeByUid(
			@PathVariable(value = "uid", required = true) Integer uid) {
		logger.debug("uid:{}",uid);
		if(uid == null){
			throw new NotFoundException("参数不能为空");
		}
		List<FishSiteEmployee> list = fishSizeService.getEmployee(uid);
		return new RespMsg<List<FishSiteEmployee>>(list);
	}
	
	@ApiOperation(value = "删除员工")
	@ApiImplicitParam(paramType = "path", name = "id", value = "员工主键id", dataType = "int",required = true)
	@RequestMapping(value = "employee/{id}",method = RequestMethod.DELETE)
	public String applyFishSiteBase(@PathVariable(value = "id", required = true) Integer id) {
		
		logger.debug("id:{}", id);
		
		if (id == null){
			throw new BadRequestException("必要参数不能为空");
		}
		fishSizeService.deleteFishSiteEmployee(id);
		return JSONObject.toJSONString(Status.success);
	}	
	
	
	@ApiOperation(value = "添加员工", notes = "参数说明：<br />"
			+ "employeeUid： dataType (int) 员工id<br/>"
			+ "uid： dataType (int) 钓场主id<br/>"
			)
	@RequestMapping(value = "employee",method = RequestMethod.POST)
	public String haveFishLike(@RequestBody JSONObject json) {
		
		logger.debug("json:{}", json);
		
		if (json.getInteger("uid") == null 
				|| json.getInteger("employeeUid") == null){
			throw new BadRequestException("必要参数不能为空");
		}
		fishSizeService.addFishSiteEmployee(json.getInteger("uid"),json.getInteger("employeeUid"));
		return JSONObject.toJSONString(Status.success);
	}	
	
	@ApiOperation(value = "搜索可添加员工",response = FishSiteEmployee.class)
	@ApiImplicitParam(paramType = "query", name = "phone", value = "电话号码", dataType = "string", required = true)
	@RequestMapping(value = "employee/usable", method = RequestMethod.GET)
	public RespMsg<FishSiteEmployee> auditHaveFish(
			@RequestParam(value = "phone", required = true) String phone) {
		logger.debug("phone:{}",phone);
		FishSiteEmployee fishSiteEmployee = fishSizeService.getUsableEmployee(phone);
		return new RespMsg<FishSiteEmployee>(fishSiteEmployee);
	}
	
}
