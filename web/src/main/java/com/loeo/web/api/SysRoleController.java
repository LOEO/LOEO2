package com.loeo.web.api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loeo.common.Result;
import com.loeo.entity.SysRole;
import com.loeo.service.ProductService;
import com.loeo.service.SysButtonService;
import com.loeo.service.SysMenuService;
import com.loeo.service.SysOrgService;
import com.loeo.service.SysPrivilegeService;
import com.loeo.service.SysResourceService;
import com.loeo.service.SysRoleService;
import com.loeo.service.SysUserOrgService;
import com.loeo.service.SysUserRoleService;
import com.loeo.service.SysUserService;
import com.loeo.shiro.ShiroContextUtils;
import com.loeo.utils.DateUtils;
import com.loeo.utils.validate.ValidateUtils;
import com.loeo.utils.validate.group.Add;
import com.loeo.utils.validate.group.Update;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
@RestController
@RequestMapping("/api/roles")
public class SysRoleController {
	@Resource
	private SysRoleService roleService;
	@Resource
	private ProductService productService;
	@Resource
	private SysButtonService sysButtonService;
	@Resource
	private SysMenuService sysMenuService;
	@Resource
	private SysOrgService sysOrgService;
	@Resource
	private SysPrivilegeService sysPrivilegeService;
	@Resource
	private SysResourceService sysResourceService;
	@Resource
	private SysUserRoleService sysUserRoleService;
	@Resource
	private SysUserOrgService sysUserOrgService;
	@Resource
	private SysUserService sysUserService;



	@PostMapping("/list")
	public Result roles(@RequestParam int page, @RequestParam int rows) {
		return Result.success(roleService.findRolesByPage(page, rows));
	}

	@PostMapping("/{roleId}/resources")
	public Result getResources(@PathVariable String roleId) {
		return Result.success(roleService.getResources(roleId));
	}

	@PostMapping
	public Result add(SysRole sysRole) {
		ValidateUtils.validate(sysRole, Add.class);
		sysRole.setCreateDt(DateUtils.now());
		sysRole.setCreateUser(ShiroContextUtils.getCurUserId());
		roleService.insert(sysRole);
		return Result.success("保存成功");
	}

	@PostMapping("/{roleId}")
	public Result update(@PathVariable String roleId, SysRole sysRole) {
		ValidateUtils.validate(sysRole, Update.class);
		roleService.updateById(sysRole);
		return Result.success("修改成功");
	}

	@DeleteMapping("/{roleId}")
	public Result delete(@PathVariable String roleId) {
		roleService.deleteById(Integer.parseInt(roleId));
		return Result.success("删除成功");
	}
}
