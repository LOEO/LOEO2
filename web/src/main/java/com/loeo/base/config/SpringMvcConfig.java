package com.loeo.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2018-02-07 13:53:11
 * @version：2018 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
//@Configuration
public class SpringMvcConfig {
	@Bean
	public ViewResolver viewResolver() {
		return new InternalResourceViewResolver("/", ".jsp");
	}
}
