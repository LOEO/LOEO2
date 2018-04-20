package com.loeo.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 功能：
 *
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2018-02-09 16:50:45
 * @version ：2018 Version：1.0
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	public static <T> T getBean(Class<T> cls) {
		System.out.println(ApplicationContextUtils.applicationContext);
		return ApplicationContextUtils.applicationContext.getBean(cls);
	}

	public static Object getBean(String beanName) {
		return ApplicationContextUtils.applicationContext.getBean(beanName);
	}

	public static ApplicationContext getApplicationContext() {
		return ApplicationContextUtils.applicationContext;
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextUtils.applicationContext = applicationContext;
	}
}
