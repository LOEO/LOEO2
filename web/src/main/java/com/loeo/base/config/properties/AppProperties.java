package com.loeo.base.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 功能：
 *
 * @author ：LT(286269159@qq.com)
 * @version ：2018 Version：1.0
 * @create ：2018-05-11 10:06:38
 */
@ConfigurationProperties("app")
@Component
public class AppProperties {
	private String adminId;
	private String defaultScheduleId;
	private String loginApi;
	/**
	 * 用户默认密码
	 */
	private String userDefaultPassword;
	/**
	 * 洗消流程中：上一步没有达到最小时长要求，是否允许下一步
	 */
	private boolean allowTheNextStep;

	public String getDefaultScheduleId() {
		return defaultScheduleId;
	}

	public void setDefaultScheduleId(String defaultScheduleId) {
		this.defaultScheduleId = defaultScheduleId;
	}

	public String getUserDefaultPassword() {
		return userDefaultPassword;
	}

	public void setUserDefaultPassword(String userDefaultPassword) {
		this.userDefaultPassword = userDefaultPassword;
	}
	public boolean isAllowTheNextStep() {
		return allowTheNextStep;
	}

	public void setAllowTheNextStep(boolean allowTheNextStep) {
		this.allowTheNextStep = allowTheNextStep;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getLoginApi() {
		return loginApi;
	}

	public void setLoginApi(String loginApi) {
		this.loginApi = loginApi;
	}
}
