package com.heipiao.api.v2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.heipiao.api.v2.domain.ActivityArticle;
import com.heipiao.api.v2.domain.Campaign;
import com.heipiao.api.v2.domain.CampaignActor;
import com.heipiao.api.v2.service.ActivityArticleService;
import com.heipiao.api.v2.service.CampaignService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 有关活动的功能：<br>
 * 创建活动、报名、抽签、活动列表、活动管理等
 * 
 * @author Chris
 * @version 3.0
 * @date 2017-03-03
 */
@Api(tags = "竞技活动模块")
@RestController
@RequestMapping(value = "campaign", produces = MediaType.APPLICATION_JSON_VALUE)
public class CampaignController {
	public static final Logger logger = LoggerFactory.getLogger(CampaignController.class);

	@Resource
	private CampaignService campaignService;

	@Resource
	private ActivityArticleService activityArticleService;

	@ApiOperation(value = "获取活动信息", response = Campaign.class)
	@ApiImplicitParam(paramType = "path", name = "id", value = "营销活动id", dataType = "int", required = true, example = "1")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Campaign getCampaign(
			@PathVariable(value = "id", required = true) int id) {
		logger.debug("id={}", id);
		
		Campaign campaign = campaignService.getCampaign(id);
		return campaign;
	}

	// 跟这里类似的返回类型可以思考一下，传Map感觉不合适，但除了pojo，还有其他选择吗？
	@ApiOperation(value = "获取活动参与人信息", response = Map.class)
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "path", name = "cid", value = "活动id", dataType = "int", required = true)
		, @ApiImplicitParam(paramType = "path", name = "uid", value = "用户id", dataType = "int", required = true)
	})
	@RequestMapping(value = "actor/{cid}/{uid}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> getCampaignActor(
			@PathVariable(value = "cid", required = true) int cid
			, @PathVariable(value="uid", required = true) int uid
			) {
		logger.debug("cid={}, uid={}", cid, uid);

		CampaignActor ca = campaignService.getCampaignActor(cid, uid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isJoin", ca != null && ca.getPayStatus() > 0 && ca.getPayStatus() < 3 ? true : false);
		map.put("isRefund", ca != null && ca.getRefundStatus() == 0 ? false : true);
		map.put("number", ca != null && ca.getLuckyNumber() != null ? ca.getLuckyNumber() : 0);
		return map;
	}

	@ApiOperation(value = "获取活动参与人（报名）列表", response = List.class) // FIXME
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "path", name = "id", value = "活动id", dataType = "int", required = true)
		, @ApiImplicitParam(paramType = "path", name = "top", value = "取前几位，用于小程序活动首页显示，传-1则不限制", dataType = "int", required = true)
	})																
	@RequestMapping(value = "actor/list/{id}/{top}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<CampaignActor> getCampaignActorList(
			@PathVariable(value = "id", required = true) int id
			, @PathVariable(value="top", required = true) int top) { // FIXME 这里可以改成不传则取全部
		logger.debug("id={}, top={}", id, top);

		List<CampaignActor> campaignActorList = campaignService.getCampaignActorList(id, top);
		return campaignActorList;
	}

	@ApiOperation(value = "获取活动列表", response = List.class)
	@ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "start", value = "起始页", dataType = "int", required = true, example = "1", defaultValue = "1")
		, @ApiImplicitParam(paramType = "query", name = "size", value = "页大小", dataType = "int", required = true, example = "10")
	})
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Campaign> getCampaignList(
			@RequestParam(value = "start", required = true) int start
			, @RequestParam(value = "size", required = true) int size
			) {
		logger.debug("start={}, size={}", start, size);

		start = start - 1 <= 0 ? 0 : (start - 1) * size;
		List<Campaign> campaignList = campaignService.getCampaignList(start, size);
		return campaignList;
	}

	@ApiOperation(value = "报名--小程序端")
	@ApiImplicitParams({
		 @ApiImplicitParam(paramType = "query", name = "uid", value = "用户id", dataType = "long", required = true), 
		 @ApiImplicitParam(paramType = "query", name = "cid", value = "活动id", dataType = "int", required = true),
		 @ApiImplicitParam(paramType = "query", name = "openid", value = "Openid", dataType = "String", required = true)
		})
	@RequestMapping(value = "actor/miniPay", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	// FIXME 思考这里的返回参数，不应当是String的
	public String miniEnter(
			@RequestParam(value = "uid", required = true) long uid,
			@RequestParam(value = "cid", required = true) Integer cid,
			@RequestParam(value = "openid", required = true) String openId) {
		logger.debug("uid={}, cid={}, openid={}", uid, cid, openId);

		String result = campaignService.enter(uid, cid, openId, 1);
		return result;
	}

	@ApiOperation(value = "报名确认--小程序端")
	@ApiImplicitParams({
		 @ApiImplicitParam(paramType = "query", name = "uid", value = "用户id", dataType = "int", required = true), 
		 @ApiImplicitParam(paramType = "query", name = "cid", value = "活动id", dataType = "int", required = true)
		})
	@RequestMapping(value = "actor/miniConfirm", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	// FIXME 思考这里的返回参数，不应当是String的
	public void miniConfirm(
			@RequestParam(value = "uid", required = true) Long uid,
			@RequestParam(value = "cid", required = true) Integer cid) {
		logger.debug("uid={}, cid={}", uid, cid);

		campaignService.payActivityConfirm(uid, cid);
	}


	// FIXME 修改url
	@ApiOperation(value = "回顾详情列表")
	@ApiImplicitParam(paramType = "path", name = "id", value = "活动id", dataType = "int", required = true, example = "1")
	@RequestMapping(value = "article/list/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<ActivityArticle> getListById(@PathVariable(value = "id", required = true) Integer id) {
		logger.debug("id={}", id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cid", id);
		List<ActivityArticle> list = activityArticleService.getListsByCid(map);
		return list;
	}

	// FIXME 修改url
	@ApiOperation(value = "获取单个回顾详情", response = ActivityArticle.class)
	@ApiImplicitParam(paramType = "path", name = "id", value = "文章id", dataType = "long", required = true, example = "1")
	@RequestMapping(value = "article/id/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ActivityArticle getListById(@PathVariable(value = "id", required = true) Long id) {
		logger.debug("id={}", id);
		
		ActivityArticle result = activityArticleService.getById(id);
		return result;
	}

}
