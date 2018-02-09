package com.loeo.service;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.loeo.dto.SysResourceTreeNode;
import com.loeo.entity.Product;
import com.loeo.entity.SysRole;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
public interface ProductService extends IService<Product> {
	Page<SysRole> findRolesByPage(int pageNo, int pageSize);

	List<SysResourceTreeNode> getResources(Serializable roleId);

	List<SysRole> getUserHasRoles(Serializable userId);

	List<SysRole> getUserNotHasRoles(Serializable userId);
}
