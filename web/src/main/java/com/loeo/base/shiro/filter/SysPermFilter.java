package com.loeo.base.shiro.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.util.StringUtils;
import org.apache.shiro.util.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.loeo.base.event.ResourceUpdateEvent;
import com.loeo.domain.entity.SysResource;
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
public class SysPermFilter extends AbstractSysFilter {
	private static final Logger logger = LoggerFactory.getLogger(SysPermFilter.class);
	private static final String CUR_SYS_RESOURCE_KEY = "CUR_SYS_RESOURCE_KEY";
	private static final String CUR_MATCHER_KEY = "CUR_MATCHER_KEY";
	private Map<String, Pattern> pathPatternMap = new HashMap<>();
	private Map<String, SysResource> resourceMap = new HashMap<>();

	@Override
	protected boolean doRegexMatch(String path, ServletRequest request) {
		Pattern pattern;
		String requestURI = getPathWithinApplication(request);
		String requestMethod = ((HttpServletRequest) request).getMethod();

		String[] pathAndMethod = path.split(ShiroService.PART_DIVIDER_TOKEN);
		String urlPattern = pathAndMethod[0];
		String method = pathAndMethod.length == 2 ? pathAndMethod[1] : null;

		pattern = getPattern(path, urlPattern);

		Matcher matcher = pattern.matcher(requestURI);
		if (match(method, requestMethod, matcher)) {
			ThreadContext.put(CUR_MATCHER_KEY, matcher);
			ThreadContext.put(CUR_SYS_RESOURCE_KEY, resourceMap.get(path));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
		return super.isAccessAllowed(request, response, mappedValue);
	}

	@Override
	public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception) throws Exception {
		super.afterCompletion(request, response, exception);
		ThreadContext.remove(CUR_MATCHER_KEY);
		ThreadContext.remove(CUR_SYS_RESOURCE_KEY);
	}

	@Override
	public void init() {
		logger.info("开始初始化系统权限...");
		ShiroService shiroService = ApplicationContextUtils.getBean(ShiroService.class);
		List<SysResource> resources = shiroService.findAllPermResources();
		appliedPaths.clear();
		resources.forEach(resource -> {
			String path = getPath(resource);
			addToAppliedPaths(path, resource);
		});
		logger.info("系统权限初始化完成...");
	}

	@Override
	public void updateResource(ResourceUpdateEvent event) {
		SysResource sysResource = event.getSource();
		if (!StringUtils.hasText(sysResource.getApi())) {
			return;
		}
		String path = getPath(sysResource);
		synchronized (this) {
			switch (event.getAction()) {
				case ADD:
					addToAppliedPaths(path, sysResource);
					break;
				case UPDATE:
					break;
				case DELETE:
					removeFromAppliedPaths(path, sysResource);
					break;
				default:
			}
		}
	}

	private Pattern getPattern(String path, String url) {
		Pattern pattern;
		if (!pathPatternMap.containsKey(path)) {
			pattern = Pattern.compile(url);
			pathPatternMap.put(path, pattern);
		} else {
			pattern = pathPatternMap.get(path);
		}
		return pattern;
	}

	private boolean match(String method, String requestMethod, Matcher matcher) {
		if (method == null) {
			return matcher.matches();
		} else {
			return method.toUpperCase().equals(requestMethod.toUpperCase()) && matcher.matches();
		}
	}

	private String getPath(SysResource sysResource) {
		return sysResource.getApi() + (StringUtils.hasText(sysResource.getMethod()) ? ShiroService.PART_DIVIDER_TOKEN + sysResource.getMethod() : "");
	}

	private String getPermStr(SysResource sysResource) {
		return sysResource.getType() + ShiroService.PART_DIVIDER_TOKEN + sysResource.getId();
	}

	private void addToAppliedPaths(String path, SysResource sysResource) {
		String permStr = getPermStr(sysResource);
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
		addToResourceMap(path, sysResource);
	}

	private void removeFromAppliedPaths(String path, SysResource sysResource) {
		String[] values = (String[]) appliedPaths.get(path);
		if (values != null) {
			if (values.length <= 1) {
				appliedPaths.remove(path);
			} else {
				List<String> valueList = new ArrayList<>(Arrays.asList(values));
				valueList.remove(getPermStr(sysResource));
				appliedPaths.put(path, valueList.toArray(new String[]{}));
			}
		}
		removeFromResourceMap(path);
	}

	private void addToResourceMap(String path, SysResource resource) {
		resourceMap.put(path, resource);
	}

	private void removeFromResourceMap(String path) {
		if (!appliedPaths.containsKey(path)) {
			resourceMap.remove(path);
		}
	}

	protected Matcher getCurPathMatcher() {
		return (Matcher) ThreadContext.get(CUR_MATCHER_KEY);
	}

	protected SysResource getCurSysResource() {
		return (SysResource) ThreadContext.get(CUR_SYS_RESOURCE_KEY);
	}

}
