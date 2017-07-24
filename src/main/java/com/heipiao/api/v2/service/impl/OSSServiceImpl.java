package com.heipiao.api.v2.service.impl;

import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.heipiao.api.v2.component.oss.SignGetToken;
import com.heipiao.api.v2.service.OSSService;

/**
 * OSS相关服务
 * @author Chris
 *
 */
@Service
public class OSSServiceImpl implements OSSService {
	
	
	@Resource
	private SignGetToken signGetToken;
//	@Resource
//	private com.heipiao.api.v2.component.oss.OSSService ossService;
//	
//	private static final Logger logger = LoggerFactory.getLogger(OSSServiceImpl.class);
//
//	@Override
//	public OSSSign generateSign(String bucket, String dir) {
//		try {
//			return ossService.generate(bucket, dir);
//		} catch (Exception e) {
//			logger.error("生成OSS签名失败", e);
//			throw new ServiceException("生成签名失败，请稍后重试");
//		}
//	}
//	
	@Override
	public Map<String, String> getTokenBySign(String bucket, String dir) throws Exception {
		return signGetToken.getToken(bucket, dir);
	}

}
