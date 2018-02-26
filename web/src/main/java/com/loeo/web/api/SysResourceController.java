package com.loeo.web.api;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loeo.common.Result;
import com.loeo.entity.SysResource;
import com.loeo.service.SysResourceService;
import com.loeo.shiro.ShiroContextUtils;
import com.loeo.utils.DateUtils;
import com.loeo.utils.EntityUtil;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
@RestController
@RequestMapping("/api/resources")
public class SysResourceController {
	@Resource
	private SysResourceService resourceService;

	@PostMapping("/list")
	public Result resources() {
		return Result.success(resourceService.getResourceTree());
	}

	@GetMapping("/{menuId}/buttons")
	public Result getChildren(@PathVariable String menuId) {
		return Result.success(resourceService.getAuthorisedButtonsByMenuId(menuId, ShiroContextUtils.getCurUserId()));
	}

	@PostMapping
	public Result add(SysResource sysResource) {
		sysResource.setCreateDt(DateUtils.now());
		sysResource.setCreateUser(ShiroContextUtils.getCurUserId());
		sysResource.setUpdateDt(sysResource.getCreateDt());
		sysResource.setUpdateUser(sysResource.getUpdateUser());
		sysResource.setIsLeaf(1);
		resourceService.insert(sysResource);
		return Result.success("保存成功");
	}

	@PostMapping("/{resourceId}")
	public Result update(@PathVariable String resourceId, @RequestParam Map<String, Object> formData) {
		SysResource sysResource = EntityUtil.buildEntity(SysResource.class, formData);
		resourceService.insertOrUpdate(sysResource);
		return Result.success("修改成功");
	}

	@DeleteMapping("/{resourceId}")
	public Result delete(@PathVariable String resourceId) {
		resourceService.deleteById(Integer.parseInt(resourceId));
		return Result.success("删除成功");
	}

}
