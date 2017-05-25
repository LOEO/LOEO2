package com.loeo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2017-05-25 18:04:40
 * @version：2017 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
@ConfigurationProperties("loeo")
public class LoeoProperties {
	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
