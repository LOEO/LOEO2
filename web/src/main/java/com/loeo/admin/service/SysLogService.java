package com.loeo.admin.service;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.service.IService;
import com.loeo.admin.domain.entity.SysLog;
import com.loeo.admin.domain.entity.SysResource;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LT
 * @since 2018-03-08
 */
public interface SysLogService extends IService<SysLog> {
	void log(HttpServletRequest request, SysResource currentResource, Object param);
}
