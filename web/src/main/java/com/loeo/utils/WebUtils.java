package com.loeo.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

/**
 * 功能：
 *
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2018-02-25 16:43:21
 * @version ：2018 Version：1.0

 */
public class WebUtils {
	public static final String AJAX_REQUEST_HEADER = "X-Requested-With";
	public static final String AJAX_REQUEST_VALUE = "XMLHttpRequest";

	public static boolean isAjax(HttpServletRequest request) {
		String ajaxHeader = request.getHeader(AJAX_REQUEST_HEADER);
		return !StringUtils.isEmpty(ajaxHeader) && ajaxHeader.contains(AJAX_REQUEST_VALUE);
	}
}
