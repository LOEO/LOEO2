package com.loeo.shiro;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import com.alibaba.fastjson.JSON;
import com.loeo.common.Result;
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
public class SysPermFilter extends PermissionsAuthorizationFilter implements ApplicationRunner {
	private static final Logger logger = LoggerFactory.getLogger(SysPermFilter.class);
	private Map<String, Pattern> pathPatternMap = new HashMap<>();

	@Override
	protected boolean pathsMatch(String path, ServletRequest request) {
		Pattern pattern;
		String requestURI = getPathWithinApplication(request);
		if (!pathPatternMap.containsKey(path)) {
			pattern = Pattern.compile(path);
			pathPatternMap.put(path, pattern);
		}else{
			pattern = pathPatternMap.get(path);
		}
		return pattern.matcher(requestURI).matches();
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

	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("开始初始化系统权限...");
		ShiroService shiroService = ApplicationContextUtils.getBean(ShiroService.class);
		Map<String,String> urlPerms = shiroService.initUrlPerms();
		appliedPaths.clear();
		for (Map.Entry<String,String> entry : urlPerms.entrySet()) {
			appliedPaths.put(entry.getKey(), splitChainDefinition(entry.getValue()));
		}
		logger.info("系统权限初始化完成...");
	}


	private String[] splitChainDefinition(String chainDefinition) {
		return StringUtils.split(chainDefinition, StringUtils.DEFAULT_DELIMITER_CHAR, '[', ']', true, true);
	}
}
