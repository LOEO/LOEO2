package com.loeo.admin.service;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.loeo.admin.domain.dto.SysResourceTreeNode;
import com.loeo.admin.domain.entity.SysResource;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
public interface SysResourceService extends IService<SysResource> {
	String ROOT_ID = "0";

	boolean add(SysResource sysResource);

	List<SysResourceTreeNode> getResourceTree();

	Page<SysResource> findResourcesByPage(int pageNo, int pageSize);

	List<SysResource> getAuthorisedButtonsByMenuId(Serializable menuId, Serializable userId);

	List<SysResourceTreeNode> convertResourceTree(List<SysResource> resources, Serializable id);
}
