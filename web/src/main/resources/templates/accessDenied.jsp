<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.loeo.bean.AjaxResult" %>
<%@ page import="com.loeo.enums.AjaxState" %><%--
  Created by IntelliJ IDEA.
  User: LT
  Date: 2016/11/15
  Time: 0:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	response.setStatus(HttpServletResponse.SC_OK);
	String requestType = request.getHeader("X-Requested-With");
	if ("XMLHttpRequest".equals(requestType)) {
		out.println(JSON.toJSONString(new AjaxResult(AjaxState.FAILED, "没有权限")));
	} else {
		out.print("没有权限");
	}
%>
