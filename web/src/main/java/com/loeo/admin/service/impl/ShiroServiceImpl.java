package com.loeo.admin.service.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.loeo.base.shiro.ShiroContextUtils;
import com.loeo.admin.domain.entity.SysPrivilege;
import com.loeo.admin.domain.entity.SysResource;
import com.loeo.admin.domain.entity.SysRole;
import com.loeo.admin.domain.entity.SysUser;
import com.loeo.admin.service.ShiroService;
import com.loeo.admin.service.SysPrivilegeService;
import com.loeo.admin.service.SysResourceService;
import com.loeo.admin.service.SysUserService;


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
		return sysRoleList.stream().map(sysRole -> sysRole.getId().toString()).collect(Collectors.toSet());
	}

	@Override
	public SysUser findUserByUserName(String username) {
		return sysUserService.findByUserName(username);
	}
}
