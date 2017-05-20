package com.heipiao.api.v2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
	public Docket demoApi() {
		return new Docket(DocumentationType.SWAGGER_2)
//				.groupName("API-v2")
//				.genericModelSubstitutes(DeferredResult.class) // 弄懂这个是什么意思
				// .genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(false)
				.pathMapping("/").select()
				.paths(PathSelectors.any())// 所有接口
				.paths(PathSelectors.regex("(?!/error).*"))// 过滤的接口
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		Contact contact = new Contact("", "", "");
		ApiInfo apiInfo = new ApiInfo("API接口文档", // 大标题
				"", // 小标题
				"2.0", // 版本
				"", contact, // 作者
				"", // 链接显示文字
				""// 网站链接
		);
		return apiInfo;
	}
	
}
