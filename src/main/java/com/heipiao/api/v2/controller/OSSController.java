package com.heipiao.api.v2.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.heipiao.api.v2.constant.RespMessage;
import com.heipiao.api.v2.constant.RespMsg;
import com.heipiao.api.v2.constant.Status;
import com.heipiao.api.v2.domain.OSSSign;
import com.heipiao.api.v2.service.OSSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "OSS模块")
@RestController
@RequestMapping(value = "token", produces = MediaType.APPLICATION_JSON_VALUE)
public class OSSController {
	
	@Resource
	private OSSService tokenService;

	private final static Logger logger = LoggerFactory.getLogger(OSSController.class);
	
	@ApiOperation(value = "获取OSS token信息", notes = "参数说明：<br />"
			+ "dir：上传OSS半路径<br/>"
			+ "bucket：子域名<br/>")
	@RequestMapping(value = "oss_sign", method = RequestMethod.POST)
	public String getTokenBySign(
			@RequestBody JSONObject json) {
		logger.debug("json:{}",json);
		String dir = json == null ? null : json.getString("dir");
		String bucket = json == null ? null : json.getString("bucket");
		if(bucket == null || dir == null){
			return JSONObject.toJSONString(new RespMsg<>(Status.value_is_null_or_error,RespMessage.getRespMsg(Status.value_is_null_or_error)));
		}	
		try {
			return JSONObject.toJSONString(new RespMsg<>(tokenService.getTokenBySign(bucket,dir)));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JSONObject.toJSONString(new RespMsg<>(Status.error,RespMessage.getRespMsg(Status.error)));
		}
	}

}
