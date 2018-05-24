package com.loeo.admin.service;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.loeo.admin.domain.dto.MenuAndButton;
import com.loeo.admin.domain.dto.SysResourceTreeNode;
import com.loeo.admin.domain.entity.SysPrivilege;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
public interface SysPrivilegeService extends IService<SysPrivilege> {
	void save(List<MenuAndButton> menuAndButtons, String master, String masterValue);

	List<SysResourceTreeNode> getResources(String master, Serializable masterId);

	boolean isExistByAccessValue(String accessValue);

	boolean isExistByMasterAndMasterValue(String master, Serializable masterValue);
}
