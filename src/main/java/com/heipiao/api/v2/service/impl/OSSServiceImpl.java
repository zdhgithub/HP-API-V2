package com.heipiao.api.v2.service.impl;

import javax.annotation.Resource;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import com.heipiao.api.v2.domain.OSSSign;
import com.heipiao.api.v2.service.OSSService;

/**
 * OSS相关服务
 * @author Chris
 *
 */
@Service
public class OSSServiceImpl implements OSSService {
	
	@Resource
	private com.heipiao.api.v2.component.oss.OSSService ossService;

	@Override
	public OSSSign generateSign(String bucket, String dir) {
		try {
			return ossService.generate(bucket, dir);
		} catch (Exception e) {
			throw new ServiceException("生成签名失败，请稍后重试");
		}
	}

}
