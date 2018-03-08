package com.loeo.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.loeo.domain.entity.SysRole;
import com.loeo.domain.entity.SysUserRole;
import com.loeo.mapper.SysUserRoleMapper;
import com.loeo.service.BaseServiceImpl;
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
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
	@Resource
	private SysUserRoleMapper sysUserRoleMapper;

	@Override
	public List<SysUserRole> findRolesByUserId(Serializable userId) {
		return selectList(new EntityWrapper<SysUserRole>().eq("user_id",userId));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void saveUserRoles(List<SysRole> roleList, Serializable userId) {
		if (CollectionUtils.isEmpty(roleList)) {
			sysUserRoleMapper.delete(new EntityWrapper<SysUserRole>().eq("user_id", userId));
		}else{
			sysUserRoleMapper.saveUserRoles(roleList, userId);
		}
	}
}
