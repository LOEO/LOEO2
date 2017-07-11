package com.loeo.web;

import org.apache.shiro.authc.*;
import org.apache.shiro.util.ThreadContext;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PostMapping("/login")
    public String doLogin(@RequestBody Map<String, String> params, @RequestHeader("X-Custom-Header") String header) {
        try {
            System.out.println(header);
            String username = params.get("username");
            String password = params.get("password");
            System.out.println(username + ":" + password);
            ThreadContext.getSubject().login(new UsernamePasswordToken(username, password));
            return "登陆成功";
        } catch (UnknownAccountException uae) {
            return "用户名或密码错误";
        } catch (IncorrectCredentialsException ice) {
            //password 不匹配，再输入?
            return "用户名或密码错误";
        } catch (LockedAccountException lae) {
            //账号锁住了，不能登入。给个提示?
            return "用户名被锁定";
        } catch (AuthenticationException ae) {
            //未考虑到的问题 - 错误?
            return "用户名或密码错误";
        }

    }
}
