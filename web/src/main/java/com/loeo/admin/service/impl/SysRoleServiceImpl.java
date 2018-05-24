package com.loeo.admin.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.loeo.admin.domain.dto.SysResourceTreeNode;
import com.loeo.admin.domain.entity.SysRole;
import com.loeo.admin.mapper.SysRoleMapper;
import com.loeo.admin.service.SysPrivilegeService;
import com.loeo.admin.service.SysRoleService;
import com.loeo.base.exception.NotAllowedOperationException;
import com.loeo.base.service.BaseServiceImpl;


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

	@Override
	public boolean deleteById(Serializable id) {
		if (privilegeService.isExistByMasterAndMasterValue("role", id)) {
			throw new NotAllowedOperationException("资源已经被使用，不能删除");
		}
		return super.deleteById(id);
	}
}
