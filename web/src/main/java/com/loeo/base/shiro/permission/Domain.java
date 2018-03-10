package com.loeo.base.shiro.permission;

import com.loeo.base.shiro.permission.authorizer.DataPermissionAuthorizer;
import com.loeo.base.shiro.permission.authorizer.OrgDataPermissionAuthorizer;
import com.loeo.base.shiro.permission.authorizer.UserDataPermissionAuthorizer;
import com.loeo.utils.ApplicationContextUtils;

/**
 * @author LT(286269159 @ qq.com)
 */

public enum Domain {
	/**
	 * 用户
	 */
	USER(ApplicationContextUtils.getBean(UserDataPermissionAuthorizer.class)),
	/**
	 * 组织
	 */
	ORG(ApplicationContextUtils.getBean(OrgDataPermissionAuthorizer.class));

	private DataPermissionAuthorizer dataPermissionAuthorizer;

	Domain(DataPermissionAuthorizer dataPermissionAuthorizer) {
		this.dataPermissionAuthorizer = dataPermissionAuthorizer;
	}

	public DataPermissionAuthorizer getDataPermissionAuthorizer() {
		return dataPermissionAuthorizer;
	}
}