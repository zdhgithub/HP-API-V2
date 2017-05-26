package com.heipiao.api.v2;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@PropertySource(value = {"conf/config.properties"})
public class API_v2 {
	
	private static final Logger logger = LoggerFactory.getLogger(API_v2.class);
	
	public static final String CONFIG = "--config";

	public static void main(String[] args) {
		boolean flag = setProperty(args);
		if (!flag) {
			logger.error("启动失败，请指定配置文件路径参数\n" + Arrays.toString(args));
			return;
		}
		
		SpringApplication.run(API_v2.class, args);
	}
	
	private static boolean setProperty(String[] args) {
		for (String arg : args) {
			String[] item = arg.split("=");
			// 过滤没有属性值的配置，如内置的：--debug
			if (item.length == 1)
				continue;
			
			String key = item[0];
			String value = item[1];
			
			if (CONFIG.equals(key)) {
				System.setProperty(key, value);
				return true;
			}
		}
		
		return false;
	}
	
}
