package com.heipiao.api.v2.controller;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.heipiao.api.v2.constant.RespMsg;
import com.heipiao.api.v2.domain.Location;
import com.heipiao.api.v2.domain.MPLoginInfo;
import com.heipiao.api.v2.domain.PageInfo;
import com.heipiao.api.v2.domain.Region;
import com.heipiao.api.v2.domain.User;
import com.heipiao.api.v2.exception.BadRequestException;
import com.heipiao.api.v2.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 用户模块
 * 
 * @author 作者 :Chris
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Resource
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@ApiOperation(value = "微信小程序登录", response = User.class)
	@RequestMapping(value = "mplogin", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public User userWxMiniLogin(@RequestBody MPLoginInfo mpLoginInfo) {
		logger.info("MPLoginInfo:{}", mpLoginInfo);
		
		String code = mpLoginInfo.getCode();
		String userInfo = mpLoginInfo.getUserInfo();
		Integer parentUid = mpLoginInfo.getParentUid();
		if (code == null || userInfo == null ) {
			throw new BadRequestException("请求参数不能为空");
		}
		
		String result = userService.getWXUserinfo(userInfo, code);
		JSONObject resultJson = JSONObject.parseObject(result);
		
		//验证微信unionId是否存在并且已绑定
		String unionId = resultJson.getString("unionId");
		User user = userService.queryUserByOpenId(unionId);
		
		if(user == null) {
			//新用户注册
			user = userService.save(unionId, resultJson.getString("gender"), resultJson.getString("nickName"), resultJson.getString("avatarUrl"),parentUid);
		}
		
		return user;
	}
	
	@ApiOperation(value = "获取所有用户列表", response = User.class, notes = "参数说明：<br />"
			+ "起始页，首页为1<br />"
			+ "起始日期，日期格式（yyyy-MM-dd）<br />"
			+ "结束日期，日期格式（yyyy-MM-dd）<br />"
			+ "排序依据，可选排序依据有ASC(升序)和DESC(降序)<br />"
			+ "起始日期和结束日期，返回的结果集包含起始日期和结束日期<br />")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "provinceId", value = "省份id", dataType = "int", required = false)
		, @ApiImplicitParam(paramType = "query", name = "cityId", value = "城市id", dataType = "int", required = false)
		, @ApiImplicitParam(paramType = "query", name = "regBegin", value = "注册起始日期", dataType = "date", required = false)
		, @ApiImplicitParam(paramType = "query", name = "regEnd", value = "注册结束日期", dataType = "date", required = false)
		, @ApiImplicitParam(paramType = "query", name = "orderBy", value = "排序依据", dataType = "String", required = false, example = "desc")
		, @ApiImplicitParam(paramType = "query", name = "start", value = "起始页", dataType = "int", required = true, example = "1", defaultValue = "1")
		, @ApiImplicitParam(paramType = "query", name = "size", value = "页大小", dataType = "int", required = true, example = "10")
	})
	@RequestMapping(value = "page", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public PageInfo<List<User>> getUserWithPage(
			@RequestParam(value = "provinceId", required = false) Integer provinceId
			, @RequestParam(value = "cityId", required = false) Integer cityId
			, @RequestParam(value = "regBegin", required = false) Date regBegin
			, @RequestParam(value = "regEnd", required = false) Date regEnd
			, @RequestParam(value = "orderBy", required = false) String orderBy
			, @RequestParam(value = "start", required = true) int start
			, @RequestParam(value = "size", required = true) int size) {
		logger.debug("provinceId:{}, cityId:{}, regBegin:{}, regEnd:{}, orderBy:{}, start:{}, size:{}", provinceId, cityId, regBegin, regEnd, orderBy, start, size);
		
		if (StringUtils.isNotBlank(orderBy) && !"asc".equalsIgnoreCase(orderBy) && !"desc".equalsIgnoreCase(orderBy)) {
			throw new BadRequestException(String.format("排序参数错误，orderBy:%s", orderBy));
		}

		start = start - 1 <= 0 ? 0 : (start - 1) * size;
		PageInfo<List<User>> pageInfo = userService.getUserWithPage(provinceId, cityId, regBegin, regEnd, orderBy, start, size);
		return pageInfo;
	}
	
	@ApiOperation(value = "更新用户位置信息")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "path", name = "uid", value = "用户id", dataType = "long", required = true)
		, @ApiImplicitParam(paramType = "query", name = "lng", value = "经度", dataType = "double", required = true)
		, @ApiImplicitParam(paramType = "query", name = "lat", value = "纬度", dataType = "double", required = true)
	})
	@RequestMapping(value = "location/{uid}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public Location updateLocation(
			@PathVariable(value = "uid", required = true) long uid
			,@RequestParam(value = "lng", required = true) double lng
			,@RequestParam(value = "lat", required = true) double lat) {
		Location location = userService.updateLocation(uid, lng, lat);
		return location;
	}
	
	@ApiOperation(value = "省份信息")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "name", value = "省份名称", dataType = "string", required = true)
	})
	@RequestMapping(value = "location", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Region> getProvince(
			@RequestParam(value = "name", required = true) String name
			){
		logger.debug("name:{}", name);
		List<Region> list = userService.getProvince(name);
		return list;
	}
	
	@ApiOperation(value = "城市信息")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "path", name = "num", value = "省份id", dataType = "int", required = true)
	})
	@RequestMapping(value = "location/{num}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Region> getAllCity(
			@PathVariable(value = "num", required = true) Integer num
			){
		logger.debug("num:{}", num);
		List<Region> list = userService.getAllCity(num);
		return list;
	}
	
	@ApiOperation(value = "获取用户的所有下线OCC调用接口", response = User.class, notes = "参数说明：<br />"
			+ "起始页，首页为1<br />"
			+ "起始日期，日期格式（yyyy-MM-dd）<br />"
			+ "结束日期，日期格式（yyyy-MM-dd）<br />"
			+ "排序依据，可选排序依据有ASC(升序)和DESC(降序)<br />"
			+ "起始日期和结束日期，返回的结果集包含起始日期和结束日期<br />")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "path", name = "parentUid", value = "上级用户id", dataType = "int", required = true)
	})
	@RequestMapping(value = "page/{parentUid}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public PageInfo<List<User>> getChildUserWithPage(
			@RequestParam(value = "regBegin", required = false) Date regBegin
			, @RequestParam(value = "regEnd", required = false) Date regEnd
			, @RequestParam(value = "orderBy", required = false) String orderBy
			, @RequestParam(value = "start", required = true) int start
			, @RequestParam(value = "size", required = true) int size
			, @PathVariable(value = "parentUid", required = true) Integer parentUid) {
		logger.debug("regBegin:{}, regEnd:{}, orderBy:{}, start:{}, size:{},parentUid:{}",regBegin, regEnd, orderBy, start, size,parentUid);
		
		if (StringUtils.isNotBlank(orderBy) && !"asc".equalsIgnoreCase(orderBy) && !"desc".equalsIgnoreCase(orderBy)) {
			throw new BadRequestException(String.format("排序参数错误，orderBy:%s", orderBy));
		}

		start = start - 1 <= 0 ? 0 : (start - 1) * size;
		PageInfo<List<User>> pageInfo = userService.getChildUserWithPage(regBegin, regEnd, orderBy, start, size,parentUid);
		return pageInfo;
	}
	
	@ApiOperation(value = "获取用户的所有下线（小程序调用接口）", response = User.class, notes = "参数说明：<br />"
			+ "page:页码，首页为1<br />"
			+ "size:页码容量<br />")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "path", name = "parentUid", value = "上级用户id", dataType = "int", required = true)
	})
	@RequestMapping(value = "branch/{parentUid}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<User> getChildUser(
			@RequestParam(value = "page", required = true) int page
			, @RequestParam(value = "size", required = true) int size
			, @PathVariable(value = "parentUid", required = true) Integer parentUid) {
		
		logger.debug("page:{}, size:{},parentUid:{}",page, size,parentUid);
		
		page = (page-1)*size;
		List<User> list = userService.getChildUserPage(page, size,parentUid);
		
		return list;
	}
}
