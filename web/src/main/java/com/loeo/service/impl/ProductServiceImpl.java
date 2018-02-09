package com.loeo.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.loeo.dto.SysResourceTreeNode;
import com.loeo.entity.Product;
import com.loeo.entity.SysRole;
import com.loeo.mapper.ProductMapper;
import com.loeo.service.BaseServiceImpl;
import com.loeo.service.ProductService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductMapper, Product> implements ProductService {

	@Override
	public Page<SysRole> findRolesByPage(int pageNo, int pageSize) {
		return null;
	}

	@Override
	public List<SysResourceTreeNode> getResources(Serializable roleId) {
		return null;
	}

	@Override
	public List<SysRole> getUserHasRoles(Serializable userId) {
		return null;
	}

	@Override
	public List<SysRole> getUserNotHasRoles(Serializable userId) {
		return null;
	}
}
