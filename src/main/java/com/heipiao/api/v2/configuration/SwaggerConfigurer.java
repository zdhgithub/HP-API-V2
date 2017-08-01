package com.heipiao.api.v2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Chris
 * @date 2017-05-26
 */
@EnableSwagger2
@Configuration
public class SwaggerConfigurer {
	
	private static final String TITLE = "API接口文档";
	private static final String VERSION = "2.0";
	
	@Bean
	public Docket buildCampaignApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("campaign")
				.genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(true)
				.pathMapping("/")
				.select()
				.paths(PathSelectors.regex("/campaign.*"))
				.build()
				.apiInfo(buildApiInfo("小程序竞技活动模块"));
	}
	
	@Bean
	public Docket buildMarketingApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("marketing")
				.genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(true)
				.pathMapping("/")
				.select()
				.paths(PathSelectors.regex("/marketing.*"))
				.build()
				.apiInfo(buildApiInfo("小程序营销活动模块"));
	}
	
	@Bean
	public Docket buildAllianceApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("alliance")
				.genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(true)
				.pathMapping("/")
				.select()
				.paths(PathSelectors.regex("/alliance.*"))
				.build()
				.apiInfo(buildApiInfo("小程序加盟商模块"));
	}
	
	@Bean
	public Docket buildUserApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("user")
				.genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(true)
				.pathMapping("/")
				.select()
				.paths(PathSelectors.regex("/user.*"))
				.build()
				.apiInfo(buildApiInfo("用户模块"));
	}
	
	
	@Bean
	public Docket buildBusinessApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("business")
				.genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(true)
				.pathMapping("/")
				.select()
				.paths(PathSelectors.regex("/business.*"))
				.build()
				.apiInfo(buildApiInfo("小程序加盟商模块"));
	}
	
	@Bean
	public Docket buildHaveFishApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("havefish")
				.genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(true)
				.pathMapping("/")
				.select()
				.paths(PathSelectors.regex("/havefish.*"))
				.build()
				.apiInfo(buildApiInfo("有鱼模块"));
	}
	
	@Bean
	public Docket buildRegionApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("region")
				.genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(true)
				.pathMapping("/")
				.select()
				.paths(PathSelectors.regex("/region.*"))
				.build()
				.apiInfo(buildApiInfo("区域模块"));
	}
	
	@Bean
	public Docket buildOSSApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("token")
				.genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(true)
				.pathMapping("/")
				.select()
				.paths(PathSelectors.regex("/token.*"))
				.build()
				.apiInfo(buildApiInfo("OSS模块"));
	}
	
	@Bean
	public Docket buildDeliveryAddressApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("deliveryAddress")
				.genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(true)
				.pathMapping("/")
				.select()
				.paths(PathSelectors.regex("/deliveryAddress.*"))
				.build()
				.apiInfo(buildApiInfo("收货地址模块"));
	}
	
	@Bean
	public Docket buildFishSiteApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("fishsite")
				.genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(true)
				.pathMapping("/")
				.select()
				.paths(PathSelectors.regex("/fishsite.*"))
				.build()
				.apiInfo(buildApiInfo("钓点模块"));
	}
	
	@Bean
	public Docket buildMarkFishApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("markfish")
				.genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(true)
				.pathMapping("/")
				.select()
				.paths(PathSelectors.regex("/markfish.*"))
				.build()
				.apiInfo(buildApiInfo("标鱼模块"));
	}
	
	@Bean
	public Docket buildSysDictApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("sysdict")
				.genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(true)
				.pathMapping("/")
				.select()
				.paths(PathSelectors.regex("/sysdict.*"))
				.build()
				.apiInfo(buildApiInfo("字典模块"));
	}
	
	private static ApiInfo buildApiInfo(String description) {
		return new ApiInfoBuilder()
				.title(TITLE)
				.description(description)
				.version(VERSION)
				.build();
	}
	
}
