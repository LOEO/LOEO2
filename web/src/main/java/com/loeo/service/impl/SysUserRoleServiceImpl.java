package com.loeo.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.loeo.entity.SysRole;
import com.loeo.entity.SysUserRole;
import com.loeo.mapper.SysUserRoleMapper;
import com.loeo.service.SysUserRoleService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
	@Resource
	private SysUserRoleMapper sysUserRoleMapper;

	@Override
	public List<SysUserRole> findRolesByUserId(Serializable userId) {
		return selectList(new EntityWrapper<SysUserRole>().eq("userId",userId));
	}

	@Override
	public void saveUserRoles(List<SysRole> roleList, Serializable userId) {
		sysUserRoleMapper.saveUserRoles(roleList, userId);
	}
}
