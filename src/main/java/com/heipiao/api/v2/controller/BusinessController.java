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
import com.heipiao.api.v2.domain.Business;
import com.heipiao.api.v2.domain.PageInfo;
import com.heipiao.api.v2.exception.BadRequestException;
import com.heipiao.api.v2.service.BusinessService;
import com.heipiao.api.v2.service.TokenService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author Duzh
 *
 */
@Api(tags = "舜微商模块")
@RestController
@RequestMapping(value = "business", produces = MediaType.APPLICATION_JSON_VALUE)
public class BusinessController {

	@Resource
	private BusinessService businessService;
	
	@Resource
	private TokenService tokenService;

	private static final Logger logger = LoggerFactory.getLogger(BusinessController.class);

	@ApiOperation(value = "申请舜微商", notes = "参数说明：<br />"
			+ "uid：用户id<br/>"
			+ "name：姓名<br/>"
			+ "address：地址<br/>"
			+ "phoneNumber: 电话号码<br/>"
			+ "longitude：经度<br/>"
			+ "latitude：纬度")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void apply(@RequestBody Business business) {
		logger.debug("business:{}", business);
		
		if (business.getUid() == null || StringUtils.isBlank(business.getPhoneNumber())
				|| StringUtils.isBlank(business.getAddress())
				|| StringUtils.isBlank(business.getName()) || business.getLongitude() == null || business.getLatitude() == null) {
			throw new BadRequestException("必要参数不能为空");
		}

		businessService.addBusiness(business);
	}

	@ApiOperation(value = "审核加盟商", notes = "状态参数说明：<br />"
			+ "0：待审核<br />"
			+ "1：审核已通过<br />"
			+ "2：审核未通过<br />"
			+"3:下架")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "path", name = "uid", value = "舜微商uid", required = true),
			@ApiImplicitParam(paramType = "query", name = "status", value = "状态", dataType = "integer", defaultValue = "1", required = true) })
	@RequestMapping(value = "audit/{uid}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void auditing(@PathVariable(value = "uid", required = true) int uid,
			@RequestParam(value = "status", required = true) Integer status) {
		logger.debug("uid:{}, status:{}", uid, status);
		
		if (status == null) {
			throw new BadRequestException("状态不能为空");
		}

		businessService.Auditing(uid, status);
	}

	@ApiOperation(value = "修改舜微商信息", notes = "参数说明：<br />"
			+ "uid： 用户id<br />"
			+ "name： 姓名<br />"
			+ "address: 地址<br />"
			+ "delivery: 送货上门（2/5/10公里）<br />"
			+ "longitude： 经度<br />"
			+ "latitude： 纬度<br />"
			+ "[stock]： 体验库存")
	@ApiImplicitParam(paramType = "path", name = "uid", value = "舜微商uid", required = true)
	@RequestMapping(value = "{uid}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void editAlliance(@PathVariable(value = "uid", required = true) int uid, @RequestBody Business business) {
		logger.debug("uid:{}, business:{}", uid, business);
		
		if (StringUtils.isNotBlank(business.getPhoneNumber())) {
			throw new BadRequestException("手机号码不允许修改");
		}
		
		if (StringUtils.isBlank(business.getAddress()) || StringUtils.isBlank(business.getName()) 
				|| business.getLongitude() == null|| business.getLatitude() == null
				|| business.getDelivery() == null) {
			throw new BadRequestException("必要参数不能为空");
		}

		businessService.updateBusiness(uid, business);
	}

	@ApiOperation(value = "重新申请加盟", notes = "参数说明：<br />"
			+ "uid： 用户id<br />"
			+ "name： 姓名<br />"
			+ "phoneNumber: 电话号码<br/>"
			+ "address：: 店铺地址<br />"
			+ "longitude：: 经度<br />"
			+ "latitude： 纬度<br />")
	@ApiImplicitParam(paramType = "path", name = "uid", value = "舜微商uid", required = true)
	@RequestMapping(value = "reapply/{uid}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void reApply(@PathVariable(value = "uid", required = true) int uid, @RequestBody Business business) {
		logger.debug("uid:{}, business:{}", uid, business);
		
		if (StringUtils.isBlank(business.getPhoneNumber()) || StringUtils.isBlank(business.getAddress())
				|| StringUtils.isBlank(business.getName())|| business.getLongitude() == null 
				|| business.getLatitude() == null) {
			throw new BadRequestException("必要参数不能为空");
		}
		
		businessService.updateBusinessForApply(uid, business);
	}

	@ApiOperation(value = "查询加盟商状态", notes = "响应状态说明：<br />"
			+ "空：未申请<br />"
			+ "0：待审核<br />"
			+ "1：审核已通过<br />"
			+ "2：审核未通过<br />"
			+ "3:下架")
	@ApiImplicitParam(paramType = "path", name = "uid", value = "用户id",dataType = "int", required = true)
	@RequestMapping(value = "status/{uid}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Integer getStatusByUid(@PathVariable(value = "uid", required = true) int uid) {
		logger.debug("uid:{}", uid);

		return businessService.getStatusByUid(uid);
	}

	@ApiOperation(value = "查询所有舜微商", notes = "响应状态说明：<br />"
			+ "regBegin：起始日期，日期格式（yyyy-MM-dd）<br />"
			+ "regEnd：结束日期，日期格式（yyyy-MM-dd）<br />"
			+ "address:地址模糊查询：<br />")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "regBegin", value = "注册起始日期", dataType = "date", required = false)
		, @ApiImplicitParam(paramType = "query", name = "regEnd", value = "注册结束日期", dataType = "date", required = false)
		, @ApiImplicitParam(paramType = "query", name = "address", value = "模糊地址", dataType = "string", required = false, example = "广东")
		, @ApiImplicitParam(paramType = "query", name = "start", value = "起始页", dataType = "int", required = true, example = "1", defaultValue = "1")
		, @ApiImplicitParam(paramType = "query", name = "size", value = "页大小", dataType = "int", required = true, example = "10")
	})
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public PageInfo<List<Business>> getBusinessList(
			 @RequestParam(value = "regBegin", required = false) Date regBegin
			, @RequestParam(value = "regEnd", required = false) Date regEnd
			, @RequestParam(value = "address", required = false) String address
			, @RequestParam(value = "start", required = true) int start
			, @RequestParam(value = "size", required = true) int size) {
		logger.debug("regBegin:{},regEnd:{},adress:{},start:{},size:{}",regBegin,regEnd,address,start,size);
		
		start = start - 1 <= 0 ? 0 : (start - 1) * size;
		PageInfo<List<Business>> pageInfo = businessService.getBusinessList(regBegin,regEnd,address,start,size);
		return pageInfo;
	}

	@ApiOperation(value = "查询指定舜微商")
	@ApiImplicitParam(paramType = "path", name = "uid", value = "舜微商uid", required = true)
	@RequestMapping(value = "{uid}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Business getAllianceByUid(@PathVariable(value = "uid", required = true) int id) {
		logger.debug("uid:{}", id);

		return businessService.getBusinessByUid(id);
	}
	
	@ApiOperation(value = "删除指定舜微商申请记录")
	@ApiImplicitParam(paramType = "path", name = "id", value = "舜微商流水号id", required = true)
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void del(@PathVariable(value = "id", required = true) int id) {
		logger.debug("id:{}", id);

		businessService.deleteBusinessById(id);
	}

	@ApiOperation(value = "获取舜微商二维码",notes = "参数说明：<br />"
			+ "path：二维码展示路径")
	@RequestMapping(value = "token/wxaqrcode",method = RequestMethod.POST)
	public String getwxaqrcode(@RequestBody JSONObject json) throws Exception{

			logger.info("json:{}",json);
			String path = json == null ? null : json.getString("path");
			if(path == null){
				throw new BadRequestException("必要参数不能为空");
			}
			String rs = tokenService.getWxaQrcode(path);
			return rs;
	}
}
