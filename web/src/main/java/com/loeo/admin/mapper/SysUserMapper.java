package com.loeo.admin.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.loeo.admin.domain.entity.SysResource;
import com.loeo.admin.domain.entity.SysUser;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
	List<SysResource> findUserMenus(Serializable userId);
}