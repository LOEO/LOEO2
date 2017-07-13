package com.loeo.shiro;

import com.loeo.entity.SysUser;
import org.apache.shiro.SecurityUtils;

/**
 * Created by LOEO on 2017/07/12 20:46
 */
public abstract class ShiroContextUtils {
    public static Integer getCurUserId() {
        return getCurUser().getId();
    }

    public static SysUser getCurUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }
}
