package com.loeo.mapper;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.loeo.domain.entity.SysResource;
import com.loeo.domain.entity.SysUser;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
	List<SysResource> findUserMenus(Serializable userId);
}