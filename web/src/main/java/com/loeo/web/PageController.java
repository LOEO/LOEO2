package com.loeo.web;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.loeo.base.shiro.ShiroContextUtils;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2017-07-17 16:01:02
 * @version：2017 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
@Controller
@SessionAttributes("user")
public class PageController {
	@GetMapping("login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String login(String username, String password, boolean rememberMe, ModelMap modelMap, HttpSession session) {
		try {
			UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
			usernamePasswordToken.setRememberMe(rememberMe);
			SecurityUtils.getSubject().login(usernamePasswordToken);
			modelMap.put("user", ShiroContextUtils.getCurUser());
			return "redirect:index";
		} catch (Exception e) {
			modelMap.put("msg", "用户名或密码错误");
			return "redirect:login";
		}
	}

	@GetMapping("/users")
	public String userManager() {
		return "system/user";
	}

	@GetMapping("/roles")
	public String roleManager() {
		return "system/role";
	}

	@GetMapping("/orgs")
	public String orgManager() {
		return "system/org";
	}

	@GetMapping("/privileges")
	public String privilegeManager() {
		return "system/privilege";
	}

	@GetMapping("/resources")
	public String resourceManager() {
		return "system/resource";
	}

}
