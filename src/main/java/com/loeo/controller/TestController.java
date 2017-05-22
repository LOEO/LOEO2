package com.loeo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2017-05-22 17:51:15
 * @version：2017 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping("/")
public class TestController {
	@GetMapping("{name}")
	public String test(@PathVariable String name) {
		return "Hello：" + name;
	}
}
