package com.loeo.admin.service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.loeo.admin.domain.entity.SysResource;
import com.loeo.admin.domain.entity.SysUser;

/**
 * 功能：
 *
 * @author ：LT(286269159@qq.com)
 * @create ：2018-02-07 11:51:25
 * @version ：2018 Version：1.0

 */
public interface ShiroService {
	String PART_DIVIDER_TOKEN = ":";

	List<SysResource> findAllPermResources();

	Set<String> findPermByRoles(Set<String> roles);

	Set<String> findPermByUserId(Serializable userId);

	Set<String> findRolesByUserId(Serializable id);

	SysUser findUserByUserName(String username);


}
