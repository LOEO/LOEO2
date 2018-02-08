package com.loeo.service;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.loeo.dto.SysResourceTreeNode;
import com.loeo.entity.SysResource;
import com.loeo.entity.SysRole;
import com.loeo.entity.SysUser;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
public interface SysUserService extends IService<SysUser> {
    SysUser findByUserName(String username);

	List<SysRole> findRolesById(Serializable id);

	List<SysResource> findUserMenus(Serializable userId);

	Page<SysUser> findUsersByPage(int pageNo, int pageSize);

	List<SysResourceTreeNode> findResource(Serializable userId);

	List<SysRole> getRoles(Serializable userId);

	List<SysRole> getNotHasRoles(Serializable userId);

	void saveUserRole(List<SysRole> roleList, String userId);
}
