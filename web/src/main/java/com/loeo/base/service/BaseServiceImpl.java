package com.loeo.base.service;

import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * 功能：
 *
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2018-02-09 14:47:29
 * @version ：2018 Version：1.0

 */
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class BaseServiceImpl<M extends BaseMapper<T>, T extends Model> extends ServiceImpl<M, T>  {

}
