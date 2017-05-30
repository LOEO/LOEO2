package com.loeo.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.loeo.entity.TestMybatis;
import com.loeo.mapper.TestMybatisMapper;
import com.loeo.service.TestMybatisService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
@Service
public class TestMybatisServiceImpl extends ServiceImpl<TestMybatisMapper, TestMybatis> implements TestMybatisService {

}