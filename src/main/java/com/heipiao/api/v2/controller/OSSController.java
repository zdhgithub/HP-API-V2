package com.heipiao.api.v2.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
	
	@ApiOperation(value = "获取OSS token信息", response = OSSSign.class)
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "bucket", value = "OSS的Bucket名称", dataType = "String", required = true)
		, @ApiImplicitParam(paramType = "query", name = "dir", value = "Bucket下目录名", dataType = "String", required = true)
	})
	@RequestMapping(value = "oss_sign", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public OSSSign getTokenBySign(
			@RequestParam(value = "bucket", required = true) String bucket
			, @RequestParam(value = "dir", required = true) String dir) {
		logger.debug("bucket:{}, dir:{}", bucket, dir);
		
		return tokenService.generateSign(bucket, dir);
	}

}
