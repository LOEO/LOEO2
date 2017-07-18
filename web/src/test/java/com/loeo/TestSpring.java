package com.loeo;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2017-07-05 10:39:21
 * @version：2017 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
public class TestSpring {
	public static void main(String[] args) {
		XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("classpath:test.xml"));
		xmlBeanFactory.getBean("test");
	}
}
