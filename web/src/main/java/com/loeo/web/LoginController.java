package com.loeo.web;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loeo.common.Result;
import com.loeo.shiro.ShiroContextUtils;

/**
 * Created by LOEO on 2017/06/14 0:58
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class LoginController {
	@GetMapping
	public String page() {
		return "login";
	}

	@PostMapping("/doLogin")
	public Result doLogin(@RequestBody Map<String, String> params,
						  @RequestHeader("X-Custom-Header") String header,
						  HttpSession session,
						  HttpServletRequest request,
						  HttpServletResponse response) {
		try {
			System.out.println(header);
			String username = params.get("username");
			String password = params.get("password");
			System.out.println(username + ":" + password);
			SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password));
			Cookie cookie = new Cookie("JSESSIONID", session.getId());
			cookie.setDomain("localhost");
			response.addCookie(cookie);
			return Result.success();
		} catch (UnknownAccountException uae) {
			return Result.failed("用户名或密码错误");
		} catch (IncorrectCredentialsException ice) {
			//password 不匹配，再输入?
			return Result.failed("用户名或密码错误");
		} catch (LockedAccountException lae) {
			//账号锁住了，不能登入。给个提示?
			return Result.failed("用户名被锁定");
		} catch (AuthenticationException ae) {
			//未考虑到的问题 - 错误?
			return Result.failed("用户名或密码错误");
		}

	}

	@PostMapping("/login")
	public String login(String username, String password, ModelMap modelMap) {
		try {
			SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password));
			return "redirect:index";
		} catch (Exception e) {
			modelMap.put("msg", "用户名或密码错误");
			return "redirect:login";
		}
	}


	@GetMapping("/users")
	public Result getUserInfo() {
		return Result.success(ShiroContextUtils.getCurUser());
	}


}
