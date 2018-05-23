package com.loeo.base.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.util.ThreadContext;

import com.loeo.admin.domain.entity.SysResource;
import com.loeo.admin.domain.entity.SysUser;

/**
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2017/07/12 20:46
 * @version ：2018 Version：1.0
 */
public abstract class ShiroContextUtils {
    private static final String CUR_SYS_RESOURCE_KEY = "CUR_SYS_RESOURCE_KEY";
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

    /**
     * 设置当前用户访问的资源对象
     * @return
     */
    public static void setCurResource(SysResource sysResource) {
        ThreadContext.put(CUR_SYS_RESOURCE_KEY, sysResource);
    }

    /**
     * 获取当前用户访问的资源对象
     * @return
     */
    public static SysResource getCurResource() {
        return (SysResource) ThreadContext.get(CUR_SYS_RESOURCE_KEY);
    }

    /**
     * 获取当前用户访问的资源对象
     * @return
     */
    public static void removeCurResource() {
        ThreadContext.remove(CUR_SYS_RESOURCE_KEY);
    }
}
