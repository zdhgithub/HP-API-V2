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
import com.heipiao.api.v2.domain.DeliveryAddress;
import com.heipiao.api.v2.exception.NotFoundException;
import com.heipiao.api.v2.service.DeliveryAddressService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 用户模块
 * 
 * @author 作者 :duzh
 */
@Api(tags = "收货地址模块")
@RestController
@RequestMapping(value = "deliveryAddress", produces = MediaType.APPLICATION_JSON_VALUE)
public class DeliveryAddressController {
	
	private static final Logger logger = LoggerFactory.getLogger(DeliveryAddressController.class);
	@Resource
	private DeliveryAddressService deliveryAddressService;
	
	@ApiOperation(value = "添加收货地址",notes = "参数说明：<br />"
			+ "uid：用户id（dateType:Integer）<br/>"
			+ "name：收货人姓名（dateType:String）<br/>"
			+ "phone: 联系方式（dateType:String）<br/>"
			+ "provinceId：省份id（dateType:Integer）<br/>"
			+ "provinceName: 省份名称（dateType:String）<br/>"
			+ "cityId: 城市id（dateType:Integer）<br/>"
			+ "cityName: 城市名称（dateType:String）<br/>"
			+ "regionId：区域编号（dateType:Integer）<br/>"
			+ "regionName：区域名称（dateType:String）<br/>"
			+ "address：详细地址（dateType:String）<br/>"
			+ "isDefault：是否为 默认设置（0-不是，1-是）（dateType:String）<br/>")
	@RequestMapping(method = RequestMethod.POST)
	public String getProvince(
			@RequestBody DeliveryAddress deliveryAddress
			){
		logger.debug("deliveryAddress:{}", deliveryAddress);
		deliveryAddressService.addDeliveryAddress(deliveryAddress);
		return JSONObject.toJSONString(Status.success);
	}
	
	@ApiOperation(value = "编辑修改收货地址",notes = "参数说明：<br />"
			+ "id：唯一主键（dateType:Integer）<br/>"
			+ "name：收货人姓名（dateType:String）<br/>"
			+ "phone: 联系方式（dateType:String）<br/>"
			+ "provinceId：省份id（dateType:Integer）<br/>"
			+ "provinceName: 省份名称（dateType:String）<br/>"
			+ "cityId: 城市id（dateType:Integer）<br/>"
			+ "cityName: 城市名称（dateType:String）<br/>"
			+ "regionId：区域编号（dateType:Integer）<br/>"
			+ "regionName：区域名称（dateType:String）<br/>"
			+ "address：详细地址（dateType:String）<br/>")
	@ApiImplicitParam(paramType = "path", name = "id", value = "唯一主键", defaultValue = "1", required = true)
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public String getAllCity(
			@PathVariable(value = "id", required = true) Integer id,
			@RequestBody DeliveryAddress deliveryAddress){
		logger.debug("id:{}", id);
		deliveryAddressService.updateDeliveryAddress(deliveryAddress);
		return JSONObject.toJSONString(Status.success);
	}
	
	@ApiOperation(value = "获取用户收货地列表", response = List.class) 	
	@ApiImplicitParam(paramType = "path", name = "uid", value = "用户id",dataType = "ingteger" ,required = true)
	@RequestMapping(value = "list/{uid}", method = RequestMethod.GET)
	public RespMsg<List<DeliveryAddress>> getDeliveryAddressByUid(
			@PathVariable(value = "uid", required = true) Integer uid) {
		logger.debug("uid:{}",uid);
		if(uid == null){
			throw new NotFoundException("参数不能为空");
		}
		List<DeliveryAddress> list = deliveryAddressService.getDeliveryAddressByUid(uid);
		return new RespMsg<List<DeliveryAddress>>(list);
	}
	
	@ApiOperation(value = "删除用户收货地列表", response = List.class) 	
	@ApiImplicitParam(paramType = "path", name = "id", value = "唯一主键id",dataType = "ingteger" ,required = true)
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String deleteDeliveryAddress(
			@PathVariable(value = "id", required = true) Integer id) {
		logger.debug("id:{}",id);
		if(id == null){
			throw new NotFoundException("参数不能为空");
		}
		deliveryAddressService.deleteDeliveryAddress(id);
		return JSONObject.toJSONString(Status.success);
	}
	
	@ApiOperation(value = "编辑收货地址是否默认",notes = "参数说明：<br />"
			+ "id：唯一主键id（dateType:Integer）<br/>"
			)
	@ApiImplicitParams({@ApiImplicitParam(paramType = "path", name = "id", value = "唯一主键", defaultValue = "1", required = true),
			@ApiImplicitParam(paramType = "query", name = "isDefault", value = "是否为默认设置（0-不是，1-是）", dataType = "Integer", defaultValue = "0", required = true)})
	@RequestMapping(value = "isDefault/{id}", method = RequestMethod.PUT)
	public String updateIsDefautl(
			@PathVariable(value = "id", required = true) Integer id,
			@RequestParam(value = "isDefault", required = true) Integer isDefault){
		logger.debug("id:{},isDefautl:{}", id,isDefault);
		deliveryAddressService.updateIsDefault(id,isDefault);
		return JSONObject.toJSONString(Status.success);
	}
	
	@ApiOperation(value = "根据id获取收货地址", response = List.class) 	
	@ApiImplicitParam(paramType = "path", name = "id", value = "id",dataType = "ingteger" ,required = true)
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public RespMsg<DeliveryAddress> getDeliveryAddressById(
			@PathVariable(value = "id", required = true) Integer id) {
		logger.debug("id:{}",id);
		if(id == null){
			throw new NotFoundException("参数不能为空");
		}
		DeliveryAddress deliveryAddress  = deliveryAddressService.getDeliveryAddressById(id);
		return new RespMsg<DeliveryAddress>(deliveryAddress);
	}
}
