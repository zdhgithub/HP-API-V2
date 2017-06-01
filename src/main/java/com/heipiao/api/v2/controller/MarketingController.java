package com.heipiao.api.v2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heipiao.api.v2.domain.LikeUser;
import com.heipiao.api.v2.domain.Marketing;
import com.heipiao.api.v2.domain.MarketingPicture;
import com.heipiao.api.v2.exception.ServiceException;
import com.heipiao.api.v2.service.MarketingService;
import com.heipiao.api.v2.util.ExDateUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author 作者 :dzh
 * @version 创建时间：2017年4月26日 下午1:40:55 类说明
 */

@Api(tags = "营销活动模块")
@RestController
@RequestMapping(value = "marketing", produces = MediaType.APPLICATION_JSON_VALUE)
public class MarketingController {
	private static final Logger logger = LoggerFactory.getLogger(MarketingController.class);

	@Resource
	private MarketingService marketingService;

	@ApiOperation(value = "获取营销活动详情", response = Marketing.class)
	@RequestMapping(value = "marketing/{id}", method = RequestMethod.GET)
	public ResponseEntity<Marketing> getOneMarketing(
			@ApiParam(value = "营销活动id", required = true) @PathVariable("id") Integer id) {
		logger.debug("id:{}", id);

		if (id == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		try {
			Marketing marketing = marketingService.getOneMarketing(id);
			return new ResponseEntity<Marketing>(marketing, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "获取发布图片的列表", response = MarketingPicture.class)
	@RequestMapping(value = "pictures/{marketingId}", method = RequestMethod.GET)
	public ResponseEntity<List<MarketingPicture>> getPicturesList(
			@ApiParam(value = "营销活动marketingId", required = true) @PathVariable("marketingId") Integer marketingId) {
		logger.debug("marketingId:{}", marketingId);

		if (marketingId == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		try {
			Map<String, Object> map = new HashMap<>();
			map.put("marketingId", marketingId);
			map.put("status", 1);
			List<MarketingPicture> list = marketingService.getPictureList(map);
			return new ResponseEntity<List<MarketingPicture>>(list, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "发布图片内容", notes = "参数说明 :(Y) 必须字段，(N) 可选字段"
			+ "\n\r发布营销活动：\n\rmarketingId(Y):营销活动id ,\n\ruid(Y) :发布用户id,\n\rpicture(Y):图片,\n\rpictureDesc(Y):图片描述")
	@RequestMapping(value = "pictures", method = RequestMethod.POST)
	public ResponseEntity<?> addPictures(@RequestBody MarketingPicture mp) {
		logger.debug("json:{}", mp);

		try {
			marketingService.addPictures(mp);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>("发布失败", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value="修改发布的图片内容",notes="参数说明 :(Y) 必须字段，(N) 可选字段"
			+ "\n\r修改内容：\n\rmarketingId(Y):营销活动id ,\n\ruid(Y) :发布用户id,\n\rpicture(Y):图片,\n\rpictureDesc(Y):图片描述")
	@RequestMapping(value="pictures",method=RequestMethod.PUT)
	public ResponseEntity<?> updatePictures(@RequestBody MarketingPicture mp) {
		logger.debug("json:{}", mp);
		
		try {
			Map<String ,Object> map = new HashMap<>();
			map.put("uid", mp.getUid());
			map.put("marketingId", mp.getMarketingId());
			map.put("picture", mp.getPicture());
			map.put("pictureDesc", mp.getPictureDesc());
			map.put("uploadTime", ExDateUtils.getDate());
			map.put("status", "0");
			
			marketingService.updatePictures(map);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new ResponseEntity<>("修改失败", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "获取发布图片内容", response = MarketingPicture.class)
	@RequestMapping(value = "picture/{marketingId}/{uid}", method = RequestMethod.GET)
	public ResponseEntity<MarketingPicture> getPicturesList(
			@ApiParam(value = "营销活动id", required = true) @PathVariable("marketingId") Integer marketingId,
			@ApiParam(value = "上传用户id", required = true) @PathVariable("uid") Long uid) {
		logger.debug("marketingId:{},uid:{}", marketingId, uid);

		if (marketingId == null || uid == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		try {
			Marketing marketing = marketingService.getOneMarketing(marketingId);
			if (marketing == null || marketing.getStatus() == 2) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // TODO
																		// 这里的响应状态码需要考虑
			}
			Map<String, Object> map = new HashMap<>();
			map.put("marketingId", marketingId);
			map.put("uid", uid);
			MarketingPicture mp = marketingService.getOneMaretingPicture(map);
			return new ResponseEntity<MarketingPicture>(mp, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "用户点赞", notes = "参数说明 :(Y) 必须字段，(N) 可选字段"
			+ "\n\r点赞用户：\n\rlikeUid(Y):点赞用户id ,\n\rmarketUid(Y) :发布用户id,\n\rmarketingId(Y):营销活动id")
	@RequestMapping(value = "likeUser", method = RequestMethod.POST)
	public ResponseEntity<?> addLikeUser(@RequestBody LikeUser _user) {
		logger.debug("json:{}", _user);

		try {
			Marketing marketing = marketingService.getOneMarketing(_user.getMarketingId());
			if (marketing == null || marketing.getStatus() == 2) {
				return new ResponseEntity<>("活动已结束", HttpStatus.BAD_REQUEST); // TODO
																				// 这里的响应状态码需要考虑
			}

			if (marketing.getEndTime().getTime() < ExDateUtils.getDate().getTime()) {
				return new ResponseEntity<>("活动点赞已结束", HttpStatus.BAD_REQUEST); // TODO
																				// 这里的响应状态码需要考虑
			}

			LikeUser likeUser = new LikeUser();
			likeUser.setLikeUid(_user.getLikeUid());
			likeUser.setMarketingId(_user.getMarketingId());
			likeUser.setMarketUid(_user.getMarketUid());
			likeUser.setLikeTime(ExDateUtils.getDate());
			marketingService.addLikeUser(likeUser);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED); // 这个异常状态需要仔细再考虑一下
		}
	}

	@ApiOperation(value = "用户是否点赞", notes = "1：表示已点赞，2：表示未点赞")
	@RequestMapping(value = "pictures/{likeUid}/{marketUid}/{marketingId}", method = RequestMethod.GET)
	public ResponseEntity<?> isLikeUser(@ApiParam("用户id") @PathVariable("likeUid") Long likeUid,
			@ApiParam("发布图片用户id") @PathVariable("marketUid") Long marketUid,
			@ApiParam("活动id") @PathVariable("marketingId") Integer marketingId) {
		logger.debug("likeUid:{}, marketUid:{},marketingId:{}", likeUid, marketUid, marketingId);

		if (likeUid == null || marketUid == null || marketingId == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("marketingId", marketingId);
			map.put("marketUid", marketUid);
			map.put("likeUid", likeUid);
			LikeUser likeUser = marketingService.getOneLikeUser(map);
			Integer status = 0;
			if (likeUser != null) {
				status = 1;
			}

			return new ResponseEntity<>(status, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "用户是否参加活动", notes = "1：表示已参加， 0：表示未参加")
	@RequestMapping(value = "{uid}/{mid}", method = RequestMethod.GET)
	public ResponseEntity<Integer> isJoin(@ApiParam("用户id") @PathVariable("uid") Long uid,
			@ApiParam("活动id") @PathVariable("mid") Integer mid) {
		logger.debug("uid:{}, mid:{}", uid, mid);
		
		if(uid == null || mid == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		try {
			Integer result = marketingService.isJoin(uid, mid);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "营销活动列表",response = Marketing.class)
	@RequestMapping(value = "list/{start}/{size}",method = RequestMethod.GET)
	public ResponseEntity<List<Marketing>> getList(@ApiParam(value="开始页,首页为1",required=true)@PathVariable("start")Integer start,
			@ApiParam(value="每页大小",required=true)@PathVariable("size")Integer size){
		logger.debug("start:{}, size:{}", start, size);
		
		try {
			List<Marketing> data = marketingService.getList(start - 1 <= 0 ? 0 : (start - 1) * size , size);
			return new ResponseEntity<>(data, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
