package com.heipiao.api.v2.component.oss;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * oss配置类
 * @author wzw
 * @date 2016年7月26日
 * @version 1.0
 */
@Component
public class OSSConfig {
	
	@Value("${oss.accessKeyId}")
	protected String accessKeyId;

	@Value("${oss.accessKeySecret}")
	protected String accessKeySecret;

	// RoleArn 需要在 RAM 控制台上获取
	@Value("${oss.roleArn}")
	protected String roleArn;
	
	@Value("${oss.endpoint}")
	protected String endpoint;

}
