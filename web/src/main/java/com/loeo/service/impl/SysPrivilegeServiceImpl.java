package com.loeo.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loeo.dto.MenuAndButton;
import com.loeo.dto.SysResourceTreeNode;
import com.loeo.entity.SysPrivilege;
import com.loeo.entity.SysResource;
import com.loeo.mapper.SysPrivilegeMapper;
import com.loeo.service.BaseServiceImpl;
import com.loeo.service.SysPrivilegeService;
import com.loeo.service.SysResourceService;
import com.loeo.shiro.ShiroContextUtils;

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


	@Override
	@Transactional
	public void save(List<MenuAndButton> menuAndButtons, String master, String masterValue) {
		privilegeMapper.deleteByMasterAndValue(master,masterValue);
		if (menuAndButtons.size() > 0) {
			boolean result = insertBatch(convert2Privileges(menuAndButtons, master, masterValue));
			System.out.println(result);
		}
		// TODO: 2018/2/8 重新设置shiro权限
	}

	@Override
	public List<SysResourceTreeNode> getResources(String master, Serializable masterId) {
		List<SysResource> resources =  privilegeMapper.getResources(master,masterId);
		return resourceService.convertResourceTree(resources, 0);
	}

	public List<SysPrivilege> convert2Privileges(List<MenuAndButton> menuAndButtons,String master,String masterValue) {
		List<SysPrivilege> sysPrivileges = new ArrayList<SysPrivilege>();
		for(int i=0;i<menuAndButtons.size();i++) {
			sysPrivileges.add(convert2Privilege(menuAndButtons.get(i),master,masterValue));
		}
		return sysPrivileges;
	}

	public SysPrivilege convert2Privilege(MenuAndButton menuAndButton,String master,String masterValue) {
		SysPrivilege sysPrivilege = new SysPrivilege();
		sysPrivilege.setMaster(master);
		sysPrivilege.setMasterValue(masterValue);
		sysPrivilege.setAccess(menuAndButton.getType());
		sysPrivilege.setAccessValue(menuAndButton.getId().toString());
		sysPrivilege.setOperation("enable");
		sysPrivilege.setCreateUser(ShiroContextUtils.getCurUserId());
		sysPrivilege.setUpdateUser(ShiroContextUtils.getCurUserId());
		sysPrivilege.setCreateDt(new Date());
		sysPrivilege.setUpdateDt(new Date());
		return sysPrivilege;
	}
}
