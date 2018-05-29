package com.loeo.base.shiro.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import com.alibaba.fastjson.JSON;
import com.loeo.base.Result;
import com.loeo.base.event.ResourceUpdateEvent;
import com.loeo.utils.WebUtils;

/**
 * 功能：
 *
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2018-03-09 15:51:39
 * @version ：2018 Version：1.0

 */
public abstract class AbstractSysFilter extends PermissionsAuthorizationFilter {

	@Override
	protected boolean pathsMatch(String path, ServletRequest request) {
		return /*super.pathsMatch(path, request) ||*/ doRegexMatch(path, request);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		if (WebUtils.isAjax((HttpServletRequest) request)) {
			response.setCharacterEncoding("utf8");
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter printWriter = response.getWriter();
			printWriter.write(JSON.toJSONString(Result.failed("没有权限")));
			printWriter.flush();
		} else {
			((HttpServletResponse) response).sendRedirect(getUnauthorizedUrl());
		}
		return false;
	}

	/**
	 * 正则验证
	 * @param path
	 * @param request
	 * @return
	 */
	protected abstract boolean doRegexMatch(String path, ServletRequest request);

	/**
	 * 初始化资源
	 */
	public abstract void init();

	/**	更新资源
	 * @param event
	 */
	public abstract void updateResource(ResourceUpdateEvent event);
}
