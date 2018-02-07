package com.loeo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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

	@Override
	public List<SysUserRole> findRolesByUserId(Integer userId) {
		SysUserRole sysUserRole = new SysUserRole();
		sysUserRole.setUserId(userId);
		return selectList(new EntityWrapper<>(sysUserRole));
	}
}
