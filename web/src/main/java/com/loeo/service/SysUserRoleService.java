package com.loeo.service;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.loeo.entity.SysRole;
import com.loeo.entity.SysUserRole;

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

	void saveUserRoles(List<SysRole> roleList, Serializable userId);
}
