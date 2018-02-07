package com.loeo.web;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loeo.common.Result;
import com.loeo.entity.SysUser;
import com.loeo.service.SysUserService;
import com.loeo.shiro.ShiroContextUtils;
import com.loeo.utils.DateUtils;

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
    public Result add(@RequestBody SysUser sysUser) {
        sysUser.setCreateDt(DateUtils.now());
        sysUser.setCreateUser(ShiroContextUtils.getCurUserId());
        sysUserService.insert(sysUser);
		return Result.success(sysUser);
	}

	@GetMapping("/{userId}/resources")
	public Result getUserResources(@PathVariable String userId) {
		return Result.success(sysUserService.findUserResources(userId));
	}
}
