package com.loeo.admin.service;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.loeo.admin.domain.entity.SysRole;
import com.loeo.admin.domain.entity.SysUserRole;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
public interface SysUserRoleService extends IService<SysUserRole> {
	List<SysUserRole> findRolesByUserId(Serializable userId);

	List<String> findUserIdByRoleId(Serializable roleId);

	void saveUserRoles(List<SysRole> roleList, Serializable userId);
}
