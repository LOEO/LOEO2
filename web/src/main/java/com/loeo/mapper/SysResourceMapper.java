package com.loeo.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.loeo.entity.SysResource;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {
	List<SysResource> getAuthorisedButtonsByMenuId(@Param("menuId") Serializable menuId, @Param("userId")Serializable userId);
}