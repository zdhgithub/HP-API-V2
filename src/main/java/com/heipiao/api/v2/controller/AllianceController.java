package com.heipiao.api.v2.controller;

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

import com.heipiao.api.v2.domain.Alliance;
import com.heipiao.api.v2.exception.BadRequestException;
import com.heipiao.api.v2.service.AllianceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 小程序加盟商相关功能
 * 
 * @author Chris
 * @version 1.0
 * @date 2017-05-22
 * 
 *       FIXME 文章中应用到里面来，特别是注解 http://www.cnblogs.com/softidea/p/6251249.html
 * 
 */
@Api(tags = "小程序加盟商模块")
@RestController
@RequestMapping(value = "alliance", produces = MediaType.APPLICATION_JSON_VALUE) // , consumes = MediaType.APPLICATION_JSON_VALUE
public class AllianceController {

	@Resource
	private AllianceService allianceService;

	private static final Logger logger = LoggerFactory.getLogger(AllianceController.class);

	@ApiOperation(value = "申请加盟", httpMethod = "POST", notes = "参数说明：<br />"
			+ "uid：用户id<br/>"
			+ "phoneNumber：手机号码<br/>"
			+ "shopName：渔具店名称<br/>"
			+ "name：姓名<br/>"
			+ "address：店铺地址<br/>"
			+ "longitude：经度<br/>"
			+ "latitude：纬度")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void apply(@RequestBody Alliance alliance) {
		logger.debug("alliance:{}", alliance);
		
		if (alliance.getUid() == null || StringUtils.isBlank(alliance.getPhoneNumber())
				|| StringUtils.isBlank(alliance.getAddress()) || StringUtils.isBlank(alliance.getShopName())
				|| StringUtils.isBlank(alliance.getName()) || alliance.getLongitude() == null || alliance.getLatitude() == null) {
			throw new BadRequestException("必要参数不能为空");
		}

		allianceService.addAlliance(alliance);
	}

	@ApiOperation(value = "获取距离当前位置最近的前?家加盟商", httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "path", name = "count", value = "取前多少家加盟商", defaultValue = "3", required = true),
			@ApiImplicitParam(paramType = "query", name = "longitude", value = "经度", dataType = "double", defaultValue = "114.032428", required = true),
			@ApiImplicitParam(paramType = "query", name = "latitude", value = "纬度", dataType = "double", defaultValue = "22.538205", required = true) })
	@RequestMapping(value = "top/{count}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Alliance> getTopAllianceList(@PathVariable(value = "count", required = true) int count,
			@RequestParam(value = "longitude", required = true) Double longitude,
			@RequestParam(value = "latitude", required = true) Double latitude) {
		logger.debug("count:{}, longitude:{}, latitude:{}", count, longitude, latitude);
		
		if (longitude == null || latitude == null) {
			throw new BadRequestException("经纬度不能为空");
		}
		
		return allianceService.getTopAllianceList(count, longitude, latitude);
	}

	@ApiOperation(value = "审核加盟商", httpMethod = "PUT", notes = "状态参数说明：<br />"
			+ "0：待审核<br />"
			+ "1：审核已通过<br />"
			+ "-1：审核未通过")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "path", name = "uid", value = "加盟商uid", required = true),
			@ApiImplicitParam(paramType = "query", name = "status", value = "状态", dataType = "integer", defaultValue = "1", required = true) })
	@RequestMapping(value = "audit/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void auditing(@PathVariable(value = "uid", required = true) int uid,
			@RequestParam(value = "status", required = true) Integer status) {
		logger.debug("uid:{}, status:{}", uid, status);
		
		if (status == null) {
			throw new BadRequestException("状态不能为空");
		}

		allianceService.Auditing(uid, status);
	}

	@ApiOperation(value = "修改加盟商信息", httpMethod = "PUT", notes = "参数说明：<br />"
			+ "uid： 用户id<br />"
			+ "shopName： 渔具店名称<br />"
			+ "name： 姓名<br />"
			+ "address：: 店铺地址<br />"
			+ "longitude：: 经度<br />"
			+ "latitude： 纬度<br />"
			+ "[stock]： 体验库存")
	@ApiImplicitParam(paramType = "path", name = "uid", value = "加盟商uid", required = true)
	@RequestMapping(value = "{uid}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void editAlliance(@PathVariable(value = "uid", required = true) int uid, @RequestBody Alliance alliance) {
		logger.debug("uid:{}, alliance:{}", uid, alliance);
		
		if (StringUtils.isNotBlank(alliance.getPhoneNumber())) {
			throw new BadRequestException("手机号码不允许修改");
		}
		
		if (StringUtils.isBlank(alliance.getAddress()) || StringUtils.isBlank(alliance.getShopName())
				|| StringUtils.isBlank(alliance.getName()) || alliance.getLongitude() == null
				|| alliance.getLatitude() == null) {
			throw new BadRequestException("必要参数不能为空");
		}

		allianceService.updateAlliance(uid, alliance);
	}

	@ApiOperation(value = "重新申请加盟", httpMethod = "PUT", notes = "参数说明：<br />"
			+ "uid： 用户id<br />"
			+ "shopName： 渔具店名称<br />"
			+ "name： 姓名<br />"
			+ "address：: 店铺地址<br />"
			+ "longitude：: 经度<br />"
			+ "latitude： 纬度<br />")
	@ApiImplicitParam(paramType = "path", name = "uid", value = "加盟商uid", required = true)
	@RequestMapping(value = "reapply/{uid}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void reApply(@PathVariable(value = "uid", required = true) int uid, @RequestBody Alliance alliance) {
		logger.debug("uid:{}, alliance:{}", uid, alliance);
		
		if (StringUtils.isBlank(alliance.getPhoneNumber()) || StringUtils.isBlank(alliance.getAddress())
				|| StringUtils.isBlank(alliance.getShopName()) || StringUtils.isBlank(alliance.getName())
				|| alliance.getLongitude() == null || alliance.getLatitude() == null) {
			throw new BadRequestException("必要参数不能为空");
		}
		
		allianceService.updateAllianceForApply(uid, alliance);
	}

	@ApiOperation(value = "查询加盟商状态", httpMethod = "GET", notes = "响应状态说明：<br />"
			+ "空：未申请<br />"
			+ "0：待审核<br />"
			+ "1：审核已通过<br />"
			+ "-1：审核未通过")
	@ApiImplicitParam(paramType = "path", name = "uid", value = "用户id", required = true)
	@RequestMapping(value = "status/{uid}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Integer getStatusByUid(@PathVariable(value = "uid", required = true) int uid) {
		logger.debug("uid:{}", uid);

		return allianceService.getStatusByUid(uid);
	}

	@ApiOperation(value = "查询所有加盟商", httpMethod = "GET")
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Alliance> getAllianceList() {
		return allianceService.getAllianceList();
	}

	@ApiOperation(value = "查询指定加盟商", httpMethod = "GET")
	@ApiImplicitParam(paramType = "path", name = "uid", value = "加盟商uid", required = true)
	@RequestMapping(value = "{uid}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Alliance getAllianceByUid(@PathVariable(value = "uid", required = true) int id) {
		logger.debug("uid:{}", id);

		return allianceService.getAllianceByUid(id);
	}
	
	@ApiOperation(value = "删除指定加盟商申请记录", httpMethod = "DELETE")
	@ApiImplicitParam(paramType = "path", name = "id", value = "加盟商流水号id", required = true)
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void del(@PathVariable(value = "id", required = true) int id) {
		logger.debug("id:{}", id);

		allianceService.deleteAllianceById(id);
	}

}
