package com.loeo.base.shiro.permission.authorizer;

import java.util.regex.Matcher;

import com.loeo.base.shiro.ShiroContextUtils;
import com.loeo.base.shiro.permission.DataPermission;
import com.loeo.domain.entity.SysUser;
import com.loeo.service.SysUserService;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2018-03-10 13:39:28
 * @version：2018 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
public class UserDataPermissionAuthorizer implements DataPermissionAuthorizer {
	private SysUserService sysUserService;

	@Override
	public boolean authorize(Matcher matcher, DataPermission dataPermission) {
		switch (dataPermission.getRole()) {
			case CREATOR:
				SysUser sysUser = sysUserService.selectById(matcher.group(dataPermission.getGroupIndex()));
				return sysUser.getCreator().equals(ShiroContextUtils.getCurUserId());
			default:
				return false;
		}
	}
}
