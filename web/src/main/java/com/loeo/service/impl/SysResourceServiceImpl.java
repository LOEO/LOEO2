package com.loeo.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.util.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.loeo.dto.SysResourceTreeNode;
import com.loeo.entity.SysResource;
import com.loeo.event.ResourceUpdateEvent;
import com.loeo.event.ResourceUpdateEvent.Action;
import com.loeo.mapper.SysResourceMapper;
import com.loeo.service.BaseServiceImpl;
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
public class SysResourceServiceImpl extends BaseServiceImpl<SysResourceMapper, SysResource> implements SysResourceService,ApplicationEventPublisherAware {
	@Resource
	private SysResourceMapper resourceMapper;
	private ApplicationEventPublisher applicationEventPublisher;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean insert(SysResource entity) {
		boolean result = super.insert(entity);
		applicationEventPublisher.publishEvent(new ResourceUpdateEvent(entity, Action.ADD));
		return result;
	}



	@Override
	public List<SysResourceTreeNode> getResourceTree() {
		List<SysResource> sysResource = selectList(null);
		return convertResourceTree(sysResource, SysResourceService.ROOT_ID);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean insertOrUpdate(SysResource entity) {
		SysResource old = selectById(entity.getId());
		boolean result = super.insertOrUpdate(entity);
		if(StringUtils.hasText(entity.getApi()) || StringUtils.hasText(entity.getType()) || StringUtils.hasText(entity.getMethod())) {
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
			applicationEventPublisher.publishEvent(new ResourceUpdateEvent(old, Action.ADD));
		}
		return result;
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

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}
}
