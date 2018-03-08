package com.loeo.base.shiro;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.util.ThreadContext;

import com.alibaba.fastjson.JSON;
import com.loeo.domain.entity.SysLog;
import com.loeo.domain.entity.SysResource;
import com.loeo.service.SysLogService;
import com.loeo.utils.ApplicationContextUtils;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2018-03-08 11:17:00
 * @version：2018 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
public class SysPermLogFilter extends SysPermFilter {
	private Map<String, SysResource> resourceMap = new HashMap<>();
	private static final String CUR_SYS_RESOURCE_KEY = "CUR_SYS_RESOURCE_KEY";

	@Override
	protected boolean pathsMatch(String path, ServletRequest request) {
		if (super.pathsMatch(path, request)) {
			//如果路径匹配上，把当前访问的资源存入当前线程中
			ThreadContext.put(CUR_SYS_RESOURCE_KEY, resourceMap.get(path));
			return true;
		}
		return false;
	}

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
		if (super.isAccessAllowed(request, response, mappedValue)) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			//记录日志
			SysLogService sysLogService = ApplicationContextUtils.getBean(SysLogService.class);
			SysLog sysLog = new SysLog();
			sysLog.setAction(((SysResource)ThreadContext.remove(CUR_SYS_RESOURCE_KEY)).getName());
			sysLog.setUrl(httpServletRequest.getRequestURL().toString());
			sysLog.setMethod(httpServletRequest.getMethod());
			sysLog.setParams(JSON.toJSONString(httpServletRequest.getParameterMap()));
			sysLogService.insert(sysLog);
			return true;
		}
		return false;
	}

	@Override
	protected void addToAppliedPaths(String path, SysResource sysResource) {
		super.addToAppliedPaths(path, sysResource);
		addToResourceMap(path, sysResource);
	}

	@Override
	protected void removeFromAppliedPaths(String path, SysResource sysResource) {
		super.removeFromAppliedPaths(path, sysResource);
		if (!super.appliedPaths.containsKey(path)) {
			removeFromResourceMap(path);
		}
	}

	private void addToResourceMap(String path, SysResource resource) {
		resourceMap.put(path, resource);
	}

	private void removeFromResourceMap(String path) {
		if (!appliedPaths.containsKey(path)) {
			resourceMap.remove(path);
		}
	}
}
