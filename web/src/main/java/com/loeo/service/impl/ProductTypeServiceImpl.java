package com.loeo.service.impl;

import org.springframework.stereotype.Service;

import com.loeo.entity.ProductType;
import com.loeo.mapper.ProductTypeMapper;
import com.loeo.service.BaseServiceImpl;
import com.loeo.service.ProductTypeService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
@Service
public class ProductTypeServiceImpl extends BaseServiceImpl<ProductTypeMapper, ProductType> implements ProductTypeService {

}
