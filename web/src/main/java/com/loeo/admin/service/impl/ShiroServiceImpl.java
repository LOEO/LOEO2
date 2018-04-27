package com.loeo.admin.service.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.loeo.admin.domain.entity.SysPrivilege;
import com.loeo.admin.domain.entity.SysResource;
import com.loeo.admin.domain.entity.SysRole;
import com.loeo.admin.domain.entity.SysUser;
import com.loeo.admin.service.ShiroService;
import com.loeo.admin.service.SysPrivilegeService;
import com.loeo.admin.service.SysResourceService;
import com.loeo.admin.service.SysUserService;
import com.loeo.base.exception.LoginFailedException;
import com.loeo.base.shiro.ShiroContextUtils;


/**
 * 功能：
 *
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2018-02-07 11:51:38
 * @version ：2018 Version：1.0

 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShiroServiceImpl implements ShiroService {
	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysResourceService sysResourceService;
	@Resource
	private SysPrivilegeService sysPrivilegeService;

	@Override
	public List<SysResource> findAllPermResources() {
		return sysResourceService.selectList(new EntityWrapper<SysResource>().isNotNull("api"));
	}

	@Override
	public Set<String> findPermByRoles(Set<String> roles) {
		Set<String> permSet = new HashSet<>();
		roles.forEach(role -> {
			List<SysPrivilege> sysPrivileges = sysPrivilegeService.selectList(new EntityWrapper<SysPrivilege>()
					.eq("master", "role")
					.eq("master_value", role));
			permSet.addAll(sysPrivileges.stream().map(sp -> sp.getAccess() + ShiroService.PART_DIVIDER_TOKEN + sp.getAccessValue()).collect(Collectors.toSet()));
		});
		return permSet;
	}

	@Override
	public Set<String> findPermByUserId(Serializable userId) {
		List<SysPrivilege> sysPrivileges = sysPrivilegeService.selectList(new EntityWrapper<SysPrivilege>().eq("master", "user").eq("master_value", userId));
		return sysPrivileges.stream().map(sp -> sp.getAccess() + ShiroService.PART_DIVIDER_TOKEN + sp.getAccessValue()).collect(Collectors.toSet());
	}

	@Override
	public Set<String> findRolesByUserId(Serializable id) {
		List<SysRole> sysRoleList = sysUserService.findRolesById(ShiroContextUtils.getCurUser().getId());
		return sysRoleList.stream().map(SysRole::getId).collect(Collectors.toSet());
	}

	@Override
	public SysUser findUserByUserName(String username) {
		return sysUserService.findByUserName(username);
	}

	@Override
	public SysUser login(String username, String password, boolean rememberMe) {
		try {
			UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
			usernamePasswordToken.setRememberMe(rememberMe);
			SecurityUtils.getSubject().login(usernamePasswordToken);
			return ShiroContextUtils.getCurUser();
		} catch (UnknownAccountException | IncorrectCredentialsException uae) {
			throw new LoginFailedException("用户名或密码错误");
		} catch (LockedAccountException lae) {
			throw new LoginFailedException("用户名被锁定");
		} catch (AuthenticationException ae) {
			throw new LoginFailedException("认证失败");
		}
	}
}
