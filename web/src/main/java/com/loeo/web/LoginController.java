package com.loeo.web;

import org.apache.shiro.authc.*;
import org.apache.shiro.util.ThreadContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by LOEO on 2017/06/14 0:58
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    public String page() {
        return "login";
    }

    @PostMapping
    public String doLogin(String username, String password, ModelMap modelMap) {
        try {
            System.out.println(username + ":" + password);
            ThreadContext.getSubject().login(new UsernamePasswordToken(username, password));
            return "redirect:index";
        } catch (UnknownAccountException uae) {
            modelMap.put("msg", "用户名或密码错误");
            return "redirect:login";
        } catch (IncorrectCredentialsException ice) {
            //password 不匹配，再输入?
            modelMap.put("msg", "用户名或密码错误");
            return "redirect:login";
        } catch (LockedAccountException lae) {
            modelMap.put("msg", "用户名被锁定");
            //账号锁住了，不能登入。给个提示?
            return "redirect:login";
        } catch (AuthenticationException ae) {
            modelMap.put("msg", "用户名或密码错误");
            //未考虑到的问题 - 错误?
            return "redirect:login";
        }

    }
}
