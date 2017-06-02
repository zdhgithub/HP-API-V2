package com.heipiao.api.v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "${config}", encoding = "UTF-8")
public class API_v2 {
	
	public static void main(String[] args) {
		SpringApplication.run(API_v2.class, args);
	}
	
}
