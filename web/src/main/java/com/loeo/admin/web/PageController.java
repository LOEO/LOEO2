package com.loeo.admin.web;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.loeo.base.shiro.ShiroContextUtils;

/**
 * 功能：
 *
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2017-07-17 16:01:02
 * @version ：2017 Version：1.0

 */
@Controller
public class PageController {
	@GetMapping("login")
	public String login() {
		return "login";
	}

	@GetMapping(value = "/",produces = "text/html")
	public String main() {
		return "redirect:index";
	}

	@GetMapping("index")
	public String index(HttpSession session) {
		if (session.getAttribute("user") == null) {
			session.setAttribute("user", ShiroContextUtils.getCurUser());
		}
		return "index";
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

	@GetMapping("/accessDenied")
	public String accessDenied() {
		return "accessDenied";
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
