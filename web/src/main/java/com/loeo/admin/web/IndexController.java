package com.loeo.admin.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.loeo.base.shiro.ShiroContextUtils;

/**
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2017/06/14 21:43
 * @version ：2018 Version：1.0
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping(produces = "text/html")
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
}
