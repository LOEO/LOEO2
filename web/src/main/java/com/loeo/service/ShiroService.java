package com.loeo.service;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import com.loeo.entity.SysUser;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2018-02-07 11:51:25
 * @version：2018 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
public interface ShiroService {
	String PART_DIVIDER_TOKEN = ":";

	Map<String, String> initUrlPerms();

	Set<String> findPermByRoles(Set<String> roles);

	Set<String> findRolesByUserId(Serializable id);

	SysUser findUserByUserName(String username);


}
