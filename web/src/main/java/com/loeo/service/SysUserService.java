package com.loeo.service;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.service.IService;
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

	List<SysRole> findRolesById(Integer id);

	List<SysResource> findUserResources(Serializable userId);
}
