package com.heipiao.api.v2.controller;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

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

import com.heipiao.api.v2.domain.Marketing;
import com.heipiao.api.v2.domain.PageInfo;
import com.heipiao.api.v2.domain.Thumbs;
import com.heipiao.api.v2.domain.ThumbsResult;
import com.heipiao.api.v2.exception.NotFoundException;
import com.heipiao.api.v2.service.MarketingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 营销活动模块
 * 
 * @author 作者 :Chris
 */

@Api(tags = "营销活动模块")
@RestController
@RequestMapping(value = "marketing", produces = MediaType.APPLICATION_JSON_VALUE)
public class MarketingController {

	@Resource
	private MarketingService marketingService;
	
	private static final Logger logger = LoggerFactory.getLogger(MarketingController.class);
	
	// TODO 添加营销活动
	
	// TODO 更新营销活动
	
	@ApiOperation(value = "营销活动列表", response = Marketing.class, notes = "参数说明：<br />"
			+ "状态，0未发布，1已发布，2已结束，3删除，传空则取全部<br />"
			+ "起始页，首页为1<br />"
			+ "起始页或页大小为空则取全部数据")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "status", value = "营销活动状态", dataType = "int", required = false, example = "1")
		, @ApiImplicitParam(paramType = "query", name = "start", value = "起始页", dataType = "int", required = false, example = "1", defaultValue = "1")
		, @ApiImplicitParam(paramType = "query", name = "size", value = "页大小", dataType = "int", required = false, example = "10")
	})
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public PageInfo<List<Marketing>> getMarketingList(
			@RequestParam(value = "status", required = false) Integer status
			, @RequestParam(value = "start", required = false) Integer start
			, @RequestParam(value = "size", required = false) Integer size){
		logger.debug("status:{}, start:{}, size:{}", status, start, size);
		
		if (start != null && size != null) {
			start = start - 1 <= 0 ? 0 : (start - 1) * size;
		}
		PageInfo<List<Marketing>> result = marketingService.getMarketingList(status, start, size);
		return result;
	}

	@ApiOperation(value = "获取营销活动详情", response = Marketing.class)
	@ApiImplicitParam(paramType = "path", name = "id", value = "营销活动id", dataType = "int", required = true, example = "1")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Marketing getMarketing(
			@PathVariable(value = "id", required = true) int id) {
		logger.debug("id:{}", id);

		Marketing marketing = marketingService.getMarketing(id);
		return marketing;
	}

	@ApiOperation(value = "获取点赞活动发布图片的列表", response = Thumbs.class, notes = "参数说明：<br />"
			+ "起始页，首页为1<br />")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "path", name = "mid", value = "点赞活动id", dataType = "int", required = true)
		, @ApiImplicitParam(paramType = "query", name = "uid", value = "用户id", dataType = "long", required = true)
		, @ApiImplicitParam(paramType = "query", name = "start", value = "起始页", dataType = "int", required = true, example = "1", defaultValue = "1")
		, @ApiImplicitParam(paramType = "query", name = "size", value = "页大小", dataType = "int", required = true, example = "10")
	})
	@RequestMapping(value = "thumbs/{mid}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<ThumbsResult> getThumbsListById(
			@PathVariable(value = "mid", required = true) int mid
			, @RequestParam(value="uid", required = true) long uid
			, @RequestParam(value = "start", required = true) int start
			, @RequestParam(value = "size", required = true) int size) {
		logger.debug("mid:{}, uid:{}, start:{}, size:{}", mid, uid, start, size);

		List<ThumbsResult> result = marketingService.getThumbsList(mid, uid, start, size);
		return result;
	}
	
	// FIXME
	// 添加排序字段参数，按发布时间或点赞数
	@ApiOperation(value = "获取所有点赞活动发布图片的列表（供OCC用）", response = ThumbsResult.class, notes = "参数说明：<br />"
			+ "审核状态：0待审核，1通过，2未通过，为空则不参与过滤<br />"
			+ "起始页，首页为1<br />"
			+ "起始日期，日期格式（yyyy-MM-dd）<br />"
			+ "结束日期，日期格式（yyyy-MM-dd）<br />"
			+ "起始日期和结束日期，返回的结果集包含起始日期和结束日期")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "path", name = "mid", value = "营销活动id", dataType = "int", required = true)
		, @ApiImplicitParam(paramType = "query", name = "status", value = "审核状态", dataType = "int", required = false)
		, @ApiImplicitParam(paramType = "query", name = "start", value = "起始页", dataType = "int", required = true, example = "1", defaultValue = "1")
		, @ApiImplicitParam(paramType = "query", name = "size", value = "页大小", dataType = "int", required = true, example = "10")
		, @ApiImplicitParam(paramType = "query", name = "begin", value = "起始日期", dataType = "date", required = false)
		, @ApiImplicitParam(paramType = "query", name = "end", value = "结束日期", dataType = "date", required = false)
	})
	@RequestMapping(value = "thumbspage/{mid}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public PageInfo<List<ThumbsResult>> getThumbsListWithPage(
			@PathVariable(value = "mid", required = true) int mid
			, @RequestParam(value = "status", required = false) Integer status
			, @RequestParam(value = "start", required = true) int start
			, @RequestParam(value = "size", required = true) int size
			, @RequestParam(value = "begin", required = false) Date begin
			, @RequestParam(value = "end", required = false) Date end) {
		logger.debug("mid:{}, status:{}, start:{}, size:{}, begin:{}, end:{}", mid, status, start, size, begin, end);

		start = start - 1 <= 0 ? 0 : (start - 1) * size;
		PageInfo<List<ThumbsResult>> pageInfo = marketingService.getThumbsWithPage(mid, status, start, size, begin, end);
		return pageInfo;
	}

	@ApiOperation(value = "发布点赞图片内容", notes = "参数说明 :<br />"
			+ "mid：营销活动id<br />"
			+ "uid：发布用户id"
			+ "picture：图片，多张图片用英文逗号分隔，必须是3张图片"
			+ "pictureDesc：图片描述")
	@RequestMapping(value = "thumbs", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void addThumbs(@RequestBody Thumbs thumbs) {
		logger.debug("thumbs:{}", thumbs);

		marketingService.addThumbs(thumbs);
	}

	@ApiOperation(value = "修改发布的图片内容",notes = "参数说明 :"
			+ "picture：图片<br />")
	@RequestMapping(value = "thumbs/{mid}/{uid}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void updateThumbs(
			@PathVariable(value = "mid", required = true) int mid
			, @RequestParam(value="uid", required = true) long uid
			, @RequestBody Thumbs thumbs) {
		logger.debug("mid:{}, uid:{}, thumbs:{}", mid, uid, thumbs);
		
		marketingService.updateThumbs(mid, uid, thumbs);
	}

	@ApiOperation(value = "获取指定用户发布点赞图片内容", response = Thumbs.class)
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "path", name = "mid", value = "点赞活动id", dataType = "int", required = true)
		, @ApiImplicitParam(paramType = "path", name = "uid", value = "用户id", dataType = "long", required = true)
	})
	@RequestMapping(value = "thumbs/{mid}/{uid}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ThumbsResult getThumbs(
			@PathVariable(value = "mid", required = true) int mid,
			@PathVariable(value = "uid", required = true) long uid) {
		logger.debug("marketingId:{}, uid:{}", mid, uid);
		
		return marketingService.getThumbs(mid, uid);
	}

	@ApiOperation(value = "用户点赞", notes = "参数说明："
			+ "marketingId：点赞营销活动id"
			+ "uid：发布点赞用户id"
			+ "likeUid：点赞用户id")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "mid", value = "点赞活动id", dataType = "int", required = true)
		, @ApiImplicitParam(paramType = "query", name = "uid", value = "发布用户用户id", dataType = "long", required = true)
		, @ApiImplicitParam(paramType = "query", name = "likeUid", value = "点赞用户id", dataType = "long", required = true)
	})
	@RequestMapping(value = "thumbs/like", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void like(
			@RequestParam(value = "mid", required = true) int mid
			, @RequestParam(value = "uid", required = true) long uid
			, @RequestParam(value = "likeUid", required = true) long likeUid) {
		logger.debug("mid:{}, uid:{}, likeUid:{}", mid, uid, likeUid);

		marketingService.like(mid, uid, likeUid);
	}
	
	@ApiOperation(value = "获取点赞列表", notes = "参数说明："
			+ "marketingId：点赞营销活动id"
			+ "uid：发布点赞用户id"
			+ "响应内容：以英文逗号分隔各昵称")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "mid", value = "点赞活动id", dataType = "int", required = true)
		, @ApiImplicitParam(paramType = "query", name = "uid", value = "发布用户用户id", dataType = "long", required = true)
	})
	@RequestMapping(value = "thumbs/like/", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String getAllLike(
			@RequestParam(value = "mid", required = true) int mid
			, @RequestParam(value = "uid", required = true) long uid) {
		logger.debug("mid:{}, uid:{}", mid, uid);
		
		return marketingService.getAllLike(mid, uid);
	}

	@ApiOperation(value = "用户是否点赞", notes = "True：表示已点赞，False：表示未点赞")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "mid", value = "点赞活动id", dataType = "int", required = true)
		, @ApiImplicitParam(paramType = "query", name = "uid", value = "发布用户用户id", dataType = "long", required = true)
		, @ApiImplicitParam(paramType = "query", name = "likeUid", value = "点赞用户id", dataType = "long", required = true)
	})
	@RequestMapping(value = "thumbs/like/status", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Boolean isLike(
			@RequestParam(value = "mid", required = true) int mid
			, @RequestParam(value = "uid", required = true) long uid
			, @RequestParam(value = "likeUid", required = true) long likeUid) {
		logger.debug("mid:{}, uid:{}, likeUid:{}", mid, uid, likeUid);

		return marketingService.isLike(mid, uid, likeUid);
	}

	@ApiOperation(value = "用户是否参加活动", notes = "True：表示已参加， False：表示未参加")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "mid", value = "点赞活动id", dataType = "int", required = true)
		, @ApiImplicitParam(paramType = "query", name = "uid", value = "发布用户用户id", dataType = "long", required = true)
	})
	@RequestMapping(value = "thumbs/status", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Boolean isJoin(
			@RequestParam(value = "mid", required = true) int mid
			, @RequestParam(value = "uid", required = true) long uid) {
		logger.debug("mid:{}, uid:{}", mid, uid);
		
		return marketingService.isJoin(mid, uid);
	}
	
	@ApiOperation(value = "审核", notes = "参数说明：<br />"
			+ "审核状态，必填，0待审核，1通过，2未通过<br />"
			+ "拒绝原因：当审核状态为2时填写")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "path", name = "mid", value = "点赞活动id", dataType = "int", required = true)
		, @ApiImplicitParam(paramType = "path", name = "uid", value = "用户id", dataType = "int", required = true)
		, @ApiImplicitParam(paramType = "path", name = "status", value = "审核状态", dataType = "int", required = true)
		, @ApiImplicitParam(paramType = "query", name = "reason", value = "拒绝原因", dataType = "String", required = false)
	})
	@RequestMapping(value = "thumbs/{mid}/{uid}/{status}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void audit(
			@PathVariable(value = "mid", required = true) int mid
			, @PathVariable(value = "uid", required = true) int uid
			, @PathVariable(value = "status", required = true) int status
			, @RequestParam(value = "reason", required = false) String reason) {
		logger.debug("mid:{}, uid:{}, status:{}, reason:{}", mid, uid, status, reason);
		
		Integer result = marketingService.audit(mid, uid, status, reason);
		if (result == null || result.intValue() == 0)
			throw new NotFoundException("指定的资源不存在或不是未审核状态");
	}

}
