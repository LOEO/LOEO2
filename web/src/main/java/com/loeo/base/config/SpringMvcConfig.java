package com.loeo.base.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.loeo.admin.web.SysLogAdvice;

/**
 * 功能：
 *
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2018-02-07 13:53:11
 * @version ：2018 Version：1.0

 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
	@Resource
	private SysLogAdvice sysLogAdvice;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(sysLogAdvice).addPathPatterns("/api/**");
	}
}
