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
import com.heipiao.api.v2.exception.BadRequestException;
import com.heipiao.api.v2.service.ActivityArticleService;
import com.heipiao.api.v2.service.CampaignService;
import com.heipiao.api.v2.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
	
	@Resource
	private UserService userService;

	@ApiOperation(value = "获取活动信息", response = Campaign.class)
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Campaign getCampaign(
			@ApiParam(value = "活动id", required = true) @PathVariable("id") Integer id) {
		logger.debug("id={}", id);

		Campaign campaign = campaignService.getCampaign(id);
		return campaign;
	}

	// 跟这里类似的返回类型可以思考一下，传Map感觉不合适，但除了pojo，还有其他选择吗？
	@ApiOperation(value = "获取活动参与人信息", response = Map.class)
	@RequestMapping(value = "actor/{cid}/{uid}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> getCampaignActor(
			@ApiParam(value = "用户id", required = true) @PathVariable("cid") Integer cid,
			@ApiParam(value = "钓场id", required = true) @PathVariable("uid") Integer uid) {
		logger.debug("cid={}, uid={}", cid, uid);

		CampaignActor ca = campaignService.getCampaignActor(cid, uid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isJoin", ca != null && ca.getPayStatus() > 0 && ca.getPayStatus() < 3 ? true : false);
		map.put("isRefund", ca != null && ca.getRefundStatus() == 0 ? false : true);
		map.put("number", ca != null && ca.getLuckyNumber() != null ? ca.getLuckyNumber() : 0);
		return map;
	}

	@ApiOperation(value = "获取活动参与人（报名）列表", response = List.class) // FIXME
																	// 这里返回List，而不是List<CampaignActor>，不确定Swagger会怎么处理
	@RequestMapping(value = "actor/list/{id}/{top}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<CampaignActor> getCampaignActorList(
			@ApiParam(value = "活动id", required = true) @PathVariable("id") Integer id,
			@ApiParam(value = "取前几位，用于小程序活动首页显示，传-1则不限制", required = true) @PathVariable("top") Integer top) { // FIXME 这里可以改成不传则取全部
		logger.debug("id={}, top={}", id, top);

		List<CampaignActor> campaignActorList = campaignService.getCampaignActorList(id, top);
		return campaignActorList;
	}

	@ApiOperation(value = "获取活动列表", response = List.class)
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Campaign> getCampaignList(
			@ApiParam(value = "起始值，首页从1开始", required = true) @RequestParam("start") Integer start,
			@ApiParam(value = "返回的记录数", required = true) @RequestParam("size") Integer size) {
		logger.debug("start={}, size={}", start, size);

		if (start == null || size == null) {
			throw new BadRequestException("缺少必要参数：start/size");
		}
		
		start = start - 1 <= 0 ? 0 : (start - 1) * size;
		List<Campaign> campaignList = campaignService.getCampaignList(start, size);
		return campaignList;
	}

	@ApiOperation(value = "报名--小程序端")
	@RequestMapping(value = "actor/miniPay", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	// FIXME 思考这里的返回参数，不应当是String的
	public String miniEnter(
			@ApiParam(value = "用户id", required = true) @PathVariable("uid") Long uid,
			@ApiParam(value = "活动id", required = true) @PathVariable("cid") Integer cid,
			@ApiParam(value = "OpenId", required = true) @PathVariable("openid") String openId) {
		logger.debug("uid={}, cid={}, openid={}", uid, cid, openId);

		String result = campaignService.enter(uid, cid, openId, 1);
		return result;
	}

	@ApiOperation(value = "报名确认--小程序端")
	@RequestMapping(value = "actor/miniConfirm", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	// FIXME 思考这里的返回参数，不应当是String的
	public void miniConfirm(
			@ApiParam(value = "用户id", required = true) @PathVariable("uid") Integer uid,
			@ApiParam(value = "活动id", required = true) @PathVariable("cid") Integer cid) {
		logger.debug("uid={}, cid={}", uid, cid);

		campaignService.payActivityConfirm(uid, cid);
	}

	// FIXME 修改url
	@ApiOperation(value = "回顾详情列表")
	@RequestMapping(value = "article/list/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<ActivityArticle> getListById(@ApiParam(value = "活动id", required = true) @PathVariable("id") Integer id) {
		logger.debug("id={}", id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cid", id);
		List<ActivityArticle> list = activityArticleService.getListsByCid(map);
		return list;
	}

	// FIXME 修改url
	@ApiOperation(value = "获取单个回顾详情", response = ActivityArticle.class)
	@RequestMapping(value = "article/id/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ActivityArticle getListById(@ApiParam("文章id") @PathVariable("id") Long id) {
		logger.debug("id={}", id);
		
		ActivityArticle result = activityArticleService.getById(id);
		return result;
	}
	
	@ApiOperation(value = "微信活动报名(线下)", notes = "参数说明：<br />"
			+ "cid，活动id<br />"
			+ "uid，用户id")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "path", name = "cid", value = "活动id", dataType = "int", required = true)
		, @ApiImplicitParam(paramType = "path", name = "uid", value = "用户id", dataType = "long", required = true)
	})
	@RequestMapping(value = "checkin/{cid}/{uid}", method = RequestMethod.POST)
	public void checkin(
			@PathVariable(name = "cid", required = true) int cid
			, @PathVariable(name = "uid", required = true) long uid) {
		logger.info("cid:{}, uid:{}", cid, uid);
		
		campaignService.enter(uid, cid, null, 0);
	}

}
