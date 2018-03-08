package com.loeo.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;

/**
 * <p>
 * 
 * </p>
 *
 * @author LT
 * @since 2018-03-08
 */
@TableName("t_sys_log")
public class SysLog extends Model<SysLog> {

    private static final long serialVersionUID = 1L;
	@TableId(value = "id")
	@TableField(fill = FieldFill.INSERT)
	private String id;
	private String url;
	private String method;
	private String action;
	private String params;
	@TableField(fill = FieldFill.INSERT)
	private String createUser;
	@TableField(fill = FieldFill.INSERT)
	private Date created;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SysLog{" +
			"id=" + id +
			", action=" + action +
			", params=" + params +
			", createUser=" + createUser +
			", created=" + created +
			"}";
	}
}
