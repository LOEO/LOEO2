package com.loeo.shiro;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.loeo.entity.SysUser;
import com.loeo.service.ShiroService;


/**
 * @author LT(286269159 @ qq.com)
 */
public class LoeoRealm extends AuthorizingRealm {
	@Resource
	private ShiroService shiroService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//获取权限信息
		Set<String> roleSet = shiroService.findRolesByUserId(ShiroContextUtils.getCurUser().getId());
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roleSet);
		simpleAuthorizationInfo.setStringPermissions(shiroService.findPermByRoles(roleSet));
		return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		String username = (String) authenticationToken.getPrincipal();
		SysUser sysUser = shiroService.findUserByUserName(username);
		if (sysUser == null) {
			throw new UnknownAccountException();
		}
		//认证用户名密码
		return new SimpleAuthenticationInfo(
				sysUser,
				sysUser.getPassword(),
				this.getClass().getName()
		);
	}

}
