package com.heipiao.api.v2.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.heipiao.api.v2.interceptor.WorkLog;

@Configuration
public class GlobalConfigurer extends WebMvcConfigurerAdapter {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new WorkLog()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}

}
