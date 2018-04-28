package com.loeo.admin.web;

/**
 * 功能：
 *
 * @author ：LT(286269159@qq.com)
 * @version ：2018 Version：1.0
 * @create ：2018-04-26 17:03:28
 */

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin("*")
public class MessageController {
	@MessageMapping("/test")
	@SendTo("/topic")
	public String test(String message) {
		System.out.println(message);
		return "test message123123123";
	}

	@RequestMapping("/webSocket")
	public String webSocket() {
		return "TestWebSocket";
	}
}
