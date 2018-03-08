package com.loeo.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.loeo.domain.entity.SysRole;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

	List<SysRole> getUserHasRoles(@Param("userId") Serializable userId);

	List<SysRole> getUserNotHasRoles(@Param("userId") Serializable userId);
}