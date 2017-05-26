package com.heipiao.api.v2.configuration;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

import com.heipiao.api.v2.API_v2;

/**
 * @author Chris
 * @date 2017-05-24
 * 
 */
@Configuration
public class PropertiesConfigurer {
	
	@Value("${config}")
	private static String path;

	@Bean
	public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() throws Exception{
		String path = System.getProperty(API_v2.CONFIG);
		
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		pspc.setLocation(new FileSystemResource(new File(path)));
//		pspc.setLocation(new PathResource(path));
		
		pspc.setFileEncoding("UTF-8");
		return pspc;
	}
	
}
