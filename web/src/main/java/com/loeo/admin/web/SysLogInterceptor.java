package com.loeo.admin.web;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.util.ThreadContext;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import com.loeo.admin.domain.entity.SysResource;
import com.loeo.admin.service.SysLogService;
import com.loeo.base.shiro.ShiroContextUtils;


/**
 * 功能：记录系统日志
 *
 * @author ：Tony.L(286269159@qq.com)
 * @version ：2018 Version：1.0
 * @create ：2018-05-23 17:12:32
 */
@ControllerAdvice("com.hydt.hm.cd.controller")
public class SysLogInterceptor extends HandlerInterceptorAdapter implements RequestBodyAdvice {
	private static final String REQUEST_PARAM_KEY = SysLogInterceptor.class.getName() + ".REQUEST_PARAM_KEY";
	@Resource
	private SysLogService sysLogService;

	@ModelAttribute
	public void logGetRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		if (HttpMethod.GET.toString().equals(request.getMethod())) {
			log(request, null);
		}
	}

	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
		return inputMessage;
	}

	/**
	 * RequestBody中的参数解析完成后会调用这个方法，这里先存入当前线程中，在controller中的逻辑执行完成后使用
	 *
	 * @param body
	 * @param inputMessage
	 * @param parameter
	 * @param targetType
	 * @param converterType
	 * @return
	 */
	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		ThreadContext.put(REQUEST_PARAM_KEY, body);
		return body;
	}

	@Override
	public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		return body;
	}

	/**
	 * controller执行完后执行此方法保存日志
	 *
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		log(request, ThreadContext.get(REQUEST_PARAM_KEY));
		ThreadContext.remove(REQUEST_PARAM_KEY);
	}

	private void log(HttpServletRequest request, Object body) {
		SysResource curResource = ShiroContextUtils.getCurResource();
		sysLogService.log(request, curResource, body);
	}
}
