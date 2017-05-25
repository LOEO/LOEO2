package com.loeo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2017-05-25 19:09:08
 * @version：2017 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
@ConfigurationProperties("druid.datasource")
public class DruidDataSourceProperties {
	private String url;
	private String username;
	private String password;
	private Boolean defaultAutoCommit;
	private Integer initialSize;
	private Integer maxActive;
	private Integer maxIdle;
	private Integer maxWait;
	private Integer timeBetweenEvictionRunsMillis;
	private Integer minEvictableIdleTimeMillis;
	private Integer validationQuery;
	private Boolean testWhileIdle;
	private Boolean testOnBorrow;
	private Boolean testOnReturn;

}
