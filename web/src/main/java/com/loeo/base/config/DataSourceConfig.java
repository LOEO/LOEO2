package com.loeo.base.config;


import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 功能：数据源配置
 *
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2017-05-25 19:03:17
 * @version ：2017 Version：1.0
 */
@Configuration
public class DataSourceConfig {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Bean
	@ConfigurationProperties("app.datasource")
	public DataSource dataSource() {
		return new DruidDataSource();
	}
}
