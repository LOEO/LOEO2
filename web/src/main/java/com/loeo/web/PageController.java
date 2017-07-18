package com.loeo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2017-07-17 16:01:02
 * @version：2017 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
@Controller
public class PageController {
	@RequestMapping("login")
	public String login() {
		return "login";
	}

}
