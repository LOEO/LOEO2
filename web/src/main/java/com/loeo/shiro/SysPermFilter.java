package com.loeo.shiro;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.loeo.common.Result;
import com.loeo.entity.SysResource;
import com.loeo.event.ResourceUpdateEvent;
import com.loeo.service.ShiroService;
import com.loeo.utils.ApplicationContextUtils;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2018-02-25 10:36:52
 * @version：2018 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
public class SysPermFilter extends PermissionsAuthorizationFilter {
	private static final Logger logger = LoggerFactory.getLogger(SysPermFilter.class);
	private Map<String, Pattern> pathPatternMap = new HashMap<>();

	@Override
	protected boolean pathsMatch(String path, ServletRequest request) {
		Pattern pattern;
		String requestURI = getPathWithinApplication(request);
		String[] pathAndMethod = path.split(ShiroService.PART_DIVIDER_TOKEN);
		String url = pathAndMethod[0];
		String method = pathAndMethod.length == 2 ? pathAndMethod[1] : null;
		if (!pathPatternMap.containsKey(path)) {
			pattern = Pattern.compile(url);
			pathPatternMap.put(path, pattern);
		} else {
			pattern = pathPatternMap.get(path);
		}

		return method == null ? pattern.matcher(requestURI).matches()
				: method.toUpperCase().equals(((HttpServletRequest) request).getMethod().toUpperCase()) && pattern.matcher(requestURI).matches();
	}

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
		return super.isAccessAllowed(request, response, mappedValue);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		response.setCharacterEncoding("utf8");
		PrintWriter printWriter = response.getWriter();
		printWriter.write(JSON.toJSONString(Result.failed("没有权限")));
		printWriter.flush();
		return false;
		/*if (WebUtils.isAjax((HttpServletRequest) request)) {
			PrintWriter printWriter = response.getWriter();
			printWriter.write(JSON.toJSONString(Result.failed("没有权限")));
			printWriter.flush();
			return false;
		}else{
			return super.onAccessDenied(request, response);
		}*/
	}


	public void init() {
		logger.info("开始初始化系统权限...");
		ShiroService shiroService = ApplicationContextUtils.getBean(ShiroService.class);
		List<SysResource> resources = shiroService.findAllPermResources();
		appliedPaths.clear();

		resources.forEach(resource -> addToAppliedPaths(resource.getApi() + (StringUtils.hasText(resource.getMethod()) ? ShiroService.PART_DIVIDER_TOKEN + resource.getMethod() : ""), resource.getType()
				+ ShiroService.PART_DIVIDER_TOKEN
				+ resource.getId()
		));
		logger.info("系统权限初始化完成...");
	}


	public void updateResource(ResourceUpdateEvent event) {
		SysResource sysResource = event.getSource();
		if (!StringUtils.hasText(sysResource.getApi())) {
			return;
		}
		synchronized (this) {
			switch (event.getAction()) {
				case ADD:
					addToAppliedPaths(sysResource.getApi() + (StringUtils.hasText(sysResource.getMethod()) ? ShiroService.PART_DIVIDER_TOKEN + sysResource.getMethod() : ""), sysResource.getType()
							+ ShiroService.PART_DIVIDER_TOKEN
							+ sysResource.getId()
					);
					break;
				case UPDATE:
					break;
				case DELETE:
					removeFromAppliedPaths(sysResource.getApi() + (StringUtils.hasText(sysResource.getMethod()) ? ShiroService.PART_DIVIDER_TOKEN + sysResource.getMethod() : ""), sysResource.getType()
							+ ShiroService.PART_DIVIDER_TOKEN
							+ sysResource.getId());
					break;
				default:
			}
		}
	}

	private void removeFromAppliedPaths(String path, String permStr) {
		String[] values = (String[]) appliedPaths.get(path);
		if (values != null) {
			if (values.length <= 1) {
				appliedPaths.remove(path);
			} else {
				List<String> valueList = new ArrayList<>(Arrays.asList(values));
				valueList.remove(permStr);
				appliedPaths.put(path, valueList.toArray(new String[]{}));
			}
		}
	}

	private void addToAppliedPaths(String path, String permStr) {
		if (appliedPaths.containsKey(path)) {
			String[] values = (String[]) appliedPaths.get(path);
			List<String> valueList = new ArrayList<>(Arrays.asList(values));
			if (!valueList.contains(permStr)) {
				valueList.add(permStr);
			}
			appliedPaths.put(path, valueList.toArray(new String[]{}));
		} else {
			appliedPaths.put(path, new String[]{permStr});
		}
	}
}
