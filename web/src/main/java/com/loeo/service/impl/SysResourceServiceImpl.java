package com.loeo.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.loeo.dto.SysResourceTreeNode;
import com.loeo.entity.SysResource;
import com.loeo.mapper.SysResourceMapper;
import com.loeo.service.SysResourceService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements SysResourceService {
	@Resource
	private SysResourceMapper resourceMapper;

	@Override
	public List<SysResourceTreeNode> getResourceTree() {
		List<SysResource> sysResource = selectList(null);
		return convertResourceTree(sysResource, 0);
	}

	@Override
	public Page<SysResource> findResourcesByPage(int pageNo, int pageSize) {
		return selectPage(new Page<>(pageNo,pageSize));
	}

	@Override
	public List<SysResource> getAuthorisedButtonsByMenuId(Serializable menuId, Serializable userId) {
		return resourceMapper.getAuthorisedButtonsByMenuId(menuId, userId);
	}

	@Override
	public List<SysResourceTreeNode> convertResourceTree(List<SysResource> resources, Serializable id) {
		List<SysResourceTreeNode> children = new ArrayList<>();
		for (SysResource resource : resources) {
			if (resource.getPid().equals(id)) {
				SysResourceTreeNode treeNode = new SysResourceTreeNode(resource);
				children.add(treeNode);
				if (treeNode.getIsLeaf() != 1) {
					treeNode.setChildren(convertResourceTree(resources, treeNode.getId()));
				}
			}
		}
		return children;
	}
}
