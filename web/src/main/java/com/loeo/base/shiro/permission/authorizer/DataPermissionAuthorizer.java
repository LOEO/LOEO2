package com.loeo.base.shiro.permission.authorizer;

import java.util.regex.Matcher;

import com.loeo.base.shiro.permission.DataPermission;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2018-03-10 09:16:52
 * @version：2018 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
public interface DataPermissionAuthorizer {
	boolean authorize(Matcher matcher, DataPermission dataPermission);

	default boolean doAuthorize(Matcher matcher, DataPermission dataPermission) {
		matcher.group(dataPermission.getGroupIndex());
		return false;
	}
}
