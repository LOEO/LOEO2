package com.loeo.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.loeo.base.Result;
import com.loeo.domain.dto.LoginDto;
import com.loeo.base.shiro.ShiroContextUtils;

/**
 * Created by LOEO on 2017/06/14 0:58
 */
@RestController
@RequestMapping("/api")
@SessionAttributes("user")
public class LoginController {
	@GetMapping
	public String page() {
		return "login";
	}

	@PostMapping("/login")
	public Result doLogin(@RequestBody LoginDto loginDto, ModelMap modelMap) {
		try {
			UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginDto.getUsername(), loginDto.getPassword());
			usernamePasswordToken.setRememberMe(loginDto.isRememberMe());
			SecurityUtils.getSubject().login(usernamePasswordToken);
			modelMap.put("user", ShiroContextUtils.getCurUser());
			return Result.success(ShiroContextUtils.getCurUser());
		} catch (UnknownAccountException | IncorrectCredentialsException uae) {
			return Result.failed("用户名或密码错误");
		} catch (LockedAccountException lae) {
			//账号锁住了，不能登入。给个提示?
			return Result.failed("用户名被锁定");
		} catch (AuthenticationException ae) {
			//未考虑到的问题 - 错误?
			return Result.failed("用户名或密码错误");
		}

	}


	@GetMapping("/users")
	public Result getUserInfo() {
		return Result.success(ShiroContextUtils.getCurUser());
	}



}
