package com.loeo.base.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;

import com.loeo.admin.domain.entity.SysUser;
import com.loeo.admin.service.ShiroService;
import com.loeo.utils.ApplicationContextUtils;


/**
 * @author LT(286269159 @ qq.com)
 */
public class LoeoRealm extends AuthorizingRealm {
	/**
	 * 此处没有用注入的方式，因为Realm初始化比事务初始化靠前，这里使用注入，会使提前使用的类的事务失效
	 */
	private ShiroService shiroService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//获取权限信息
		Set<String> roleSet = getShiroService().findRolesByUserId(ShiroContextUtils.getCurUser().getId());
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roleSet);
		if (ShiroContextUtils.getCurUserId() == "1") {
			Set<String> permissions = new HashSet<>();
			permissions.add("*");
			simpleAuthorizationInfo.setStringPermissions(permissions);
		} else {
			Set<String> permSet = new HashSet<>();
			permSet.addAll(getShiroService().findPermByRoles(roleSet));
			permSet.addAll(getShiroService().findPermByUserId(ShiroContextUtils.getCurUserId()));
			simpleAuthorizationInfo.setStringPermissions(permSet);
		}
		return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		String username = (String) authenticationToken.getPrincipal();
		SysUser sysUser = getShiroService().findUserByUserName(username);
		if (sysUser == null) {
			throw new UnknownAccountException();
		}
		//认证用户名密码
		return new SimpleAuthenticationInfo(
				sysUser,
				sysUser.getPassword(),
				new SimpleByteSource(sysUser.getUsername()),
				this.getClass().getName()
		);
	}

	private ShiroService getShiroService() {
		if (this.shiroService == null) {
			synchronized (this) {
				if (this.shiroService == null) {
					this.shiroService = ApplicationContextUtils.getBean(ShiroService.class);
				}
			}
		}
		return this.shiroService;
	}

}
