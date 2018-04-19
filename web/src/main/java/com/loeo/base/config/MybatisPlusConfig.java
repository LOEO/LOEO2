package com.loeo.base.config;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.enums.DBType;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;

/**
 * 功能：
 *
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2017-05-25 17:31:49
 * @version ：2017 Version：1.0
 */
@Configuration
@MapperScan("com.loeo.mapper")
public class MybatisPlusConfig {
	/**
	 * mybatis-plus分页插件<br>
	 * 文档：http://mp.baomidou.com<br>
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor(DataSource dataSource) {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		paginationInterceptor.setDialectType(DBType.MYSQL.getDb());
		//paginationInterceptor.setOptimizeType(Optimize.JSQLPARSER.getOptimize());
		return paginationInterceptor;
	}
}
