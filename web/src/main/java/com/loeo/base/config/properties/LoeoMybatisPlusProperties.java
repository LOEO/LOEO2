package com.loeo.base.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusProperties;

/**
 * 功能：实际没有用到，只是为了在application.properties中有提示
 *
 * @author ：LT(286269159@qq.com)
 * @version ：2018 Version：1.0
 * @create ：2018-05-03 18:00:15
 */
@ConfigurationProperties("mybatis-plus")
public class LoeoMybatisPlusProperties extends MybatisPlusProperties {
}
