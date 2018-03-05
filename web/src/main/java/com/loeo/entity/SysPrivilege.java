package com.loeo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.loeo.utils.validate.group.Add;
import com.loeo.utils.validate.group.Update;


/**
 * <p>
 * <p>
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
@TableName("t_sys_privilege")
public class SysPrivilege extends Model<SysPrivilege> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id")
	@TableField(fill = FieldFill.INSERT)
	@NotNull(groups = Update.class)
	private String id;
	@NotEmpty(groups = Add.class)
	private String master;
	@NotEmpty(groups = Add.class)
	private String masterValue;
	@NotEmpty(groups = Add.class)
	private String access;
	@NotEmpty(groups = Add.class)
	private String accessValue;
	@NotEmpty(groups = Add.class)
	private String operation;
	@TableField(fill = FieldFill.INSERT)
	private String createUser;
	@TableField(fill = FieldFill.INSERT)
	private Date createDt;
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private String updateUser;
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateDt;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public String getMasterValue() {
		return masterValue;
	}

	public void setMasterValue(String masterValue) {
		this.masterValue = masterValue;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public String getAccessValue() {
		return accessValue;
	}

	public void setAccessValue(String accessValue) {
		this.accessValue = accessValue;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
