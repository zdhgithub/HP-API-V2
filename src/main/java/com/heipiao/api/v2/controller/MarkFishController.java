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
import com.heipiao.api.v2.domain.HaveFishDefault;
import com.heipiao.api.v2.domain.MarkCode;
import com.heipiao.api.v2.domain.MarkFish;
import com.heipiao.api.v2.domain.MarkHaveFish333;
import com.heipiao.api.v2.exception.BadRequestException;
import com.heipiao.api.v2.exception.NotFoundException;
import com.heipiao.api.v2.service.MarkFishService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "标鱼模块")
@RestController
@RequestMapping(value = "markfish", produces = MediaType.APPLICATION_JSON_VALUE)
public class MarkFishController {
	
	@Resource
	private MarkFishService markFishService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(MarkFishController.class);
	
	
	@ApiOperation(value = "（OCC和小程序公用）钓场标鱼列表", response = MarkFish.class)
	@ApiImplicitParams({@ApiImplicitParam(paramType = "path", name = "siteUid", value = "钓场主id",dataType = "int",required = true),
		@ApiImplicitParam(paramType = "query", name = "status", value = "状态（0-正常，1-下架）",dataType = "int", required = false)})
	
	@RequestMapping(value = "sitemark/{siteUid}", method = RequestMethod.GET)
	public RespMsg<List<MarkFish>> getSiteMarkFishDetail(
			@PathVariable(value = "siteUid", required = true) Integer siteUid,
			@RequestParam(value = "status", required = false) Integer status) {
		logger.debug("siteUid:{},status:{}",siteUid,status);
		if(siteUid == null){
			throw new NotFoundException("参数不能为空");
		}
		List<MarkFish> list = markFishService.getMarkFishList(siteUid,status);
		return new RespMsg<List<MarkFish>>(list);
	}
	
	@ApiOperation(value = "我的标鱼列表", response = MarkHaveFish333.class) 	
	@ApiImplicitParams({@ApiImplicitParam(paramType = "path", name = "uid", value = "用户id",dataType = "int",required = true),
		@ApiImplicitParam(paramType = "query", name = "start", value = "查询页码，首页传1",dataType = "int", required = true)})
	@RequestMapping(value = "markhavefish/{uid}", method = RequestMethod.GET)
	public RespMsg<List<MarkHaveFish333>> getMarkHaveFishOccDetail(
			@PathVariable(value = "uid", required = true) Integer uid,
			@RequestParam(value = "start", required = true) Integer start) {
		logger.debug("uid:{},start:{}",uid,start);
		if(uid == null || start==null ){
			throw new NotFoundException("参数不能为空");
		}
		start = start - 1 <= 0 ? 0 : (start - 1); 
		List<MarkHaveFish333> list = markFishService.getMarkHaveFishList(uid,start);
		return new RespMsg<List<MarkHaveFish333>>(list);
	}
	
	@ApiOperation(value = "（OCC）获取鱼标编码", response = HaveFishDefault.class) 	
	
	@ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "status", value = "鱼标状态（0-未使用，1-已使用，2-下架，3-全部）",dataType = "int",required = false),
		@ApiImplicitParam(paramType = "query", name = "uid", value = "当前用户id",dataType = "int", required = false)})
	@RequestMapping(value = "markcode", method = RequestMethod.GET)
	public RespMsg<List<MarkCode>> getMarkCodeList(
			@PathVariable(value = "status", required = false) Integer status,
			@PathVariable(value = "uid", required = false) Integer uid) {
		logger.debug("status:{},uid:{}",status,uid);
		List<MarkCode> list = markFishService.getMarkCode(status);
		return new RespMsg<List<MarkCode>>(list);
	}
	
	@ApiOperation(value = "OCC添加钓场鱼标", notes = "参数说明：<br/>"
			+ "siteUid：钓场主id<br/>"
			+ "markNum：鱼标编码<br/>"
			+ "markContent: 奖品内容<br/>"
			+ "condition: 奖励条件<br/>"
			+ "deliveryType：送货方式<br/>"
			)
	@RequestMapping(value = "sitemark",method = RequestMethod.POST)
	public String addSiteMarkFish(@RequestBody MarkFish markFish) {
		
		logger.debug("markFish:{}", markFish);
		
		if (markFish.getSiteUid() == null 
				|| markFish.getMarkNum() == null
				|| markFish.getCondition() == null
				|| markFish.getDeliveryType() == null
				|| markFish.getMarkContent() == null){
			throw new BadRequestException("必要参数不能为空");
		}
		markFishService.addSiteMarkFish(markFish);
		return JSONObject.toJSONString(Status.success);
	}	
	
	@ApiOperation(value = "OCC修改钓场鱼标", notes = "参数说明：<br/>"
			+ "id：主键id<br/>"
			+ "siteUid：钓场主id<br/>"
			+ "markNum：鱼标编码<br/>"
			+ "markContent: 奖品内容<br/>"
			+ "condition: 奖励条件<br/>"
			+ "deliveryType：送货方式<br/>"
			)
	@RequestMapping(value = "sitemark",method = RequestMethod.PUT)
	public String updateSiteMarkFish(@RequestBody MarkFish markFish) {
		
		logger.debug("markFish:{}", markFish);
		
		if (markFish.getSiteUid() == null 
				|| markFish.getMarkNum() == null
				|| markFish.getCondition() == null
				|| markFish.getDeliveryType() == null
				|| markFish.getMarkContent() == null){
			throw new BadRequestException("必要参数不能为空");
		}
		markFishService.updateSiteMarkFish(markFish);
		return JSONObject.toJSONString(Status.success);
	}	
	
	@ApiOperation(value = "OCC删除钓场标鱼", response = MarkFish.class) 	
	@ApiImplicitParam(paramType = "path", name = "id", value = "主键id",dataType = "int",required = true)
	@RequestMapping(value = "sitemark/{id}", method = RequestMethod.DELETE)
	public String deleteSiteMarkFishDetail(
			@PathVariable(value = "id", required = true) Integer id) {
		logger.debug("id:{}",id);
		if(id == null){
			throw new NotFoundException("参数不能为空");
		}
		markFishService.deleteMarkFishList(id);
		return JSONObject.toJSONString(Status.success);
	}
	
	@ApiOperation(value = "OCC鱼标对应的标鱼列表", response = MarkHaveFish333.class) 	
	@ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "marknum", value = "鱼标编号",dataType = "string", required = true),
		@ApiImplicitParam(paramType = "query", name = "start", value = "页码（首页传1）",dataType = "int", required = true)})
	@RequestMapping(value = "markhavefish/marknum", method = RequestMethod.GET)
	public RespMsg<List<MarkHaveFish333>> getMarkHaveFishOfmarkNum(
			@RequestParam(value = "marknum", required = true) String marknum,
			@RequestParam(value = "start",required = true) Integer start) {
		logger.debug("marknum:{},start:{}",marknum,start);
		if(marknum == null || start == null ){
			throw new NotFoundException("参数不能为空");
		}
		start = start - 1;
		List<MarkHaveFish333> list = markFishService.getMarkHaveFishOfmarkNumList(marknum,start);
		return new RespMsg<List<MarkHaveFish333>>(list);
	}
	
	@ApiOperation(value = "OCC钓场鱼标下架")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "path", name = "uid", value = "钓场主id",dataType = "int", required = true),
		@ApiImplicitParam(paramType = "query", name = "marknum", value = "鱼标编码",dataType = "string", required = true),
		@ApiImplicitParam(paramType = "query", name = "status", value = "（0-正常，1-下架）",dataType = "int", required = true)})
	@RequestMapping(value = "sitemark/{uid}", method = RequestMethod.PUT)
	public String updateSiteMarkFish(
			@PathVariable(value = "uid", required = true) Integer uid,
			@RequestParam(value = "marknum", required = true) String marknum,
			@RequestParam(value = "status", required = true) Integer status) {
		logger.debug("uid:{},marknum:{},status:{}",uid,marknum,status);
		markFishService.updateMarkFish(uid,marknum,status);
		return JSONObject.toJSONString(Status.success);
	}
	
	@ApiOperation(value = "钓场鱼标申请")
	@ApiImplicitParam(paramType = "path", name = "uid", value = "钓场主id",dataType = "int", required = true)
	@RequestMapping(value = "sitemarkfish/apply/{uid}", method = RequestMethod.PUT)
	public String getOneHaveFish(
			@PathVariable(value = "uid", required = true) Integer uid) {
		logger.debug("uid:{},applyMark:{}",uid);
		markFishService.updateFishSiteBase(uid);
		return JSONObject.toJSONString(Status.success);
	}
	
	@ApiOperation(value = "OCC审核用户标鱼", notes = "参数说明：<br/>"
			+ "auditStatus：审核状态（0-待审核，1-审核通过，2-审核不通过）<br/>"
			+ "auditResult：审核结果<br/>"
			+ "grantResult: 奖品发放结果<br/>"
			+ "id: 主键id<br/>"
			)
	@RequestMapping(value = "markhavefish",method = RequestMethod.PUT)
	public String addSiteMarkFish(@RequestBody MarkHaveFish333 markHaveFish) {
		
		logger.debug("markHaveFish:{}", markHaveFish);
		
		if ( markHaveFish.getAuditStatus() == null
				|| markHaveFish.getId() == null){
			throw new BadRequestException("必要参数不能为空");
		}
		markFishService.updatemarkHaveFish(markHaveFish);
		return JSONObject.toJSONString(Status.success);
	}	
}
