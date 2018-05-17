package com.loeo.base.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;

import com.loeo.admin.domain.entity.SysUser;

/**
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2017/07/12 20:46
 * @version ：2018 Version：1.0
 */
public abstract class ShiroContextUtils {
    public static String getCurUserId() {
        return getCurUser().getId();
    }

    public static SysUser getCurUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    public static void login(AuthenticationToken authenticationToken) {
        SecurityUtils.getSubject().login(authenticationToken);
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }
}
