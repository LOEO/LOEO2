package com.loeo.base.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.loeo.base.Result;
import org.apache.shiro.web.filter.authc.UserFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author ：Tony.L
 * @version ：2018 Version：1.0
 * @create ：2018/07/20 19:29
 */
public class SysUserFilter extends UserFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(600);
        httpServletResponse.setCharacterEncoding("utf8");
        httpServletResponse.setContentType("application/json; charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSON.toJSONString(Result.failed("请先登录")));
        printWriter.flush();
        return false;
    }
}
