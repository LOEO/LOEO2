package com.loeo.service;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.loeo.dto.MenuAndButton;
import com.loeo.dto.SysResourceTreeNode;
import com.loeo.entity.SysPrivilege;

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
}
