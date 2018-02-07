package com.loeo.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.loeo.entity.SysResource;
import com.loeo.entity.SysRole;
import com.loeo.entity.SysUser;
import com.loeo.entity.SysUserRole;
import com.loeo.exception.DuplicateUsernameException;
import com.loeo.mapper.SysUserMapper;
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
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
	@Resource
	private SysUserMapper sysUserMapper;
	@Resource
	private SysUserRoleService sysUserRoleService;
	@Resource
	private SysRoleService sysRoleService;

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
	public List<SysRole> findRolesById(Integer id) {
		List<SysUserRole> sysUserRoles = sysUserRoleService.findRolesByUserId(id);
		return sysRoleService.selectBatchIds(sysUserRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList()));
	}

	@Override
	public List<SysResource> findUserResources(Serializable userId) {
		return sysUserMapper.findUserResources(userId);
	}
}
