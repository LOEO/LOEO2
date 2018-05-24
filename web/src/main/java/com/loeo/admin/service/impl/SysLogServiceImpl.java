package com.loeo.admin.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.loeo.admin.domain.dto.LoginDto;
import com.loeo.admin.domain.entity.SysLog;
import com.loeo.admin.domain.entity.SysResource;
import com.loeo.admin.mapper.SysLogMapper;
import com.loeo.admin.service.SysLogService;
import com.loeo.base.config.properties.AppProperties;
import com.loeo.base.service.BaseServiceImpl;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LT
 * @since 2018-03-08
 */
@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLogMapper, SysLog> implements SysLogService {
	@Resource
	private AppProperties appProperties;

	@Override
	public void log(HttpServletRequest request, SysResource currentResource, Object param) {
		if (currentResource != null) {
			SysLog sysLog = new SysLog();
			sysLog.setAction(currentResource.getName());
			sysLog.setUrl(request.getRequestURI());
			sysLog.setMethod(request.getMethod());
			if (isLoginApi(request)) {
				LoginDto loginDtoCopy = new LoginDto();
				BeanUtils.copyProperties(param, loginDtoCopy, "password");
				sysLog.setParams(JSON.toJSONString(loginDtoCopy));
			} else {
				sysLog.setParams(JSON.toJSONString(param));
			}
			insert(sysLog);
		}
	}

	private boolean isLoginApi(HttpServletRequest request) {
		return (request.getRequestURI() + ":" + request.getMethod()).equals(appProperties.getLoginApi());
	}
}
