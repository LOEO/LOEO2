package com.loeo.admin.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.loeo.admin.domain.enums.PrivilegeMasterEnum;
import com.loeo.admin.service.SysUserRoleService;
import com.loeo.base.config.properties.AppProperties;
import org.apache.shiro.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loeo.admin.domain.dto.MenuAndButton;
import com.loeo.admin.domain.dto.SysResourceTreeNode;
import com.loeo.admin.domain.entity.SysPrivilege;
import com.loeo.admin.domain.entity.SysResource;
import com.loeo.admin.mapper.SysPrivilegeMapper;
import com.loeo.base.service.BaseServiceImpl;
import com.loeo.admin.service.SysPrivilegeService;
import com.loeo.admin.service.SysResourceService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
@Service
public class SysPrivilegeServiceImpl extends BaseServiceImpl<SysPrivilegeMapper, SysPrivilege> implements SysPrivilegeService {
	@Resource
	private SysPrivilegeMapper privilegeMapper;
	@Resource
	private SysResourceService resourceService;
	@Resource
	private CacheManager cacheManager;
	@Resource
	private AppProperties appProperties;
	@Resource
	private SysUserRoleService sysUserRoleService;


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(List<MenuAndButton> menuAndButtons, String master, String masterValue) {
		privilegeMapper.deleteByMasterAndValue(master,masterValue);
		if (menuAndButtons.size() > 0) {
			boolean result = insertBatch(convert2Privileges(menuAndButtons, master, masterValue));
			System.out.println(result);
		}
		clearPermCache(master, masterValue);
	}

	@Override
	public List<SysResourceTreeNode> getResources(String master, Serializable masterId) {
		List<SysResource> resources =  privilegeMapper.getResources(master,masterId);
		return resourceService.convertResourceTree(resources, SysResourceService.ROOT_ID);
	}

	@Override
	public boolean isExistByAccessValue(String accessValue) {
		return privilegeMapper.isExistByAccessValue(accessValue);
	}

	@Override
	public boolean isExistByMasterAndMasterValue(String master, Serializable masterValue) {
		return privilegeMapper.isExistByMasterAndMasterValue(master, masterValue);
	}

	private void clearPermCache(String master, String masterValue) {
		org.apache.shiro.cache.Cache<String, Object> cache = cacheManager.getCache(appProperties.getPermCachePrefix());
		switch (PrivilegeMasterEnum.valueOf(master.toUpperCase())) {
			case USER:
				cache.remove(masterValue);
				break;
			case ROLE:
				List<String> userIds = sysUserRoleService.findUserIdByRoleId(masterValue);
				userIds.forEach(userId -> cache.remove(masterValue));
				break;
			case ORG:
				break;
			default:
		}
	}

	private List<SysPrivilege> convert2Privileges(List<MenuAndButton> menuAndButtons, String master, String masterValue) {
		List<SysPrivilege> sysPrivileges = new ArrayList<SysPrivilege>();
		for (MenuAndButton menuAndButton : menuAndButtons) {
			sysPrivileges.add(convert2Privilege(menuAndButton, master, masterValue));
		}
		return sysPrivileges;
	}

	private SysPrivilege convert2Privilege(MenuAndButton menuAndButton, String master, String masterValue) {
		SysPrivilege sysPrivilege = new SysPrivilege();
		sysPrivilege.setMaster(master);
		sysPrivilege.setMasterValue(masterValue);
		sysPrivilege.setAccess(menuAndButton.getType());
		sysPrivilege.setAccessValue(menuAndButton.getId());
		sysPrivilege.setOperation("enable");
		return sysPrivilege;
	}
}
