package com.loeo.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.loeo.common.uniquekey.IdGenerateFactory;
import com.loeo.test.TestBeanPostProcessor;

/**
 * 功能：系统配置
 *
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2017-06-14 10:05:36
 * @version ：2017 Version：1.0
 */

@Configuration
public class AppConfig {
    @Bean
    public TestBeanPostProcessor testBeanPostProcessor() {
        return new TestBeanPostProcessor();
    }

    @Bean
    public IdGenerateFactory idGererateFactory() {
        return new IdGenerateFactory(1L);
    }
}
