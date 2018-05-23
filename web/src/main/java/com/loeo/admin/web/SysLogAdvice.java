package com.loeo.admin.web;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import com.alibaba.fastjson.JSON;
import com.loeo.admin.domain.entity.SysLog;
import com.loeo.admin.domain.entity.SysResource;
import com.loeo.admin.service.SysLogService;
import com.loeo.base.shiro.ShiroContextUtils;


/**
 * 功能：
 *
 * @author ：Tony.L(286269159@qq.com)
 * @version ：2018 Version：1.0
 * @create ：2018-05-23 17:12:32
 */
@ControllerAdvice("com.hydt.hm.cd.controller")
public class SysLogAdvice implements RequestBodyAdvice {
	@Resource
	private SysLogService sysLogService;

	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
		return inputMessage;
	}

	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		SysResource curResource = ShiroContextUtils.getCurResource();
		if (curResource != null) {
			SysLog sysLog = new SysLog();
			sysLog.setAction(curResource.getName());
			sysLog.setUrl(request.getRequestURI());
			sysLog.setMethod(request.getMethod());
			sysLog.setParams(JSON.toJSONString(body));
			sysLogService.insert(sysLog);
		}
		return body;
	}

	@Override
	public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		return body;
	}
}
