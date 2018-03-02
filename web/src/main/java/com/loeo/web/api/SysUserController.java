package com.loeo.web.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.loeo.common.Result;
import com.loeo.entity.SysRole;
import com.loeo.entity.SysUser;
import com.loeo.service.SysUserService;
import com.loeo.shiro.ShiroContextUtils;
import com.loeo.utils.DateUtils;
import com.loeo.utils.EntityUtil;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
@RestController
@RequestMapping("/api/users")
public class SysUserController {
	@Resource
	private SysUserService sysUserService;

	@PostMapping
	public Result add(SysUser sysUser) {
		sysUser.setCreateDt(DateUtils.now());
		sysUser.setCreateUser(ShiroContextUtils.getCurUserId());
		sysUserService.add(sysUser);
		return Result.success("保存成功");
	}

	@PostMapping("/{userId}")
	public Result update(@PathVariable String userId, @RequestParam Map<String, Object> formData) {
		SysUser sysUser = EntityUtil.buildEntity(SysUser.class, formData);
		sysUserService.insertOrUpdate(sysUser);
		return Result.success("修改成功");
	}

	@DeleteMapping("/{userId}")
	public Result delete(@PathVariable String userId) {
		sysUserService.deleteById(Integer.parseInt(userId));
		return Result.success("删除成功");
	}

	@PostMapping("/list")
	public Result users(@RequestParam int page, @RequestParam int rows) {
		return Result.success(sysUserService.findUsersByPage(page, rows));
	}

	@PostMapping("/{userId}/menus")
	public Result getUserResources(@PathVariable String userId) {
		return Result.success(sysUserService.findUserMenus(userId));
	}

	@PostMapping("/{userId}/resources")
	public Result getResources(@PathVariable String userId) {
		return Result.success(sysUserService.findResource(userId));
	}

	@PostMapping("/{userId}/hasroles")
	public Result getRoles(@PathVariable String userId) {
		return Result.success(sysUserService.getRoles(userId));
	}

	@PostMapping("/{userId}/noroles")
	public Result getNotHasRoles(@PathVariable String userId) {
		return Result.success(sysUserService.getNotHasRoles(userId));
	}

	@PostMapping("/{userId}/roles")
	public Result saveRoles(@PathVariable String userId,String roles) {
		List<SysRole> roleList = JSON.parseArray(roles, SysRole.class);
		sysUserService.saveUserRole(roleList,userId);
		return Result.success("保存成功");
	}

}
