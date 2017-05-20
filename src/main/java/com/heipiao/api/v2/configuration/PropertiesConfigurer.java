package com.heipiao.api.v2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.UrlResource;

/**
 * @author wzw
 * @date 2017年2月25日
 */
@Configuration
public class PropertiesConfigurer {

	@Bean
	public PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() throws Exception{
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		String path = System.getProperty("config");
		pspc.setLocations(new UrlResource("file:" + path + "/config.properties")); // TODO 改实现方式
		pspc.setFileEncoding("UTF-8");
		return pspc;
	}
	
}
