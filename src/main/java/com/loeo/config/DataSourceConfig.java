package com.loeo.config;

import javax.activation.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2017-05-25 19:03:17
 * @version：2017 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
@Configuration
public class DataSourceConfig {
	private DataSourceProperties dataSourceProperties;

	@Bean
	public DataSource dataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUrl(dataSourceProperties.getUrl());
		druidDataSource.setUsername(dataSourceProperties.getUsername());
		druidDataSource.setPassword(dataSourceProperties.getPassword());
		return druidDataSource;
	}
}
