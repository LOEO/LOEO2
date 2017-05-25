package com.loeo.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.loeo.mapper.ProductTypeMapper;
import com.loeo.entity.ProductType;
import com.loeo.service.ProductTypeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements ProductTypeService {
	
}
