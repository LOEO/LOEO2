package com.loeo.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.loeo.dto.SysResourceTreeNode;
import com.loeo.entity.SysResource;
import com.loeo.entity.SysRole;
import com.loeo.entity.SysUser;
import com.loeo.entity.SysUserRole;
import com.loeo.exception.DuplicateUsernameException;
import com.loeo.mapper.SysUserMapper;
import com.loeo.service.BaseServiceImpl;
import com.loeo.service.SysPrivilegeService;
import com.loeo.service.SysRoleService;
import com.loeo.service.SysUserRoleService;
import com.loeo.service.SysUserService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
@Service
@Transactional
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {
	public SysUserServiceImpl() {
		System.out.println("=======================");
	}
	@Resource
	private SysUserMapper sysUserMapper;
	@Resource
	private SysUserRoleService sysUserRoleService;
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysPrivilegeService privilegeService;
	@Resource
	private SysRoleService roleService;
	@Resource
	private SysUserRoleService userRoleService;

	@Override
	public SysUser findByUserName(String username) {
		List<SysUser> sysUserList = selectByMap(new HashMap<String, Object>() {
			{
				put("username", username);
			}
		});
		if (sysUserList != null && sysUserList.size() > 0) {
			if (sysUserList.size() == 1) {
				return sysUserList.get(0);
			} else if (sysUserList.size() > 1) {
				throw new DuplicateUsernameException();
			}
		}
		return null;
	}

	@Override
	public List<SysRole> findRolesById(Serializable id) {
		List<SysUserRole> sysUserRoles = sysUserRoleService.findRolesByUserId(id);
		return sysRoleService.selectBatchIds(sysUserRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList()));
	}

	@Override
	public List<SysResource> findUserMenus(Serializable userId) {
		return sysUserMapper.findUserMenus(userId);
	}

	@Override
	public Page<SysUser> findUsersByPage(int pageNo, int pageSize) {
		return selectPage(new Page<>(pageNo, pageSize));
	}

	@Override
	public List<SysResourceTreeNode> findResource(Serializable userId) {
		return privilegeService.getResources("user", userId);
	}

	@Override
	public List<SysRole> getRoles(Serializable userId) {
		return roleService.getUserHasRoles(userId);
	}

	@Override
	public List<SysRole> getNotHasRoles(Serializable userId) {
		return roleService.getUserNotHasRoles(userId);
	}

	@Override
	public void saveUserRole(List<SysRole> roleList, String userId) {
		userRoleService.delete(new EntityWrapper<SysUserRole>().eq("userId", userId));
		userRoleService.saveUserRoles(roleList,userId);
	}
}
