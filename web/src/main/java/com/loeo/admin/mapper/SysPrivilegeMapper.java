package com.loeo.admin.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.loeo.admin.domain.entity.SysPrivilege;
import com.loeo.admin.domain.entity.SysResource;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
@Mapper
public interface SysPrivilegeMapper extends BaseMapper<SysPrivilege> {

	void deleteByMasterAndValue(@Param("master") String master, @Param("masterValue") Serializable masterValue);

	List<SysResource> getResources(@Param("master") String master, @Param("masterValue") Serializable masterValue);

	boolean isExistByAccessValue(@Param("accessValue") String accessValue);

	boolean isExistByMasterAndMasterValue(@Param("master") String master, @Param("masterValue") Serializable masterValue);
}