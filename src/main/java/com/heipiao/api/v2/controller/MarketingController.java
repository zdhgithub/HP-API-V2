package com.heipiao.api.v2.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.heipiao.api.v2.domain.LikeUser;
import com.heipiao.api.v2.domain.Marketing;
import com.heipiao.api.v2.domain.MarketingPicture;
import com.heipiao.api.v2.domain.PageInfo;
import com.heipiao.api.v2.domain.Thumbs;
import com.heipiao.api.v2.exception.NotFoundException;
import com.heipiao.api.v2.service.MarketingService;
import com.heipiao.api.v2.util.ExDateUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 营销活动模块
 * 
 * TODO：
 * 营销活动会有多个，当前做法是获取全部（listpage/{start}/{size}），再默认取第1个（获取发布图片的列表）
 * 改进为：
 * 1、拉取营销活动类型
 * 2、拉取指定的营销活动
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

	@ApiOperation(value = "获取营销活动详情", response = Marketing.class)
	@RequestMapping(value = "marketing/{id}", method = RequestMethod.GET)
	public Marketing getOneMarketing(
			@ApiParam(value = "营销活动id", required = true) @PathVariable("id") Integer id) {
		logger.debug("id:{}", id);

		Marketing marketing = marketingService.getOneMarketing(id);
		return marketing;
	}

	@ApiOperation(value = "获取点赞活动发布图片的列表", response = MarketingPicture.class)
	@RequestMapping(value = "list/{marketingId}/{uid}", method = RequestMethod.GET)
	public List<MarketingPicture> getPicturesList(
			@ApiParam(value = "营销活动marketingId", required = true) @PathVariable("marketingId") Integer marketingId,
			@ApiParam(value="用户id", required=true) @PathVariable("uid") Integer uid) {
		logger.debug("marketingId:{}, uid:{}", marketingId, uid);

		Map<String, Object> map = new HashMap<>();
		map.put("marketingId", marketingId);
		map.put("uid", uid);
		map.put("status", 1);
		List<MarketingPicture> list = marketingService.getPictureList(map);
		return list;
	}

	@ApiOperation(value = "发布图片内容", notes = "参数说明 :(Y) 必须字段，(N) 可选字段"
			+ "\n\r发布营销活动：\n\rmarketingId(Y):营销活动id ,\n\ruid(Y) :发布用户id,\n\rpicture(Y):图片,\n\rpictureDesc(Y):图片描述")
	@RequestMapping(value = "pictures", method = RequestMethod.POST)
	public void addPictures(@RequestBody MarketingPicture mp) {
		logger.debug("json:{}", mp);

		marketingService.addPictures(mp);
	}
	
	@ApiOperation(value="修改发布的图片内容",notes="参数说明 :(Y) 必须字段，(N) 可选字段"
			+ "\n\r修改内容：\n\rmarketingId(Y):营销活动id ,\n\ruid(Y) :发布用户id,\n\rpicture(Y):图片,\n\rpictureDesc(Y):图片描述")
	@RequestMapping(value="pictures",method=RequestMethod.PUT)
	public void updatePictures(@RequestBody MarketingPicture mp) {
		logger.debug("json:{}", mp);
		
		Map<String ,Object> map = new HashMap<>();
		map.put("uid", mp.getUid());
		map.put("marketingId", mp.getMarketingId());
		map.put("picture", mp.getPicture());
		map.put("pictureDesc", mp.getPictureDesc());
		map.put("uploadTime", ExDateUtils.getDate());
		map.put("status", "0");
		
		marketingService.updatePictures(map);
	}

	@ApiOperation(value = "获取指定用户发布点赞图片内容", response = MarketingPicture.class)
	@RequestMapping(value = "picture/{marketingId}/{uid}", method = RequestMethod.GET)
	public MarketingPicture getPicturesList(
			@ApiParam(value = "营销活动id", required = true) @PathVariable("marketingId") Integer marketingId,
			@ApiParam(value = "上传用户id", required = true) @PathVariable("uid") Long uid) {
		logger.debug("marketingId:{}, uid:{}", marketingId, uid);

//		Marketing marketing = marketingService.getOneMarketing(marketingId);
//		if (marketing == null || marketing.getStatus() == 2) {
//			return new BadRequestException("活动已结束");
//		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("marketingId", marketingId);
		map.put("uid", uid);
		MarketingPicture mp = marketingService.getOneMaretingPicture(map);
		return mp;
	}

	@ApiOperation(value = "用户点赞", notes = "参数说明 :(Y) 必须字段，(N) 可选字段"
			+ "\n\r点赞用户：\n\rlikeUid(Y):点赞用户id ,\n\rmarketUid(Y) :发布用户id,\n\rmarketingId(Y):营销活动id")
	@RequestMapping(value = "likeUser", method = RequestMethod.POST)
	public void addLikeUser(@RequestBody LikeUser user) {
		logger.debug("json:{}", user);

		LikeUser likeUser = new LikeUser();
		likeUser.setLikeUid(user.getLikeUid());
		likeUser.setMarketingId(user.getMarketingId());
		likeUser.setMarketUid(user.getMarketUid());
		likeUser.setLikeTime(ExDateUtils.getDate());
		marketingService.addLikeUser(likeUser);
	}

	@ApiOperation(value = "用户是否点赞", notes = "1：表示已点赞，0：表示未点赞")
	@RequestMapping(value = "status/{likeUid}/{marketUid}/{marketingId}", method = RequestMethod.GET)
	public Integer isLikeUser(@ApiParam("用户id") @PathVariable("likeUid") Long likeUid,
			@ApiParam("发布图片用户id") @PathVariable("marketUid") Long marketUid,
			@ApiParam("活动id") @PathVariable("marketingId") Integer marketingId) {
		logger.debug("likeUid:{}, marketUid:{}, marketingId:{}", likeUid, marketUid, marketingId);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("marketingId", marketingId);
		map.put("marketUid", marketUid);
		map.put("likeUid", likeUid);
		LikeUser likeUser = marketingService.getOneLikeUser(map);
		Integer status = likeUser != null ? 1 : 0;
		
		return status;
	}
	
	@ApiOperation(value = "用户是否参加活动", notes = "1：表示已参加， 0：表示未参加")
	@RequestMapping(value = "status/{uid}/{mid}", method = RequestMethod.GET)
	public Integer isJoin(@ApiParam("用户id") @PathVariable("uid") Long uid,
			@ApiParam("活动id") @PathVariable("mid") Integer mid) {
		logger.debug("uid:{}, mid:{}", uid, mid);
		
		Integer result = marketingService.isJoin(uid, mid);
		return result;
	}
	
	@ApiOperation(value = "营销活动列表",response = Marketing.class)
	@RequestMapping(value = "listpage/{start}/{size}",method = RequestMethod.GET)
	public List<Marketing> getList(@ApiParam(value="开始页,首页为1", required=true) @PathVariable("start") Integer start,
			@ApiParam(value="每页大小", required=true) @PathVariable("size") Integer size){
		logger.debug("start:{}, size:{}", start, size);
		
		List<Marketing> data = marketingService.getList(start - 1 <= 0 ? 0 : (start - 1) * size, size);
		return data;
	}
	
	@ApiOperation(value = "获取所有点赞活动发布图片的列表（供OCC用）", response = Thumbs.class, notes = "参数说明：<br />"
			+ "审核状态：0待审核，1通过，2未通过<br />"
			+ "起始页，必填，首页为1<br />"
			+ "起始时间，可选，日期格式（yyyy-MM-dd）<br />"
			+ "结束时间，可选，日期格式（yyyy-MM-dd）<br />"
			+ "起始时间和结束时间，使用“BETWEEN AND”实现，日期格式的时间默认值为0，所以返回的结果集包含起始时间，但并不包含结束时间")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "path", name = "mid", value = "营销活动id", dataType = "int", required = true)
		, @ApiImplicitParam(paramType = "path", name = "status", value = "审核状态", dataType = "int", defaultValue = "0", required = true)
		, @ApiImplicitParam(paramType = "query", name = "start", value = "起始页，首页为1", dataType = "int", defaultValue = "1", required = true)
		, @ApiImplicitParam(paramType = "query", name = "size", value = "页大小", dataType = "int", defaultValue = "10", required = true)
		, @ApiImplicitParam(paramType = "query", name = "begin", value = "起始时间", dataType = "date", required = false)
		, @ApiImplicitParam(paramType = "query", name = "end", value = "结束时间", dataType = "date", required = false)
	})
	@RequestMapping(value = "thumbs/{mid}/{status}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public PageInfo<List<Thumbs>> getThumbsWithPage(
			@PathVariable(value = "mid", required = true) Integer mid
			, @PathVariable(value = "status", required = true) Integer status
			, @RequestParam(value = "start", required = true) Integer start
			, @RequestParam(value = "size", required = true) Integer size
			, @RequestParam(value = "begin", required = false) Date begin
			, @RequestParam(value = "end", required = false) Date end) {
		logger.debug("mid:{}, status:{}, start:{}, size:{}, begin:{}, end:{}", mid, status, start, size, begin, end);

		start = start - 1 <= 0 ? 0 : (start - 1) * size;
		PageInfo<List<Thumbs>> pageInfo = marketingService.getThumbsWithPage(mid, status, start, size, begin, end);
		return pageInfo;
	}
	
	@ApiOperation(value = "审核", notes = "参数说明：<br />"
			+ "营销活动id，必填<br />"
			+ "用户id，必填<br />"
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
			@PathVariable(value = "mid", required = true) Integer mid
			, @PathVariable(value = "uid", required = true) Integer uid
			, @PathVariable(value = "status", required = true) Integer status
			, @RequestParam(value = "reason", required = false) String reason) {
		logger.debug("mid:{}, uid:{}, status:{}, reason:{}", mid, uid, status, reason);
		
		Integer result = marketingService.audit(mid, uid, status, reason);
		if (result == null || result.intValue() == 0)
			throw new NotFoundException("指定的资源不存在或不是未审核状态");
	}

}
