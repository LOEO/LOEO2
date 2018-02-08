package com.loeo.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.loeo.dto.SysResourceTreeNode;
import com.loeo.entity.SysRole;
import com.loeo.mapper.SysRoleMapper;
import com.loeo.service.SysPrivilegeService;
import com.loeo.service.SysRoleService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
	@Resource
	private SysPrivilegeService privilegeService;
	@Resource
	private SysRoleMapper roleMapper;

	@Override
	public Page<SysRole> findRolesByPage(int pageNo, int pageSize) {
		return selectPage(new Page<>(pageNo, pageSize));
	}

	@Override
	public List<SysResourceTreeNode> getResources(Serializable roleId) {
		return privilegeService.getResources("role", roleId);
	}

	@Override
	public List<SysRole> getUserHasRoles(Serializable userId) {
		return roleMapper.getUserHasRoles(userId);
	}

	@Override
	public List<SysRole> getUserNotHasRoles(Serializable userId) {
		return roleMapper.getUserNotHasRoles(userId);
	}

}
