package com.loeo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.loeo.entity.SysUserOrg;
import com.loeo.mapper.SysUserOrgMapper;
import com.loeo.service.SysUserOrgService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserOrgServiceImpl extends ServiceImpl<SysUserOrgMapper, SysUserOrg> implements SysUserOrgService {

}
