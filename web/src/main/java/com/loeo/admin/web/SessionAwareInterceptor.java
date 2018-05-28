package com.loeo.admin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.loeo.base.shiro.ShiroContextUtils;


/**
 * 功能：
 *
 * @author ：Tony.L(286269159@qq.com)
 * @version ：2018 Version：1.0
 * @create ：2018-05-28 16:01:38
 */
@Component
public class SessionAwareInterceptor extends HandlerInterceptorAdapter {
	private static final String USER_SESSION_KEY = "user";
	/**
	 * 把当前用户存入session中
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute(USER_SESSION_KEY) == null && ShiroContextUtils.getCurUser() != null) {
			session.setAttribute(USER_SESSION_KEY, ShiroContextUtils.getCurUser());
		}
		return super.preHandle(request, response, handler);
	}
}
