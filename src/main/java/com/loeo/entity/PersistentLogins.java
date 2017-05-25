package com.loeo.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
@TableName("persistent_logins")
public class PersistentLogins extends Model<PersistentLogins> {

    private static final long serialVersionUID = 1L;

	private String username;
	private String series;
	private String token;
	@TableField("last_used")
	private Date lastUsed;


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

	@Override
	protected Serializable pkVal() {
		return this.series;
	}

}
