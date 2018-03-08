package com.loeo.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.loeo.domain.dto.SysResourceTreeNode;
import com.loeo.domain.entity.SysRole;
import com.loeo.mapper.SysRoleMapper;
import com.loeo.service.BaseServiceImpl;
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
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
	@Resource
	private SysPrivilegeService privilegeService;
	@Resource
	private SysRoleMapper sysRoleMapper;

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
		return sysRoleMapper.getUserHasRoles(userId);
	}

	@Override
	public List<SysRole> getUserNotHasRoles(Serializable userId) {
		return sysRoleMapper.getUserNotHasRoles(userId);
	}

}
