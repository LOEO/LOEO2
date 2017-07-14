package com.loeo.web;

import com.loeo.common.Result;
import com.loeo.shiro.ShiroContextUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
            return Result.buildSuccess();
        } catch (UnknownAccountException uae) {
            return Result.buildFailed("用户名或密码错误");
        } catch (IncorrectCredentialsException ice) {
            //password 不匹配，再输入?
            return Result.buildFailed("用户名或密码错误");
        } catch (LockedAccountException lae) {
            //账号锁住了，不能登入。给个提示?
            return Result.buildFailed("用户名被锁定");
        } catch (AuthenticationException ae) {
            //未考虑到的问题 - 错误?
            return Result.buildFailed("用户名或密码错误");
        }

    }

    @GetMapping("/users")
    public Result getUserInfo() {
        return Result.buildSuccess(ShiroContextUtils.getCurUser());
    }
}
