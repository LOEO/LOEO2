package com.loeo.shiro;

import org.apache.shiro.SecurityUtils;

import com.loeo.entity.SysUser;

/**
 * Created by LOEO on 2017/07/12 20:46
 */
public abstract class ShiroContextUtils {
    public static String getCurUserId() {
        return getCurUser().getId();
    }

    public static SysUser getCurUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }
}
