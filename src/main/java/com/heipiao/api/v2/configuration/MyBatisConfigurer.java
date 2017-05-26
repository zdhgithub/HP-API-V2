package com.heipiao.api.v2.configuration;

import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jolbox.bonecp.BoneCPConfig;
import com.jolbox.bonecp.BoneCPDataSource;

/**
 * @author Chris
 * @date 2017年2月25日
 * 
 * 这个类值得再研究一下自动配置和方法注入，特别是事务控制
 * http://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#jpa.java-config
 */
@Configuration
@MapperScan(basePackages="com.heipiao.api.v2.mapper")
@EnableTransactionManagement(proxyTargetClass = true)
public class MyBatisConfigurer {

	@Value("${mysql.driverClass}")
	private String driverClass;
	
	@Value("${mysql.jdbcUrl}")
	private String url;
	
	@Value("${mysql.username}")
	private String username;
	
	@Value("${mysql.password}")
	private String password;
	
	@Value("${mysql.partitionCount}")
	private Integer partitionCount;
	
	@Value("${mysql.maxConnectionsPerPartition}")
	private Integer maxConnectionsPerPartition;
	
	@Value("${mysql.minConnectionsPerPartition}")
	private Integer minConnectionsPerPartition;
	
	@Value("${mysql.acquireIncrement}")
	private Integer acquireIncrement;
	
	@Value("${mysql.statementsCacheSize}")
	private Integer statementsCacheSize;
	
	@Value("${mysql.idleMaxAgeInSeconds}")
	private Integer idleMaxAgeInSeconds;
	
	@Value("${mysql.connectionTimeoutInMs}")
	private Integer connectionTimeoutInMs;
	
	@Bean
	public BoneCPDataSource getBoneCPDataSource(){
		BoneCPConfig config =  new BoneCPConfig();
		config.setJdbcUrl(url);
		config.setUsername(username);
		config.setPassword(password);
		config.setPartitionCount(partitionCount);
		config.setMaxConnectionsPerPartition(maxConnectionsPerPartition);
		config.setMinConnectionsPerPartition(minConnectionsPerPartition);
		config.setAcquireIncrement(acquireIncrement);
		config.setStatementsCacheSize(statementsCacheSize);
		config.setIdleMaxAgeInSeconds(idleMaxAgeInSeconds);
		config.setConnectionTimeoutInMs(connectionTimeoutInMs);
		BoneCPDataSource bcp = new BoneCPDataSource(config);
		bcp.setDriverClass(driverClass);
		return bcp;
	}
	
	@Bean
	public SqlSessionFactoryBean getSqlSessionFactoryBean(DataSource dataSource) throws IOException{
		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		ssfb.setDataSource(dataSource);
		ssfb.setTypeAliasesPackage("com.heipiao.api.v2.domain");
		ssfb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
		ssfb.setVfs(SpringBootVFS.class);
		return ssfb;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setDataSource(dataSource);
//		transactionManager.setDataSource(getBoneCPDataSource());
		return transactionManager;
	}
	
}
