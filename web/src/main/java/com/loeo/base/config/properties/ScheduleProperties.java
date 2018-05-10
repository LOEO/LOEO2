package com.loeo.base.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 功能：
 *
 * @author ：LT(286269159@qq.com)
 * @version ：2018 Version：1.0
 * @create ：2018-05-10 10:40:10
 */
@ConfigurationProperties("app.schedule")
public class ScheduleProperties {
	private boolean enable = false;

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
}
