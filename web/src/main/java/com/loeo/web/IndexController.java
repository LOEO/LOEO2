package com.loeo.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.loeo.shiro.ShiroContextUtils;

/**
 * Created by LOEO on 2017/06/14 21:43
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @GetMapping("index")
    public String index(HttpSession session) {
        if (session.getAttribute("user") == null) {
            session.setAttribute("user", ShiroContextUtils.getCurUser());
        }
        return "index";
    }
}
