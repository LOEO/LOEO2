package com.loeo.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.loeo.domain.entity.SysPrivilege;
import com.loeo.domain.entity.SysResource;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
public interface SysPrivilegeMapper extends BaseMapper<SysPrivilege> {

	void deleteByMasterAndValue(@Param("master") String master, @Param("masterValue") Serializable masterValue);

	List<SysResource> getResources(@Param("master") String master, @Param("masterValue") Serializable masterValue);
}