package com.loeo.admin.web.api;

import java.util.List;

import javax.annotation.Resource;

import com.loeo.base.config.properties.AppProperties;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.loeo.admin.domain.entity.SysRole;
import com.loeo.admin.domain.entity.SysUser;
import com.loeo.admin.service.SysUserService;
import com.loeo.base.Result;
import com.loeo.base.shiro.ShiroContextUtils;
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
@RequestMapping("/api/users")
public class SysUserController {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private AppProperties appProperties;

    @GetMapping("/currentUser")
    public Result currentUser() {
        return Result.success(ShiroContextUtils.getCurUser());
    }

    @PostMapping("/add")
    public Result add1(SysUser sysUser) {
        ValidateUtils.validate(sysUser, Add.class);
        sysUserService.add(sysUser);
        return Result.success("保存成功");
    }

    @PostMapping
    public Result add(@RequestBody SysUser sysUser) {
        sysUser.setPassword(appProperties.getUserDefaultPassword());
        ValidateUtils.validate(sysUser, Add.class);
        sysUserService.add(sysUser);
        return Result.success("保存成功");
    }

    @PostMapping("/{userId}")
    public Result update(@PathVariable String userId, SysUser sysUser) {
        ValidateUtils.validate(sysUser, Update.class);
        sysUserService.updateById(sysUser);
        return Result.success("修改成功");
    }

    @DeleteMapping("/{userId}")
    public Result delete(@PathVariable String userId) {
        sysUserService.deleteById(userId);
        return Result.success("删除成功");
    }

    @PostMapping("/list")
    public Result users(@RequestParam int page, @RequestParam int rows) {
        return Result.success(sysUserService.findUsersByPage(page, rows));
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "2") int pageSize) {
        return Result.success(sysUserService.findUsersByPage(currentPage, pageSize));
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
    public Result saveRoles(@PathVariable String userId, String roles) {
        List<SysRole> roleList = JSON.parseArray(roles, SysRole.class);
        sysUserService.saveUserRole(roleList, userId);
        return Result.success("保存成功");
    }

}
