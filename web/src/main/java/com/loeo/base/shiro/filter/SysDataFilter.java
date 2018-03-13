package com.loeo.base.shiro.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.loeo.base.exception.DataPermissionResolveException;
import com.loeo.base.shiro.permission.DataPermission;
import com.loeo.base.shiro.permission.authorizer.DataPermissionAuthorizer;
import com.loeo.base.shiro.permission.Domain;
import com.loeo.base.shiro.permission.Role;
import com.loeo.domain.entity.SysResource;
import com.loeo.service.ShiroService;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2018-03-08 17:15:01
 * @version：2018 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
public class SysDataFilter extends SysPermFilter {

	private Map<DataPermission, DataPermissionAuthorizer> permissionDataPermissionAuthorizerMap = new HashMap<>();

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
		if (super.isAccessAllowed(request, response, mappedValue)) {
			Matcher matcher = super.getCurPathMatcher();
			SysResource sysResource = super.getCurSysResource();
			String dataPermissionStr = sysResource.getDataPermission();
			if (StringUtils.isEmpty(dataPermissionStr)) {
				return true;
			}
			DataPermission dataPermission = resolveDataPermission(matcher, dataPermissionStr);
			return dataPermission.authorize();
		}
		return false;
	}

	@Override
	public void init() {
		super.init();

	}

	/**
	 * 域:正则中的group索引:角色
	 * org:1:member
	 *
	 * @param matcher
	 * @param script
	 */
	private DataPermission resolveDataPermission(Matcher matcher, String script) {
		String[] scriptArr = script.split(ShiroService.PART_DIVIDER_TOKEN);
		if (scriptArr.length == 3) {
			return new DataPermission(Domain.valueOf(scriptArr[0]), matcher.group(Integer.parseInt(scriptArr[1])), Role.valueOf(scriptArr[2]));
		}
		throw new DataPermissionResolveException();
	}


}
