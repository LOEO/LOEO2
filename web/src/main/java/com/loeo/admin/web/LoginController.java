package com.loeo.admin.web;

import javax.annotation.Resource;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.loeo.admin.domain.dto.LoginDto;
import com.loeo.admin.domain.entity.SysUser;
import com.loeo.admin.service.ShiroService;
import com.loeo.base.Result;
import com.loeo.base.shiro.ShiroContextUtils;

/**
 * @author ：Tony.L(286269159@qq.com)
 * @version ：2018 Version：1.0
 * @create ：2017/06/14 0:58
 */
@RestController
@RequestMapping("/api")
@SessionAttributes("user")
public class LoginController {
	@Resource
	private ShiroService shiroService;

	@GetMapping
	public String page() {
		return "login";
	}

	@PostMapping("/login")
	public Result doLogin(@RequestBody LoginDto loginDto, ModelMap modelMap) {
		SysUser sysUser = shiroService.login(loginDto.getUsername(), loginDto.getPassword(), loginDto.isRememberMe());
		modelMap.put("user", sysUser);
		return Result.success(sysUser);
	}

	@GetMapping("/users")
	public Result getUserInfo() {
		return Result.success(ShiroContextUtils.getCurUser());
	}

}
