package com.loeo.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.loeo.mapper.ProductMapper;
import com.loeo.entity.Product;
import com.loeo.service.ProductService;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
	
}
