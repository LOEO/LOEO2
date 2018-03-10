package com.loeo.base.shiro.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.loeo.domain.entity.SysLog;
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
public class SysPermLogFilter extends SysDataFilter {

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
		if (super.isAccessAllowed(request, response, mappedValue)) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			//记录日志
			SysLogService sysLogService = ApplicationContextUtils.getBean(SysLogService.class);
			SysLog sysLog = new SysLog();
			sysLog.setAction(super.getCurSysResource().getName());
			sysLog.setUrl(httpServletRequest.getRequestURL().toString());
			sysLog.setMethod(httpServletRequest.getMethod());
			sysLog.setParams(JSON.toJSONString(httpServletRequest.getParameterMap()));
			sysLogService.insert(sysLog);
			return true;
		}
		return false;
	}

}
