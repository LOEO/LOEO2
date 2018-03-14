package com.loeo.base.shiro.permission.authorizer;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.loeo.base.shiro.ShiroContextUtils;
import com.loeo.base.shiro.permission.DataPermission;
import com.loeo.admin.domain.entity.SysUser;
import com.loeo.admin.service.SysUserService;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2018-03-10 13:39:28
 * @version：2018 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
@Component
public class UserDataPermissionAuthorizer implements DataPermissionAuthorizer {
	@Resource
	private SysUserService sysUserService;

	@Override
	public boolean authorize(DataPermission dataPermission) {
		switch (dataPermission.getRole()) {
			case CREATOR:
				SysUser sysUser = sysUserService.selectById(dataPermission.getResourceId());
				return ShiroContextUtils.getCurUserId().equals(sysUser.getCreator());
			default:
				return false;
		}
	}
}
