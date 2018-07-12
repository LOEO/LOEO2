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
	private String permCachePrefix;
	private long permCacheExpire;
	/**
	 * 用户默认密码
	 */
	private String userDefaultPassword;


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

	public String getPermCachePrefix() {
		return permCachePrefix;
	}

	public void setPermCachePrefix(String permCachePrefix) {
		this.permCachePrefix = permCachePrefix;
	}

	public long getPermCacheExpire() {
		return permCacheExpire;
	}

	public void setPermCacheExpire(long permCacheExpire) {
		this.permCacheExpire = permCacheExpire;
	}
}
