package com.loeo.base.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 功能：功能：实际没有用到，只是为了在application.properties中有提示
 *
 * @author ：LT(286269159@qq.com)
 * @version ：2018 Version：1.0
 * @create ：2018-05-03 17:56:44
 */
@ConfigurationProperties("app.datasource")
public class DataSourceProperties extends DruidDataSource {
}
