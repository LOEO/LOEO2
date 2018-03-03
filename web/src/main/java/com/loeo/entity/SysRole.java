package com.loeo.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.loeo.utils.validate.group.Add;
import com.loeo.utils.validate.group.Update;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author LT
 * @since 2017-05-25
 */
@TableName("t_sys_role")
public class SysRole extends Model<SysRole> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id")
	@TableField(fill = FieldFill.INSERT)
	@NotNull(groups = Update.class)
	private String id;
	@NotEmpty(groups = {Add.class, Update.class})
	private String name;
	@NotEmpty(groups = {Add.class, Update.class})
	private String code;
	private String descp;
	@TableField(fill = FieldFill.INSERT)
	private String createUser;
	@TableField(fill = FieldFill.INSERT)
	private Date createDt;
	@TableField(fill = FieldFill.UPDATE)
	private String updateUser;
	@TableField(fill = FieldFill.UPDATE)
	private Date updateDt;
	private Integer enable;


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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
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

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
