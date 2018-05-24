package com.loeo.admin.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.loeo.admin.domain.dto.SysResourceTreeNode;
import com.loeo.admin.domain.entity.SysResource;
import com.loeo.admin.mapper.SysResourceMapper;
import com.loeo.admin.service.SysPrivilegeService;
import com.loeo.admin.service.SysResourceService;
import com.loeo.base.event.ResourceUpdateEvent;
import com.loeo.base.event.ResourceUpdateEvent.Action;
import com.loeo.base.exception.IsNotLeafNodeCanNotDelete;
import com.loeo.base.exception.NotAllowedOperationException;
import com.loeo.base.service.BaseServiceImpl;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
@Service
public class SysResourceServiceImpl extends BaseServiceImpl<SysResourceMapper, SysResource> implements SysResourceService, ApplicationEventPublisherAware {
	@Resource
	private SysResourceMapper resourceMapper;
	@Resource
	private SysPrivilegeService sysPrivilegeService;

	private ApplicationEventPublisher applicationEventPublisher;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean insert(SysResource entity) {
		boolean result = super.insert(entity);
		applicationEventPublisher.publishEvent(new ResourceUpdateEvent(entity, Action.ADD));
		return result;
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean add(SysResource sysResource) {
		if (!SysResourceService.ROOT_ID.equals(sysResource.getPid())) {
			SysResource parent = selectById(sysResource.getPid());
			if (parent.isLeaf()) {
				parent.setLeaf(Boolean.FALSE);
				parent.updateById();
			}
		}
		insert(sysResource);
		return false;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean deleteById(Serializable id) {
		SysResource sysResource = selectById(id);
		if (sysResource == null) {
			return true;
		}
		if (sysPrivilegeService.isExistByAccessValue(sysResource.getId())) {
			throw new NotAllowedOperationException("资源已经被使用，不能删除");
		}
		if (!sysResource.isLeaf()) {
			throw new IsNotLeafNodeCanNotDelete();
		}
		if (!SysResourceService.ROOT_ID.equals(sysResource.getPid())) {
			List<SysResource> sysResources = selectList(new EntityWrapper<SysResource>().eq("pid", sysResource.getPid()));
			if (sysResources.size() == 1) {
				SysResource resource = new SysResource();
				resource.setId(sysResource.getPid());
				resource.setLeaf(Boolean.TRUE);
				resource.updateById();
			}

		}
		return super.deleteById(id);
	}

	@Override
	public List<SysResourceTreeNode> getResourceTree() {
		List<SysResource> sysResource = selectList(null);
		return convertResourceTree(sysResource, SysResourceService.ROOT_ID);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean updateById(SysResource entity) {
		SysResource old = selectById(entity.getId());
		boolean result = super.updateById(entity);
		if (!StringUtils.isEmpty(entity.getApi()) || !StringUtils.isEmpty(entity.getType()) || !StringUtils.isEmpty(entity.getMethod())) {
			applicationEventPublisher.publishEvent(new ResourceUpdateEvent(old, Action.DELETE));
			if (StringUtils.hasText(entity.getApi())) {
				old.setApi(entity.getApi());
			}
			if (StringUtils.hasText(entity.getType())) {
				old.setType(entity.getType());
			}
			if (StringUtils.hasText(entity.getMethod())) {
				old.setMethod(entity.getMethod());
			}
			if (StringUtils.hasText(entity.getDataPermission())) {
				old.setDataPermission(entity.getDataPermission());
			}
			applicationEventPublisher.publishEvent(new ResourceUpdateEvent(old, Action.ADD));
		}
		return result;
	}

	@Override
	public Page<SysResource> findResourcesByPage(int pageNo, int pageSize) {
		return selectPage(new Page<>(pageNo, pageSize));
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
				if (!treeNode.isLeaf()) {
					treeNode.setChildren(convertResourceTree(resources, treeNode.getId()));
				}
			}
		}
		return children;
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}
}
