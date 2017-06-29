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
	private String accessKeyId;

	@Value("${oss.accessKeySecret}")
	private String accessKeySecret;

	@Value("${oss.roleArn}")
	private String roleArn;
	
	@Value("${oss.endpoint}")
	private String endpoint;

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public String getRoleArn() {
		return roleArn;
	}

	public void setRoleArn(String roleArn) {
		this.roleArn = roleArn;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

}
