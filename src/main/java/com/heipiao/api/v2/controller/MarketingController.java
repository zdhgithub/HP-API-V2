package com.heipiao.api.v2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heipiao.api.v2.domain.LikeUser;
import com.heipiao.api.v2.domain.Marketing;
import com.heipiao.api.v2.domain.MarketingPicture;
import com.heipiao.api.v2.service.MarketingService;
import com.heipiao.api.v2.util.ExDateUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
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

	@ApiOperation(value = "获取发布图片的列表", response = MarketingPicture.class)
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

	@ApiOperation(value = "获取发布图片内容", response = MarketingPicture.class)
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
		
		List<Marketing> data = marketingService.getList(start - 1 <= 0 ? 0 : (start - 1) * size , size);
		return data;
	}

}
