package com.heipiao.api.v2.configuration;

import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import com.google.common.base.Predicate;
import com.heipiao.api.v2.controller.AllianceController;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author wzw
 * @date 2017年3月2日
 */
@EnableSwagger2
@Configuration
public class SwaggerConfigurer {
	
	private static final String TITLE = "API接口文档";
	private static final String VERSION = "2.0";
	
//	@Bean
//	public Docket demoCp() {
//		return new Docket(DocumentationType.SWAGGER_2).groupName("cp").genericModelSubstitutes(DeferredResult.class)
//				// .genericModelSubstitutes(ResponseEntity.class)
//				.useDefaultResponseMessages(false).forCodeGeneration(false).pathMapping("/").select()
//				.paths(PathSelectors.regex("/cp/.*"))// 过滤的接口
////				.paths(PathSelectors.any())// 所有接口
//				.build()
//		 .apiInfo(demoApiInfo());
//	}
	
	@Bean
	public Docket buildAllianceApi() {
		Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
            public boolean apply(RequestHandler input) {
                Class<?> declaringClass = input.declaringClass();
                if (declaringClass == BasicErrorController.class)
            	  return false;
                if (declaringClass == AllianceController.class)
                	return true;
                return false;
            }
        };
        
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("Alliance")
				// TODO 弄懂以下这些是什么意思
//				.useDefaultResponseMessages()
//				.globalOperationParameters(operationParameters)
//				.globalResponseMessage(requestMethod, responseMessages)
//				.forCodeGeneration
//				.genericModelSubstitutes(DeferredResult.class)
				.genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(false)
				.pathMapping("/")
				.select()
				.paths(PathSelectors.any())// 所有接口
				.apis(predicate)
				.build()
				.apiInfo(buildApiInfo("小程序加盟商模块"));
	}
	
	private static Contact buildContact() {
		return new Contact("", "", "");
	}
	
	private static ApiInfo buildApiInfo(String description) {
		return new ApiInfo(TITLE, // 大标题
				description, // 描述
				VERSION, // 版本
				"",
				buildContact(), // 作者
				"", // 链接显示文字
				""// 网站链接
		);
	}
	
}
