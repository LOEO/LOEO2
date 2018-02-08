package com.loeo.web.api;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.loeo.common.Result;
import com.loeo.dto.MenuAndButton;
import com.loeo.service.SysPrivilegeService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
@RestController
@RequestMapping("/api/privileges")
public class SysPrivilegeController {
	@Resource
	private SysPrivilegeService privilegeService;

	@PostMapping
	public Result savePrivilege(String nodes,String master,String masterValue) {
		List<MenuAndButton> menuAndButtons = JSON.parseArray(nodes,MenuAndButton.class);
		privilegeService.save(menuAndButtons,master,masterValue);
		return Result.success();
	}

}
