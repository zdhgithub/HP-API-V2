package com.heipiao.api.v2.configuration;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * CORS
 * Spring Boot应有统一的处理方式
 * @author Chris
 *
 */
@Configuration
public class CorsConfigurer extends WebMvcConfigurerAdapter {
	
	@Value("${cors.origins:*}")
	private String origins;
	
//	@Value("${cors.methods:GET,POST,PUT,DELETE,OPTIONS,HEAD}")
	@Value("${cors.methods:*}")
	private String methods;
	
//	@Value("${cors.heads:*}")
//	private String heads;

	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		
		List<String> allowedOrigins = new ArrayList<String>(1);
		allowedOrigins.add(origins);
		corsConfiguration.setAllowedOrigins(allowedOrigins);
		
		List<String> allowedMethods = new ArrayList<String>(1);
		if (StringUtils.isNotBlank(methods)) {
			String[] methodAry = methods.split(",");
			for (String method : methodAry) {
				allowedMethods.add(method);
			}
		}
		corsConfiguration.setAllowedMethods(allowedMethods);
		
//		List<String> allowedHeaders = new ArrayList<String>(1);
//		allowedHeaders.add(heads);
//		corsConfiguration.setAllowedHeaders(allowedHeaders);
		
		corsConfiguration.setMaxAge(86400L);
		
		return corsConfiguration;
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", buildConfig());
		return new CorsFilter(source);
	}

}
